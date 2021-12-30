package com.yd.scala.aviator;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;
import com.yd.scala.aviator.fuction.Exists;
import com.yd.scala.aviator.fuction.backport.SeqNewListFunction;
import com.yd.scala.aviator.fuction.backport.SeqNewSetFunction;
import com.yd.scala.aviator.fuction.bit.BitNotSetted;
import com.yd.scala.aviator.fuction.bit.BitSetted;
import com.yd.scala.aviator.fuction.codec.DecodeBase64;
import com.yd.scala.aviator.fuction.collection.InRange;
import com.yd.scala.aviator.fuction.extract.*;
import com.yd.scala.aviator.fuction.geo.CondCalculate;
import com.yd.scala.aviator.fuction.geo.DynamicGpsResolve;
import com.yd.scala.aviator.fuction.geo.GeoDistance;
import com.yd.scala.aviator.fuction.geo.LocatorGeoDistance;
import com.yd.scala.aviator.fuction.json.Extract;
import com.yd.scala.aviator.fuction.json.HasField;
import com.yd.scala.aviator.fuction.num.Between;
import com.yd.scala.aviator.fuction.str.Contains;
import com.yd.scala.aviator.fuction.str.Length;
import com.yd.scala.aviator.fuction.str.StartsWith;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author created by Zeb灬D on 2021-07-22 16:54
 */
@Slf4j
public class AviatorScript {
    public static final Map<String, Expression> exprCache = new ConcurrentHashMap<>();

    public static final Expression buildExpr(String expr) {
        return exprCache.computeIfAbsent(expr, (key) -> AviatorEvaluator.compile(expr));
    }

    /**
     * 计算表达式
     *
     * @param expr    表达式
     * @param context 上下文
     * @return 表达式是否满足
     */
    public static boolean eval(String expr, Map<String, Object> context) throws Exception {
        try {
            if (StringUtils.isBlank(expr)) {
                return false;
            }
            Expression exprObj = buildExpr(expr);

            Object result = exprObj.execute(context);
            if (result instanceof Boolean) {
                return (Boolean) result;
            }
            return false;
        } catch (Exception e) {
            log.warn("计算表达式错误, expr:{},context:{}, msg:{}", expr, context, e.getMessage(), e);
            throw new Exception("express[" + expr + "] execute error.", e);
        }
    }

    /**
     * 计算表达式
     *
     * @param expr    表达式
     * @param context 上下文
     * @return 表达式计算结果值
     */
    public static Object execute(String expr, Map<String, Object> context) throws Exception {
        try {
            if (StringUtils.isBlank(expr)) {
                return null;
            }
            Expression exprObj = buildExpr(expr);
            Object result = exprObj.execute(context);
            return result;
        } catch (Exception e) {
            log.warn("计算表达式错误, expr:{},context:{}", expr, context, e);
            throw new Exception("express[" + expr + "] execute error.", e);
        }
    }


    public static String buildEvalExprFromString(String expr) {
        if (StringUtils.isBlank(expr)) {
            return "";
        }
        try {
            Object json = JSON.parse(expr);
            StringBuilder groovyExpr = new StringBuilder();
            boolean first = true;
            if (json instanceof JSONArray) {
                JSONArray jsonArray = (JSONArray) json;
                for (int i = 0; i < jsonArray.size(); i++) {
                    String exprPart = buildEvalExprPart(jsonArray.get(i));
                    if (StringUtils.isBlank(exprPart)) {
                        continue;
                    }

                    if (first) {
                        first = false;
                    } else {
                        //如果是表达式数组,默认用"&&"连接,表示全部满足
                        groovyExpr.append("&& ");
                    }
                    groovyExpr.append(exprPart);
                }
                expr = groovyExpr.toString();
            } else {
                expr = buildEvalExprPart(json);
            }
        } catch (JSONException e) {
            log.warn("json构建表达式出错,原始表达式为{}", expr);
        }
        return expr.trim();
    }


    private static final List<String> commonOpList = Lists.newArrayList(">", ">=", "==", "<", "<=", "!=");

    public static boolean isCustomFunc(Object op) {
        return op instanceof String && (!commonOpList.contains(op));
    }

    public static boolean isBuiltinOperate(Object op) {
        return op instanceof String && commonOpList.contains(op);
    }


    private static String buildEvalExprPart(Object obj) {
        StringBuilder exprPart = new StringBuilder();
        if (obj instanceof JSONArray) {
            JSONArray jsonArray = (JSONArray) obj;
            if (jsonArray.size() >= 2 && jsonArray.get(1) == null) {
                return "";
            }
            //函数调用
            if (jsonArray.size() >= 2 && isCustomFunc(jsonArray.get(1))) {
                exprPart.append(jsonArray.get(1)).append("(");
                exprPart.append(buildEvalExprPart(jsonArray.get(0)));
                for (int i = 2; i < jsonArray.size(); i++) {
                    exprPart.append(",");
                    exprPart.append(buildEvalExprPart(jsonArray.get(i)));
                }
                exprPart.append(") ");
            } else {
                for (int i = 0; i < jsonArray.size(); i++) {
                    //嵌套的数组表达式
                    if (jsonArray.get(i) instanceof JSONArray && i > 0) {
                        exprPart.append(" && ");
                    }
                    //第2个位置的总认为是operator
                    if (i == 1 && isBuiltinOperate(jsonArray.get(i))) {
                        exprPart.append(jsonArray.get(i)).append(" ");
                    } else {
                        exprPart.append(buildEvalExprPart(jsonArray.get(i))).append(" ");
                    }
                }
            }
            //目前只应用于 {"||" [] [] [] } 这种形式,函数调用和比较操作都是用JSONArray
        } else if (obj instanceof JSONObject) {
            JSONObject jsonObj = (JSONObject) obj;
            String operation = jsonObj.keySet().iterator().next();
            JSONArray jsonArray = jsonObj.getJSONArray(operation);

            List<String> exprs = new ArrayList<>();
            for (int i = 0; i < jsonArray.size(); i++) {
                String expr = buildEvalExprPart(jsonArray.get(i));
                if (StringUtils.isNotBlank(expr)) {
                    exprs.add(expr);
                }
            }

            if (exprs.size() == 1) {
                exprPart.append(exprs.get(0));
            } else {
                for (int i = 0; i < exprs.size(); i++) {
                    if (i > 0) {
                        exprPart.append(operation);
                    }
                    exprPart.append("(").append(exprs.get(i)).append(")");
                }
            }
        } else if (obj instanceof String) {
            String tmp = (String) obj;
            //后面两种是json-path写法，不能替换为变量名
            if (tmp.startsWith("$") && !tmp.startsWith("$.") && !tmp.startsWith("$[")) {
                exprPart.append(tmp.substring(1));
            } else {
                exprPart.append("\"" + tmp + "\"");
            }
        } else {
            if (obj != null) {
                exprPart.append(obj.toString());
            } else {
                log.warn("buildEvalExprPartNPE");
                exprPart.append("null");
            }
        }

        return exprPart.toString();
    }

    public static void main(String[] args) throws Exception {
        String expr = null;
        Map<String, Object> context = null;

        expr = "c1 && c2";
        context = ImmutableMap.of("c1", (Object) true, "c2", false);
        boolean result = AviatorScript.eval(expr, context);
        System.out.println(result);

        expr = "c1 && c2";
        context = ImmutableMap.of("c1", (Object) true, "c2", true);
        result = AviatorScript.eval(expr, context);
        System.out.println(result);


        expr = "(c1) && (c2)";
        context = ImmutableMap.of("c1", (Object) true, "c2", true);
        result = AviatorScript.eval(expr, context);
        System.out.println(result);

        expr = "c1 > c2 && c1 < c3";
        context = ImmutableMap.of("c1", (Object) 20, "c2", 10, "c3", 30);
        result = AviatorScript.eval(expr, context);
        System.out.println(result);


        expr = "c1 > c2 && c1 < c3";
        context = ImmutableMap.of("c1", (Object) 20, "c2", 10, "c3", 15);
        result = AviatorScript.eval(expr, context);
        System.out.println(result);

        expr = "c1 > c2 && (c1 < c3 || c5) ";
        context = ImmutableMap.of("c1", (Object) 20, "c2", 10, "c3", 15, "c5", true);
        result = AviatorScript.eval(expr, context);
        System.out.println(result);


        //expr = "distance(c1,'120.125298,30.221107') == 0";
        //context = ImmutableMap.of("c1", (Object)"90.125298,10.221107");
        //result = AviatorScript.eval(expr,context);
        //System.out.println(result);

//        LinkageRuleVO ruleVO = new LinkageRuleVO();
//        ruleVO.setAttribute(2L);
//        expr = "bitEq(ruleVO.attribute, 1)";
//        context = ImmutableMap.of("ruleVO", ruleVO);
//        result = AviatorScript.eval(expr,context);
//        System.out.println(result);


        expr = "bitEq(c1,11)";
        context = ImmutableMap.of("c1", (Object) 2048);
        result = AviatorScript.eval(expr, context);
        System.out.println(result);

        expr = "bitNotEq(c1,0)";
        context = ImmutableMap.of("c1", (Object) 2);
        result = AviatorScript.eval(expr, context);
        System.out.println(result);

        expr = "bitNotEq(c1,0)";
        context = ImmutableMap.of("c1", (Object) 3);
        result = AviatorScript.eval(expr, context);
        System.out.println(result);

        expr = "exists(c1)";
        context = ImmutableMap.of("c1", (Object) 3);
        result = AviatorScript.eval(expr, context);
        System.out.println(result);

        expr = "exists(c1)";
        context = ImmutableMap.of("c1", (Object) "whatever");
        result = AviatorScript.eval(expr, context);
        System.out.println(result);

        expr = "exists(c1)";
        context = ImmutableMap.of("c1", (Object) (new byte[5]));
        result = AviatorScript.eval(expr, context);
        System.out.println(result);

        expr = "exists(c1)";
        context = ImmutableMap.of("c2", (Object) 3);
        result = AviatorScript.eval(expr, context);
        System.out.println(result);


        expr = "length(dp1)  > 3";
        context = ImmutableMap.of("dp1", (Object) "aa");
        result = AviatorScript.eval(expr, context);
        System.out.println(result);


        expr = "length(dp1)  > 3";
        context = ImmutableMap.of("dp1", (Object) "aasd");
        result = AviatorScript.eval(expr, context);
        System.out.println(result);

        //base串的内容为 {"username":"13687881211","email":"wsn@tuya.com"}
        expr = "extract(decodeBase64(dp1), \"$.username\") == \"13687881211\" ";
        context = ImmutableMap.of("dp1", (Object) "eyJ1c2VybmFtZSI6IjEzNjg3ODgxMjExIiwiZW1haWwiOiJ3c25AdHV5YS5jb20ifQ==");
        result = AviatorScript.eval(expr, context);
        System.out.println(result);

        //base串的内容为 ["v1","v2",{"username":"13687881211","email":"wsn@tuya.com"}]
        expr = "extract(decodeBase64(dp1), \"$[2].email\") == \"wsn@tuya.com\" ";
        context = ImmutableMap.of("dp1", (Object) "WyJ2MSIsInYyIix7InVzZXJuYW1lIjoiMTM2ODc4ODEyMTEiLCJlbWFpbCI6IndzbkB0dXlhLmNvbSJ9XQ==");
        result = AviatorScript.eval(expr, context);
        System.out.println(result);

        //base串的内容为 ["v1","v2",{"username":"13687881211","email":"wsn@tuya.com"}]
        expr = "extract(decodeBase64(dp1), \"$[2].email\") == \"wsn1@tuya.com\" ";
        context = ImmutableMap.of("dp1", (Object) "WyJ2MSIsInYyIix7InVzZXJuYW1lIjoiMTM2ODc4ODEyMTEiLCJlbWFpbCI6IndzbkB0dXlhLmNvbSJ9XQ==");
        result = AviatorScript.eval(expr, context);
        System.out.println(result);

        //base串的内容为 ["v1","v2",{"username":"13687881211","email":"wsn@tuya.com"}]
        expr = "hasField(decodeBase64(dp1), \"$[2].email\")";
        context = ImmutableMap.of("dp1", (Object) "WyJ2MSIsInYyIix7InVzZXJuYW1lIjoiMTM2ODc4ODEyMTEiLCJlbWFpbCI6IndzbkB0dXlhLmNvbSJ9XQ==");
        result = AviatorScript.eval(expr, context);
        System.out.println(result);

        //base串的内容为 {"age":20,"email":"wsn@tuya.com"}
        expr = "extract(decodeBase64(dp1), \"$.age\") == 20 ";
        context = ImmutableMap.of("dp1", (Object) "eyJhZ2UiOjIwLCJlbWFpbCI6IndzbkB0dXlhLmNvbSJ9");
        result = AviatorScript.eval(expr, context);
        System.out.println(result);


        expr = "inRange(c1,c2)";
        context = ImmutableMap.of("c1", 3, "c2", "3,4,5");
        result = AviatorScript.eval(expr, context);
        System.out.println(result);

        expr = "inRange(c1,c2)";
        context = ImmutableMap.of("c1", "3", "c2", "3,4,5");
        result = AviatorScript.eval(expr, context);
        System.out.println(result);

        //expr = "inRange(c1,10, 15)";
        //context = ImmutableMap.of("c1", (Object)12);
        //result = AviatorScript.eval(expr,context);
        //System.out.println(result);

        //base串的内容为 {"age":20,"email":"wsn@tuya.com"}
        expr = "extract(decodeBase64(dp1), \"$.age\") > 10 ";
        context = ImmutableMap.of("dp1", (Object) "eyJhZ2UiOjIwLCJlbWFpbCI6IndzbkB0dXlhLmNvbSJ9");
        result = AviatorScript.eval(expr, context);
        System.out.println(result);

        //base串的内容为 {"age":20,"email":"wsn@tuya.com"}
        expr = "include(seq.list(10,30, 20,40), extract(decodeBase64(dp1), \"$.age\")) ";
        context = ImmutableMap.of("dp1", (Object) "eyJhZ2UiOjIwLCJlbWFpbCI6IndzbkB0dXlhLmNvbSJ9");
        result = AviatorScript.eval(expr, context);
        System.out.println(result);

        expr = "between(c1,10, 15)";
        context = ImmutableMap.of("c1", (Object) 12);
        result = AviatorScript.eval(expr, context);
        System.out.println(result);

        expr = "between(c1,10, 15)";
        context = ImmutableMap.of("c1", (Object) 20);
        result = AviatorScript.eval(expr, context);
        System.out.println(result);

        LinkageRuleVO ruleVO = new LinkageRuleVO();
        ruleVO.setAttribute(4L);
        expr = "bitEq(ruleVO.attribute, 2)";
        context = ImmutableMap.of("ruleVO", ruleVO);
        result = AviatorScript.eval(expr, context);
        System.out.println(result);

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("mode", "2");
        params.put("bb", 20);
        JSONObject calObj = JSONObject.parseObject("{\"calType\":\"duration\",\"timeWindow\":5}");
        params.put("calObj", calObj);
        expr = "[[[[\"$mode\",\"==\",\"2\"],\"condCalculate\",\"$calObj\"],\"==\",true]]";
        String avaitorExpr = buildEvalExprFromString(expr);
        result = AviatorScript.eval(avaitorExpr, params);
        System.out.println(result);

        try {
            //值不对，会报错
            expr = "c1 > c2 && (c1 < c3 || c5) ";
            context = ImmutableMap.of("c1", (Object) 20);
            result = AviatorScript.eval(expr, context);
        } catch (Exception e) {
            System.out.println(e instanceof Exception);
        }


        //定义条件: 事件是否在每天的10:20之后发生
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //ExecuteContext executeContext = ExecuteContext.getContext();
        //EventEntity event = new EventEntity();
        //String eventTime = "2016-01-02 08:00:08";
        //event.setEventTime(sdf.parse(eventTime).getTime());
        //executeContext.setEvent(event);

        //expr = "c1 > 10 && after('10:20') ";
        //context = ImmutableMap.of("c1", (Object)20);
        //result = AviatorScript.eval(expr,context);
        //System.out.println(result);
    }

    @Data
    public static class LinkageRuleVO {
        private long attribute;
    }

    static {
        AviatorEvaluator.addFunction(new BitSetted());
        AviatorEvaluator.addFunction(new BitNotSetted());
        AviatorEvaluator.addFunction(new Exists());
        AviatorEvaluator.addFunction(new GeoDistance());
        AviatorEvaluator.addFunction(new Between());

        //字符串函数
        AviatorEvaluator.addFunction(new Length());
        AviatorEvaluator.addFunction(new StartsWith());
        AviatorEvaluator.addFunction(new Contains());
        AviatorEvaluator.addFunction(new InRange());

        //json数据抽取
        AviatorEvaluator.addFunction(new Extract());
        AviatorEvaluator.addFunction(new DecodeBase64());
        AviatorEvaluator.addFunction(new HasField());

        AviatorEvaluator.addFunction(new SeqNewSetFunction());
        AviatorEvaluator.addFunction(new SeqNewListFunction());

        AviatorEvaluator.addFunction(new LocatorGeoDistance());
        AviatorEvaluator.addFunction(new DynamicGpsResolve());

        AviatorEvaluator.addFunction(new CondCalculate());

        /**
         * raw类型数据转化函数
         */
        AviatorEvaluator.addFunction(new ValueRawByteExtract());
        AviatorEvaluator.addFunction(new BoolRawByteExtract());
        AviatorEvaluator.addFunction(new EnumRawByteExtract());
        /**
         * String类型数据转化函数
         */
        AviatorEvaluator.addFunction(new ValueStringCharExtract());
        AviatorEvaluator.addFunction(new BoolStringCharExtract());
        AviatorEvaluator.addFunction(new EnumStringCharExtract());
    }
}

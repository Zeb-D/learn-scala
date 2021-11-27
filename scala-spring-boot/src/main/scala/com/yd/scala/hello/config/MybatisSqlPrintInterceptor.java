package com.yd.scala.hello.config;

import com.yd.scala.hello.hbase.service.HBaseClientImpl;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;

/**
 * 这里没有使用拦截 {@link org.apache.ibatis.executor.Executor 主要是因为PageHelp处理的时候，直接调用Executor的方法进行处理，没有调用invocation.proceed() 下一个拦截器处理，直接处理SQL
 * 的修改，因此，将这个拦截设置到最后的查询阶段去处理}
 * {@linkplain com.github.pagehelper.PageInterceptor executor.query(ms, parameter, rowBounds, resultHandler, cacheKey, boundSql) }
 * <p>
 * 因此拦截StatementHandler 肯定不会错误【StatementHandler，语句处理器负责和JDBC层具体交互，包括prepare语句，执行语句，以及调用ParameterHandler.parameterize()设置参数】
 */
@Intercepts({@Signature(type = StatementHandler.class, method = "query", args = {Statement.class, ResultHandler.class}),
        @Signature(type = StatementHandler.class, method = "update", args = {Statement.class}),
        @Signature(type = StatementHandler.class, method = "batch", args = {Statement.class})})
public class MybatisSqlPrintInterceptor implements Interceptor, Ordered {
    private static final Logger log = LoggerFactory.getLogger(HBaseClientImpl.class);
    private Configuration configuration = null;

    private static final ThreadLocal<SimpleDateFormat> DATE_FORMAT_THREAD_LOCAL = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        }
    };

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        // 检查是否需要打印日志 todo aore
//        if (!isNeedPrint(invocation)){
//            return invocation.proceed();
//        }
        Object target = invocation.getTarget();
        long startTime = System.currentTimeMillis();
        try {
            return invocation.proceed();
        } finally {
            String sql = this.getSql(target);
            long endTime = System.currentTimeMillis();
            long sqlCost = endTime - startTime;
            log.info("SQL:{}    执行耗时={}", sql, sqlCost);
        }
    }

    private boolean isNeedPrint(Invocation invocation) {
        try {
            // package 检查
            String packageName = invocation.getMethod().getDeclaringClass().getPackage().getName();
            boolean packageMatch = Arrays.stream(SqlPrintConfig.packageExpression.split(","))
                    .anyMatch(s -> s.contains(packageName));
            return packageMatch;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 获取sql
     *
     * @param target
     * @return
     * @throws IllegalAccessException
     */
    private String getSql(Object target) {
        try {
            StatementHandler statementHandler = (StatementHandler) target;
            BoundSql boundSql = statementHandler.getBoundSql();
            if (configuration == null) {
                final DefaultParameterHandler parameterHandler = (DefaultParameterHandler) statementHandler.getParameterHandler();
                Field configurationField = ReflectionUtils.findField(parameterHandler.getClass(), "configuration");
                ReflectionUtils.makeAccessible(configurationField);
                this.configuration = (Configuration) configurationField.get(parameterHandler);
            }
            //替换参数格式化Sql语句，去除换行符
            return formatSql(boundSql, configuration);
        } catch (Exception e) {
            log.warn("get sql error {}", target, e);
        }
        return "";
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }

    /**
     * 获取完整的sql实体的信息
     *
     * @param boundSql
     * @return
     */
    private String formatSql(BoundSql boundSql, Configuration configuration) {
        String sql = boundSql.getSql();
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        Object parameterObject = boundSql.getParameterObject();
        // 输入sql字符串空判断
        if (sql == null || sql.length() == 0) {
            return "";
        }

        if (configuration == null) {
            return "";
        }

        TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();

        // 美化sql
        sql = beautifySql(sql);
        /**
         * @see DefaultParameterHandler 参考Mybatis 参数处理
         */
        if (parameterMappings != null) {
            for (ParameterMapping parameterMapping : parameterMappings) {
                if (parameterMapping.getMode() != ParameterMode.OUT) {
                    Object value;
                    String propertyName = parameterMapping.getProperty();
                    if (boundSql.hasAdditionalParameter(propertyName)) {
                        value = boundSql.getAdditionalParameter(propertyName);
                    } else if (parameterObject == null) {
                        value = null;
                    } else if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                        value = parameterObject;
                    } else {
                        MetaObject metaObject = configuration.newMetaObject(parameterObject);
                        value = metaObject.getValue(propertyName);
                    }
                    String paramValueStr = "";
                    if (value instanceof String) {
                        paramValueStr = "'" + value + "'";
                    } else if (value instanceof Date) {
                        paramValueStr = "'" + DATE_FORMAT_THREAD_LOCAL.get().format(value) + "'";
                    } else {
                        paramValueStr = value + "";
                    }
                    // mybatis generator 中的参数不打印出来
                    if (!propertyName.contains("frch_criterion")) {
                        paramValueStr = "/*" + propertyName + "*/" + paramValueStr;
                    }
                    sql = sql.replaceFirst("\\?", Matcher.quoteReplacement(paramValueStr));
                }
            }
        }
        return sql;
    }

    /**
     * 美化Sql
     */
    private String beautifySql(String sql) {
        sql = sql.replaceAll("[\\s\n ]+", " ");
        return sql;
    }


    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }


    static class SqlPrintConfig {
        static String packageExpression;
    }
}
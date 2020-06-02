package com.yd.scala.hello.spel;

import org.springframework.expression.EvaluationException;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParseException;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.ArrayList;
import java.util.List;

public class GenericConvertExample {
    public List<Integer> nums = new ArrayList();

    public static void main(String[] args) {

        GenericConvertExample example = new GenericConvertExample();
        example.nums.add(1);

        //创建表达式上下文
        StandardEvaluationContext context = new StandardEvaluationContext(example);
        //创建表达式解析器
        ExpressionParser parser = new SpelExpressionParser();

        String expression = "nums[0]";
        //自动将 2 转换为 Integer 类型
        parser.parseExpression(expression).setValue(context, 2);
        System.out.println("nums:" + example.nums);

        //抛出 ConverterNotFoundException
        try {
            parser.parseExpression(expression).setValue(context, true);
        } catch (EvaluationException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}

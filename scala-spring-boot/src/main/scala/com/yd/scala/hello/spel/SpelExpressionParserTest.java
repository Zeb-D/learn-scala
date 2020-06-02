package com.yd.scala.hello.spel;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.SpelCompilerMode;
import org.springframework.expression.spel.SpelParserConfiguration;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author created by Zeb灬D on 2020-03-02 17:04
 */
public class SpelExpressionParserTest {
    public static void main(String[] args) {
        ExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression("6+2");
        Integer result = (Integer) expression.getValue();
        System.out.println("result:" + result);

        parser = new SpelExpressionParser();
        expression = parser.parseExpression("'SpEL'.concat(' thinking')");
        String results = expression.getValue(String.class);
        System.out.println("results:" + results);

        Account account = new Account("Deniro");
        parser = new SpelExpressionParser();
        EvaluationContext context = new StandardEvaluationContext(account);
        expression = parser.parseExpression("name");
        results = expression.getValue(context, String.class);
        System.out.println("results:" + results);

        //Spel 解析配置器
        SpelParserConfiguration configuration = new SpelParserConfiguration
                (SpelCompilerMode.IMMEDIATE, SpelExpressionParserTest.class.getClassLoader());
        //解析器
        parser = new SpelExpressionParser(configuration);
        //上下文
        account = new Account("Deniro");
        account.setUser(new User("aasdd", 1213));
        context = new StandardEvaluationContext(account);
        //解析表达式
        Expression spelExpression = parser.parseExpression("user?.address");
        System.out.println(spelExpression.getValue(context));

        account.setUser(null);
        spelExpression = parser.parseExpression("user?.address");
        System.out.println(spelExpression.getValue(context));

        expression = parser.parseExpression("('Hello' + ' World').concat(#end)");
        context = new StandardEvaluationContext();
        context.setVariable("end", "!");
        System.out.println("Hello World!".equals(expression.getValue(context, String.class)));

        parser = new SpelExpressionParser();
        // invokes 'getBytes().length'
        Expression exp = parser.parseExpression("'Hello World'.bytes.length");
        System.out.println(exp.getValue(Integer.class));


        // create an array of integers
        List<Integer> primes = new ArrayList<Integer>();
        primes.addAll(Arrays.asList(2, 3, 5, 7, 11, 13, 17));

        // create parser and set variable 'primes' as the array of integers
        parser = new SpelExpressionParser();
        StandardEvaluationContext context1 = new StandardEvaluationContext();
        context1.setVariable("primes", primes);

        // all prime numbers > 10 from the list (using selection ?{...})
        // evaluates to [11, 13, 17]
        List<Integer> primesGreaterThanTen = (List<Integer>) parser.parseExpression(
                "#primes.?[#this>10]").getValue(context1);
        System.out.println(primesGreaterThanTen);

        String randomPhrase = parser.parseExpression(
                "random number is #{T(java.lang.Math).random()}",
                new TemplateParserContext()).getValue(String.class);
        // evaluates to "random number is 0.7038186818312008"
        System.out.println(randomPhrase);
    }

    static class Account {
        private String name;
        private User user;

        public Account(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }
    }

    static class User {
        private String address;
        private Integer age;

        public User(String address, Integer age) {
            this.address = address;
            this.age = age;
        }

        public String getAddress() {
            return address;
        }

        public Integer getAge() {
            return age;
        }
    }

}

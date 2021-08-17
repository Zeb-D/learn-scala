package com.yd.scala.hello;

/**
 * （1）一只青蛙一次可以跳上 1 级台阶，也可以跳上2 级。求该青蛙跳上一个n 级的台阶总共有多少种跳法。
 * （2）一只青蛙一次可以跳上1级台阶，也可以跳上2 级……它也可以跳上n 级，此时该青蛙跳上一个n级的台阶总共有多少种跳法？
 *
 * @author created by Zeb灬D on 2021-07-29 19:24
 */
public class ATest {
    //跳第N阶
    public static int Fib_N(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        return Fib_N(n - 1) + Fib_N(n - 2);
    }

    //跳N阶总
    public static int Fib_AN(int n) {
        if (n == 1 || n == 0) {
            return 1;
        }
        return 2 * Fib_AN(n - 1);
    }


    public static void main(String[] args) {
        System.out.println("0c5b68bd-cc51-5ac0-9bf4-6c44a812d159.png".length());
        System.out.println(Fib_N(7));
        System.out.println(Fib_N(3));
        System.out.println(Fib_N(4));
        System.out.println(Fib_AN(3));
    }
}

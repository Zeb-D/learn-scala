package com.yd.scala.hello;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

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
            return 1;
        }
        return Fib_N(n - 1) + Fib_N(n - 2);
    }

    //跳第N阶
    public static int Fib_N2(int n) {
        return 0;
    }

    public static int matrix(int n) {
        int[][] base = {{1, 1}, {1, 0}};//# 元矩阵，这里可以把元矩阵看做是2**0=1
        int[][] ret = {{1, 0}, {0, 1}}; //结果矩阵  最开始的结果矩阵也可以看做是1，因为这个矩阵和任意二阶A矩阵相乘结果都是A
        while (n > 0) {
            if ((n & 1) == 1) {//取n的二进制的最后一位和1做与运算，如果最后一位是1，则进入if体内部
                ret = multiply(ret, base, 2);//如果在该位置n的二进制为1，则计算ans和base矩阵
            }
            n >>= 1;//n的二进制往右移一位
            base = multiply(base, base, 2);//base矩阵相乘，相当于初始base矩阵的幂*2
        }
        System.out.println(JSONObject.toJSONString(ret));
        System.out.println(JSONObject.toJSONString(base));
        return ret[0][1];//最后获取到的二阶矩阵的[0][1]即f(n)的值，假设f(0)此时的值就在ret
    }

    //计算二阶矩阵的相乘
    public static int[][] multiply(int[][] a, int[][] b, int length) {
        int[][] c = new int[length][length];//定义一个空的二阶矩阵
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                for (int k = 0; k < length; k++) {//新二阶矩阵的值计算
                    c[i][j] = c[i][j] + a[i][k] * b[k][j];
                }
            }
        }
        return c;
    }

    //跳N阶总
    public static int Fib_AN(int n) {
        if (n == 1 || n == 0) {
            return 1;
        }
        return 2 * Fib_AN(n - 1);
    }


    public static void main(String[] args) {
//        System.out.println(arrayNesting(new int[]{1, 0, 3, 4, 2}));
//        System.out.println("0c5b68bd-cc51-5ac0-9bf4-6c44a812d159.png".length());
//        System.out.println(Fib_N(7));
        System.out.println(Fib_N(6));
        System.out.println(matrix(6));
        System.out.println("1=>" + Fib_N2(6));
        System.out.println(Fib_AN(3));
    }

    public static int arrayNesting(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != Integer.MAX_VALUE) {
                int start = nums[i], count = 0;
                while (nums[start] != Integer.MAX_VALUE) {
                    int temp = start;
                    start = nums[start];
                    count++;
                    nums[temp] = Integer.MAX_VALUE;
                }
                res = Math.max(res, count);
            }
        }
        return res;
    }
}

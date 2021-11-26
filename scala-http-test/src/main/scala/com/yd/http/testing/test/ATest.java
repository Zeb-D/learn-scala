package com.yd.http.testing.test;

import com.yd.http.testing.HttpUtil;

import static com.yd.http.testing.test.FunctionTest.testQueryFunctions;
import static com.yd.http.testing.test.ProductTest.testGetProduct;
import static com.yd.http.testing.test.ProductTest.testQueryProducts;

/**
 * @author created by Zeb灬D on 2019-11-05 17:39
 */
public class ATest {
    public static void main(String[] args) {
        HttpUtil.Init("https://openapi-cn.wgine.com", "u54a9nj4vw347hypgem7", "jvvesfswkx9ffsahurm5q7umgx123456");
        HttpUtil.InitToken("7726960a738021a593ae5de6ce248dab");
        HttpUtil.InitProHOST("https://openapi.tuyacn.com");

        //测试:获取产品分类下的指令集
        testQueryFunctions();

        //开发者账号查询产品列表
        testQueryProducts();

        //开发者账号查询具体产品信息
        testGetProduct();
    }
}

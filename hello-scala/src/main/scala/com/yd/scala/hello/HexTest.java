package com.yd.scala.hello;

import com.alibaba.fastjson.JSON;
import lombok.Data;

/**
 * @author created by Zeb灬D on 2021-08-18 11:04
 */
public class HexTest {
    public static void main(String[] args) {
        String tableType = "MallCommodity";
        int hash = tableType.hashCode();
        int a = hash >>> (32 - 4); //高4位
        int b = (hash << (32 - 4)) >>> (32 - 4); //低4位
        if (tableType.contains("_")) {
            a = tableType.split("_")[0].hashCode() >>> (32 - 4);
            b = (tableType.split("_")[1].hashCode() << (32 - 4)) >>> (32 - 4);
        }
        System.out.println(Integer.toHexString(a));
        System.out.println(Integer.toHexString(b));
        System.out.println(Integer.toBinaryString(-118));
        System.out.println(Integer.toBinaryString(118));
        System.out.println(Integer.toBinaryString(138));
        int aa = 0xffffffff;
        System.out.println(aa);
        System.out.println(Integer.toBinaryString(0xffffffff));
        System.out.println(Integer.toBinaryString(-1));
        System.out.println(Integer.toBinaryString(-127));

        int bb = (byte) -118;//负数为补码
        System.out.println(bb);
        int cc = bb&0xff;//与32位的 255 进行 &，保留了负数的补码位
        System.out.println(cc);
        byte dd = (byte)(cc &0xff);//直接截取低8位，这样位上没丢
        System.out.println(dd);

//        print();
        //print1("");

//        System.out.println(JSONObject.toJSONString(StringUtils.splitByCharacterTypeCamelCase("MallCommodity")));
//        System.out.println(JSONObject.toJSONString(StringUtils.splitByCharacterTypeCamelCase("Mall_Commodity")));
//        System.out.println(JSONObject.toJSONString(StringUtils.splitByCharacterTypeCamelCase("Mall_")));
//        System.out.println(JSONObject.toJSONString(StringUtils.splitByCharacterTypeCamelCase("mall_commodity")));
//        System.out.println(JSONObject.toJSONString(StringUtils.splitByCharacterTypeCamelCase("mall")));
//        System.out.println(JSONObject.toJSONString(StringUtils.splitByCharacterTypeCamelCase("Mall")));
    }

    private static final void print() {
        //欧洲
        print(s1);
        print(s2);
        print(s3);
        print(s4);

        //美国区
        print(s5);
        print(s6);

        //中国区
    }

    public static final void print1(String s) {
//        System.out.println(s0.substring(100,220));
        for (Country country : JSON.parseArray(s0, Country.class)) {
            String enName = country.enName.replaceAll(",", "_");
            enName = enName.replaceAll(" ", "_");
            enName = enName.replaceAll("/", "_");
            System.out.println(enName + "(" + country.code + ",\"" + country.zhName + "\", \"" + country.enName + "\",\"" + country.region + "\",\"" + country.countryCode + "\"),");
        }
    }

    public static final void print(String s1) {
        String[] str = s1.split("\\n");
//        System.out.println("====================>" + str.length);
        for (int i = 0; i < str.length; i = i + 3) {
            System.out.println(str[i + 2] + "(" + str[i] + ",\"" + str[i + 2] + "\",\"" + str[i + 2] + "\",\"" + str[i + 1].trim() + "\"),");
        }
    }

    @Data
    static class Country {
        public Integer code;
        public String zhName;
        public String enName;
        public String region;
        public String countryCode;
    }


    private static final String s0 = "[{\"code\": 86,\"zhName\": \"中国\",\n" +
            "    \"enName\": \"China\",\n" +
            "    \"region\": \"AY\",\n" +
            "    \"lang\": \"zh\",\n" +
            "    \"countryCode\": \"CN\"\n" +
            "  },\n" +
            "  {\"code\": 1,\"zhName\": \"美国/加拿大\",\n" +
            "    \"enName\": \"United States/Canada\",\n" +
            "    \"region\": \"AZ\",\n" +
            "    \"lang\": \"en\",\n" +
            "    \"countryCode\": \"US\"\n" +
            "  },\n" +
            "  {\"code\": 93,\"zhName\": \"阿富汗\",\n" +
            "    \"enName\": \"Afghanistan\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"AF\"\n" +
            "  },\n" +
            "  {\"code\": 355,\"zhName\": \"阿尔巴尼亚\",\n" +
            "    \"enName\": \"Albania\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"AL\"\n" +
            "  },\n" +
            "  {\"code\": 213,\"zhName\": \"阿尔及利亚\",\n" +
            "    \"enName\": \"Algeria\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"DZ\"\n" +
            "  },\n" +
            "  {\"code\": 244,\"zhName\": \"安哥拉\",\n" +
            "    \"enName\": \"Angola\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"AO\"\n" +
            "  },\n" +
            "  {\"code\": 54,\"zhName\": \"阿根廷\",\n" +
            "    \"enName\": \"Argentina\",\n" +
            "    \"region\": \"AZ\",\n" +
            "    \"countryCode\": \"AR\"\n" +
            "  },\n" +
            "  {\"code\": 374,\"zhName\": \"亚美尼亚\",\n" +
            "    \"enName\": \"Armenia\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"AM\"\n" +
            "  },\n" +
            "  {\"code\": 61,\"zhName\": \"澳大利亚\",\n" +
            "    \"enName\": \"Australia\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"lang\": \"en\",\n" +
            "    \"countryCode\": \"AU\"\n" +
            "  },\n" +
            "  {\"code\": 43,\"zhName\": \"奥地利\",\n" +
            "    \"enName\": \"Austria\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"AT\"\n" +
            "  },\n" +
            "  {\"code\": 994,\"zhName\": \"阿塞拜疆\",\n" +
            "    \"enName\": \"Azerbaijan\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"AZ\"\n" +
            "  },\n" +
            "  {\"code\": 973,\"zhName\": \"巴林\",\n" +
            "    \"enName\": \"Bahrain\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"BH\"\n" +
            "  },\n" +
            "  {\"code\": 880,\"zhName\": \"孟加拉国\",\n" +
            "    \"enName\": \"Bangladesh\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"BD\"\n" +
            "  },\n" +
            "  {\"code\": 375,\"zhName\": \"白俄罗斯\",\n" +
            "    \"enName\": \"Belarus\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"BY\"\n" +
            "  },\n" +
            "  {\"code\": 32,\"zhName\": \"比利时\",\n" +
            "    \"enName\": \"Belgium\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"BE\"\n" +
            "  },\n" +
            "  {\"code\": 501,\"zhName\": \"伯利兹\",\n" +
            "    \"enName\": \"Belize\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"BZ\"\n" +
            "  },\n" +
            "  {\"code\": 229,\"zhName\": \"贝宁\",\n" +
            "    \"enName\": \"Benin\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"BJ\"\n" +
            "  },\n" +
            "  {\"code\": 975,\"zhName\": \"不丹\",\n" +
            "    \"enName\": \"Bhutan\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"BT\"\n" +
            "  },\n" +
            "  {\"code\": 591,\"zhName\": \"玻利维亚\",\n" +
            "    \"enName\": \"Bolivia\",\n" +
            "    \"region\": \"AZ\",\n" +
            "    \"countryCode\": \"BO\"\n" +
            "  },\n" +
            "  {\"code\": 387,\"zhName\": \"波斯尼亚和黑塞哥维那\",\n" +
            "    \"enName\": \"Bosnia and Herzegovina\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"BA\"\n" +
            "  },\n" +
            "  {\"code\": 267,\"zhName\": \"博茨瓦纳\",\n" +
            "    \"enName\": \"Botswana\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"BW\"\n" +
            "  },\n" +
            "  {\"code\": 55,\"zhName\": \"巴西\",\n" +
            "    \"enName\": \"Brazil\",\n" +
            "    \"region\": \"AZ\",\n" +
            "    \"countryCode\": \"BR\"\n" +
            "  },\n" +
            "  {\"code\": 1284,\"zhName\": \"英属维京群岛\",\n" +
            "    \"enName\": \"Virgin Islands, US\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"VG\"\n" +
            "  },\n" +
            "  {\"code\": 673,\"zhName\": \"文莱\",\n" +
            "    \"enName\": \"Brunei\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"BN\"\n" +
            "  },\n" +
            "  {\"code\": 359,\"zhName\": \"保加利亚\",\n" +
            "    \"enName\": \"Bulgaria\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"BG\"\n" +
            "  },\n" +
            "  {\"code\": 226,\"zhName\": \"布基纳法索\",\n" +
            "    \"enName\": \"Burkina Faso\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"BF\"\n" +
            "  },\n" +
            "  {\"code\": 257,\"zhName\": \"布隆迪\",\n" +
            "    \"enName\": \"Burundi\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"BI\"\n" +
            "  },\n" +
            "  {\"code\": 855,\"zhName\": \"柬埔寨\",\n" +
            "    \"enName\": \"Cambodia\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"KH\"\n" +
            "  },\n" +
            "  {\"code\": 237,\"zhName\": \"喀麦隆\",\n" +
            "    \"enName\": \"Cameroon\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"CM\"\n" +
            "  },\n" +
            "  {\"code\": 238,\"zhName\": \"佛得角\",\n" +
            "    \"enName\": \"Cape Verde\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"CV\"\n" +
            "  },\n" +
            "  {\"code\": 1345,\"zhName\": \"开曼群岛\",\n" +
            "    \"enName\": \"Cayman Islands\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"KY\"\n" +
            "  },\n" +
            "  {\"code\": 236,\"zhName\": \"中非共和国\",\n" +
            "    \"enName\": \"Central African Republic\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"CF\"\n" +
            "  },\n" +
            "  {\"code\": 235,\"zhName\": \"乍得\",\n" +
            "    \"enName\": \"Chad\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"TD\"\n" +
            "  },\n" +
            "  {\"code\": 56,\"zhName\": \"智利\",\n" +
            "    \"enName\": \"Chile\",\n" +
            "    \"region\": \"AZ\",\n" +
            "    \"countryCode\": \"CL\"\n" +
            "  },\n" +
            "  {\"code\": 57,\"zhName\": \"哥伦比亚\",\n" +
            "    \"enName\": \"Colombia\",\n" +
            "    \"region\": \"AZ\",\n" +
            "    \"countryCode\": \"CO\"\n" +
            "  },\n" +
            "  {\"code\": 269,\"zhName\": \"科摩罗\",\n" +
            "    \"enName\": \"Comoros\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"KM\"\n" +
            "  },\n" +
            "  {\"code\": 242,\"zhName\": \"刚果\",\n" +
            "    \"enName\": \"Republic Of The Congo\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"CG\"\n" +
            "  },\n" +
            "  {\"code\": 243,\"zhName\": \"刚果\",\n" +
            "    \"enName\": \"Democratic Republic of the Congo\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"CD\"\n" +
            "  },\n" +
            "  {\"code\": 506,\"zhName\": \"哥斯达黎加\",\n" +
            "    \"enName\": \"Costa Rica\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"CR\"\n" +
            "  },\n" +
            "  {\"code\": 385,\"zhName\": \"克罗地亚\",\n" +
            "    \"enName\": \"Croatia\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"HR\"\n" +
            "  },\n" +
            "  {\"code\": 357,\"zhName\": \"塞浦路斯\",\n" +
            "    \"enName\": \"Cyprus\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"CY\"\n" +
            "  },\n" +
            "  {\"code\": 420,\"zhName\": \"捷克共和国\",\n" +
            "    \"enName\": \"Czech Republic\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"CZ\"\n" +
            "  },\n" +
            "  {\"code\": 45,\"zhName\": \"丹麦\",\n" +
            "    \"enName\": \"Denmark\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"DK\"\n" +
            "  },\n" +
            "  {\"code\": 253,\"zhName\": \"吉布提\",\n" +
            "    \"enName\": \"Djibouti\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"DJ\"\n" +
            "  },\n" +
            "  {\"code\": 1809,\"zhName\": \"多米尼加共和国\",\n" +
            "    \"enName\": \"Dominican Republic\",\n" +
            "    \"region\": \"AZ\",\n" +
            "    \"countryCode\": \"DO\"\n" +
            "  },\n" +
            "  {\"code\": 593,\"zhName\": \"厄瓜多尔\",\n" +
            "    \"enName\": \"Ecuador\",\n" +
            "    \"region\": \"AZ\",\n" +
            "    \"countryCode\": \"EC\"\n" +
            "  },\n" +
            "  {\"code\": 20,\"zhName\": \"埃及\",\n" +
            "    \"enName\": \"Egypt\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"EG\"\n" +
            "  },\n" +
            "  {\"code\": 503,\"zhName\": \"萨尔瓦多\",\n" +
            "    \"enName\": \"El Salvador\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"SV\"\n" +
            "  },\n" +
            "  {\"code\": 240,\"zhName\": \"赤道几内亚\",\n" +
            "    \"enName\": \"Equatorial Guinea\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"GQ\"\n" +
            "  },\n" +
            "  {\"code\": 372,\"zhName\": \"爱沙尼亚\",\n" +
            "    \"enName\": \"Estonia\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"EE\"\n" +
            "  },\n" +
            "  {\"code\": 251,\"zhName\": \"埃塞俄比亚\",\n" +
            "    \"enName\": \"Ethiopia\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"ET\"\n" +
            "  },\n" +
            "  {\"code\": 679,\"zhName\": \"斐济\",\n" +
            "    \"enName\": \"Fiji\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"FJ\"\n" +
            "  },\n" +
            "  {\"code\": 358,\"zhName\": \"芬兰\",\n" +
            "    \"enName\": \"Finland\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"FI\"\n" +
            "  },\n" +
            "  {\"code\": 33,\"zhName\": \"法国\",\n" +
            "    \"enName\": \"France\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"lang\": \"fr\",\n" +
            "    \"countryCode\": \"FR\"\n" +
            "  },\n" +
            "  {\"code\": 241,\"zhName\": \"加蓬\",\n" +
            "    \"enName\": \"Gabon\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"GA\"\n" +
            "  },\n" +
            "  {\"code\": 220,\"zhName\": \"冈比亚\",\n" +
            "    \"enName\": \"Gambia\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"GM\"\n" +
            "  },\n" +
            "  {\"code\": 995,\"zhName\": \"格鲁吉亚\",\n" +
            "    \"enName\": \"Georgia\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"GE\"\n" +
            "  },\n" +
            "  {\"code\": 49,\"zhName\": \"德国\",\n" +
            "    \"enName\": \"Germany\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"lang\": \"de\",\n" +
            "    \"countryCode\": \"DE\"\n" +
            "  },\n" +
            "  {\"code\": 233,\"zhName\": \"加纳\",\n" +
            "    \"enName\": \"Ghana\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"GH\"\n" +
            "  },\n" +
            "  {\"code\": 30,\"zhName\": \"希腊\",\n" +
            "    \"enName\": \"Greece\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"GR\"\n" +
            "  },\n" +
            "  {\"code\": 299,\"zhName\": \"格陵兰\",\n" +
            "    \"enName\": \"Greenland\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"GL\"\n" +
            "  },\n" +
            "  {\"code\": 502,\"zhName\": \"危地马拉\",\n" +
            "    \"enName\": \"Guatemala\",\n" +
            "    \"region\": \"AZ\",\n" +
            "    \"countryCode\": \"GT\"\n" +
            "  },\n" +
            "  {\"code\": 224,\"zhName\": \"几内亚\",\n" +
            "    \"enName\": \"Guinea\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"GN\"\n" +
            "  },\n" +
            "  {\"code\": 592,\"zhName\": \"圭亚那\",\n" +
            "    \"enName\": \"Guyana\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"GY\"\n" +
            "  },\n" +
            "  {\"code\": 509,\"zhName\": \"海地\",\n" +
            "    \"enName\": \"Haiti\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"HT\"\n" +
            "  },\n" +
            "  {\"code\": 504,\"zhName\": \"洪都拉斯\",\n" +
            "    \"enName\": \"Honduras\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"HN\"\n" +
            "  },\n" +
            "  {\"code\": 852,\"zhName\": \"中国香港特别行政区\",\n" +
            "    \"enName\": \"Hong Kong\",\n" +
            "    \"region\": \"AZ\",\n" +
            "    \"countryCode\": \"HK\"\n" +
            "  },\n" +
            "  {\"code\": 36,\"zhName\": \"匈牙利\",\n" +
            "    \"enName\": \"Hungary\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"HU\"\n" +
            "  },\n" +
            "  {\"code\": 354,\"zhName\": \"冰岛\",\n" +
            "    \"enName\": \"Iceland\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"IS\"\n" +
            "  },\n" +
            "  {\"code\": 91,\"zhName\": \"印度\",\n" +
            "    \"enName\": \"India\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"IN\"\n" +
            "  },\n" +
            "  {\"code\": 62,\"zhName\": \"印度尼西亚\",\n" +
            "    \"enName\": \"Indonesia\",\n" +
            "    \"region\": \"AZ\",\n" +
            "    \"countryCode\": \"ID\"\n" +
            "  },\n" +
            "  {\"code\": 964,\"zhName\": \"伊拉克\",\n" +
            "    \"enName\": \"Iraq\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"IQ\"\n" +
            "  },\n" +
            "  {\"code\": 353,\"zhName\": \"爱尔兰\",\n" +
            "    \"enName\": \"Ireland\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"IE\"\n" +
            "  },\n" +
            "  {\"code\": 972,\"zhName\": \"以色列\",\n" +
            "    \"enName\": \"Israel\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"IL\"\n" +
            "  },\n" +
            "  {\"code\": 39,\"zhName\": \"意大利\",\n" +
            "    \"enName\": \"Italy\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"IT\"\n" +
            "  },\n" +
            "  {\"code\": 225,\"zhName\": \"科特迪瓦\",\n" +
            "    \"enName\": \"Ivory Coast\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"CI\"\n" +
            "  },\n" +
            "  {\"code\": 1876,\"zhName\": \"牙买加\",\n" +
            "    \"enName\": \"Jamaica\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"JM\"\n" +
            "  },\n" +
            "  {\"code\": 81,\"zhName\": \"日本\",\n" +
            "    \"enName\": \"Japan\",\n" +
            "    \"region\": \"AZ\",\n" +
            "    \"lang\": \"ja\",\n" +
            "    \"countryCode\": \"JP\"\n" +
            "  },\n" +
            "  {\"code\": 962,\"zhName\": \"约旦\",\n" +
            "    \"enName\": \"Jordan\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"JO\"\n" +
            "  },\n" +
            "  {\"code\": 254,\"zhName\": \"肯尼亚\",\n" +
            "    \"enName\": \"Kenya\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"KE\"\n" +
            "  },\n" +
            "  {\"code\": 82,\"zhName\": \"韩国\",\n" +
            "    \"enName\": \"South Korea\",\n" +
            "    \"region\": \"AZ\",\n" +
            "    \"countryCode\": \"KR\"\n" +
            "  },\n" +
            "  {\"code\": 965,\"zhName\": \"科威特\",\n" +
            "    \"enName\": \"Kuwait\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"KW\"\n" +
            "  },\n" +
            "  {\"code\": 996,\"zhName\": \"吉尔吉斯斯坦\",\n" +
            "    \"enName\": \"Kyrgyzstan\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"KG\"\n" +
            "  },\n" +
            "  {\"code\": 856,\"zhName\": \"老挝\",\n" +
            "    \"enName\": \"Laos\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"LA\"\n" +
            "  },\n" +
            "  {\"code\": 371,\"zhName\": \"拉脱维亚\",\n" +
            "    \"enName\": \"Latvia\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"LV\"\n" +
            "  },\n" +
            "  {\"code\": 961,\"zhName\": \"黎巴嫩\",\n" +
            "    \"enName\": \"Lebanon\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"LB\"\n" +
            "  },\n" +
            "  {\"code\": 266,\"zhName\": \"莱索托\",\n" +
            "    \"enName\": \"Lesotho\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"LS\"\n" +
            "  },\n" +
            "  {\"code\": 231,\"zhName\": \"利比里亚\",\n" +
            "    \"enName\": \"Liberia\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"LR\"\n" +
            "  },\n" +
            "  {\"code\": 218,\"zhName\": \"利比亚\",\n" +
            "    \"enName\": \"Libya\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"LY\"\n" +
            "  },\n" +
            "  {\"code\": 370,\"zhName\": \"立陶宛\",\n" +
            "    \"enName\": \"Lithuania\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"LT\"\n" +
            "  },\n" +
            "  {\"code\": 352,\"zhName\": \"卢森堡\",\n" +
            "    \"enName\": \"Luxembourg\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"LU\"\n" +
            "  },\n" +
            "  {\"code\": 853,\"zhName\": \"中国澳门特别行政区\",\n" +
            "    \"enName\": \"Macau\",\n" +
            "    \"region\": \"AZ\",\n" +
            "    \"countryCode\": \"MO\"\n" +
            "  },\n" +
            "  {\"code\": 389,\"zhName\": \"马其顿\",\n" +
            "    \"enName\": \"Macedonia\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"MK\"\n" +
            "  },\n" +
            "  {\"code\": 261,\"zhName\": \"马达加斯加\",\n" +
            "    \"enName\": \"Madagascar\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"MG\"\n" +
            "  },\n" +
            "  {\"code\": 265,\"zhName\": \"马拉维\",\n" +
            "    \"enName\": \"Malawi\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"MW\"\n" +
            "  },\n" +
            "  {\"code\": 60,\"zhName\": \"马来西亚\",\n" +
            "    \"enName\": \"Malaysia\",\n" +
            "    \"region\": \"AZ\",\n" +
            "    \"countryCode\": \"MY\"\n" +
            "  },\n" +
            "  {\"code\": 960,\"zhName\": \"马尔代夫\",\n" +
            "    \"enName\": \"Maldives\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"MV\"\n" +
            "  },\n" +
            "  {\"code\": 223,\"zhName\": \"马里\",\n" +
            "    \"enName\": \"Mali\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"ML\"\n" +
            "  },\n" +
            "  {\"code\": 356,\"zhName\": \"马耳他\",\n" +
            "    \"enName\": \"Malta\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"MT\"\n" +
            "  },\n" +
            "  {\"code\": 222,\"zhName\": \"毛利塔尼亚\",\n" +
            "    \"enName\": \"Mauritania\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"MR\"\n" +
            "  },\n" +
            "  {\"code\": 230,\"zhName\": \"毛里求斯\",\n" +
            "    \"enName\": \"Mauritius\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"MU\"\n" +
            "  },\n" +
            "  {\"code\": 52,\"zhName\": \"墨西哥\",\n" +
            "    \"enName\": \"Mexico\",\n" +
            "    \"region\": \"AZ\",\n" +
            "    \"countryCode\": \"MX\"\n" +
            "  },\n" +
            "  {\"code\": 373,\"zhName\": \"摩尔多瓦\",\n" +
            "    \"enName\": \"Moldova\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"MD\"\n" +
            "  },\n" +
            "  {\"code\": 377,\"zhName\": \"摩纳哥\",\n" +
            "    \"enName\": \"Monaco\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"MC\"\n" +
            "  },\n" +
            "  {\"code\": 976,\"zhName\": \"蒙古\",\n" +
            "    \"enName\": \"Mongolia\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"MN\"\n" +
            "  },\n" +
            "  {\"code\": 382,\"zhName\": \"黑山共和国\",\n" +
            "    \"enName\": \"Montenegro\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"ME\"\n" +
            "  },\n" +
            "  {\"code\": 212,\"zhName\": \"摩洛哥\",\n" +
            "    \"enName\": \"Morocco\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"MA\"\n" +
            "  },\n" +
            "  {\"code\": 258,\"zhName\": \"莫桑比克\",\n" +
            "    \"enName\": \"Mozambique\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"MZ\"\n" +
            "  },\n" +
            "  {\"code\": 95,\"zhName\": \"缅甸\",\n" +
            "    \"enName\": \"Myanmar\",\n" +
            "    \"region\": \"AZ\",\n" +
            "    \"countryCode\": \"MM\"\n" +
            "  },\n" +
            "  {\"code\": 264,\"zhName\": \"纳米比亚\",\n" +
            "    \"enName\": \"Namibia\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"NA\"\n" +
            "  },\n" +
            "  {\"code\": 977,\"zhName\": \"尼泊尔\",\n" +
            "    \"enName\": \"Nepal\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"NP\"\n" +
            "  },\n" +
            "  {\"code\": 31,\"zhName\": \"荷兰\",\n" +
            "    \"enName\": \"Netherlands\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"NL\"\n" +
            "  },\n" +
            "  {\"code\": 64,\"zhName\": \"新西兰\",\n" +
            "    \"enName\": \"New Zealand\",\n" +
            "    \"region\": \"AZ\",\n" +
            "    \"lang\": \"en\",\n" +
            "    \"countryCode\": \"NZ\"\n" +
            "  },\n" +
            "  {\"code\": 505,\"zhName\": \"尼加拉瓜\",\n" +
            "    \"enName\": \"Nicaragua\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"NI\"\n" +
            "  },\n" +
            "  {\"code\": 227,\"zhName\": \"尼日尔\",\n" +
            "    \"enName\": \"Niger\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"NE\"\n" +
            "  },\n" +
            "  {\"code\": 234,\"zhName\": \"尼日利亚\",\n" +
            "    \"enName\": \"Nigeria\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"NG\"\n" +
            "  },\n" +
            "  {\"code\": 47,\"zhName\": \"挪威\",\n" +
            "    \"enName\": \"Norway\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"NO\"\n" +
            "  },\n" +
            "  {\"code\": 968,\"zhName\": \"阿曼\",\n" +
            "    \"enName\": \"Oman\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"OM\"\n" +
            "  },\n" +
            "  {\"code\": 92,\"zhName\": \"巴基斯坦\",\n" +
            "    \"enName\": \"Pakistan\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"PK\"\n" +
            "  },\n" +
            "  {\"code\": 507,\"zhName\": \"巴拿马\",\n" +
            "    \"enName\": \"Panama\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"PA\"\n" +
            "  },\n" +
            "  {\"code\": 595,\"zhName\": \"巴拉圭\",\n" +
            "    \"enName\": \"Paraguay\",\n" +
            "    \"region\": \"AZ\",\n" +
            "    \"countryCode\": \"PY\"\n" +
            "  },\n" +
            "  {\"code\": 51,\"zhName\": \"秘鲁\",\n" +
            "    \"enName\": \"Peru\",\n" +
            "    \"region\": \"AZ\",\n" +
            "    \"countryCode\": \"PE\"\n" +
            "  },\n" +
            "  {\"code\": 63,\"zhName\": \"菲律宾\",\n" +
            "    \"enName\": \"Philippines\",\n" +
            "    \"region\": \"AZ\",\n" +
            "    \"countryCode\": \"PH\"\n" +
            "  },\n" +
            "  {\"code\": 48,\"zhName\": \"波兰\",\n" +
            "    \"enName\": \"Poland\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"PL\"\n" +
            "  },\n" +
            "  {\"code\": 351,\"zhName\": \"葡萄牙\",\n" +
            "    \"enName\": \"Portugal\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"PT\"\n" +
            "  },\n" +
            "  {\"code\": 1787,\"zhName\": \"波多黎各\",\n" +
            "    \"enName\": \"Puerto Rico\",\n" +
            "    \"region\": \"AZ\",\n" +
            "    \"countryCode\": \"PR\"\n" +
            "  },\n" +
            "  {\"code\": 974,\"zhName\": \"卡塔尔\",\n" +
            "    \"enName\": \"Qatar\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"QA\"\n" +
            "  },\n" +
            "  {\"code\": 262,\"zhName\": \"留尼旺\",\n" +
            "    \"enName\": \"Réunion Island\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"RE\"\n" +
            "  },\n" +
            "  {\"code\": 40,\"zhName\": \"罗马尼亚\",\n" +
            "    \"enName\": \"Romania\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"RO\"\n" +
            "  },\n" +
            "  {\"code\": 7,\"zhName\": \"俄罗斯/哈萨克斯坦\",\n" +
            "    \"enName\": \"Russia/Kazakhstan\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"RU\"\n" +
            "  },\n" +
            "  {\"code\": 250,\"zhName\": \"卢旺达\",\n" +
            "    \"enName\": \"Rwanda\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"RW\"\n" +
            "  },\n" +
            "  {\"code\": 378,\"zhName\": \"圣马力诺\",\n" +
            "    \"enName\": \"San Marino\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"SM\"\n" +
            "  },\n" +
            "  {\"code\": 966,\"zhName\": \"沙特阿拉伯\",\n" +
            "    \"enName\": \"Saudi Arabia\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"SA\"\n" +
            "  },\n" +
            "  {\"code\": 221,\"zhName\": \"塞内加尔\",\n" +
            "    \"enName\": \"Senegal\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"SN\"\n" +
            "  },\n" +
            "  {\"code\": 381,\"zhName\": \"塞尔维亚\",\n" +
            "    \"enName\": \"Serbia\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"RS\"\n" +
            "  },\n" +
            "  {\"code\": 232,\"zhName\": \"塞拉利昂\",\n" +
            "    \"enName\": \"Sierra Leone\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"SL\"\n" +
            "  },\n" +
            "  {\"code\": 65,\"zhName\": \"新加坡\",\n" +
            "    \"enName\": \"Singapore\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"SG\"\n" +
            "  },\n" +
            "  {\"code\": 421,\"zhName\": \"斯洛伐克\",\n" +
            "    \"enName\": \"Slovakia\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"SK\"\n" +
            "  },\n" +
            "  {\"code\": 386,\"zhName\": \"斯洛文尼亚\",\n" +
            "    \"enName\": \"Slovenia\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"SI\"\n" +
            "  },\n" +
            "  {\"code\": 252,\"zhName\": \"索马里\",\n" +
            "    \"enName\": \"Somalia\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"SO\"\n" +
            "  },\n" +
            "  {\"code\": 27,\"zhName\": \"南非\",\n" +
            "    \"enName\": \"South Africa\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"ZA\"\n" +
            "  },\n" +
            "  {\"code\": 34,\"zhName\": \"西班牙\",\n" +
            "    \"enName\": \"Spain\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"lang\": \"es\",\n" +
            "    \"countryCode\": \"ES\"\n" +
            "  },\n" +
            "  {\"code\": 94,\"zhName\": \"斯里兰卡\",\n" +
            "    \"enName\": \"Sri Lanka\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"LK\"\n" +
            "  },\n" +
            "  {\"code\": 249,\"zhName\": \"苏丹\",\n" +
            "    \"enName\": \"Sudan\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"SD\"\n" +
            "  },\n" +
            "  {\"code\": 597,\"zhName\": \"苏里南\",\n" +
            "    \"enName\": \"Suriname\",\n" +
            "    \"region\": \"AZ\",\n" +
            "    \"countryCode\": \"SR\"\n" +
            "  },\n" +
            "  {\"code\": 268,\"zhName\": \"斯威士兰\",\n" +
            "    \"enName\": \"Swaziland\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"SZ\"\n" +
            "  },\n" +
            "  {\"code\": 46,\"zhName\": \"瑞典\",\n" +
            "    \"enName\": \"Sweden\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"SE\"\n" +
            "  },\n" +
            "  {\"code\": 41,\"zhName\": \"瑞士\",\n" +
            "    \"enName\": \"Switzerland\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"CH\"\n" +
            "  },\n" +
            "  {\"code\": 886,\"zhName\": \"中国台湾\",\n" +
            "    \"enName\": \"Taiwan\",\n" +
            "    \"region\": \"AZ\",\n" +
            "    \"countryCode\": \"TW\"\n" +
            "  },\n" +
            "  {\"code\": 992,\"zhName\": \"塔吉克斯坦\",\n" +
            "    \"enName\": \"Tajikistan\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"TJ\"\n" +
            "  },\n" +
            "  {\"code\": 255,\"zhName\": \"坦桑尼亚\",\n" +
            "    \"enName\": \"Tanzania\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"TZ\"\n" +
            "  },\n" +
            "  {\"code\": 66,\"zhName\": \"泰国\",\n" +
            "    \"enName\": \"Thailand\",\n" +
            "    \"region\": \"AZ\",\n" +
            "    \"countryCode\": \"TH\"\n" +
            "  },\n" +
            "  {\"code\": 228,\"zhName\": \"多哥\",\n" +
            "    \"enName\": \"Togo\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"TG\"\n" +
            "  },\n" +
            "  {\"code\": 676,\"zhName\": \"汤加\",\n" +
            "    \"enName\": \"Tonga\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"TO\"\n" +
            "  },\n" +
            "  {\"code\": 1868,\"zhName\": \"特立尼达和多巴哥\",\n" +
            "    \"enName\": \"Trinidad and Tobago\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"TT\"\n" +
            "  },\n" +
            "  {\"code\": 216,\"zhName\": \"突尼斯\",\n" +
            "    \"enName\": \"Tunisia\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"TN\"\n" +
            "  },\n" +
            "  {\"code\": 90,\"zhName\": \"土耳其\",\n" +
            "    \"enName\": \"Turkey\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"TR\"\n" +
            "  },\n" +
            "  {\"code\": 993,\"zhName\": \"土库曼斯坦\",\n" +
            "    \"enName\": \"Turkmenistan\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"TM\"\n" +
            "  },\n" +
            "  {\"code\": 1340,\"zhName\": \"美属维尔京群岛\",\n" +
            "    \"enName\": \"Virgin Islands, British\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"VI\"\n" +
            "  },\n" +
            "  {\"code\": 256,\"zhName\": \"乌干达\",\n" +
            "    \"enName\": \"Uganda\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"UG\"\n" +
            "  },\n" +
            "  {\"code\": 380,\"zhName\": \"乌克兰\",\n" +
            "    \"enName\": \"Ukraine\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"UA\"\n" +
            "  },\n" +
            "  {\"code\": 971,\"zhName\": \"阿拉伯联合酋长国\",\n" +
            "    \"enName\": \"United Arab Emirates\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"UA\"\n" +
            "  },\n" +
            "  {\"code\": 44,\"zhName\": \"英国\",\n" +
            "    \"enName\": \"United Kingdom\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"GB\"\n" +
            "  },\n" +
            "  {\"code\": 598,\"zhName\": \"乌拉圭\",\n" +
            "    \"enName\": \"Uruguay\",\n" +
            "    \"region\": \"AZ\",\n" +
            "    \"countryCode\": \"UY\"\n" +
            "  },\n" +
            "  {\"code\": 998,\"zhName\": \"乌兹别克斯坦\",\n" +
            "    \"enName\": \"Uzbekistan\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"UZ\"\n" +
            "  },\n" +
            "  {\"code\": 58,\"zhName\": \"委内瑞拉\",\n" +
            "    \"enName\": \"Venezuela\",\n" +
            "    \"region\": \"AZ\",\n" +
            "    \"countryCode\": \"VE\"\n" +
            "  },\n" +
            "  {\"code\": 84,\"zhName\": \"越南\",\n" +
            "    \"enName\": \"Vietnam\",\n" +
            "    \"region\": \"AZ\",\n" +
            "    \"countryCode\": \"VN\"\n" +
            "  },\n" +
            "  {\"code\": 967,\"zhName\": \"也门\",\n" +
            "    \"enName\": \"Yemen\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"YE\"\n" +
            "  },\n" +
            "  {\"code\": 260,\"zhName\": \"赞比亚\",\n" +
            "    \"enName\": \"Zambia\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"ZM\"\n" +
            "  },\n" +
            "  {\"code\": 263,\"zhName\": \"津巴布韦\",\n" +
            "    \"enName\": \"Zimbabwe\",\n" +
            "    \"region\": \"EU\",\n" +
            "    \"countryCode\": \"ZW\"\n" +
            "  }]";


    private static final String s1 = "1242\n" +
            "巴哈马                   \n" +
            "Bahamas\n" +
            "1246\n" +
            "巴巴多斯                 \n" +
            "Barbados\n" +
            "1264\n" +
            "安圭拉                   \n" +
            "Anguilla\n" +
            "1268\n" +
            "安提瓜和巴布达           \n" +
            "Antigua and Barbuda\n" +
            "1284\n" +
            "英属维尔京群岛           \n" +
            "British Virgin Islands\n" +
            "1340\n" +
            "美属维尔京群岛           \n" +
            "U.S. Virgin Islands\n" +
            "1345\n" +
            "开曼群岛                 \n" +
            "Cayman Islands\n" +
            "1441\n" +
            "百慕大                   \n" +
            "Bermuda\n" +
            "1473\n" +
            "格林纳达                 \n" +
            "Grenada\n" +
            "1649\n" +
            "特克斯和凯科斯群岛       \n" +
            "Turks and Caicos Islands\n" +
            "1664\n" +
            "蒙特塞拉特               \n" +
            "Montserrat\n" +
            "1670\n" +
            "Northern Mariana Islands \n" +
            "Northern Mariana Islands\n" +
            "1671\n" +
            "关岛                     \n" +
            "Guam\n" +
            "1684\n" +
            "American Samoa           \n" +
            "American Samoa\n" +
            "1758\n" +
            "圣卢西亚                 \n" +
            "Saint Lucia\n" +
            "1767\n" +
            "多米尼克                 \n" +
            "Dominica\n" +
            "1784\n" +
            "圣文森特和格林纳丁斯     \n" +
            "Saint Vincent and the Grenadines\n" +
            "1868\n" +
            "特立尼达和多巴哥         \n" +
            "Trinidad and Tobago\n" +
            "1869\n" +
            "圣基茨和尼维斯           \n" +
            "Saint Kitts and Nevis\n" +
            "1876\n" +
            "牙买加                   \n" +
            "Jamaica";

    private static final String s2 = "20\n" +
            "埃及                     \n" +
            "Egypt\n" +
            "212\n" +
            "摩洛哥                   \n" +
            "Morocco\n" +
            "213\n" +
            "阿尔及利亚               \n" +
            "Algeria\n" +
            "216\n" +
            "突尼斯                   \n" +
            "Tunisia\n" +
            "218\n" +
            "利比亚                   \n" +
            "Libya\n" +
            "220\n" +
            "冈比亚                   \n" +
            "Gambia\n" +
            "221\n" +
            "塞内加尔                 \n" +
            "Senegal\n" +
            "222\n" +
            "毛里塔尼亚               \n" +
            "Mauritania\n" +
            "223\n" +
            "马里                     \n" +
            "Mali\n" +
            "224\n" +
            "几内亚                   \n" +
            "Guinea\n" +
            "225\n" +
            "科特迪瓦                 \n" +
            "Ivory Coast\n" +
            "226\n" +
            "布基纳法索               \n" +
            "Burkina Faso\n" +
            "227\n" +
            "尼日尔                   \n" +
            "Niger\n" +
            "228\n" +
            "多哥                     \n" +
            "Togo\n" +
            "229\n" +
            "贝宁                     \n" +
            "Benin\n" +
            "230\n" +
            "毛里求斯                 \n" +
            "Mauritius\n" +
            "231\n" +
            "利比里亚                 \n" +
            "Liberia\n" +
            "232\n" +
            "塞拉利                   \n" +
            "Sierra Leone\n" +
            "233\n" +
            "加纳                     \n" +
            "Ghana\n" +
            "234\n" +
            "尼日利亚                 \n" +
            "Nigeria\n" +
            "235\n" +
            "乍得                     \n" +
            "Chad\n" +
            "236\n" +
            "中非                     \n" +
            "Central African Republic\n" +
            "237\n" +
            "喀麦隆                   \n" +
            "Cameroon\n" +
            "238\n" +
            "佛得角                   \n" +
            "Cape Verde\n" +
            "240\n" +
            "赤道几内亚               \n" +
            "Equatorial Guinea\n" +
            "241\n" +
            "加蓬                     \n" +
            "Gabon\n" +
            "242\n" +
            "刚果（布）               \n" +
            "Republic of the Congo\n" +
            "243\n" +
            "刚果（金）               \n" +
            "Democratic Republic of the Congo\n" +
            "244\n" +
            "安哥拉                   \n" +
            "Angola\n" +
            "248\n" +
            "塞舌尔                   \n" +
            "Seychelles\n" +
            "249\n" +
            "苏丹                     \n" +
            "Sudan\n" +
            "250\n" +
            "卢旺达                   \n" +
            "Rwanda\n" +
            "251\n" +
            "埃塞俄比亚               \n" +
            "Ethiopia\n" +
            "252\n" +
            "索马里                   \n" +
            "Somalia\n" +
            "253\n" +
            "吉布提                   \n" +
            "Djibouti\n" +
            "254\n" +
            "肯尼亚                   \n" +
            "Kenya\n" +
            "255\n" +
            "坦桑尼亚                 \n" +
            "Tanzania\n" +
            "256\n" +
            "乌干达                   \n" +
            "Uganda\n" +
            "257\n" +
            "布隆迪                   \n" +
            "Burundi\n" +
            "258\n" +
            "莫桑比克                 \n" +
            "Mozambique\n" +
            "260\n" +
            "赞比亚                   \n" +
            "Zambia\n" +
            "261\n" +
            "马达加斯加               \n" +
            "Madagascar\n" +
            "262\n" +
            "留尼汪                   \n" +
            "Réunion\n" +
            "263\n" +
            "津巴布韦                 \n" +
            "Zimbabwe\n" +
            "264\n" +
            "纳米尼亚                 \n" +
            "Namibia\n" +
            "265\n" +
            "马拉维                   \n" +
            "Malawi\n" +
            "266\n" +
            "莱索托                   \n" +
            "Lesotho\n" +
            "267\n" +
            "博茨瓦纳                 \n" +
            "Botswana\n" +
            "268\n" +
            "斯威士兰                 \n" +
            "Swaziland\n" +
            "269\n" +
            "马约特                   \n" +
            "Comoros\n" +
            "27\n" +
            "南非                     \n" +
            "South Africa\n" +
            "291\n" +
            "厄立特里亚               \n" +
            "Eritrea\n" +
            "297\n" +
            "阿鲁巴                   \n" +
            "Aruba\n" +
            "298\n" +
            "法罗群岛                 \n" +
            "Faroe Islands\n" +
            "299\n" +
            "格陵兰                   \n" +
            "Greenland\n" +
            "30\n" +
            "希腊                     \n" +
            "Greece";

    private static final String s3 = "31\n" +
            "荷兰                     \n" +
            "Netherlands\n" +
            "32\n" +
            "比利时                   \n" +
            "Belgium\n" +
            "33\n" +
            "法国                     \n" +
            "France\n" +
            "34\n" +
            "西班牙                   \n" +
            "Spain\n" +
            "350\n" +
            "直布罗陀                 \n" +
            "Gibraltar\n" +
            "351\n" +
            "葡萄牙                   \n" +
            "Portugal\n" +
            "352\n" +
            "卢森堡                   \n" +
            "Luxembourg\n" +
            "353\n" +
            "爱尔兰                   \n" +
            "Ireland\n" +
            "354\n" +
            "冰岛                     \n" +
            "Iceland\n" +
            "355\n" +
            "阿尔巴尼亚               \n" +
            "Albania\n" +
            "356\n" +
            "马耳他                   \n" +
            "Malta\n" +
            "357\n" +
            "塞浦路斯                 \n" +
            "Cyprus\n" +
            "358\n" +
            "芬兰                     \n" +
            "Finland\n" +
            "359\n" +
            "保加利亚                 \n" +
            "Bulgaria\n" +
            "36\n" +
            "匈牙利                   \n" +
            "Hungary\n" +
            "370\n" +
            "立陶宛                   \n" +
            "Lithuania\n" +
            "371\n" +
            "拉脱维亚                 \n" +
            "Latvia\n" +
            "372\n" +
            "爱沙尼亚                 \n" +
            "Estonia\n" +
            "373\n" +
            "摩尔多瓦                 \n" +
            "Moldova\n" +
            "374\n" +
            "亚美尼亚                 \n" +
            "Armenia\n" +
            "375\n" +
            "白俄罗斯                 \n" +
            "Belarus\n" +
            "376\n" +
            "安道尔                   \n" +
            "Andorra\n" +
            "377\n" +
            "摩纳哥                   \n" +
            "Monaco\n" +
            "378\n" +
            "圣马力诺                 \n" +
            "San Marino\n" +
            "379\n" +
            "梵蒂冈                   \n" +
            "Vatican\n" +
            "380\n" +
            "乌克兰                   \n" +
            "Ukraine\n" +
            "381\n" +
            "塞尔维亚和黑山           \n" +
            "Serbia\n" +
            "382\n" +
            "Montenegro               \n" +
            "Montenegro\n" +
            "385\n" +
            "克罗地亚                 \n" +
            "Croatia\n" +
            "386\n" +
            "斯洛文尼亚               \n" +
            "Slovenia\n" +
            "387\n" +
            "波黑                     \n" +
            "Bosnia and Herzegovina\n" +
            "389\n" +
            "前南马其顿               \n" +
            "Macedonia\n" +
            "39\n" +
            "意大利                   \n" +
            "Italy\n" +
            "40\n" +
            "罗马尼亚                 \n" +
            "Romania\n" +
            "41\n" +
            "瑞士                     \n" +
            "Switzerland\n" +
            "420\n" +
            "捷克                     \n" +
            "Czech Republic\n" +
            "421\n" +
            "斯洛伐克                 \n" +
            "Slovakia\n" +
            "423\n" +
            "列支敦士登               \n" +
            "Liechtenstein\n" +
            "43\n" +
            "奥地利                   \n" +
            "Austria\n" +
            "44\n" +
            "英国                     \n" +
            "Jersey\n" +
            "45\n" +
            "丹麦                     \n" +
            "Denmark\n" +
            "46\n" +
            "瑞典                     \n" +
            "Sweden\n" +
            "47\n" +
            "斯瓦尔巴岛和扬马延岛     \n" +
            "Norway\n" +
            "48\n" +
            "波兰                     \n" +
            "Poland\n" +
            "49\n" +
            "德国                     \n" +
            "Germany";
    private static final String s4 = "501\n" +
            "伯利兹                   \n" +
            "Belize\n" +
            "503\n" +
            "萨尔瓦多                 \n" +
            "El Salvador\n" +
            "504\n" +
            "洪都拉斯                 \n" +
            "Honduras\n" +
            "505\n" +
            "尼加拉瓜                 \n" +
            "Nicaragua\n" +
            "506\n" +
            "哥斯达黎加               \n" +
            "Costa Rica\n" +
            "507\n" +
            "巴拿马                   \n" +
            "Panama\n" +
            "508\n" +
            "圣皮埃尔和密克隆         \n" +
            "Saint Pierre and Miquelon\n" +
            "509\n" +
            "海地                     \n" +
            "Haiti\n" +
            "590\n" +
            "瓜德罗普                 \n" +
            "Guadeloupe\n" +
            "592\n" +
            "圭亚那                   \n" +
            "Guyana\n" +
            "596\n" +
            "马提尼克                 \n" +
            "Martinique\n" +
            "61\n" +
            "澳大利亚 \n" +
            "Australia\n" +
            "65\n" +
            "新加坡                   \n" +
            "Singapore\n" +
            "673\n" +
            "文莱                     \n" +
            "Brunei\n" +
            "676\n" +
            "汤加                     \n" +
            "Tonga\n" +
            "679\n" +
            "斐济                     \n" +
            "Fiji\n" +
            "680\n" +
            "帕劳                     \n" +
            "Palau\n" +
            "681\n" +
            "瓦利斯和富图纳           \n" +
            "Wallis and Futuna\n" +
            "685\n" +
            "萨摩亚                   \n" +
            "Samoa\n" +
            "687\n" +
            "新喀里多尼亚             \n" +
            "New Caledonia\n" +
            "688\n" +
            "图瓦卢                   \n" +
            "Tuvalu\n" +
            "689\n" +
            "法属波利尼西亚           \n" +
            "French Polynesia\n" +
            "691\n" +
            "密克罗尼西亚             \n" +
            "Micronesia\n" +
            "692\n" +
            "马绍尔群岛               \n" +
            "Marshall Islands\n" +
            "7\n" +
            "俄罗斯                   \n" +
            "Russia\n" +
            "850\n" +
            "朝鲜                     \n" +
            "North Korea\n" +
            "855\n" +
            "柬埔寨                   \n" +
            "Cambodia\n" +
            "856\n" +
            "老挝                     \n" +
            "Laos\n" +
            "880\n" +
            "孟加拉国                 \n" +
            "Bangladesh\n" +
            "90\n" +
            "土耳其                   \n" +
            "Turkey\n" +
            "91\n" +
            "印度                     \n" +
            "India\n" +
            "92\n" +
            "巴基斯坦                 \n" +
            "Pakistan\n" +
            "93\n" +
            "阿富汗                   \n" +
            "Afghanistan\n" +
            "94\n" +
            "斯里兰卡                 \n" +
            "Sri Lanka\n" +
            "960\n" +
            "马尔代夫                 \n" +
            "Maldives\n" +
            "961\n" +
            "黎巴嫩                   \n" +
            "Lebanon\n" +
            "962\n" +
            "约旦                     \n" +
            "Jordan\n" +
            "963\n" +
            "叙利亚                   \n" +
            "Syria\n" +
            "964\n" +
            "伊拉克                   \n" +
            "Iraq\n" +
            "965\n" +
            "科威特                   \n" +
            "Kuwait\n" +
            "966\n" +
            "沙特阿拉伯               \n" +
            "Saudi Arabia\n" +
            "967\n" +
            "也门                     \n" +
            "Yemen\n" +
            "968\n" +
            "阿曼                     \n" +
            "Oman\n" +
            "971\n" +
            "阿拉伯联合酋长国         \n" +
            "United Arab Emirates\n" +
            "972\n" +
            "以色列                   \n" +
            "Israel\n" +
            "973\n" +
            "巴林                     \n" +
            "Bahrain\n" +
            "974\n" +
            "卡塔尔                   \n" +
            "Qatar\n" +
            "975\n" +
            "不丹                     \n" +
            "Bhutan\n" +
            "976\n" +
            "蒙古                     \n" +
            "Mongolia\n" +
            "977\n" +
            "尼泊尔                   \n" +
            "Nepal\n" +
            "98\n" +
            "伊朗                     \n" +
            "Iran\n" +
            "992\n" +
            "塔吉克斯坦               \n" +
            "Tajikistan\n" +
            "993\n" +
            "土库曼斯坦               \n" +
            "Turkmenistan\n" +
            "994\n" +
            "阿塞拜疆                 \n" +
            "Azerbaijan\n" +
            "995\n" +
            "格鲁吉亚                 \n" +
            "Georgia\n" +
            "996\n" +
            "吉尔吉斯斯坦             \n" +
            "Kyrgyzstan\n" +
            "998\n" +
            "乌兹别克斯坦             \n" +
            "Uzbekistan";

    private static final String s5 = "1\n" +
            "美国           \n" +
            "United States\n" +
            "1787\n" +
            "波多黎各       \n" +
            "Puerto Rico\n" +
            "1809\n" +
            "多米尼加       \n" +
            "Dominica\n" +
            "1829\n" +
            "多米尼加       \n" +
            "Dominica\n" +
            "1849\n" +
            "多米尼加       \n" +
            "Dominica\n" +
            "502\n" +
            "危地马拉       \n" +
            "Guatemala\n" +
            "51\n" +
            "秘鲁           \n" +
            "Peru\n" +
            "52\n" +
            "墨西哥         \n" +
            "Mexico\n" +
            "54\n" +
            "阿根廷         \n" +
            "Argentina\n" +
            "55\n" +
            "巴西           \n" +
            "Brazil\n" +
            "56\n" +
            "智利           \n" +
            "Chile\n" +
            "57\n" +
            "哥伦比亚       \n" +
            "Colombia\n" +
            "58\n" +
            "委内瑞拉       \n" +
            "Venezuela\n" +
            "591\n" +
            "玻利维亚       \n" +
            "Bolivia\n" +
            "593\n" +
            "厄瓜多尔       \n" +
            "Ecuador\n" +
            "595\n" +
            "巴拉圭         \n" +
            "Paraguay\n" +
            "597\n" +
            "苏里南         \n" +
            "Suriname\n" +
            "598\n" +
            "乌拉圭         \n" +
            "Uruguay\n" +
            "5999\n" +
            "库腊索         \n" +
            "Curacao\n" +
            "60\n" +
            "马来西亚       \n" +
            "Malaysia\n" +
            "62\n" +
            "印度尼西亚     \n" +
            "Indonesia\n" +
            "63\n" +
            "菲律宾         \n" +
            "Philippines\n" +
            "64\n" +
            "新西兰         \n" +
            "New Zealand\n" +
            "66\n" +
            "泰国           \n" +
            "Thailand\n" +
            "81\n" +
            "日本           \n" +
            "Japan\n" +
            "82\n" +
            "韩国           \n" +
            "South Korea\n" +
            "84\n" +
            "越南           \n" +
            "Vietnam\n" +
            "852\n" +
            "香港           \n" +
            "Hong Kong\n" +
            "853\n" +
            "澳门           \n" +
            "Macao\n" +
            "886\n" +
            "台湾           \n" +
            "Taiwan\n" +
            "95\n" +
            "缅甸           \n" +
            "Myanmar [Burma]";

    private static final String s6 = "211\n" +
            "南苏丹\n" +
            "South Sudan\n" +
            "239\n" +
            "圣多美和普林西比\n" +
            "Sao Tome and Principe\n" +
            "245\n" +
            "几内亚比绍\n" +
            "Guinea-Bissau\n" +
            "246\n" +
            "英属印度洋领地\n" +
            "British Indian Ocean Territory\n" +
            "500\n" +
            "福克兰群岛\n" +
            "Falkland Islands\n" +
            "594\n" +
            "法属圭亚那\n" +
            "French Guiana\n" +
            "670\n" +
            "东帝汶\n" +
            "East Timor\n" +
            "672\n" +
            "诺福克岛\n" +
            "Norfolk Island\n" +
            "674\n" +
            "瑙鲁\n" +
            "Nauru\n" +
            "675\n" +
            "巴布亚新几内亚\n" +
            "Papua New Guinea\n" +
            "677\n" +
            "所罗门群岛\n" +
            "Solomon Islands\n" +
            "678\n" +
            "瓦努阿图\n" +
            "Vanuatu\n" +
            "682\n" +
            "库克群岛\n" +
            "Island\n" +
            "683\n" +
            "纽埃\n" +
            "Niue\n" +
            "686\n" +
            "基里巴斯\n" +
            "Kiribati\n" +
            "690\n" +
            "托克劳\n" +
            "Tokelau\n" +
            "970\n" +
            "巴勒斯坦\n" +
            "Palestine\n" +
            "1721\n" +
            "荷属圣马丁\n" +
            "Sint Maarten\n" +
            "4779\n" +
            "斯瓦尔巴和扬马延\n" +
            "Svalbard and Jan Mayen\n" +
            "35818\n" +
            "奥兰\n" +
            "Oran";

    private static final String s7 = "86\n" +
            "中国大陆\n" +
            "China";
}

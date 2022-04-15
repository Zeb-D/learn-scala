package com.yd.test;

import lombok.Data;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

public enum SiteEnum {
    China(86, "中国", "China", "AY"),
    United_States_Canada(1, "美国/加拿大", "United States/Canada", "AZ"),
    Afghanistan(93, "阿富汗", "Afghanistan", "EU"),
    Albania(355, "阿尔巴尼亚", "Albania", "EU"),
    Algeria(213, "阿尔及利亚", "Algeria", "EU"),
    Angola(244, "安哥拉", "Angola", "EU"),
    Argentina(54, "阿根廷", "Argentina", "AZ"),
    Armenia(374, "亚美尼亚", "Armenia", "EU"),
    Australia(61, "澳大利亚", "Australia", "EU"),
    Austria(43, "奥地利", "Austria", "EU"),
    Azerbaijan(994, "阿塞拜疆", "Azerbaijan", "EU"),
    Bahrain(973, "巴林", "Bahrain", "EU"),
    Bangladesh(880, "孟加拉国", "Bangladesh", "EU"),
    Belarus(375, "白俄罗斯", "Belarus", "EU"),
    Belgium(32, "比利时", "Belgium", "EU"),
    Belize(501, "伯利兹", "Belize", "EU"),
    Benin(229, "贝宁", "Benin", "EU"),
    Bhutan(975, "不丹", "Bhutan", "EU"),
    Bolivia(591, "玻利维亚", "Bolivia", "AZ"),
    Bosnia_and_Herzegovina(387, "波斯尼亚和黑塞哥维那", "Bosnia and Herzegovina", "EU"),
    Botswana(267, "博茨瓦纳", "Botswana", "EU"),
    Brazil(55, "巴西", "Brazil", "AZ"),
    Virgin_Islands__US(1284, "英属维京群岛", "Virgin Islands, US", "EU"),
    Brunei(673, "文莱", "Brunei", "EU"),
    Bulgaria(359, "保加利亚", "Bulgaria", "EU"),
    Burkina_Faso(226, "布基纳法索", "Burkina Faso", "EU"),
    Burundi(257, "布隆迪", "Burundi", "EU"),
    Cambodia(855, "柬埔寨", "Cambodia", "EU"),
    Cameroon(237, "喀麦隆", "Cameroon", "EU"),
    Cape_Verde(238, "佛得角", "Cape Verde", "EU"),
    Cayman_Islands(1345, "开曼群岛", "Cayman Islands", "EU"),
    Central_African_Republic(236, "中非共和国", "Central African Republic", "EU"),
    Chad(235, "乍得", "Chad", "EU"),
    Chile(56, "智利", "Chile", "AZ"),
    Colombia(57, "哥伦比亚", "Colombia", "AZ"),
    Comoros(269, "科摩罗", "Comoros", "EU"),
    Republic_Of_The_Congo(242, "刚果", "Republic Of The Congo", "EU"),
    Democratic_Republic_of_the_Congo(243, "刚果", "Democratic Republic of the Congo", "EU"),
    Costa_Rica(506, "哥斯达黎加", "Costa Rica", "EU"),
    Croatia(385, "克罗地亚", "Croatia", "EU"),
    Cyprus(357, "塞浦路斯", "Cyprus", "EU"),
    Czech_Republic(420, "捷克共和国", "Czech Republic", "EU"),
    Denmark(45, "丹麦", "Denmark", "EU"),
    Djibouti(253, "吉布提", "Djibouti", "EU"),
    Dominican_Republic(1809, "多米尼加共和国", "Dominican Republic", "AZ"),
    Ecuador(593, "厄瓜多尔", "Ecuador", "AZ"),
    Egypt(20, "埃及", "Egypt", "EU"),
    El_Salvador(503, "萨尔瓦多", "El Salvador", "EU"),
    Equatorial_Guinea(240, "赤道几内亚", "Equatorial Guinea", "EU"),
    Estonia(372, "爱沙尼亚", "Estonia", "EU"),
    Ethiopia(251, "埃塞俄比亚", "Ethiopia", "EU"),
    Fiji(679, "斐济", "Fiji", "EU"),
    Finland(358, "芬兰", "Finland", "EU"),
    France(33, "法国", "France", "EU"),
    Gabon(241, "加蓬", "Gabon", "EU"),
    Gambia(220, "冈比亚", "Gambia", "EU"),
    Georgia(995, "格鲁吉亚", "Georgia", "EU"),
    Germany(49, "德国", "Germany", "EU"),
    Ghana(233, "加纳", "Ghana", "EU"),
    Greece(30, "希腊", "Greece", "EU"),
    Greenland(299, "格陵兰", "Greenland", "EU"),
    Guatemala(502, "危地马拉", "Guatemala", "AZ"),
    Guinea(224, "几内亚", "Guinea", "EU"),
    Guyana(592, "圭亚那", "Guyana", "EU"),
    Haiti(509, "海地", "Haiti", "EU"),
    Honduras(504, "洪都拉斯", "Honduras", "EU"),
    Hong_Kong(852, "中国香港特别行政区", "Hong Kong", "AZ"),
    Hungary(36, "匈牙利", "Hungary", "EU"),
    Iceland(354, "冰岛", "Iceland", "EU"),
    India(91, "印度", "India", "EU"),
    Indonesia(62, "印度尼西亚", "Indonesia", "AZ"),
    Iraq(964, "伊拉克", "Iraq", "EU"),
    Ireland(353, "爱尔兰", "Ireland", "EU"),
    Israel(972, "以色列", "Israel", "EU"),
    Italy(39, "意大利", "Italy", "AZ"),
    Ivory_Coast(225, "科特迪瓦", "Ivory Coast", "EU"),
    Jamaica(1876, "牙买加", "Jamaica", "EU"),
    Japan(81, "日本", "Japan", "AZ"),
    Jordan(962, "约旦", "Jordan", "EU"),
    Kenya(254, "肯尼亚", "Kenya", "EU"),
    South_Korea(82, "韩国", "South Korea", "AZ"),
    Kuwait(965, "科威特", "Kuwait", "EU"),
    Kyrgyzstan(996, "吉尔吉斯斯坦", "Kyrgyzstan", "EU"),
    Laos(856, "老挝", "Laos", "EU"),
    Latvia(371, "拉脱维亚", "Latvia", "EU"),
    Lebanon(961, "黎巴嫩", "Lebanon", "EU"),
    Lesotho(266, "莱索托", "Lesotho", "EU"),
    Liberia(231, "利比里亚", "Liberia", "EU"),
    Libya(218, "利比亚", "Libya", "EU"),
    Lithuania(370, "立陶宛", "Lithuania", "EU"),
    Luxembourg(352, "卢森堡", "Luxembourg", "EU"),
    Macau(853, "中国澳门特别行政区", "Macau", "AZ"),
    Macedonia(389, "马其顿", "Macedonia", "EU"),
    Madagascar(261, "马达加斯加", "Madagascar", "EU"),
    Malawi(265, "马拉维", "Malawi", "EU"),
    Malaysia(60, "马来西亚", "Malaysia", "AZ"),
    Maldives(960, "马尔代夫", "Maldives", "EU"),
    Mali(223, "马里", "Mali", "EU"),
    Malta(356, "马耳他", "Malta", "EU"),
    Mauritania(222, "毛利塔尼亚", "Mauritania", "EU"),
    Mauritius(230, "毛里求斯", "Mauritius", "EU"),
    Mexico(52, "墨西哥", "Mexico", "AZ"),
    Moldova(373, "摩尔多瓦", "Moldova", "EU"),
    Monaco(377, "摩纳哥", "Monaco", "EU"),
    Mongolia(976, "蒙古", "Mongolia", "EU"),
    Montenegro(382, "黑山共和国", "Montenegro", "EU"),
    Morocco(212, "摩洛哥", "Morocco", "EU"),
    Mozambique(258, "莫桑比克", "Mozambique", "EU"),
    Myanmar(95, "缅甸", "Myanmar", "AZ"),
    Namibia(264, "纳米比亚", "Namibia", "EU"),
    Nepal(977, "尼泊尔", "Nepal", "EU"),
    Netherlands(31, "荷兰", "Netherlands", "EU"),
    New_Zealand(64, "新西兰", "New Zealand", "AZ"),
    Nicaragua(505, "尼加拉瓜", "Nicaragua", "AZ"),
    Niger(227, "尼日尔", "Niger", "EU"),
    Nigeria(234, "尼日利亚", "Nigeria", "EU"),
    Norway(47, "挪威", "Norway", "EU"),
    Oman(968, "阿曼", "Oman", "EU"),
    Pakistan(92, "巴基斯坦", "Pakistan", "EU"),
    Panama(507, "巴拿马", "Panama", "EU"),
    Paraguay(595, "巴拉圭", "Paraguay", "AZ"),
    Peru(51, "秘鲁", "Peru", "AZ"),
    Philippines(63, "菲律宾", "Philippines", "AZ"),
    Poland(48, "波兰", "Poland", "EU"),
    Portugal(351, "葡萄牙", "Portugal", "EU"),
    Puerto_Rico(1787, "波多黎各", "Puerto Rico", "AZ"),
    Qatar(974, "卡塔尔", "Qatar", "EU"),
    Réunion_Island(262, "留尼旺", "Réunion Island", "EU"),
    Romania(40, "罗马尼亚", "Romania", "EU"),
    Russia_Kazakhstan(7, "俄罗斯/哈萨克斯坦", "Russia/Kazakhstan", "EU"),
    Rwanda(250, "卢旺达", "Rwanda", "EU"),
    San_Marino(378, "圣马力诺", "San Marino", "EU"),
    Saudi_Arabia(966, "沙特阿拉伯", "Saudi Arabia", "EU"),
    Senegal(221, "塞内加尔", "Senegal", "EU"),
    Serbia(381, "塞尔维亚", "Serbia", "EU"),
    Sierra_Leone(232, "塞拉利昂", "Sierra Leone", "EU"),
    Singapore(65, "新加坡", "Singapore", "AZ"),
    Slovakia(421, "斯洛伐克", "Slovakia", "EU"),
    Slovenia(386, "斯洛文尼亚", "Slovenia", "EU"),
    Somalia(252, "索马里", "Somalia", "EU"),
    South_Africa(27, "南非", "South Africa", "EU"),
    Spain(34, "西班牙", "Spain", "EU"),
    Sri_Lanka(94, "斯里兰卡", "Sri Lanka", "EU"),
    Sudan(249, "苏丹", "Sudan", "EU"),
    Suriname(597, "苏里南", "Suriname", "AZ"),
    Swaziland(268, "斯威士兰", "Swaziland", "EU"),
    Sweden(46, "瑞典", "Sweden", "EU"),
    Switzerland(41, "瑞士", "Switzerland", "EU"),
    Taiwan(886, "中国台湾", "Taiwan", "AZ"),
    Tajikistan(992, "塔吉克斯坦", "Tajikistan", "EU"),
    Tanzania(255, "坦桑尼亚", "Tanzania", "EU"),
    Thailand(66, "泰国", "Thailand", "AZ"),
    Togo(228, "多哥", "Togo", "EU"),
    Tonga(676, "汤加", "Tonga", "EU"),
    Trinidad_and_Tobago(1868, "特立尼达和多巴哥", "Trinidad and Tobago", "EU"),
    Tunisia(216, "突尼斯", "Tunisia", "EU"),
    Turkey(90, "土耳其", "Turkey", "EU"),
    Turkmenistan(993, "土库曼斯坦", "Turkmenistan", "EU"),
    Virgin_Islands__British(1340, "美属维尔京群岛", "Virgin Islands, British", "EU"),
    Uganda(256, "乌干达", "Uganda", "EU"),
    Ukraine(380, "乌克兰", "Ukraine", "EU"),
    United_Arab_Emirates(971, "阿拉伯联合酋长国", "United Arab Emirates", "EU"),
    United_Kingdom(44, "英国", "United Kingdom", "EU"),
    Uruguay(598, "乌拉圭", "Uruguay", "AZ"),
    Uzbekistan(998, "乌兹别克斯坦", "Uzbekistan", "EU"),
    Venezuela(58, "委内瑞拉", "Venezuela", "AZ"),
    Vietnam(84, "越南", "Vietnam", "AZ"),
    Yemen(967, "也门", "Yemen", "EU"),
    Zambia(260, "赞比亚", "Zambia", "EU"),
    Zimbabwe(263, "津巴布韦", "Zimbabwe", "EU"),

    ;

    SiteEnum(Integer code, String zhName, String enName, String region) {
        this.code = code;
        this.zhName = zhName;
        this.enName = enName;
        this.region = region;
    }

    @Getter
    private Integer code;
    private String zhName;
    private String enName;
    private String region;

    public static final Map<String, List<Country>> COUNTRY_LIST = listCountry();

    public static final List<Country> listBy(String region) {
        return COUNTRY_LIST.getOrDefault(region, Collections.emptyList());
    }

    public static Map<String, List<Country>> listCountry() {
        return Arrays.stream(SiteEnum.values()).map(s -> {
            Country c = new Country();
            c.code = s.code;
            c.zhName = s.zhName;
            c.enName = s.enName;
            c.region = s.region;
            return c;
        }).collect(Collectors.groupingBy(Country::getRegion));
    }


    public static String getSite(String code) {
        return Optional.ofNullable(getSiteEnum(code))
            .map(SiteEnum::getCode).map(String::valueOf)
            .orElse("");
    }

    public static SiteEnum getSiteEnum(String code) {
        if (StringUtils.isBlank(code)) {
            return SiteEnum.China;
        }
        for (SiteEnum siteEnum : SiteEnum.values()) {
            if (siteEnum.code.equals(code)) {
                return siteEnum;
            }
        }
        return null;
    }

    @Data
    public static class Country {
        public Integer code;
        public String zhName;
        public String enName;
        public String region;
    }
}

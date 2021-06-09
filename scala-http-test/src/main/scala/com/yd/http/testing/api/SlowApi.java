package com.yd.http.testing.api;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.util.*;

/**
 * 慢接口？
 *
 * @author created by Zeb灬D on 2020-02-15 17:13
 */
public class SlowApi {
    // cn
    private static String cnSlowApi = "POST:/v1.0/homes/{home_id}/automations/{automation_id}/conditions/trigger,GET:/v1.0/infrareds/{infraredId}/operators/{operatorId}/brands,DELETE:/v1.0/devices/{devId}/timers,GET:/v1.0/devices,GET:/v1.0/homes/{home_id}/automations,GET:/v1.0/position/weather,GET:/v1.0/apps/{schema}/users-live-datas,POST:/v1.0/devices/{deviceId}/commands,GET:/v1.0/infrareds/{infraredId}/operators/{operatorId}/brands/{brandId}/iptvs,POST:/v1.0/infrareds/{infraredId}/remotes/{remoteId}/learning-codes,POST:/v1.0/homes/{home_id}/scenes/{scene_id}/trigger,POST:/v1.0/open-iot-hub/access/config,GET:/v1.0/homes/{home_id}/devices,PUT:/v1.0/devices/{devId}/timers/groups/{groupId},GET:/v1.0/users/{uid}/devices,GET:/v1.0/devices/{deviceId}/specifications,GET:/v1.0/devices/{devId}/timers/categories/{category},POST:/v1.0/home/create-home,GET:/v1.0/ip/countries,GET:/v1.0/infrareds/{infraredId}/areas/{areaId}/iptvs,GET:/v1.0/devices/{devId}/video-streaming,GET:/v1.0/devices/{gwId}/statistics/total,POST:/v1.0/developer/sign-check,GET:/v1.0/devices/{deviceId}/functions,GET:/v1.0/infrareds/{infraredId}/categories/{categoryId}/brands,GET:/v1.0/skills/devices/{deviceId}/status,PUT:/v1.0/homes/{home_id},GET:/v1.0/homes/{home_id}/rooms,GET:/v1.0/homes/{home_id}/automations/{automation_id},GET:/v1.0/apps/{schema}/devices-survey,POST:/v1.0/infrareds/{infraredId}/send-keys,GET:/v1.0/devices/{deviceId},GET:/v1.0/devices/{deviceId}/status,PUT:/v1.0/devices/{device_id},POST:/v1.0/homes/{home_id}/automations,POST:/v1.0/user/auth,POST:/v1.0/apps/{schema}/user,GET:/v1.0/devices/{deviceId}/sub-devices,GET:/v1.0/users/{uid}/homes,GET:/v1.0/devices/{deviceId}/list-sub,GET:/v1.0/devices/tokens/{token},GET:/v1.0/apps/{schema}/users,GET:/v1.0/skills/devices/{deviceId}/skills,GET:/v1.0/devices/status,GET:/v1.0/devices/{devId}/timers,GET:/v1.0/devices/{gwId}/statistics/days,DELETE:/v1.0/homes/{home_id},POST:/v1.0/devices/{devId}/timers,GET:/v1.0/ip/weather-forecast,GET:/v1.0/devices/{device_id}/multiple-names,GET:/v1.0/infrareds/{infraredId}/remotes,DELETE:/v1.0/homes/{home_id}/scenes/{scene_id},GET:/v1.0/token,DELETE:/v1.0/devices/{deviceId},GET:/v1.0/infrareds/{infraredId}/categories/{categoryId}/remotes/{remoteIndex}/codes,POST:/v1.0/infrareds/{infraredId}/box/add-remote,GET:/v1.0/token/{refreshToken},POST:/v1.0/infrareds/{infrared_id}/ac/send-keys,POST:/v1.0/devices/token,GET:/v1.0/homes/{home_id}/rooms/{room_id}/devices,GET:/v1.0/devices/{device_id}/logs,GET:/v1.0/infrareds/{infraredId}/provinces/{provinceId}/cities/{cityId}/areas,POST:/v1.0/infrareds/{infraredId}/add-remote,GET:/v1.0/users/{uid}/infos,DELETE:/v1.0/devices/{device_id}/door-lock/temp-passwords/{password_id},GET:/v1.0/countries/{countryCode}/cities,GET:/v1.0/user/devices,POST:/v1.0/apps/{schema}/user-check,PUT:/v1.0/devices/{deviceId}/enabled-sub-discovery,POST:/v1.0/infrareds/{infraredId}/normal/add-remote,GET:/v1.0/auth/skill-infos,POST:/v1.0/user/password-login,POST:/v1.0/users/{uid}/devices/{deviceId}/commands,GET:/v1.0/homes/{home_id}/scenes,GET:/v1.0/infrareds/{infrared_id}/{remote_id}/channel,DELETE:/v1.0/devices/{devId}/timers/categories/{category}/groups/{groupId},GET:/v1.0/cities/{cityId}/weathers,GET:/v1.0/skills/users/{uid}/discovery,GET:/v1.0/homes/{home_id},POST:/v1.0/skills/scenes/{scene_id}/trigger,GET:/v1.0/cities/{cityId}/weather-forecast,POST:/v1.0/skills/devices/{deviceId}/commands,DELETE:/v1.0/devices/{devId}/timers/categories/{category},GET:/v1.0/infrareds/{infraredId}/categories/{categoryId}/brands/{brandId},POST:/v1.0/user/verify-code,GET:/v1.0/oauth/languages,GET:/v1.0/users/{uid}/devices/{deviceId},PUT:/v1.0/homes/{home_id}/scenes/{scene_id},POST:/v1.0/homes/{home_id}/scenes,GET:/v1.0/developer/products,DELETE:/v1.0/homes/{home_id}/rooms/{room_id},GET:/v1.0/platform-address,GET:/v1.0/users/{uid}/devices/{deviceId}/status,POST:/v1.0/feedbacks/{dialog_id},GET:/v1.0/devices/{device_id}/upgrade-infos";
    // eu
    private static String euSlowApi = "GET:/v1.0/cities/{cityId}/weather-forecast,GET:/v1.0/position/weather,GET:/v1.0/cities/{cityId}/weathers,GET:/v1.0/infrareds/{infraredId}/remotes/{remoteId}/ac/status,GET:/v1.0/devices/live-datas,POST:/v1.0/infrareds/{infrared_id}/matching-remotes,GET:/v1.0/devices/{deviceId}/status,GET:/v1.0/users/{uid}/homes,POST:/v1.0/oauth/user/register,GET:/v1.0/token/{refreshToken},GET:/v1.0/devices/tokens/{token},POST:/v1.0/devices/{deviceId}/commands,GET:/v1.0/user/infos,GET:/v1.0/devices/{devId}/timers,POST:/v1.0/infrareds/{infraredId}/send-keys,GET:/v1.0/devices/{gwId}/statistics/days,DELETE:/v1.0/devices/{deviceId},GET:/v1.0/devices,GET:/v1.0/devices/{device_id}/logs,GET:/v1.0/token";
    // us
    private static String usSlowApi = "GET:/v1.0/auth/skill-infos,GET:/v1.0/position/weather,GET:/v1.0/cities/{cityId}/weather-forecast,GET:/v1.0/user/infos,GET:/v1.0/cities/{cityId}/weathers,GET:/v1.0/countries/{countryCode}/cities,GET:/v1.0/ip/weather-forecast,GET:/v1.0/token,GET:/v1.0/devices/{deviceId},POST:/v1.0/devices/{deviceId}/commands,POST:/v1.0/open-iot-hub/access/config,POST:/v1.0/devices/token,GET:/v1.0/users/{uid}/devices,POST:/v1.0/user/verify-code,GET:/v1.0/users/{uid}/devices/{deviceId},GET:/v1.0/devices/active-datas,GET:/v1.0/token/{refreshToken},GET:/v1.0/devices/{device_id}/logs,GET:/v1.0/devices,POST:/v1.0/device-groups/{device_group_id}/issued";
    private static String cnSlowApis = "POST:/v1.0/homes/{home_id}/automations/{automation_id}/conditions/trigger#3227.667#30;POST:/v1.0/infrareds/{infrared_id}/matching-remotes#1769.912#102;PUT:/v1.0/devices/{device_id}/door-lock/temp-passwords/{password_id}/modify-password#791.1#10;GET:/v1.0/devices/{gwId}/statistics/quarters#733#4;GET:/v1.0/devices#714.007#21504;DELETE:/v1.0/devices/{devId}/timers#628.527#510;POST:/v1/devices/deviceInfo#568#1;GET:/v1.0/devices/{device_id}/door-lock/temp-password/{password_id}#557.8#10;GET:/v1.0/devices/{device_id}/multiple-names#513.047#1106;GET:/v1.0/user/devices#501.178#73;GET:/v1.0/homes/{home_id}/automations#481#7;POST:/v1.0/user/auth#467.683#41;POST:/v1.0/encryptions/rsa/public-key/actions/generate#441.571#21;POST:/v1/devices/deviceStatus#428.5#2;GET:/v1.0/token/{refreshToken}#423.489#88;POST:/v1.0/devices/{device_id}/door-lock/temp-password#418.25#4;GET:/v1.0/cities/{cityId}/weathers#416.5#4;GET:/v1.0/homes/{home_id}/devices#394.956#7727;POST:/v1.0/user/password-login#386.571#28;PUT:/v1.0/devices/{devId}/timers/groups/{groupId}#380.031#262;GET:/v1.0/infrareds/{infraredId}/remotes#369.15#60;DELETE:/v1.0/devices/{deviceId}#364.385#26;POST:/v1.0/devices/token#349.941#17;GET:/v1.0/ip/weather-forecast#347.8#5;GET:/v1.0/infrareds/{infraredId}/categories/{categoryId}/remotes/{remoteIndex}/codes#347.667#9;POST:/v1.0/open-iot-hub/access/config#347.5#4;POST:/v1.0/users/{uid}/ticket#344#2;GET:/v1.0/users/{uid}/devices#343.804#28123;GET:/v1.0/devices/{devId}/timers#341.373#590;GET:/v1.0/countries/{countryCode}/cities#337#10;GET:/v1.0/access/{device_id}/config#335.167#6;POST:/v1.0/homes/{home_id}/automations#328.457#35;POST:/v1.0/skills/scenes/{scene_id}/trigger#326#1;GET:/v1.0/infrareds/{infraredId}/categories/{categoryId}/brands/{brandId}#323#1;GET:/v1.0/infrareds/{infraredId}/remotes/{remoteId}/ac/status#322.741#27;GET:/v1.0/skills/devices/{deviceId}/status#321#18;POST:/v1.0/infrareds/{infraredId}/send-keys#316.375#64;GET:/v1.0/users/{uid}/homes#315.929#141;GET:/v1.0/devices/{deviceId}/list-sub#311.917#12;POST:/v1/user/login#311#1;GET:/v1.0/devices/tokens/{token}#308.703#74;GET:/v1.0/devices/{devId}/video-streaming#304.385#13;GET:/v1.0/homes/{home_id}/rooms#304.019#54;GET:/v1.0/apps/{schema}/users#303.432#118;POST:/v1.0/homes/{home_id}/room#301.5#6;GET:/v1.0/auth/skill-infos#299#1;GET:/v1.0/countries-search#297#1;GET:/v1.0/devices/{deviceId}#296.006#1803;GET:/v1.0/position/city#296#1;GET:/v1.0/skills/users/{uid}/discovery#292.876#121;GET:/v1.0/homes/{home_id}/automations/{automation_id}#292.269#26;GET:/v1.0/devices/{device_id}/door-lock/temp-passwords#290.333#6;GET:/v1.0/users/{uid}/infos#286#1;POST:/v1.0/apps/{schema}/user#281.6#2981;GET:/v1.0/devices/{deviceId}/functions#277.488#84;DELETE:/v1.0/homes/{home_id}/members/{uid}#275.333#6;POST:/v1.0/oauth/qrcode-token#273#1;GET:/v1.0/devices/{gwId}/statistics/days#272.643#56;GET:/v1.0/devices/{gwId}/statistics/months#271.254#4437;GET:/v1.0/infrareds/{infraredId}/provinces/{provinceId}/cities/{cityId}/areas#270.538#13;POST:/v1.0/devices/{devId}/timers#268.17#766;GET:/v1.0/token#266.511#1109;GET:/v1.0/oauth/qrcode-token#265.333#3;POST:/v1.0/users/{uid}/devices/{deviceId}/commands#265.286#7;POST:/v1.0/home/create-home#264.857#42;GET:/v1.0/cities/{cityId}/weather-forecast#262.8#5;POST:/v1.0/user/wx-applet/login#262.313#16;GET:/v1.0/infrareds/{infraredId}/provinces/{provinceId}/cities#262#1;GET:/v1.0/homes/{home_id}/rooms/{room_id}/devices#261.692#104;POST:/v1.0/infrareds/{infrared_id}/remotes/{remote_id}/send-keys#259.667#3;GET:/v1.0/devices/{devId}/timers/categories/{category}#257#1;GET:/v1.0/devices/{device_id}/logs#256.028#727;GET:/v1.0/devices/{deviceId}/sub-devices#254.571#49;GET:/v1.0/devices/{deviceId}/status#252.357#42;GET:/v1.0/infrareds/{infraredId}/categories#250.5#2;PUT:/v1.0/homes/{home_id}#246#5;GET:/v1.0/infrareds/{infrared_id}/{remote_id}/channel#245.2#15;POST:/v1.0/devices/{deviceId}/commands#240.139#36;PUT:/v1.0/devices/{device_id}#238.444#36;GET:/v1.0/user/infos#235#1;GET:/v1.0/position/weather#233.5#2;GET:/v1.0/infrareds/{infraredId}/areas/{areaId}/iptvs#231.333#12;DELETE:/v1.0/homes/{home_id}/scenes/{scene_id}#230.167#24;GET:/v1.0/infrareds/{infraredId}/operators/{operatorId}/areas/{areaId}#227#1;POST:/v1.0/infrareds/{infraredId}/box/add-remote#216.389#54;POST:/v1.0/infrareds/{infraredId}/normal/add-remote#209.138#152;POST:/v1.0/infrareds/{infraredId}/add-remote#209#2;POST:/v1.0/infrareds/{infraredId}/learning-codes#208#2;POST:/v1.0/infrareds/{infraredId}/timers#205.986#69;GET:/v1.0/homes/{home_id}#205#3;DELETE:/v1.0/infrareds/{infraredId}/remotes/{remoteId}#204#2;POST:/v1.0/homes/{home_id}/scenes#199.972#504;POST:/v1.0/skills/devices/{deviceId}/commands#199.333#12;GET:/v1.0/devices/{gwId}/statistics/total#198.727#22;PUT:/v1.0/homes/{home_id}/scenes/{scene_id}#198.084#509;POST:/v1.0/user/verify-code#197.857#14;GET:/v1.0/devices/locations-active-datas#197.667#3;POST:/v1.0/infrareds/{infrared_id}/ac/send-keys#196.652#23;GET:/v1.0/developer/products#196.5#2;POST:/v1.0/apps/{schema}/user-check#196.316#38;GET:/v1.0/skills/devices/{deviceId}/skills#196#1;DELETE:/v1.0/devices/{devId}/timers/categories/{category}/groups/{groupId}#195.972#252;DELETE:/v1.0/infrareds/{infraredId}/learning-codes/{learn_id}#195#1;DELETE:/v1.0/devices/{devId}/timers/categories/{category}#194.076#250;GET:/v1.0/devices/status#193.252#6269;PUT:/v1.0/infrareds/{infraredId}/timers/groups/{groupId}#190.98#102;GET:/v1.0/statistics-datas-survey#188#5;POST:/v1.0/oauth/user/register#186.333#6;PUT:/v1.0/infrareds/{infraredId}/timers/groups/{groupId}/status#184.04#25;GET:/v1.0/devices/{device_id}/upgrade-infos#184#1;GET:/v1.0/devices/{deviceId}/specifications#182#2;GET:/v1.0/homes/{home_id}/scenes#181.368#68427;DELETE:/v1.0/infrareds/{infraredId}/timers#181.353#68;GET:/v1.0/infrareds/{infraredId}/operators/{operatorId}/brands#179.5#2;PUT:/v1.0/devices/{deviceId}/enabled-sub-discovery#177#1;GET:/v1.0/users/{uid}/devices/{deviceId}#176.4#5;POST:/v1.0/homes/{home_id}/members#176.182#11;DELETE:/v1.0/homes/{home_id}#166.786#14;DELETE:/v1.0/infrareds/{infraredId}/timers/groups/{groupId}#166.714#7;GET:/v1.0/apps/{schema}/users-live-datas#165#1;POST:/v1.0/feedbacks/{dialog_id}#163#1;GET:/v1.0/functions/{category}#162#1;POST:/v1/devices/op#160#1;PUT:/v1.0/homes/{home_id}/automations/{automation_id}#156#1;GET:/v1.0/devices/{device_id}/door-lock/open-logs#151#1";
    private static String euSlowApis = "GET:/v1.0/devices/{device_id}/upgrade-infos#1055#1;GET:/v1.0/position/weather#889.333#3;GET:/v1.0/cities/{cityId}/weathers#886.667#3;GET:/v1.0/cities/{cityId}/weather-forecast#755.333#3;GET:/v1.0/devices/live-datas#490#1;GET:/v1.0/token/{refreshToken}#427.412#17;GET:/v1.0/devices/{devId}/video-streaming#363.5#14;POST:/v1.0/infrareds/{infrared_id}/categories/{category_id}/remotes/{remote_index}/send-keys#348#2;POST:/v1.0/infrareds/{infraredId}/send-keys#326.174#23;GET:/v1.0/infrareds/{infraredId}/remotes/{remoteId}/ac/status#322.984#62;GET:/v1.0/devices/{deviceId}/status#314.723#130;GET:/v1.0/devices/{device_id}/logs#314.185#38071;GET:/v1.0/token#302#10;POST:/v1.0/devices/token#278#1;GET:/v1.0/devices/{deviceId}#263.399#3252;GET:/v1.0/user/devices#262.5#24;DELETE:/v1.0/infrareds/{infraredId}/remotes/{remoteId}#260#1;GET:/v1.0/users/{uid}/devices#256.028#1715;GET:/v1.0/infrareds/{infraredId}/remotes#254#1;POST:/v1.0/user/password-login#250.333#6;GET:/v1.0/devices/{devId}/timers#250#1;GET:/v1.0/users/{uid}/homes#244.333#3;POST:/v1.0/devices/{deviceId}/commands#241.022#93;GET:/v1.0/devices#237.241#3467;GET:/v1.0/devices/{gwId}/statistics/days#234.091#11;GET:/v1.0/apps/{schema}/users#233.5#4;GET:/v1.0/user/infos#226#8;PUT:/v1.0/homes/{home_id}#220.333#3;POST:/v1.0/infrareds/{infraredId}/send-ackeys#215.462#13;GET:/v1.0/infrareds/{infraredId}/categories/{categoryId}/brands#209#1;GET:/v1.0/users/{uid}/devices/{deviceId}#209#1;GET:/v1.0/homes/{home_id}/devices#202.225#200;POST:/v1.0/user/verify-code#201#1;GET:/v1.0/countries/{countryCode}/cities#198#1;POST:/v1.0/feedbacks/{dialog_id}#194#1;POST:/v1.0/infrareds/{infraredId}/box/add-remote#192#1;POST:/v1.0/apps/{schema}/user#191.544#90;POST:/v1.0/home/create-home#191#1;GET:/v1.0/devices/tokens/{token}#189.8#5;POST:/v1.0/infrareds/{infraredId}/add-remote#189.717#92;GET:/v1.0/infrareds/{infraredId}/categories/{categoryId}/brands/{brandId}/remotes/{remoteIndex}/rules#185#1;POST:/v1.0/user/auth#180.643#241;POST:/v1.0/infrareds/{infraredId}/normal/add-remote#177.275#109;GET:/v1.0/infrareds/{infraredId}/areas/{areaId}/iptvs#174#1;POST:/v1.0/infrareds/{infrared_id}/ac/send-keys#173.667#3;POST:/v1.0/infrareds/{infraredId}/learning-codes#163#1;POST:/v1.0/devices/{devId}/timers#153.667#3";
    private static String usSlowApis = "POST:/v1.0/infrareds/{infrared_id}/matching-remotes#5220.333#3;GET:/v1.0/countries/{countryCode}/cities#2138#1;GET:/v1.0/cities/{cityId}/weathers#859.333#3;GET:/v1.0/oauth/languages#847#1;GET:/v1.0/position/weather#843.333#3;GET:/v1.0/users/{uid}/devices/{deviceId}/status#579#1;GET:/v1.0/cities/{cityId}/weather-forecast#552.667#3;GET:/v1.0/devices/active-datas#443#1;GET:/v1.0/devices/{deviceId}/status#439.308#78;GET:/v1.0/token/{refreshToken}#389.844#32;POST:/v1.0/infrareds/{infrared_id}/ac/send-keys#385.333#3;POST:/v1.0/devices/{deviceId}/commands#369.577#492;GET:/v1.0/devices/status#346.5#2;GET:/v1.0/devices/{deviceId}#338.083#96;POST:/v1.0/user/verify-code#330.5#6;GET:/v1.0/devices/{device_id}/movement-configs#319.5#4;POST:/v1.0/device-groups/{device_group_id}/issued#316.364#11;GET:/v1.0/devices/{device_id}/logs#309.604#57296;GET:/v1.0/token#308.222#9;GET:/v1.0/users/{uid}/devices#297.711#647";

    public static void main(String[] args) {
//        HashSet<String> cnSApi = new LinkedHashSet<String>(Arrays.asList(cnSlowApi.split(",")));
//        HashSet<String> euSApi = new LinkedHashSet<String>(Arrays.asList(euSlowApi.split(",")));
//        HashSet<String> usSApi = new LinkedHashSet<String>(Arrays.asList(usSlowApi.split(",")));
//        System.out.println(cnSApi.size());
//        System.out.println(euSApi.size());
//        System.out.println(usSApi.size());
//        HashSet<String> noT = new LinkedHashSet<>(cnSApi);
//        noT.addAll(euSApi);
//        noT.addAll(usSApi);
//        System.out.println(noT);
//        System.out.println(noT.size());
        LinkedHashSet<slowApi> cnApis = ToSlowApis(cnSlowApis, "cn");
        System.out.println(cnApis.size());
        LinkedHashSet<slowApi> euApis = ToSlowApis(euSlowApis, "eu");
        System.out.println(euApis.size());
        LinkedHashSet<slowApi> usApis = ToSlowApis(usSlowApis, "us");
        System.out.println(usApis.size());
        addAll(cnApis, euApis);
        addAll(cnApis, usApis);
        System.out.println(cnApis.size());
        System.out.println(Sorts(cnApis));
    }

    public static List<slowApi> Sorts(LinkedHashSet<slowApi> apis) {
        LinkedList<slowApi> r = new LinkedList<slowApi>(apis);
        Collections.sort(r, new Comparator<slowApi>() {
            @Override
            public int compare(slowApi o1, slowApi o2) {
                if (o1.avg > o2.avg) {
                    return -1;
                } else if (o1.avg == o2.avg) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });
        return r;
    }

    public static LinkedHashSet<slowApi> ToSlowApis(String api, String qu) {
        String[] apis = api.split(";");
        if (apis == null || apis.length == 0) {
            return new LinkedHashSet<>();
        }
        LinkedHashSet<slowApi> apiHashSet = new LinkedHashSet<slowApi>();
        for (String a : apis) {
            String[] ss = a.split("#");
            if (ss.length != 3) {
                System.out.println("截取出问题了：" + a);
                continue;
            }
            String i = ss[0];
            double avg = Double.valueOf(ss[1]);
            int count = Integer.valueOf(ss[2]);
            apiHashSet.add(newSlowApi(i, avg, count, qu));
        }
        return apiHashSet;
    }

    public static void addAll(LinkedHashSet<slowApi> a, LinkedHashSet<slowApi> b) {
        HashSet<slowApi> temp = new HashSet<>();
        for (slowApi slowApi : a) {
            for (slowApi s : b) {
                if (slowApi.equals(s) && slowApi.avg < s.avg) {
                    slowApi.qu = s.qu + "-[" + slowApi.qu + " " + slowApi.avg + "]";
                    slowApi.avg = s.avg;
                } else if (!slowApi.equals(s)) {
                    temp.add(s);
                }
            }
        }
        a.addAll(temp);
    }

    public static slowApi newSlowApi(String api, double avg, int count, String qu) {
        slowApi s = new slowApi();
        s.api = api;
        s.avg = avg;
        s.count = count;
        s.qu = qu;
        return s;
    }

    static class slowApi {
        public String api;
        public double avg;
        public int count;
        public String qu;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            slowApi slowApi = (slowApi) o;
            return StringUtils.equals(api, slowApi.api);
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37)
                    .append(api)
                    .toHashCode();
        }

        @Override
        public String toString() {
            return api + "  " + avg + "  " + count + "  " + qu + '\n';
        }
    }
}

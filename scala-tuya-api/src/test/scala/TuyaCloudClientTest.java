
import com.alibaba.fastjson.JSONObject;
import com.yd.sdk.smart.TuyaCloudClient;
import com.yd.sdk.smart.config.ClientConfig;
import com.yd.sdk.smart.model.RequestMessage;
import com.yd.sdk.smart.model.ResponseMessage;

import java.util.HashMap;
import java.util.Map;


public class TuyaCloudClientTest {

    public static void main(String[] args) throws Exception {
        userList();
//
//        System.out.println(new String(Base64Utils.decode("aHR0cDovL3R5LWNuLXN0b3JhZ2UzMC5vc3MtY24taGFuZ3pob3UuYWxpeXVuY3MuY29tLzAwMzcyMS00NDA2MTI0LXBwMDE2ZWZlN2U4MThjMjYzZmUyL2RldGVjdC8xNTYxOTQ4NzE3LmpwZWc/RXhwaXJlcz0xNTYxOTQ5MDIyJk9TU0FjY2Vzc0tleUlkPVNUUy5OSjJiMWhWbndGeE4yWHFzZUw1QzRYRmF5JlNpZ25hdHVyZT1EZE5mSDAyQkV2UHNqZDZibk1nMzlxTjVTZEklM0Qmdj0xLjAmYnVja2V0PXR5LWNuLXN0b3JhZ2UzMCZzZWN1cml0eS10b2tlbj1DQUlTclFOMXE2RnQ1QjJ5ZlNqSXI0bUhLWXZjdTdGVzhicWxNSDdBbDJVWk9jd1l0NFBLbXp6MklIcExlblJ1QSUyQndYdHYwM21tdFI3UFlUbHFCNlQ1NU9TQW1jTlpJb2VBck1SZEhrTWVUN29NV1F3ZUV1RnZUSGNESGhrM2Vac2ViV1olMkJMbU5vMiUyRkh0Nm1kMUhEa0FKcTNMTCUyQmJrJTJGTWRsZTVNSnFQJTJCJTJCZ0ZDOU1NUlZ1WFlDWmhEdFZiTFJjQXpjZ0JMaW5wS09xS09Cem5pWGFxeWlNSzNEQnhrbVJpODYlMkJ5NzlTQjR4N0Y5ajNBeCUyRlFTdXA3NkwlMkJwZEJxdEJOWnhrQWRDdXg3NDBKT2lUekQ4VjRCNFA3N3R4bHFWTDlHeWYlMkJZbkZXQVlMdWxEYWI3S0txNGN6YVJkaFB2RmtGcUJKOCUyRldzeksxejRMS0ltWW10akJKS01xWVhNUVRXVDV2eXg5RGZTcUg3ZElRbFpQUHdQVEhXenRlVlA1YjQ2RnRqT1NwQWExa1RQb1o5Y2lFcFUwQnBVaW1DYyUyRkdxOWwyVmIxZjZHNlBmanZvNDNNQXl3MWowOE5HV1lCcXRPJTJCekpqWDlDWk1SaFB4NTFhVUpQakRiYkglMkZWWUkxQVdRVFJPSEsySVNJaCUyRlZoZGFwSjd6c0JmYVBpOGFsU3NJNGFDZ1BhJTJCSDR2bEhOOTZtQk1JWHklMkZvQmVZVlA5bUFqU2xudFF2ZTFsa0FPZldOb0JLeFIxS1Q5OFZzRkx6NCUyQkNvNGFnQUV4NGVtdlU0a21nQUdUck9iUUFYVjVVeUlhNWNSUVBZRmw2JTJCTHglMkIwUVZNUGdRdmpqOXJjMms1TjRDOEoxM053R1l0YnVsNGdEa0I2VHhJY1NmMnNUT0FyaTRTOTlHZ3IlMkZIaW02c0p5RHo5dFFiZm1HUG5wNU5PSERoOXN5emZvY1F4Ym4yNjA5czlzbGFQbjhOa1A0b25YWFB0S2RuWVRRY00lMkY1TmQweWdXZyUzRCUzRA==".getBytes())));
    }

    private static void userList() {
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.setSocketTimeout(4000);
        clientConfig.setConnectionTimeout(4000);

        //String endUri = "http://a.daily.tuya-inc.cn/a1513641600pi.json";
//        String endUri = "https://a1.cn.wgine.com/api.json";
        String endUri = "https://a1.tuyacn.com/api.json";

        //TODO 填写密钥对
        String accessId = "g9fpjr9kcf7uvgvw99y7";
        String accessKey = "mq3r7aaxeda9saxutn77kqf5uhdq8tt8";
        String session = "ay156317n4194780cXrpAWH37b424cf79319be4d0b69403d51dc94cc";

        TuyaCloudClient client = new TuyaCloudClient(accessId, accessKey, endUri, clientConfig);

        RequestMessage request = new RequestMessage();
        request.setApi("tuya.m.device.dp.publish");
        request.setApiVersion("1.0");
        request.setOs("IOS");
        request.setLang("zh");
        request.setType("app");
        request.setSession(session);
        request.setTime(System.currentTimeMillis() / 1000);

        Map<String, String> params = new HashMap<String, String>();

        String dps = "{\"1\":false}";

        //业务参数
        params.put("devId", "vdevo156317424032308");
        params.put("dps", dps);

        request.setParams(params);

        ResponseMessage response = client.sendRequest(request);

        System.out.println(JSONObject.toJSONString(response, true));
    }
}

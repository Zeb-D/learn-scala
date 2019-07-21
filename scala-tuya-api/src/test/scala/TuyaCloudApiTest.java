
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yd.api.TuyaClient;
import com.yd.api.model.Command;
import com.yd.api.model.TuyaResult;
import com.yd.api.model.domain.device.*;
import com.yd.api.model.domain.user.UserList;
import com.yd.api.model.enums.HttpMethod;
import com.yd.api.model.enums.RegionEnum;
import com.yd.api.moudle.User;
import com.yd.api.util.LockPwdUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TuyaCloudApiTest {

    public static void main(String args[]) throws InterruptedException {
        test();
//        System.out.println(LockPwdUtil.encryptAccessKey("123987","290d3f7474e51c87"));

//        System.out.println("eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6ImY5ODYzYjI4MzAyODNkNjhkMGQxNmZhMWNlMjgxNTI0OWJkMzYyYWFlNTAxODNiOTk1NzI3ZGFmMWVlNDdmM2VhMDMwYjk5MTExZGZjNWI2In0.eyJhdWQiOiJ0dXlhIiwianRpIjoiZjk4NjNiMjgzMDI4M2Q2OGQwZDE2ZmExY2UyODE1MjQ5YmQzNjJhYWU1MDE4M2I5OTU3MjdkYWYxZWU0N2YzZWEwMzBiOTkxMTFkZmM1YjYiLCJpYXQiOjE1NjA4NDUxODEsIm5iZiI6MTU2MDg0NTE4MSwiZXhwIjoxNTYwOTMxNTgxLCJzdWIiOiI4OTI0OCIsInNjb3BlcyI6eyJvcGVuaWQiOiJvcGVuaWQiLCJlbWFpbCI6ImVtYWlsIn19.eYo7i2FJ0axUXyEjhj97boe7OBPqVE5fjGnoAfLzdNS2DLWDnP4rhxukgoPHlS05zKjvkUP8hwEJYBlNF_iH1RSz47xrKKrL2SwdI6rOs9vDR0dT19mU1OtQulOcqSWGSTZWtBtztANXy6XAgoeQaJfAOT81ckn055SdmR1aryW_S8_py8OuRBjXrSPO3mccJ_WGRKW4kpl52SsC4GTqmXjyQyv0S57krwiXXCclTwlJXCvIqL0nv5yBtHCUj3A-gEsnn0ZtrgQDwG5u--UTluyl2peeh1JQeA80agMo3JjETQrTJ5Q_1FjDYPeTM40O-Gjz_f9iU-hcy-YMk0x7Rg".length());

    }

    public static void test(){
        final String UID = "ay1536837166536zrjMe";//"ay1536837166536zrjMe";
        final String DEV_ID = "vdevo155056015362995";//"vdevo154832171596238"; //0420048868c63ac56700
        final String CATEGORY = "kg";
        List<String> devIds = new ArrayList<String>(2);
        devIds.add("vdevo155056015362995"); // vdevo153502654052871
//        devIds.add("vdevo154458004640088");

        // 简单模式
        TuyaClient client = new TuyaClient("4vh8pk3anqksc9ygtr8y", "tp79usfcawjtx8hv837rpmm4qwr8hqwp", RegionEnum.US);


        // 授权码模式
//        TuyaClient client = new TuyaClient("4vh8pk3anqksc9ygtr8y", "tp79usfcawjtx8hv837rpmm4qwr8hqwp", null, "37405955a0d430935c02950d79399cf5");
//        System.out.println(JSONObject.toJSONString(client.getHeaders(true)));

        // 获取token
//        client.getToken();
        // 刷新token
//        client.refreshToken();

        // 获取用户列表
        for (int i=0;i<1000;i++){
            long runTime = System.currentTimeMillis();
            UserList result = client.getUsers("iote", 1,10);
            System.out.println("获取用户列表:第 "+i+" 次请求,耗时:"+(System.currentTimeMillis() - runTime)+"ms");
//            System.out.println(JSONObject.toJSONString(result));
        }

//
//        Thread.sleep(7201 * 1000);
//
//        result = client.getUsers("iote", 1,10);
//        System.out.println("获取用户列表: ");
//        System.out.println(JSONObject.toJSONString(result));

        // 注册用户
//        String uid = client.registerUser("iote","86", "test", "123456", "123456", null);
//        System.out.println("注册用户: ");
//        System.out.println(uid);
//        // 获取用户设备列表
//        List<DeviceVo> deviceFunctions = client.getUserDevices(UID);
//        System.out.println("获取用户设备列表: ");
//        System.out.println(JSONObject.toJSONString(deviceFunctions));
//
//        // 生成设备配网token
//        DeviceToken deviceToken = client.getDeviceToken(UID, "Asia/Shanghai", "", "", "zh");
//        System.out.println("生成设备配网token: ");
//        System.out.println(JSONObject.toJSONString(deviceToken));
//
//        // 根据配网token获取获取设备列表
//        DeviceResultOfToken deviceListOfToken = client.getDeviceListOfToken(deviceToken.getToken());
//        System.out.println("根据配网token获取获取设备列表: ");
//        System.out.println(JSONObject.toJSONString(deviceListOfToken));
//
//        // 获取设备详细信息
//        DeviceVo deviceVo = client.getDeviceInfo(DEV_ID);
//        System.out.println("获取设备信息: ");
//        System.out.println(JSONObject.toJSONString(deviceVo));
//
//        // 批量获取设备详细信息
//        BatchDevices batchDevices = client.getDeviceListInfo(devIds);
//        System.out.println("批量获取设备详细信息: ");
//        System.out.println(JSONObject.toJSONString(batchDevices));
//
//        // 根据category获取function列表
//        CategoryFunctions categoryFunctions = client.getFunctionByCategory(CATEGORY);
//        System.out.println("根据category获取function列表: ");
//        System.out.println(JSONObject.toJSONString(categoryFunctions));
//
//        // 获取设备状态信息
//        List<Status> deviceStatus= client.getDeviceStatus(DEV_ID);
//        System.out.println("获取设备状态信息: ");
//        System.out.println(JSONObject.toJSONString(deviceStatus));
//
//        // 批量获取设备状态
//        List<StatusWithDevId> statusWithDevIds = client.getDeviceListStatus(devIds);
//        System.out.println("批量获取设备状态: ");
//        System.out.println(JSONObject.toJSONString(statusWithDevIds));
//
//        // 获取设备支持指令集
//        categoryFunctions = client.getFunctionsByDevId(DEV_ID);
//        System.out.println("根据设备id 获取function列表: ");
//        System.out.println(JSONObject.toJSONString(categoryFunctions));

        // 下发设备控制指令
//        JSONObject temp = new JSONObject();
//        temp.put("v", 0);
//        temp.put("s", 1);
//        temp.put("h", 1);
//        Command command = new Command("colour_data", temp);
//        List<Command> list = new ArrayList<Command>(1);
//        list.add(command);
////        Boolean isSuccess = client.postDeviceCommand(DEV_ID, list);
//        Boolean isSuccess = client.postDeviceCommand("vdevo155013689282523", list);
//        System.out.println("下发设备控制指令: ");
//        System.out.println(isSuccess);
//
        // 移除设备
//        Boolean isDelete = client.deleteDevice("vdevo155013720403768");
//        System.out.println("移除设备: ");
//        System.out.println(isDelete);

        /**
         * 万能接口
         */
//        Map<String, String> headers = new HashMap<String, String>();
//        String tuyaResult = client.commonHttpRequest("https://openapi-cn.wgine.com/v1.0/devices/" + DEV_ID, HttpMethod.GET, headers, null);
//        System.out.println("万能接口返回：" + tuyaResult);
    }

}

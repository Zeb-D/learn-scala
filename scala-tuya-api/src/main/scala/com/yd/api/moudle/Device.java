package com.yd.api.moudle;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yd.api.model.BaseInfo;
import com.yd.api.model.Command;
import com.yd.api.model.Request;
import com.yd.api.model.domain.device.*;
import com.yd.api.model.enums.HttpMethod;

import java.util.*;

/**
 * 设备相关模块
 *
 *
 * @date: 2019-01-07 21:18
 */
public class Device extends Oauth {

    public Device(BaseInfo baseInfo) {
        super(baseInfo);
    }

    /**
     * 获取设备配网token
     *
     * @return
     */
    public DeviceToken deviceToken(JSONObject body) {
        BaseInfo baseInfo = this.getBaseInfo();
        String url = baseInfo.getBaseUrl() + "/v1.0/devices/token";
        Request request = new Request(url, HttpMethod.POST, null, false, body.toJSONString(), baseInfo);
        String result = this.sendHttpRequestWithOauthToken(request);
        DeviceToken deviceToken = JSON.parseObject(result, DeviceToken.class);
        return deviceToken;
    }

    /**
     * 根据配网token获取获取设备列表；
     *
     * @param token
     * @return
     */
    public DeviceResultOfToken deviceListOfToken(String token) {
        BaseInfo baseInfo = this.getBaseInfo();
        String url = baseInfo.getBaseUrl() + "/v1.0/devices/tokens/" + token;
        Request request = new Request(url, HttpMethod.GET, null, false, null, baseInfo);
        String tuyaResult = this.sendHttpRequestWithOauthToken(request);
        DeviceResultOfToken resultOfToken = JSON.parseObject(tuyaResult, DeviceResultOfToken.class);
        return resultOfToken;
    }

    /**
     * 获取设备的详细信息
     *
     * @param devId
     * @return
     */
    public DeviceVo deviceInfo(String devId) {
        BaseInfo baseInfo = this.getBaseInfo();
        String url = baseInfo.getBaseUrl() + "/v1.0/devices/" + devId;
        Request request = new Request(url, HttpMethod.GET, null, false, null, baseInfo);
        String tuyaResult = this.sendHttpRequestWithOauthToken(request);
        DeviceVo deviceVo = JSON.parseObject(tuyaResult, DeviceVo.class);
        return deviceVo;
    }

    /**
     * 批量获取设备信息
     *
     * @return
     */
    public BatchDevices devices(List<String> devIds) {
        BaseInfo baseInfo = this.getBaseInfo();
        String url = baseInfo.getBaseUrl() + "/v1.0/devices";
        Request request = new Request(url, HttpMethod.GET, listToMapForParam(devIds), false, null, baseInfo);
        String tuyaResult = this.sendHttpRequestWithOauthToken(request);
        BatchDevices batchDevices = JSON.parseObject(tuyaResult, BatchDevices.class);
        return batchDevices;
    }

    /**
     * 获取function列表
     *
     * @return
     */
    public CategoryFunctions functionsByCategory(String category) {
        BaseInfo baseInfo = this.getBaseInfo();
        String url = baseInfo.getBaseUrl() + "/v1.0/functions/" + category;
        Request request = new Request(url, HttpMethod.GET, null, false, null, baseInfo);
        return request4CategoryFunctions(request);
    }

    /**
     * 获取设备状态信息
     *
     * @param devId
     * @return
     */
    public List<Status> deviceStatus(String devId) {
        BaseInfo baseInfo = this.getBaseInfo();
        String url = baseInfo.getBaseUrl() + "/v1.0/devices/" + devId + "/status";
        Request request = new Request(url, HttpMethod.GET, null, false, null, baseInfo);
        String tuyaResult = this.sendHttpRequestWithOauthToken(request);
        List<Status> list = JSONArray.parseArray(tuyaResult, Status.class);
        return list;
    }

    /**
     * 批量获取设备状态
     *
     * @return
     */
    public List<StatusWithDevId> deviceListStatus(List<String> devIds) {
        BaseInfo baseInfo = this.getBaseInfo();
        String url = baseInfo.getBaseUrl() + "/v1.0/devices/status";
        Request request = new Request(url, HttpMethod.GET, listToMapForParam(devIds), false, null, baseInfo);
        String tuyaResult = this.sendHttpRequestWithOauthToken(request);
        JSONObject temp = JSONObject.parseObject(tuyaResult);
        Set<String> devIdList = temp.keySet();
        List<StatusWithDevId> result = new ArrayList<StatusWithDevId>();
        for (String each : devIdList) {
            JSONArray status = temp.getJSONArray(each);
            List<Status> list = JSONArray.parseArray(status.toJSONString(), Status.class);
            StatusWithDevId statusWithDevId = new StatusWithDevId();
            statusWithDevId.setDevId(each);
            statusWithDevId.setStatusList(list);
            result.add(statusWithDevId);
        }
        return result;
    }


    /**
     * 根据设备id 获取function列表
     *
     * @param devId
     * @return
     */
    public CategoryFunctions functionsByDevId(String devId) {
        BaseInfo baseInfo = this.getBaseInfo();
        String url = baseInfo.getBaseUrl() + "/v1.0/devices/" + devId + "/functions";
        Request request = new Request(url, HttpMethod.GET, null, false, null, baseInfo);
        return request4CategoryFunctions(request);
    }

    /**
     * 根据设备的ID来对设备进行指令下发。
     * @param devId
     * @param list
     * @return
     */
    public Boolean deviceCommands(String devId, List<Command> list) {
        BaseInfo baseInfo = this.getBaseInfo();
        String url = baseInfo.getBaseUrl() + "/v1.0/devices/" + devId + "/commands";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("commands", list);
        Request request = new Request(url, HttpMethod.POST, null, false, jsonObject.toJSONString(), baseInfo);
        String tuyaResult = this.sendHttpRequestWithOauthToken(request);
        if("true".equals(tuyaResult)){
            return true;
        }
        return false;
    }

    /**
     * 移除设备
     * @param devId
     * @return
     */
    public Boolean deleteDevice(String devId){
        BaseInfo baseInfo = this.getBaseInfo();
        String url = baseInfo.getBaseUrl() + "/v1.0/devices/" + devId;
        Request request = new Request(url, HttpMethod.DELETE, null, false, null, baseInfo);
        String tuyaResult = this.sendHttpRequestWithOauthToken(request);
        if("true".equals(tuyaResult)){
            return true;
        }
        return false;
    }

    private Map<String, String> listToMapForParam(List<String> devIds) {
        StringBuilder sb = new StringBuilder();
        for (String each : devIds) {
            sb.append(each).append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        Map<String, String> urlParam = new HashMap<String, String>();
        urlParam.put("device_ids", sb.toString());
        return urlParam;
    }


    private CategoryFunctions request4CategoryFunctions(Request request){
        String tuyaResult = this.sendHttpRequestWithOauthToken(request);
        CategoryFunctions functionsOfDevId = JSON.parseObject(tuyaResult, CategoryFunctions.class);
        return functionsOfDevId;
    }

}

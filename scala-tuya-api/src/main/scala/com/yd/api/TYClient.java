package com.yd.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yd.api.model.BaseInfo;
import com.yd.api.model.Command;
import com.yd.api.model.domain.device.*;
import com.yd.api.model.domain.user.UserList;
import com.yd.api.model.enums.HttpMethod;
import com.yd.api.model.enums.RegionEnum;
import com.yd.api.model.enums.ServerEnum;
import com.yd.api.model.enums.UserTypeEnum;
import com.yd.api.moudle.Common;
import com.yd.api.moudle.Device;
import com.yd.api.moudle.User;
import org.apache.http.Header;

import java.util.List;
import java.util.Map;

/**
 * 用于访问涂鸦云端API
 *
 *
 * @date: 2019-01-07 10:36
 */
public class TYClient {

    // 基础信息
    private BaseInfo baseInfo;
    // 默认当前环境
    private ServerEnum SERVER_TYPE = ServerEnum.CN_ONLINE;
    // 设备模块
    private Device device;
    // 公用接口模块
    private Common common;
    // 用户模块
    private User user;

    /**
     * 授权码模式
     * @param clientId
     * @param sercet
     * @param regionEnum
     * @param code
     */
    public TYClient(String clientId, String sercet, RegionEnum regionEnum, String code) {
        baseInfo = new BaseInfo(sercet, clientId, regionEnum, code);
        if(regionEnum == null){
            baseInfo.setBaseUrl(ServerEnum.CN_ONLINE.getUrl());
        }else {
            switch (regionEnum) {
                case CN: {
                    baseInfo.setBaseUrl(ServerEnum.CN_ONLINE.getUrl());
                    break;
                }
                case EU:{
                    baseInfo.setBaseUrl(ServerEnum.EU_ONLINE.getUrl());
                    break;
                }
                case US:{
                    baseInfo.setBaseUrl(ServerEnum.US_ONLINE.getUrl());
                    break;
                }
                default:
                    baseInfo.setBaseUrl(ServerEnum.CN_ONLINE.getUrl());
            }
        }
        device = new Device(baseInfo);
        common = new Common(baseInfo);
        user = new User(baseInfo);
    }

    /**
     * 简单模式
     * @param clientId
     * @param sercet
     * @param regionEnum
     */
    public TYClient(String clientId, String sercet, RegionEnum regionEnum){
        baseInfo = new BaseInfo(sercet, clientId, regionEnum, null);
        switch (regionEnum) {
            case CN: {
                baseInfo.setBaseUrl(SERVER_TYPE.getUrl());
                break;
            }
            case EU:{
                baseInfo.setBaseUrl(ServerEnum.EU_ONLINE.getUrl());
                break;
            }
            case US:{
                baseInfo.setBaseUrl(ServerEnum.US_ONLINE.getUrl());
                break;
            }
            default:
                baseInfo.setBaseUrl(ServerEnum.CN_ONLINE.getUrl());
        }
        device = new Device(baseInfo);
        common = new Common(baseInfo);
        user = new User(baseInfo);
    }

    // token
    public void refreshToken(){
        user.getRefreshToken();
    }

    /**
     * 获取 注册用户
     * @param schema 应用唯一标识,对应涂鸦开发者平台应用管理中的"标识名"
     * @param pageNo 页数
     * @param pageSize 大小
     * @return
     */
    public UserList getUsers(String schema, int pageNo, int pageSize){
        return user.userList(schema, pageNo, pageSize);
    }

    /**
     * 用户注册
     * @param schema 应用唯一标识,对应涂鸦开发者平台应用管理中的"标识名"
     * @param countryCode 国家码
     * @param username 账户
     * @param password MD5加密密码
     * @param nickName 用户名
     * @param userEntity 账户类型 可为空
     * @return
     */
    public String registerUser(String schema, String countryCode, String username, String password, String nickName, UserTypeEnum userEntity){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("country_code", countryCode);
        jsonObject.put("username", username);
        jsonObject.put("password", password);
        jsonObject.put("nick_name", nickName);
        if(userEntity != null){
            jsonObject.put("username_type", userEntity.getValue());
        }else{
            jsonObject.put("username_type", UserTypeEnum.USER_NAME.getValue());
        }
        return user.registerUser(schema, jsonObject);
    }

    /**
     * 获取用户下的设备列表
     * @param uid 用户唯一标识
     * @return
     */
    public List<DeviceVo> getUserDevices(String uid){
        return user.userDevices(uid);
    }

    /**
     * 生成设备配网token
     * @param uid 用户唯一标识
     * @param timeZoneId 用户所在时区id，州/省份（Asia/Shanghai）
     * @param lon 经度 可为空
     * @param lat 纬度 可为空
     * @param lang 系统语言，zh,eu等，默认zh 可为空
     * @return
     * 需要说明的是，使用涂鸦配网sdk，需要将result参数组装：region+token+secret 用于初始化！
     */
    public DeviceToken getDeviceToken(String uid, String timeZoneId, String lon, String lat, String lang){
        JSONObject body = new JSONObject();
        body.put("uid", uid);
        body.put("timeZoneId", timeZoneId);
        body.put("lon", lon);
        body.put("lat", lat);
        body.put("lang", lang);
        return device.deviceToken(body);
    }

    /**
     * 根据配网token获取获取设备列表；
     * @param token 获取设备配网token返回result中的token
     * @return
     */
    public DeviceResultOfToken getDeviceListOfToken(String token){
        return device.deviceListOfToken(token);
    }

    /**
     * 获取设备的详细信息
     * @param devId 设备ID
     * @return
     */
    public DeviceVo getDeviceInfo(String devId) {
        return device.deviceInfo(devId);
    }

    /**
     * 批量获取设备信息
     * @param devIds 设备ID列表
     * @return
     */
    public BatchDevices getDeviceListInfo(List<String> devIds) {
        return device.devices(devIds);
    }

    /**
     * 获取function列表
     * @param category
     * @return
     */
    public CategoryFunctions getFunctionByCategory(String category){
        return device.functionsByCategory(category);
    }

    /**
     * 获取设备状态信息
     * @param devId 设备ID
     * @return
     */
    public List<Status> getDeviceStatus(String devId){
        return device.deviceStatus(devId);
    }

    /**
     * 批量获取设备状态
     * @param devIds 设备ID列表
     * @return
     */
    public List<StatusWithDevId> getDeviceListStatus(List<String> devIds){
        return device.deviceListStatus(devIds);
    }

    /**
     * 获取设备支持指令集
     * @param devId 设备ID
     * @return
     */
    public CategoryFunctions getFunctionsByDevId(String devId){
        return device.functionsByDevId(devId);
    }

    /**
     * 下发设备控制命令
     * @param devId 设备ID
     * @param list 指令列表
     * @return
     */
    public Boolean postDeviceCommand(String devId, List<Command> list){
        return device.deviceCommands(devId, list);
    }

    /**
     * 移除设备
     * @param devId 设备ID
     * @return
     */
    public Boolean deleteDevice(String devId){
        return device.deleteDevice(devId);
    }

    /**
     * 获取Header列表
     * @param isToken 是否是token相关请求，一般是false
     * @return
     */
    public List<Header> getHeaders(Boolean isToken){
        return device.getHeader(isToken, this.baseInfo);
    }

    /**
     * 万能涂鸦接口
     * @param url
     * @param method 请求类型（例如：GET）
     * @param headers 请求头内容（额外新增的header）
     * @param body
     * @return
     */
    public String commonHttpRequest(String url, HttpMethod method, Map<String, String> headers, Object body) {
        return this.common.commonHttpRequest(url, method, headers, body);
    }

}


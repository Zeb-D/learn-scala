package com.yd.api.moudle;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yd.api.exception.TuyaClientException;
import com.yd.api.model.BaseInfo;
import com.yd.api.model.Request;
import com.yd.api.model.domain.device.DeviceVo;
import com.yd.api.model.domain.user.UserList;
import com.yd.api.model.enums.HttpMethod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户模块
 *
 *
 * @date: 2019-01-10 21:57
 */
public class User extends Oauth {

    public User(BaseInfo baseInfo) {
        super(baseInfo);
    }

    /**
     * 获取用户列表
     *
     * @param schema
     * @param pageNo
     * @param pageSize
     * @return
     */
    public UserList userList(String schema, int pageNo, int pageSize) {
        BaseInfo baseInfo = this.getBaseInfo();
        String url = baseInfo.getBaseUrl() + "/v1.0/apps/" + schema + "/users";
        Map<String, String> urlParams = new HashMap<String, String>();
        urlParams.put("page_no", pageNo + "");
        urlParams.put("page_size", pageSize + "");
        Request request = new Request(url, HttpMethod.GET, urlParams, false, null, baseInfo);
        String result = this.sendHttpRequestWithOauthToken(request);
        UserList userList = JSON.parseObject(result, UserList.class);
        return userList;
    }

    /**
     * 用户注册
     *
     * @param schema
     * @param body
     * @return
     */
    public String registerUser(String schema, JSONObject body) {
        BaseInfo baseInfo = this.getBaseInfo();
        String url = baseInfo.getBaseUrl() + "/v1.0/apps/" + schema + "/user";
        Request request = new Request(url, HttpMethod.POST, null, false, body.toJSONString(), baseInfo);
        String result = this.sendHttpRequestWithOauthToken(request);
        return result;
    }

    /**
     * 根据用户id来获取账户下的所有设备
     *
     * @param uid
     * @return
     */
    public List<DeviceVo> userDevices(String uid) {
        BaseInfo baseInfo = this.getBaseInfo();
        String url = baseInfo.getBaseUrl() + "/v1.0/users/" + uid + "/devices";
        Request request = new Request(url, HttpMethod.GET, null, false, null, baseInfo);
        String result = this.sendHttpRequestWithOauthToken(request);
        List<DeviceVo> list = JSONArray.parseArray(result, DeviceVo.class);
        return list;
    }

}

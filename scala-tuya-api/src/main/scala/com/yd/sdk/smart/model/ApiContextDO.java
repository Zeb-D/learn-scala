package com.yd.sdk.smart.model;

import java.util.HashMap;
import java.util.Map;


public class ApiContextDO{

	private static final long	serialVersionUID	= 3836954456077493208L;

	private String				ttid;

	private String				deviceid;

	private String				imei;

	private String				imsi;													//客户端用户标识,如果没有imsi,传递客户端ip

	private String				lat;

	private String				lon;

	private String				ip;

	private String				uid;

	private String				nick;

	private String				proxyIp;												// 代理ip

	private String				sid;													// mtop sid

	private boolean				isLongin;

	private String				isH5;													//1代表是H5

	private String				h5TokenValue;											//h5登陆的加密值

	private String				channelId;												// 渠道ID

	//private String				cst;													//csrftoken，H5需要,,移到外面去

	private String				appVersion;											// 客户端版本信息

    private boolean				apiVersionFromUrl;									// api版本是否从url中传递

	private String				apiVersion;											// api版本

	private String				api;													// api接口名

	private String				os;													// 客户端类型 ios或android

	private String				type;													// 客户端类型

	private String				platform;												//机型

	private String				osSystem;												//客户端操作系统

	private String				lang				= "zh-Hans";						//语言包

	private String				userAgent;

	private Boolean				checkDeviceId;											//ajax 是否验证设备

	private String				bizId;													//ajax 用业务ID

    private Integer				nek;												//客户端的网络

    private Integer				ele;												//客户端的电量

	private String				targetCountry;										//要访问的目标国家数据,现仅供tuya商家后台使用

	private Map<String, Object>	params				= new HashMap<String, Object>();	// 入参


    private String appRnVersion;

    private String sdkVersion;


	public String getTtid() {
		return ttid;
	}

	public void setTtid(String ttid) {
		this.ttid = ttid;
	}

	public String getDeviceid() {
		return deviceid;
	}

	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getImsi() {
		return imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLon() {
		return lon;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getProxyIp() {
		return proxyIp;
	}

	public void setProxyIp(String proxyIp) {
		this.proxyIp = proxyIp;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public boolean isLongin() {
		return isLongin;
	}

	public void setLongin(boolean isLongin) {
		this.isLongin = isLongin;
	}

	public String getIsH5() {
		return isH5;
	}

	public void setIsH5(String isH5) {
		this.isH5 = isH5;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public String getApiVersion() {
		return apiVersion;
	}

	public void setApiVersion(String apiVersion) {
		this.apiVersion = apiVersion;
	}

	public String getApi() {
		return api;
	}

	public void setApi(String api) {
		this.api = api;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

	public String getH5TokenValue() {
		return h5TokenValue;
	}

	public void setH5TokenValue(String h5TokenValue) {
		this.h5TokenValue = h5TokenValue;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getOsSystem() {
		return osSystem;
	}

	public void setOsSystem(String osSystem) {
		this.osSystem = osSystem;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public Boolean isCheckDeviceId() {
		return checkDeviceId;
	}

	public void setCheckDeviceId(Boolean checkDeviceId) {
		this.checkDeviceId = checkDeviceId;
	}

	public String getBizId() {
		return bizId;
	}

	public void setBizId(String bizId) {
		this.bizId = bizId;
	}

    public Integer getNek() {
        return nek;
    }

    public void setNek(Integer nek) {
        this.nek = nek;
    }

    public Integer getEle() {
        return ele;
    }

    public void setEle(Integer ele) {
        this.ele = ele;
    }


    public boolean isApiVersionFromUrl() {
        return apiVersionFromUrl;
    }

    public void setApiVersionFromUrl(boolean apiVersionFromUrl) {
        this.apiVersionFromUrl = apiVersionFromUrl;
    }

    public String getAppRnVersion() {
        return appRnVersion;
    }

    public void setAppRnVersion(String appRnVersion) {
        this.appRnVersion = appRnVersion;
    }

    public String getSdkVersion() {
        return sdkVersion;
    }

    public void setSdkVersion(String sdkVersion) {
        this.sdkVersion = sdkVersion;
    }

	public String getTargetCountry() {
		return targetCountry;
	}

	public void setTargetCountry(String targetCountry) {
		this.targetCountry = targetCountry;
	}
}
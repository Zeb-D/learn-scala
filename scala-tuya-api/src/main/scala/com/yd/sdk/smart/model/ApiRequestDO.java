package com.yd.sdk.smart.model;

/*
 * Copyright 2014 etao.com All right reserved. This software is the
 * confidential and proprietary information of etao.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with etao.com .
 */

import java.io.Serializable;

/**
 * 类ApiRequestDO.java的实现描述：TODO 类实现描述
 * 
 * @author kedumin 2014-7-4 上午12:00:24
 */
public class ApiRequestDO implements Serializable {

	private static final long	serialVersionUID	= 4957776030070092305L;

	private ApiContextDO		apiContextDo;								//请求上下文数据

	private String				sign;										//签名

	private String				session;									//token用于获取用户登陆信息

	private String				h5Token;									//h5登陆用的token

	private String				cst;

	private String				api;										//接口

	private String				data;										//json格式的请求数据

	private String				t;											//请求时间戳

	private String				type;										//输出的数据格式

	private String				traceId;									//跟踪的id

	private AppInfoDO			appInfoDo;									//应用信息

	private Boolean				openSign			= true;

	private String				ddebug;										//是否调试模式

	private String				queryString;

	private String				gwId;										//网关Id

	private String				otherData;									//json格式的请求数据。data有可能是加密后的数据，other放不加密数据。

	private String				plant;										//平台  smart,airtake

	private String				entry;										//入口

	private String				n4h5;										//是否native为h5写的万能接口

	private String				sp;											//是否为万能接口 （super的简写）

	private String				irp;										//internal redirect parameter 内部接口跳转参数,数据进行加密

	private String				requestId;									//请求id,不放入签名中，调试中用到

	private String				gwIdAlias;									//gwId别名,不同的api场景下url的参数名称不同,有gwId/devId/uuid三种情况

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getSession() {
		return session;
	}

	public void setSession(String session) {
		this.session = session;
	}

	public String getApi() {
		return api;
	}

	public void setApi(String api) {
		this.api = api;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getT() {
		return t;
	}

	public void setT(String t) {
		this.t = t;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTraceId() {
		return traceId;
	}

	public void setTraceId(String traceId) {
		this.traceId = traceId;
	}

	public ApiContextDO getApiContextDo() {
		return apiContextDo;
	}

	public void setApiContextDo(ApiContextDO apiContextDo) {
		this.apiContextDo = apiContextDo;
	}

	public AppInfoDO getAppInfoDo() {
		return appInfoDo;
	}

	public void setAppInfoDo(AppInfoDO appInfoDo) {
		this.appInfoDo = appInfoDo;
	}

	public Boolean getOpenSign() {
		return openSign;
	}

	public void setOpenSign(Boolean openSign) {
		this.openSign = openSign;
	}

	public String getH5Token() {
		return h5Token;
	}

	public void setH5Token(String h5Token) {
		this.h5Token = h5Token;
	}

	public String getDdebug() {
		return ddebug;
	}

	public void setDdebug(String ddebug) {
		this.ddebug = ddebug;
	}

	public String getQueryString() {
		return queryString;
	}

	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}

	public String getCst() {
		return cst;
	}

	public void setCst(String cst) {
		this.cst = cst;
	}

	//@Override
	//public String toString() {
	//	return ToStringBuilder.reflectionToString(this, new NoNullFieldStringStyle());
	//}

	public String getGwId() {
		return gwId;
	}

	public void setGwId(String gwId) {
		this.gwId = gwId;
	}

	public String getPlant() {
		return plant;
	}

	public void setPlant(String plant) {
		this.plant = plant;
	}

	public String getOtherData() {
		return otherData;
	}

	public void setOtherData(String otherData) {
		this.otherData = otherData;
	}

	public String getEntry() {
		return entry;
	}

	public void setEntry(String entry) {
		this.entry = entry;
	}

	public boolean isAirtakePlant() {
		return this.plant != null && this.plant.equals("airtake");
	}

	public boolean isSmartPlant() {
		return this.plant != null && this.plant.equals("smart");
	}

	public boolean isBasicPlant() {
		return this.plant != null && this.plant.equals("basic");
	}

	public String getN4h5() {
		return n4h5;
	}

	public void setN4h5(String n4h5) {
		this.n4h5 = n4h5;
	}

	public String getSp() {
		return sp;
	}

	public void setSp(String sp) {
		this.sp = sp;
	}

	public String getIrp() {
		return irp;
	}

	public void setIrp(String irp) {
		this.irp = irp;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getGwIdAlias() {
		return gwIdAlias;
	}

	public void setGwIdAlias(String gwIdAlias) {
		this.gwIdAlias = gwIdAlias;
	}
}

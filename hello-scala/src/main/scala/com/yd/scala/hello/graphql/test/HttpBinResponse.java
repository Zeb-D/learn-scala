
package com.yd.scala.hello.graphql.test;

import com.google.gson.annotations.Expose;

import javax.annotation.Generated;
import java.util.Map;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class HttpBinResponse {

    @Expose
    private Map<String, Object> args;
    @Expose
    private String data;
    @Expose
    private Map<String, Object> files;
    @Expose
    private Map<String, Object> form;
    @Expose
    private Object json;
    @Expose
    private String method;
    @Expose
    private String origin;
    @Expose
    private String url;

    public Map<String, Object> getArgs() {
        return args;
    }

    public void setArgs(Map<String, Object> args) {
        this.args = args;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Map<String, Object> getFiles() {
        return files;
    }

    public void setFiles(Map<String, Object> files) {
        this.files = files;
    }

    public Map<String, Object> getForm() {
        return form;
    }

    public void setForm(Map<String, Object> form) {
        this.form = form;
    }

    public Object getJson() {
        return json;
    }

    public void setJson(Object json) {
        this.json = json;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "HttpBinResponse{" +
            "args=" + args +
            ", data='" + data + '\'' +
            ", files=" + files +
            ", form=" + form +
            ", json=" + json +
            ", method='" + method + '\'' +
            ", origin='" + origin + '\'' +
            ", url='" + url + '\'' +
            '}';
    }
}

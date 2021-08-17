package com.yd.scala.hello.sdk.http;

import com.yd.scala.hello.sdk.HttpMethod;
import com.yd.scala.hello.sdk.MediaType;
import com.yd.scala.hello.sdk.SdkHttp;
import lombok.Data;

import java.util.List;

//注意，api会有限流
//https://docs.github.com/en/rest/overview/resources-in-the-rest-api#rate-limiting
public interface IGithubHttpSdkService {

    @SdkHttp(path = "/repos/Zeb-D/learn-scala/contributors", method = HttpMethod.GET, responseType = MediaType.Json)
    List<GithubInfo> getRequest();

    @SdkHttp(path = "/users/Zeb-D", method = HttpMethod.GET, responseType = MediaType.Json)
    UserInfo userInfo();

    @Data
    class GithubInfo {
        private Integer contributions;
        private String avatar_url;
    }

    @Data
    class UserInfo {
        private Integer followers;
        private Integer following;
        private String bio;
        private String blog;
        private String updated_at;
        private String created_at;
    }
}

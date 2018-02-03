package com.yd.security.security;

import java.security.Principal;

/**
 * @author Yd on  2018-01-20
 * @Description：
 **/
public class LoginPrincipal implements Principal {

    /**
     * euip(兴业银行统一用户身份信息管理系统)账户
     */
    public static final String USER_TYPE_EUIP = "0";

    /**
     * 本地系统原有账户
     */
    public static final String USER_TYPE_LOCAL = "1";
    /**
     * 登陆用户名
     */
    private String userName;

    /**
     * 登陆用户类型（euip账户，本地账户)
     */
    private String userType;

    public LoginPrincipal() {

    }

    public LoginPrincipal(String userName, String userType) {
        this.userName = userName;
        this.userType = userType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Override
    public String getName() {
        return this.userName;
    }


}

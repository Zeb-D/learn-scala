package com.yd.security.mySecurity;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class MyAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }

        String username = obtainUsername(request);
        String password = obtainPassword(request);
        String roletype = request.getParameter("roletype");

        username = username.trim();

        UsernamePasswordAuthenticationToken authRequest = null;

        if(!"".equals(roletype) || roletype != null){
            if("student".equals(roletype)){
                //通过session把用户对象设置到session中
                request.getSession().setAttribute("session_user", username);

                //将角色标志在username上
                username = "stu"+username;
                try {
                    if (username == null || !username.equals(password)) {
                        BadCredentialsException exception = new BadCredentialsException("用户名或密码不匹配");
                        throw exception;
                    }
                } catch (Exception e) {
                    BadCredentialsException exception = new BadCredentialsException("没有此用户");
                    throw exception;
                }

            }else if("teacher".equals(roletype)){
                //通过session把用户对象设置到session中
                request.getSession().setAttribute("session_user", username);

                //将角色标志在username上
                username = "tea"+username;

                try {
                    if (username == null || !username.equals(password)) {
                        BadCredentialsException exception = new BadCredentialsException("用户名或密码不匹配");
                        throw exception;
                    }
                } catch (Exception e) {
                    BadCredentialsException exception = new BadCredentialsException("没有此用户");
                    throw exception;
                }

            }else if("admin".equals(roletype)){
                //通过session把用户对象设置到session中
                request.getSession().setAttribute("session_user", username);

                //将角色标志在username上
                username = "adm"+username;
                try {
                    if (username == null || !username.equals(password)) {
                        BadCredentialsException exception = new BadCredentialsException("用户名或密码不匹配");
                        throw exception;
                    }
                } catch (Exception e) {
                    BadCredentialsException exception = new BadCredentialsException("没有此用户");
                    throw exception;
                }

            }else{
                BadCredentialsException exception = new BadCredentialsException("系统错误：没有对应的角色！");
                throw exception;
            }
        }

        //实现验证
        authRequest = new UsernamePasswordAuthenticationToken(username, password);
        //允许设置用户详细属性
        setDetails(request, authRequest);
        //运行
        return this.getAuthenticationManager().authenticate(authRequest);
    }

    @Override
    protected String obtainUsername(HttpServletRequest request) {
        Object obj = request.getParameter(USERNAME);
        return null == obj ? "" : obj.toString();
    }

    @Override
    protected String obtainPassword(HttpServletRequest request) {
        Object obj = request.getParameter(PASSWORD);
        return null == obj ? "" : obj.toString();
    }

    @Override
    protected void setDetails(HttpServletRequest request, UsernamePasswordAuthenticationToken authRequest) {
        super.setDetails(request, authRequest);
    }
}

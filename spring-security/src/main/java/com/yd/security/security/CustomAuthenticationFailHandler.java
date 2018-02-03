package com.yd.security.security;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Date;

/**
 * @author Yd on  2018-01-20
 * @Description：
 **/
public class CustomAuthenticationFailHandler extends
        SimpleUrlAuthenticationFailureHandler {
    public static final String SPRING_SECURITY_FORM_USERNAME_KEY = "username";
    public static final String SPRING_SECURITY_FORM_PASSWORD_KEY = "password";

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response, AuthenticationException exception)
            throws IOException, ServletException {

        //密码错误做重试次数加1处理
        boolean isLoginPwdErr = true;
        if (isLoginPwdErr) {
            String userNo = request.getParameter(SPRING_SECURITY_FORM_USERNAME_KEY);

            if (StringUtils.isEmpty(userNo)) {
                if (logger.isDebugEnabled()) {
                    logger.debug(MessageFormat.format("登录认证失败[{1}]:[loginName={0}].", userNo, new Date()));
                }
                //登入错误次数+1 并且写入操作日志
            }

            //密码错误不能直接提示
            exception = new BadCredentialsException("登录失败, 请输入正确的用户名和密码!");
        }

        //登录失败，再次登录需加上验证码
        request.getSession().setAttribute("j_needCode", true);

        super.onAuthenticationFailure(request, response, exception);

    }

}

package com.yd.security.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
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
public class CustomAuthenticationSuccessHandler extends
        SavedRequestAwareAuthenticationSuccessHandler {
    private final Logger logger = LoggerFactory.getLogger(CustomAuthenticationSuccessHandler.class);

    private static final String BIND_NOTESID_URL = "/bindNotesid.do";
    //未绑定notesid用户需跳转至绑定页面
    private String bindNotesidUrl = BIND_NOTESID_URL;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {
        SessionContext sessionContext = (SessionContext) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (logger.isDebugEnabled()) {
            logger.debug("sessionContext" + sessionContext.getUserNo());
        }
        request.getSession().setAttribute("j_needCode", false);//登录成功，取消验证码

        super.onAuthenticationSuccess(request, response, authentication);
    }

    /**
     * 重写父类方法,根据用户是否绑定notesid来返回不同的跳转地址
     * add by zhengwu at 2016.04.13
     */
    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response) {
        Object sessionContext = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String targetUrl = super.getDefaultTargetUrl();
        //未绑定notesid,且用户类型为普通操作员的才跳转至绑定界面
        if (StringUtils.isEmpty(sessionContext)) {
            targetUrl = bindNotesidUrl;
        }
        return targetUrl;
    }

    public String getBindNotesidUrl() {
        return bindNotesidUrl;
    }

    public void setBindNotesidUrl(String bindNotesidUrl) {
        this.bindNotesidUrl = bindNotesidUrl;
    }
}

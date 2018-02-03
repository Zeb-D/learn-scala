package com.yd.security.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Iterator;

/**
 * @author Yd on  2018-01-20
 * @Description：
 **/
@Service("menuAccessDecisionManagerBean")
public class MenuAccessDecisionManager implements AccessDecisionManager {
    private static Logger logger = LoggerFactory.getLogger(MenuAccessDecisionManager.class);

    @Override
    public void decide(Authentication authentication, Object object,
                       Collection<ConfigAttribute> configAttributes)
            throws AccessDeniedException, InsufficientAuthenticationException {

        if (configAttributes == null) {
            return;
        }

        SessionContext sessionContext = (SessionContext) authentication
                .getPrincipal();
        FilterInvocation fi = (FilterInvocation) object;
        HttpServletRequest req = fi.getHttpRequest();
        String funId = (String) req.getAttribute("FUN_ID");
        String url = req.getRequestURL().toString();

        StringBuilder sb = null;
        if (logger.isDebugEnabled()) {
            sb = new StringBuilder();
            sb.append("url=[" + url + "]\nconfig=[" + configAttributes
                    + "]\nfunId=[" + funId + "]\nauthentication=[");

            boolean b = false;
            for (GrantedAuthority ga : authentication.getAuthorities()) {
                if (b) {
                    sb.append(',');
                } else {
                    b = true;
                }
                sb.append(ga);
            }

            sb.append(']');
        }

    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        // TODO Auto-generated method stub
        return true;
    }

    /**
     * 根据用户角色类型检查
     *
     * @param configAttributes
     * @param authentication
     * @return
     */
    private boolean decideByAuthentication(Collection<ConfigAttribute> configAttributes, Authentication authentication, String funId, SessionContext sessionContext) {
        boolean flag = false;
        Iterator<ConfigAttribute> ite = configAttributes.iterator();
        while (ite.hasNext()) {
            ConfigAttribute ca = ite.next();
            String needRole = ca.getAttribute();
            for (GrantedAuthority ga : authentication.getAuthorities()) {
                if (needRole.equals(ga.getAuthority())) {// ga is user's role.
                    return true;
                }
            }
        }
        return flag;
    }

}


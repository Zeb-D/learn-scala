package com.yd.security.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Yd on  2018-01-20
 * @Description：
 **/
@Service("menuSecurityMetadataSource")
public class MenuInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource, InitializingBean {
    private final static String PARENT_MENU_URL = "#";
    private final Logger logger = LoggerFactory.getLogger(MenuInvocationSecurityMetadataSource.class);

    public MenuInvocationSecurityMetadataSource() {
    }

    private String removeSessionId(String uri) {
        int i = uri.indexOf(';');
        if (i != -1) {
            uri = uri.substring(0, i);
        }
        return uri;
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {

        return null;
    }

    /**
     * 获取菜单权限列表
     *
     * @return
     */
    private Map<String, Collection<ConfigAttribute>> getMenuResourceMap() {
        Map<String, Collection<ConfigAttribute>> resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
        return resourceMap;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    public void afterPropertiesSet() throws Exception {
    }

}


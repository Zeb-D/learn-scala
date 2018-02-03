package com.yd.security.security;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author Yd on  2018-01-20
 * @Description：
 **/
public class UserDetailsServiceImpl implements UserDetailsService {

    private static Logger logger = LoggerFactory
            .getLogger(UserDetailsServiceImpl.class);

    /**
     * 获取用户Details信息的回调函数.
     */
    public UserDetails loadUserByUsername(String userName)
            throws UsernameNotFoundException, DataAccessException {
        // String password = SecurityContextHolder.getPassword();
        // TODO 实现从数据库获取用户对象的业务逻辑

        /**
         * *
         * wizard begin 这里开发人员可根据实际业务情况，从数据库读取用户信息赋值给User对象。 例如： Saccount
         * account = UserManager.getUserByName(userName);
         * 如果用户不存在可以抛出UsernameNotFoundException; authSet
         * 是用户的资源集合，可通过配置用户和资源的多对多的关系来获取。 详情可参见用户使用说明手册。
         */
        // 1-简单值校验
        if (StringUtils.isBlank(userName)) {
            throw new BadCredentialsException("登录异常，登录信息不完整！");
        }

        SessionContext sessionContext = new SessionContext();
        sessionContext.setUserNo("axzas");
        sessionContext.setBranchNo("1234");

        /**
         * wizard end
         */
        return sessionContext;
    }
}

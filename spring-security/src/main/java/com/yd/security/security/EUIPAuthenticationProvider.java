package com.yd.security.security;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.authentication.encoding.PlaintextPasswordEncoder;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author Yd on  2018-01-20
 * @Description：
 **/
public class EUIPAuthenticationProvider extends
        AbstractUserDetailsAuthenticationProvider {

    private static Logger logger = LoggerFactory.getLogger(EUIPAuthenticationProvider.class);

    private PasswordEncoder passwordEncoder = new PlaintextPasswordEncoder();

    private SaltSource saltSource;

    private boolean includeDetailsObject = true;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails,
                                                  UsernamePasswordAuthenticationToken authentication)
            throws AuthenticationException {

        LoginPrincipal loginPrincipal = (LoginPrincipal) authentication.getPrincipal();
        String userType = loginPrincipal.getUserType();
        if (StringUtils.isBlank(userType)) {
            throw new AuthenticationServiceException("登录账户类型不能为空");
        }

        if (LoginPrincipal.USER_TYPE_LOCAL.equals(userType)) {//用户类型为本地原始账户的才需要校验密码
            Object salt = null;

            if (this.saltSource != null) {
                salt = this.saltSource.getSalt(userDetails);
            }

            if (authentication.getCredentials() == null) {
                throw new BadCredentialsException(messages.getMessage(
                        "AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"),
                        includeDetailsObject ? userDetails : null);
            }

            String presentedPassword = authentication.getCredentials().toString();

            if (!passwordEncoder.isPasswordValid(userDetails.getPassword(), presentedPassword, salt)) {
                throw new BadCredentialsException(messages.getMessage(
                        "AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"),
                        includeDetailsObject ? userDetails : null);
            }
        }

    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {

        LoginPrincipal loginPrincipal = (LoginPrincipal) authentication.getPrincipal();
        String userType = loginPrincipal.getUserType();
        if (StringUtils.isBlank(userType)) {
            throw new AuthenticationServiceException("登录账户类型不能为空");
        }

        UserDetails loadedUser = null;
        //service.findByName(username);

        return loadedUser;
    }

}


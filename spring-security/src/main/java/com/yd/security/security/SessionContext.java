package com.yd.security.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
/**
 * @author Yd on  2018-01-20
 * @Description：
 **/
public class SessionContext extends Hashtable<String, Object> implements UserDetails {
    private static final long serialVersionUID = 1L;

    public final static String KEY_BRANCH_NO = "BRANCH_NO"; //用户归属机构代号
    public final static String KEY_BRANCH_NAME = "BRANCH_NAME"; //用户归属机构名称
    public final static String KEY_BRANCH_LEVEL = "BRANCH_LEVEL"; //机构级别

    public final static String KEY_USER_NO = "USRID";// 操作员工号
    public final static String KEY_USERNAME = "NAME";// 操作员名称
    public final static String KEY_PASSWORD = "PWD";// 密码
    public final static String KEY_SYSROLE = "USRTYPE";// 系统操作员类型(0-管理员 1-主管 2-普通用户)
    public final static String KEY_BIZ2ROLES = "BIZ2ROLES";//授权书中业务->角色对应关系

    public final static String KEY_MENUS = "MENUS";//操作员拥有菜单
    public final static String KEY_CHANNEL_LIST = "CHANNEL_LIST";//常用功能By AsOne

    public final static String KEY_FAIL_TIME = "FAILTIME";// 最近失败时间
    public final static String KEY_SUCC_TIME = "SUCCTIME";// 最近成功时间
    public final static String KEY_LOCALE = "LOCALE";// 多语言设置(中文、英文等)

    public final static String KEY_ASSIGNEE = "ASSIGNEE";// 指定复核/授权人员
    public final static String KEY_CHANNEL = "CHANNEL";//应用渠道
    public final static String KEY_OFX_ONLINE = "ONLNE";//银企直连

    public final static String RESET_PASSWORD_TIME="RESETPASSWORDTIME"; //更新密码时间
    public final static String RESET_PASSWORD="RESETPASSWORD"; //首次登入密码是否修改

    public final static String KEY_NOTESID = "NOTESID"; //notesid

    private static boolean disabled;


    public SessionContext() {
        super();
    }

    public SessionContext(int initialCapacity) {
        super(initialCapacity);
    }

    public SessionContext(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }

    public SessionContext(Map<String, Object> map) {
        super(map);
    }

    /**
     * 获取用户归属机构代码
     * @param branchNo
     */
    public void setBranchNo(String branchNo) {
        this.put(KEY_BRANCH_NO, branchNo);
    }

    /**
     * 设置用户归属机构代码
     */
    public String getBranchNo() {
        return (String) this.get(KEY_BRANCH_NO);
    }

    /**
     * 获取用户归属机构名称
     */
    public void setBranchName(String branchName) {
        this.put(KEY_BRANCH_NAME, branchName);
    }

    /**
     * 设置用户归属机构名称
     */
    public String getBranchName() {
        return (String) this.get(KEY_BRANCH_NAME);
    }

    /**
     * 获取用户归属机构级别
     */
    public void setBranchLevel(Integer branchLevel) {
        this.put(KEY_BRANCH_LEVEL, branchLevel);
    }

    /**
     * 设置用户归属机构代码
     */
    public Integer getBranchLevel() {
        return (Integer) this.get(KEY_BRANCH_LEVEL);
    }

    /**
     * 设置操作员工号
     * @param userNo
     */
    public void setUserNo(String userNo) {
        this.put(KEY_USER_NO, userNo);
    }

    /**
     * 获取操作员工号
     * @return
     */
    public String getUserNo() {
        return (String) this.get(KEY_USER_NO);
    }

    /**
     * 设置操作员姓名
     * @param userName
     */
    public void setUserName(String userName) {
        this.put(KEY_USERNAME, userName);
    }

    /**
     * 获取操作员姓名
     * @return
     */
    public String getUserName() {
        return (String) this.get(KEY_USERNAME);
    }

    /**
     * 获取notesid
     * @return
     */
    public String getNotesid() {
        return (String)this.get(KEY_NOTESID);
    }

    /**
     * 设置notesid
     * @param notesid
     */
    public void setNotesid(String notesid) {
        this.put(KEY_NOTESID, notesid);
    }

    /**
     * 设置操作员密码
     * @param password
     */
    public void setUserPassword(String password) {
        this.put(KEY_PASSWORD, password);
    }

    /**
     * 获取操作员密码
     * @return
     */
    public String getUserPassword() {
        return (String) this.get(KEY_PASSWORD);
    }

    /**
     * 设置系统管理角色
     */
    public void setSystemRole(String role) {
        this.put(KEY_SYSROLE, role);
    }

    /**
     * 获取系统管理角色
     * @return
     */
    public String getSystemRole() {
        return (String) this.get(KEY_SYSROLE);
    }

    /**
     * 设置业务角色
     * @param bizRoles
     */
    public void setBizRoles(Map<String, Set<String>> bizRoles) {
        this.put(KEY_BIZ2ROLES, bizRoles);
    }

    /**
     * 获取业务角色
     * @return
     */
    @SuppressWarnings("unchecked")
    public Map<String, Set<String>> getBizRoles() {
        return (Map<String, Set<String>>) this.get(KEY_BIZ2ROLES);
    }

    /**
     * 判断是否拥有业务权限
     * @param bizCode
     * @param roleId
     * @return
     */
    @SuppressWarnings("unchecked")
    public boolean isBizRole(String bizCode, String roleId) {
        Map<String, Set<String>> map = (Map<String, Set<String>>) this.get(KEY_BIZ2ROLES);
        if (map == null) {
            return false;
        }

        Set<String> roles = map.get(bizCode);
        if (roles == null) {
            return false;
        }

        return roles.contains(roleId);
    }

    /**
     * 获取上次失败时间
     * @return
     */
    public Date getLastFailTime() {
        return (Date) this.get(KEY_FAIL_TIME);
    }

    /**
     * 获取上次成功时间
     * @return
     */
    public Date getLastSuccTime() {
        return (Date) this.get(KEY_SUCC_TIME);
    }

    /**
     * 设置失败时间
     * @param lastFailTime
     */
    public void setLastFailTime(Date lastFailTime) {
        this.put(KEY_FAIL_TIME, lastFailTime);
    }

    /**
     * 设置成功时间
     * @param lastSuccTime
     */
    public void setLastSuccTime(Date lastSuccTime) {
        this.put(KEY_SUCC_TIME, lastSuccTime);
    }



    /**
     * 是否密码更新时间超过180
     * @return
     */
    public Boolean getisUpPasswordTimeOver() {
        return (Boolean) this.get(RESET_PASSWORD_TIME);
    }
    /**
     * 设置密码更新时间是否超过180
     */
    public void setisUpPasswordTimeOver(boolean isUpPasswordTimeOver) {
        this.put(RESET_PASSWORD_TIME,isUpPasswordTimeOver);
    }
    /**
     * 获取是否重置初始密码
     */
    public boolean getisInitPassword() {
        return  (Boolean) this.get(RESET_PASSWORD);
    }
    /**
     * 设置 是否初始化初始密码
     */
    public void setisInitPassword(boolean initPassword) {
        this.put(RESET_PASSWORD, initPassword);
    }

    public Collection<GrantedAuthority> getAuthorities() {
//		List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
//		grantedAuthorities.add(new GrantedAuthorityImpl((String) this.get(KEY_SYSROLE)));
//		return grantedAuthorities.toArray(new GrantedAuthority[1]);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
        grantedAuthorities.add(new GrantedAuthorityImpl((String) this.get(KEY_SYSROLE)));
        return grantedAuthorities;
    }

    public String getAuthoritiesString() {
        List<String> authorities = new ArrayList<String>();
        for (GrantedAuthority authority : this.getAuthorities()) {
            authorities.add(authority.getAuthority());
        }
        return StringUtils.join(authorities, ",");
    }

    public String getPassword() {
        return (String) this.get(KEY_PASSWORD);
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return !disabled;
    }

    public static void setDisabled(boolean disabled) {
        SessionContext.disabled = disabled;
    }

    public String getMachineFlag() {
        return "";
    }

    public void setLocale(Locale locale) {
        this.put(KEY_LOCALE, locale);
    }

    public Locale getLocale() {
        return (Locale) this.get(KEY_LOCALE);
    }

    public String getOfxOnline(){
        return (String)this.get(KEY_OFX_ONLINE);
    }

    public void setOfxOnline(String ofxOnline){
        this.put(KEY_OFX_ONLINE, ofxOnline);
    }

    @Override
    public boolean equals(Object that) {
        if (null == that) {
            return false;
        }
        if (that == this) {
            return true;
        }
        if (this.getClass() == that.getClass()) {
            SessionContext sc = (SessionContext) that;
            return this.getUserNo().equals(sc.getUserNo());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.getUserNo().hashCode();
    }

    @Override
    public String getUsername() {
        return (String) this.get(KEY_USERNAME);
    }

}


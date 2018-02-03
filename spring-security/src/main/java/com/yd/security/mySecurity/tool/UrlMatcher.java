package com.yd.security.mySecurity.tool;

/**
 * @author Yd on  2018-01-22
 * @Description：
 **/

public interface UrlMatcher{
    Object compile(String paramString);
    boolean pathMatchesUrl(Object paramObject, String paramString);
    String getUniversalMatchPattern();
    boolean requiresLowerCaseUrl();
}


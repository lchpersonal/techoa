
package com.tech.oa.util;

        import javax.servlet.http.HttpServletRequest;

/**
 * Created by chengli on 2016/11/16.
 */
public class SessionUtil {
    private static final String SESSION_USER_KEY = "username";

    public static String getUserId(HttpServletRequest req) {
        return req.getSession().getAttribute(SESSION_USER_KEY) + "";
    }
}

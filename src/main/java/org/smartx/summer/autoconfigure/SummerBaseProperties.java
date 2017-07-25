package org.smartx.summer.autoconfigure;

import org.smartx.summer.session.SessionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by Wenxin on 2017/7/24.
 */
@ConfigurationProperties(
        prefix = "summer"
)
public class SummerBaseProperties {

    @Value("${jwt-token-secret:ajMybGtqZGZwYW1qY3ZrbHdqd2Vy}")
    private String jwtTokenSecret;

    @Value("${session-manager:org.smartx.summer.session.Impl.SessionManagerImpl}")
    private Class<SessionManager> sessionManager;

    @Value("${web-expire-time:43200}")
    private Integer webExpireTime;

    @Value("${app-expire-time:0}")
    private Integer appExpireTime;

    public String getJwtTokenSecret() {
        return jwtTokenSecret;
    }

    public void setJwtTokenSecret(String jwtTokenSecret) {
        this.jwtTokenSecret = jwtTokenSecret;
    }

    public Class<SessionManager> getSessionManager() {
        return sessionManager;
    }

    public void setSessionManager(Class<SessionManager> sessionManager) {
        this.sessionManager = sessionManager;
    }

    public Integer getWebExpireTime() {
        return webExpireTime;
    }

    public void setWebExpireTime(Integer webExpireTime) {
        this.webExpireTime = webExpireTime;
    }

    public Integer getAppExpireTime() {
        return appExpireTime;
    }

    public void setAppExpireTime(Integer appExpireTime) {
        this.appExpireTime = appExpireTime;
    }
}

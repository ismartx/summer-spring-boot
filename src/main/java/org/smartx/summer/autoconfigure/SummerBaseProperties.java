package org.smartx.summer.autoconfigure;

import org.smartx.summer.session.SessionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by Wenxin on 2017/7/24.
 */
@ConfigurationProperties(
        prefix = "org.smartx.summer"
)
public class SummerBaseProperties {

    @Value("${jwt-token-secret:ajMybGtqZGZwYW1qY3ZrbHdqd2Vy}")
    private String jwtTokenSecret;

    @Value("${session-manager:org.smartx.summer.session.Impl.SessionManagerImpl}")
    private Class<SessionManager> sessionManager;

    @Value("${audience-expire-time:WEB:43200;APP:0}")
    private String audienceExpireTime;

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

    public String getAudienceExpireTime() {
        return audienceExpireTime;
    }

    public void setAudienceExpireTime(String audienceExpireTime) {
        this.audienceExpireTime = audienceExpireTime;
    }
}

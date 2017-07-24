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

    @Value("${jwt-filter-patterns:/api/*}")
    private String jwtFilterPatterns;

    private String jwtFilterExcludeUrl;

    @Value("${audience-expire-time:WEB:43200;APP:0}")
    private String audienceExpireTime;

    @Value("${logging-filter-url-patterns:/api/*}")
    private String loggingFilterUrlPatterns;

    @Value("${logging=filter-url-patterns:/api/base/*}")
    private String loggoingFilterExcludeUrl;

    @Value("${session-manager:org.smartx.summer.session.Impl.SessionManagerImpl}")
    private Class<SessionManager> sessionManager;

    public String getJwtTokenSecret() {
        return jwtTokenSecret;
    }

    public void setJwtTokenSecret(String jwtTokenSecret) {
        this.jwtTokenSecret = jwtTokenSecret;
    }

    public String getJwtFilterPatterns() {
        return jwtFilterPatterns;
    }

    public void setJwtFilterPatterns(String jwtFilterPatterns) {
        this.jwtFilterPatterns = jwtFilterPatterns;
    }

    public String getAudienceExpireTime() {
        return audienceExpireTime;
    }

    public void setAudienceExpireTime(String audienceExpireTime) {
        this.audienceExpireTime = audienceExpireTime;
    }

    public String getLoggingFilterUrlPatterns() {
        return loggingFilterUrlPatterns;
    }

    public void setLoggingFilterUrlPatterns(String loggingFilterUrlPatterns) {
        this.loggingFilterUrlPatterns = loggingFilterUrlPatterns;
    }

    public String getLoggoingFilterExcludeUrl() {
        return loggoingFilterExcludeUrl;
    }

    public void setLoggoingFilterExcludeUrl(String loggoingFilterExcludeUrl) {
        this.loggoingFilterExcludeUrl = loggoingFilterExcludeUrl;
    }

    public Class<SessionManager> getSessionManager() {
        return sessionManager;
    }

    public void setSessionManager(Class<SessionManager> sessionManager) {
        this.sessionManager = sessionManager;
    }

    public String getJwtFilterExcludeUrl() {
        return jwtFilterExcludeUrl;
    }

    public void setJwtFilterExcludeUrl(String jwtFilterExcludeUrl) {
        this.jwtFilterExcludeUrl = jwtFilterExcludeUrl;
    }
}

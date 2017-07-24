package org.smartx.summer.autoconfigure;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by Wenxin on 2017/7/24.
 */
@ConfigurationProperties(
        prefix = "org.smartx.summer.web"
)
public class SummerWebProperties {

    @Value("${jwt-filter-patterns:/api/*}")
    private String jwtFilterPatterns;

    @Value("${jwt-filter-exclude-url:/api/*}")
    private String jwtFilterExcludeUrl;

    @Value("${logging-filter-url-patterns:/api/*}")
    private String loggingFilterUrlPatterns;

    @Value("${logging=filter-url-patterns:/api/base/*}")
    private String loggoingFilterExcludeUrl;

    public String getJwtFilterPatterns() {
        return jwtFilterPatterns;
    }

    public void setJwtFilterPatterns(String jwtFilterPatterns) {
        this.jwtFilterPatterns = jwtFilterPatterns;
    }

    public String getJwtFilterExcludeUrl() {
        return jwtFilterExcludeUrl;
    }

    public void setJwtFilterExcludeUrl(String jwtFilterExcludeUrl) {
        this.jwtFilterExcludeUrl = jwtFilterExcludeUrl;
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
}

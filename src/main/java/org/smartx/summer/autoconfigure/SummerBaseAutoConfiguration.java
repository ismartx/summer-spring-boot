package org.smartx.summer.autoconfigure;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smartx.summer.session.SessionManager;
import org.smartx.summer.session.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

import io.jsonwebtoken.lang.Assert;

/**
 * Created by Wenxin on 2017/7/24.
 */
@Configuration
@ConditionalOnProperty(
        name = "summer.enable",
        matchIfMissing = true
)
@EnableConfigurationProperties(SummerBaseProperties.class)
public class SummerBaseAutoConfiguration {

    private static final Logger log = LoggerFactory.getLogger(SummerBaseAutoConfiguration.class);

    @Autowired
    private SummerBaseProperties summerBaseProperties;

    @PostConstruct
    private void init() {
        checkRequiredProperties();
    }

    private void checkRequiredProperties() {
        Assert.hasText(summerBaseProperties.getJwtTokenSecret(), "jwt-token-secret must not be blank");
        Assert.notNull(summerBaseProperties.getSessionManager(), "session manager must not be null");
    }

    @Bean(name = "sessionManager")
    public SessionManager sessionManager() throws IllegalAccessException, InstantiationException {
        Class<SessionManager> sessionManager = summerBaseProperties.getSessionManager();
        log.warn("inject session manager: {}", sessionManager.getName());
        return sessionManager.newInstance();
    }

    @Bean(name = "tokenProvider")
    public TokenProvider tokenProvider() {
        TokenProvider tokenProvider = new TokenProvider();
        String audienceExpireTime = "WEB:".concat(summerBaseProperties.getWebExpireTime().toString()).concat(";")
                .concat("APP:").concat(summerBaseProperties.getAppExpireTime().toString());
        tokenProvider.setAudienceExpireTime(audienceExpireTime);
        tokenProvider.setSecret(summerBaseProperties.getJwtTokenSecret());
        log.warn("inject token provider");
        return tokenProvider;
    }
}

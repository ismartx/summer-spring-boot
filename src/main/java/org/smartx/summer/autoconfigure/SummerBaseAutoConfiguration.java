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
        name = "org.smartx.summer.enable",
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
        Assert.hasText(summerBaseProperties.getAudienceExpireTime(), "audience-expire-time must not be blank");
        Assert.notNull(summerBaseProperties.getSessionManager(), "session manager must not be null");
    }

    @Bean(name = "sessionManager")
    public SessionManager sessionManager() throws IllegalAccessException, InstantiationException {
        return summerBaseProperties.getSessionManager().newInstance();
    }

    @Bean(name = "tokenProvider")
    public TokenProvider tokenProvider() {
        TokenProvider tokenProvider = new TokenProvider();
        String audienceExpireTime = summerBaseProperties.getAudienceExpireTime();
        tokenProvider.setAudienceExpireTime(audienceExpireTime);
        tokenProvider.setSecret(summerBaseProperties.getJwtTokenSecret());
        log.info("注入 tokenProvider");
        return tokenProvider;
    }
}

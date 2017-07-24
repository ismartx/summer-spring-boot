package org.smartx.summer.autoconfigure;

import org.smartx.summer.redis.JedisClusterConnectionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by Wenxin on 2017/7/24.
 */
@ComponentScan(basePackages = {"org.smartx.summer.interceptor"})
@Configuration
@ConditionalOnProperty(
        name = "org.smartx.summer.enable",
        matchIfMissing = true
)
@EnableConfigurationProperties(SummerRedisProperties.class)
@AutoConfigureAfter(SummerBaseAutoConfiguration.class)
public class SummerWebAutoConfiguration {

}

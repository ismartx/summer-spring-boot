package org.smartx.summer.autoconfigure;

import org.smartx.summer.redis.JedisClusterConnectionFactoryBean;
import org.smartx.summer.redis.template.HashRedisTemplate;
import org.smartx.summer.redis.template.ValueRedisTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by Wenxin on 2017/7/24.
 */
@Configuration
@ConditionalOnProperty(
        name = "summer.redis.enable",
        matchIfMissing = true
)
@ConditionalOnMissingBean(HashRedisTemplate.class)
@AutoConfigureBefore(SummerBaseAutoConfiguration.class)
//@ComponentScan(basePackages = {"org.smartx.summer.redis"})
@EnableConfigurationProperties(SummerRedisProperties.class)
public class SummerRedisAutoConfiguration {


    @Autowired
    private SummerRedisProperties summerRedisProperties;

    @Bean
    public JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(summerRedisProperties.getPoolMaxTal());
        jedisPoolConfig.setMinIdle(summerRedisProperties.getPoolMinDle());
        jedisPoolConfig.setMaxIdle(summerRedisProperties.getPoolMaxDle());
        jedisPoolConfig.setTestOnBorrow(summerRedisProperties.getTestOnBorrow());
        return jedisPoolConfig;
    }

    @Bean
    public JedisClusterConnectionFactoryBean jedisClusterConnectionFactoryBean() {
        JedisClusterConnectionFactoryBean jedisClusterConnectionFactoryBean = new JedisClusterConnectionFactoryBean();
        jedisClusterConnectionFactoryBean.setServers(summerRedisProperties.getRedisServer());
        jedisClusterConnectionFactoryBean.setPoolConfig(this.jedisPoolConfig());
        return jedisClusterConnectionFactoryBean;
    }

    @Bean
    public HashRedisTemplate hashRedisTemplate() {
        return new HashRedisTemplate();
    }

    @Bean
    public ValueRedisTemplate valueRedisTemplate() {
        return new ValueRedisTemplate();
    }


}

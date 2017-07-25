package org.smartx.summer.autoconfigure;

import org.smartx.summer.redis.JedisClusterConnectionFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by Wenxin on 2017/7/24.
 */
@ConfigurationProperties(
        prefix = "summer.redis"
)
public class SummerRedisProperties {


    @Value("${servers:127.0.0.1:6379}")
    private String redisServer;

    @Value("${pool-max-total:20}")
    private Integer poolMaxTal;

    @Value("${pool-min-idle:5}")
    private Integer poolMinDle;

    @Value("${pool-max-idle:100}")
    private Integer poolMaxDle;

    @Value("${pool-test-on-borrow:true}")
    private Boolean testOnBorrow;

    public String getRedisServer() {
        return redisServer;
    }

    public void setRedisServer(String redisServer) {
        this.redisServer = redisServer;
    }

    public Integer getPoolMaxTal() {
        return poolMaxTal;
    }

    public void setPoolMaxTal(Integer poolMaxTal) {
        this.poolMaxTal = poolMaxTal;
    }

    public Integer getPoolMinDle() {
        return poolMinDle;
    }

    public void setPoolMinDle(Integer poolMinDle) {
        this.poolMinDle = poolMinDle;
    }

    public Integer getPoolMaxDle() {
        return poolMaxDle;
    }

    public void setPoolMaxDle(Integer poolMaxDle) {
        this.poolMaxDle = poolMaxDle;
    }

    public Boolean getTestOnBorrow() {
        return testOnBorrow;
    }

    public void setTestOnBorrow(Boolean testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
    }
}

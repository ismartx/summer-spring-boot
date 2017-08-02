## summer-spring-boot-starter
开源项目summer的starter
### 依赖
```$xslt
    <groupId>org.smartx</groupId>
    <artifactId>summer-spring-boot-starter</artifactId>
    <version>1.0-SNAPSHOT</version>
```
### 完整配置
#### jwt密钥
summer.jwt-token-secret   
默认ajMybGtqZGZwYW1qY3ZrbHdqd2Vy
#### 会话管理器
summer.session-manager   
默认使用基于redis的会话管理器  org.smartx.summer.session.Impl.SessionManagerImpl
#### web端token过期时间
web-expire-time 默认 43200
#### app端token过期时间
app-expire-time 默认 0
#### redis服务器配置
summer.redis.enable 默认true,为false需要指定会话管理
summer.redis.servers 默认 127.0.0.1:6379 多个用```,```隔开
#### redis连接池配置
summer.redis.pool-max-total  默认 20   
summer.redis.pool-min-idle   默认 5   
summer.redis.pool-max-idle   默认 100   
summer.redis.pool-test-on-borrow 默认true
#### summer-web配置
summer.web.jwt-filter-patterns 默认 /api/*
summer.web.jwt-filter-exclude-url 默认 /api/base/*
summer.web.logging-filter-url-patterns 默认/api/*
summer.web.logging=filter-url-patterns: 默认 /api/base/*
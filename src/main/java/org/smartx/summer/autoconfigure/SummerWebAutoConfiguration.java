package org.smartx.summer.autoconfigure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smartx.summer.config.register.FormatterRegister;
import org.smartx.summer.filter.JwtTokenAuthFilter;
import org.smartx.summer.filter.LoggingFilter;
import org.smartx.summer.interceptor.JwtInterceptor;
import org.smartx.summer.redis.JedisClusterConnectionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

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
@AutoConfigureAfter(SummerBaseAutoConfiguration.class)
@EnableConfigurationProperties(SummerWebProperties.class)
public class SummerWebAutoConfiguration  extends WebMvcConfigurerAdapter {

    @Autowired
    private SummerWebProperties summerWebProperties;

    @Autowired(required = false)
    private FormatterRegister formatterRegistry;

    @Autowired(required = false)
    private JwtInterceptor jwtInterceptor;

    private static Logger log = LoggerFactory.getLogger(SummerWebAutoConfiguration.class);

    @Bean
    public FilterRegistrationBean loggingFilter() {
        FilterRegistrationBean loggingFilter = new FilterRegistrationBean();
        loggingFilter.setFilter(new LoggingFilter());
        loggingFilter.addUrlPatterns(summerWebProperties.getLoggingFilterUrlPatterns());
        loggingFilter.addInitParameter("excludeUrl", summerWebProperties.getLoggoingFilterExcludeUrl());
        return loggingFilter;
    }

    @Order(5)
    @Bean
    public FilterRegistrationBean delegatingFilterProxy() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new DelegatingFilterProxy("jwtTokenAuthFilter"));
        filterRegistrationBean.addUrlPatterns(summerWebProperties.getJwtFilterPatterns());
        return filterRegistrationBean;
    }

    @Order(4)
    @Bean("jwtTokenAuthFilter")
    public JwtTokenAuthFilter jwtTokenAuthFilter() {
        JwtTokenAuthFilter jwtTokenAuthFilter = new JwtTokenAuthFilter();
        Set<Pattern> excludeUrl = new HashSet<>();
        excludeUrl.add(Pattern.compile(summerWebProperties.getJwtFilterExcludeUrl()));
        jwtTokenAuthFilter.setExcludeUrl(excludeUrl);
        return jwtTokenAuthFilter;
    }

    @Bean
    public FilterRegistrationBean registration(JwtTokenAuthFilter filter) {
        FilterRegistrationBean registration = new FilterRegistrationBean(filter);
        registration.setEnabled(false);
        return registration;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        if (jwtInterceptor != null) {
            log.info("injection jwtInterceptor ");
            registry.addInterceptor(jwtInterceptor);
        }
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        if (formatterRegistry != null) {
            formatterRegistry.registry(registry);
        }
    }


}

package com.mysiteforme.admin.config;

import com.google.common.collect.Maps;
import com.mysiteforme.admin.base.CaptchaFormAuthenticationFilter;
import com.mysiteforme.admin.realm.AuthRealm;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by wangl on 2017/11/22.
 * todo:
 */
@Configuration
public class ShiroConfig {
    private Logger  logger = LoggerFactory.getLogger(ShiroConfig.class);

    @Value("${spring.redis.host}")
    private String jedisHost;

    @Value("${spring.redis.port}")
    private Integer jedisPort;

    @Value("${spring.redis.password}")
    private String jedisPassword;

    @Bean
    public FilterRegistrationBean delegatingFilterProxy(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        DelegatingFilterProxy proxy = new DelegatingFilterProxy();
        proxy.setTargetFilterLifecycle(true);
        proxy.setTargetBeanName("shiroFilter");
        filterRegistrationBean.setFilter(proxy);
        filterRegistrationBean.setDispatcherTypes(DispatcherType.ERROR,DispatcherType.REQUEST,DispatcherType.FORWARD,DispatcherType.INCLUDE);
        return filterRegistrationBean;
    }

    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("authRealm")AuthRealm authRealm){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager(authRealm));
        bean.setSuccessUrl("/index");
        bean.setLoginUrl("/login");
        Map<String,Filter> map = Maps.newHashMap();
        map.put("authc",new CaptchaFormAuthenticationFilter());
        bean.setFilters(map);
        //配置访问权限
        LinkedHashMap<String, String> filterChainDefinitionMap = Maps.newLinkedHashMap();
        filterChainDefinitionMap.put("/static/**","anon");
        filterChainDefinitionMap.put("/showBlog/**","anon");
        filterChainDefinitionMap.put("/blog/**","anon");
        filterChainDefinitionMap.put("/login/main","anon");
//        filterChainDefinitionMap.put("/index","authc");
        filterChainDefinitionMap.put("/home","anon");
        filterChainDefinitionMap.put("/doLogin","anon");
        filterChainDefinitionMap.put("/index","anon");
        filterChainDefinitionMap.put("/doRegister","anon");
        filterChainDefinitionMap.put("/image/**","anon");
        filterChainDefinitionMap.put("/genCaptcha","anon");
        filterChainDefinitionMap.put("/systemLogout","authc");
        filterChainDefinitionMap.put("/**","authc");
        bean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return bean;
    }

    @Bean
    public SecurityManager securityManager(@Qualifier("authRealm")AuthRealm authRealm){
        logger.info("- - - - - - -shiro开始加载- - - - - - ");
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(authRealm);
        defaultWebSecurityManager.setRememberMeManager(rememberMeManager());
        defaultWebSecurityManager.setSessionManager(webSessionManager());
        defaultWebSecurityManager.setCacheManager(cacheManager());
        return defaultWebSecurityManager;
    }



    @Bean
    public SimpleCookie rememberMeCookie(){
        //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
        SimpleCookie cookie = new SimpleCookie("rememberMe");
        cookie.setHttpOnly(true);
        //记住我有效期长达30天
        cookie.setMaxAge(2592000);
        return cookie;
    }

    @Bean
    public CookieRememberMeManager rememberMeManager(){
        CookieRememberMeManager rememberMeManager = new CookieRememberMeManager();
        rememberMeManager.setCookie(rememberMeCookie());
        rememberMeManager.setCipherKey(Base64.decode("2AvVhdsgUs0FSA3SDFAdag=="));
        return rememberMeManager;
    }

    /**
     * AOP式方法级权限检查
     * @return
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator creator=new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }

    /**
     * 保证实现了Shiro内部lifecycle函数的bean执行
     * @return
     */
    @Bean
    public static LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("authRealm")AuthRealm authRealm) {
        SecurityManager manager= securityManager(authRealm);
        AuthorizationAttributeSourceAdvisor advisor=new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(manager);
        return advisor;
    }

    @Bean
    public SessionManager webSessionManager(){
        DefaultWebSessionManager manager = new DefaultWebSessionManager();
        //设置session过期时间为1小时(单位：毫秒)，默认为30分钟
        manager.setGlobalSessionTimeout(60 * 60 * 1000);
        manager.setSessionValidationSchedulerEnabled(true);
        manager.setSessionDAO(redisSessionDAO());
        return manager;
    }

    @Bean
    public RedisManager redisManager(){
        RedisManager manager = new RedisManager();
        manager.setHost(jedisHost);
        manager.setPort(jedisPort);
        //这里是用户session的时长 跟上面的setGlobalSessionTimeout 应该保持一直（上面是1个小时 下面是秒做单位的 我们设置成3600）
        manager.setExpire(60 * 60);
        manager.setPassword(jedisPassword);
        return manager;
    }

    @Bean
    public RedisSessionDAO redisSessionDAO(){
        RedisSessionDAO sessionDAO = new RedisSessionDAO();
        sessionDAO.setKeyPrefix("wl_");
        sessionDAO.setRedisManager(redisManager());
        return sessionDAO;
    }

    @Bean("myCacheManager")
    public RedisCacheManager cacheManager(){
        RedisCacheManager manager = new RedisCacheManager();
        manager.setRedisManager(redisManager());
        return manager;
    }
}

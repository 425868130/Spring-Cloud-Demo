package com.example.authcenter.config;

import com.example.authcenter.entity.StatelessDefaultSubjectFactory;
import com.example.authcenter.realm.JsonWebTokenRealm;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@Configuration
public class ShiroConfig {
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager, JWTFilter jwtFilter) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 将securityManager交由SecurityUtils管理
        SecurityUtils.setSecurityManager(securityManager);
        // 自定义过滤器
        Map<String, Filter> filterMap = new HashMap<>();
        filterMap.put("jwtFilter", jwtFilter);
        shiroFilterFactoryBean.setFilters(filterMap);

        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setUnauthorizedUrl("/unauthc");

        // 设置拦截器
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();

        // 开放登录、未登录等映射
        filterChainDefinitionMap.put("/doLogin", "anon");

        // 拦截接口
//        filterChainDefinitionMap.put("/authc/**", "roles[admin]");

        /*其余的默认全部使用jwtFilter,过滤链有顺序要求,这条规则必须在最后配置,否则前面的开放路径配置都会被覆盖*/
        filterChainDefinitionMap.put("/token/**", "jwtFilter");
        filterChainDefinitionMap.put("/authc/**", "jwtFilter[admin]");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        log.info("Shiro >> Shiro拦截器工厂类注入成功");
        return shiroFilterFactoryBean;
    }

    /**
     * 注入 securityManager
     */
    @Bean
    public SecurityManager securityManager(JsonWebTokenRealm jsonWebTokenRealm, StatelessDefaultSubjectFactory subjectFactory) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 设置 realm.
        securityManager.setRealm(jsonWebTokenRealm);

        // 关闭 shiro 自带的session
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        //禁用session存储
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);

        securityManager.setSubjectDAO(subjectDAO);

        DefaultSessionManager sessionManager = new DefaultSessionManager();
        //禁用定时过期会话
        sessionManager.setSessionValidationSchedulerEnabled(false);

        securityManager.setSubjectFactory(subjectFactory);
        securityManager.setSessionManager(sessionManager);
        return securityManager;
    }
}

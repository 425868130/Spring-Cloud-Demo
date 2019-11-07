## 采坑笔记  

shiro整合jwt配置无状态授权认证时,请求验证token时,执行到getSubject(request, response).login(token)时抛出异常
```log
org.apache.shiro.UnavailableSecurityManagerException: No SecurityManager accessible to the calling code, either bound to the org.apache.shiro.util.ThreadContext or as a vm static singleton.  This is an invalid application configuration.
	at org.apache.shiro.SecurityUtils.getSecurityManager(SecurityUtils.java:123) ~[shiro-core-1.4.0.jar:1.4.0]
	at org.apache.shiro.subject.Subject$Builder.<init>(Subject.java:626) ~[shiro-core-1.4.0.jar:1.4.0]
	at org.apache.shiro.SecurityUtils.getSubject(SecurityUtils.java:56) ~[shiro-core-1.4.0.jar:1.4.0]
	at org.apache.shiro.web.filter.AccessControlFilter.getSubject(AccessControlFilter.java:97) ~[shiro-web-1.4.0.jar:1.4.0]
	at com.example.authcenter.config.JWTFilter.executeLogin(JWTFilter.java:37) ~[classes/:na]
    ...
```
百度多种办法都是基于session的解决方法，均不适用，多次尝试后发现的解决办法：在配置ShiroFilterFactoryBean时强行给SecurityUtils指定当前的securityManager,即增加配置代码 ` SecurityUtils.setSecurityManager(securityManager);`
```java
    @Bean
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager, JWTFilter jwtFilter) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        /** 给SecurityUtils指定securityManager **/
        SecurityUtils.setSecurityManager(securityManager);
        // .... 其他配置项
        return shiroFilterFactoryBean;
    }
```

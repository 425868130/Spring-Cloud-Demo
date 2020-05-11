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

shiro重复执行认证逻辑

原因自定义JWTFliter未重写onAccessDenied()方法，而在过滤器基类AccessControlFilter中执行完login方法后会调用onPreHandle()方法来确定请求是否需要继续处理,查看onPreHandle()方法源码：
```java
public boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        return isAccessAllowed(request, response, mappedValue) || onAccessDenied(request, response, mappedValue);
    }

```
可知，onPreHandle方法需要执行isAccessAllowed或onAccessDenied方法,而父类BasicHttpAuthenticationFilter的onAccessDenied()方法中可见又调用了一次executeLogin(),因此导致再次执行认证
```java
 protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        boolean loggedIn = false; //false by default or we wouldn't be in this method
        if (isLoginAttempt(request, response)) {
            loggedIn = executeLogin(request, response);
        }
        if (!loggedIn) {
            sendChallenge(request, response);
        }
        return loggedIn;
    }
```
所以解决方法为重写父类的onPreHandle()方法，在其中只调用isAccessAllowed方法即可，当然如果业务逻辑有认证失败的后续操作则可以改为不重写onPreHandle而重写onAccessDenied()方法即可
```java
   @Override
    public boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        return isAccessAllowed(request, response, mappedValue);
    }
```
java 8 迁移java 11  
1. eureka 无法启动
```log
java.lang.TypeNotPresentException: Type javax.xml.bind.JAXBContext not present
...
Caused by: java.lang.ClassNotFoundException: javax.xml.bind.JAXBContext
```
可以添加以下依赖：
```xml
<dependency>
   <groupId>javax.xml.bind</groupId>
    <artifactId>jaxb-api</artifactId>
</dependency>
<dependency>
    <groupId>com.sun.xml.bind</groupId>
    <artifactId>jaxb-impl</artifactId>
    <version>2.3.0</version>
</dependency>
<dependency>
    <groupId>org.glassfish.jaxb</groupId>
    <artifactId>jaxb-runtime</artifactId>
    <version>2.3.0</version>
</dependency>
<dependency>
    <groupId>javax.activation</groupId>
    <artifactId>activation</artifactId>
    <version>1.1.1</version>
</dependency>
```
或者直接升级Spring boot和cloud到最新版本可以直接解决


2.常见异常警告
```log
WARNING: An illegal reflective access operation has occurred
WARNING: Illegal reflective access by com.thoughtworks.xstream.core.util.Fields (file:/F:/apache-maven-3.3.3/repository/com/thoughtworks/xstream/xstream/1.4.11.1/xstream-1.4.11.1.jar) to field java.util.TreeMap.comparator
WARNING: Please consider reporting this to the maintainers of com.thoughtworks.xstream.core.util.Fields
WARNING: Use --illegal-access=warn to enable warnings of further illegal reflective access operations
WARNING: All illegal access operations will be denied in a future release
```
解决方法:添加启动参数
```
--add-opens java.base/java.util=ALL-UNNAMED --add-opens java.base/java.lang.reflect=ALL-UNNAMED --add-opens java.base/java.text=ALL-UNNAMED --add-opens java.desktop/java.awt.font=ALL-UNNAMED
```

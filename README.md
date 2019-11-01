# Spring-Cloud-Demo
spring cloud学习

idea推荐插件：  
MapStruct Support
lombok

端口划分:  
注册中心eureka-server集群: 810x  
api网关gateway: 820x  
配置中心config-server: 830x  
授权中心auth-center: 840x
用户中心user-service:850x

笔记:  

server.port端口配置可以放云端

当把feign接口单独作为一个模块提供，其他模块通过maven进行jar包依赖时，使用feign接口的模块需要在启动类的@EnableFeignClients注解中指定包路径，否则启动时会提示自动注入失败。
```java
@EnableFeignClients(basePackages = {"com.feign.provider"})
```
Maven项目POM依赖关系:
modules-pom：保存当前项目下的全部模块版本信息,聚合全部子模块


第一阶段：  
完成注册中心及微服务整体框架搭建  
完成微服务间互相调用  
完成统一网关认证，shiro权限整合  
提供网关权限配置页面，动态配置路由权限规则    
整合微服务监测框架，实现全链路监控及日志  

第二阶段：  
完成用户中心微服务，实现账户权限管理、多租户模式、组织机构管理  
提供统一后台管理页面  
支持单点登录sso、支持第三方登录  


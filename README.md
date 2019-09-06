# Spring-Cloud-Demo
spring cloud学习

端口划分:  
注册中心eureka-server集群: 810x  
api网关gateway: 820x  
配置中心config-server: 830x  
授权中心auth-center: 840x

笔记:  

server.port端口配置可以放云端

当把feign接口单独作为一个模块提供，其他模块通过maven进行jar包依赖时，使用feign接口的模块需要在启动类的@EnableFeignClients注解中指定包路径，否则启动时会提示自动注入失败。
```
@EnableFeignClients(basePackages = {"com.feign.provider"})
```

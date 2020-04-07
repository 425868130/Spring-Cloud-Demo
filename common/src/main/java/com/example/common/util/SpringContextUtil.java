package com.example.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.ClassUtils;

import java.beans.Introspector;

/**
 * 获取spring容器，以访问容器中定义的其他bean
 */
public class SpringContextUtil implements ApplicationContextAware {

    // Spring应用上下文环境
    private static ApplicationContext applicationContext;

    /**
     * 实现ApplicationContextAware接口的回调方法，设置上下文环境
     *
     * @param applicationContext
     */
    public void setApplicationContext(ApplicationContext applicationContext) {
        SpringContextUtil.applicationContext = applicationContext;
    }

    /**
     * @return ApplicationContext
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 获取对象
     * 这里重写了bean方法，起主要作用
     *
     * @param name
     * @return Object 一个以所给名字注册的bean的实例
     * @throws BeansException
     */
    public static Object getBean(String name) throws BeansException {
        return applicationContext.getBean(name);
    }

    /**
     * 通过类获取该类的bean对象,用于没有手动指定bean名称的场景，入直接使用@Component、@Bean注解的类
     * 内部使用spring默认的bean名称生成方式获取bean名称
     *
     * @param clazz 要获取bean示例的对象
     * @return 一个对应于给定类的bean实例
     * @throws BeansException
     */
    public static Object getBeanByClass(Class clazz) throws BeansException {
        String shortClassName = ClassUtils.getShortName(clazz);
        return applicationContext.getBean(Introspector.decapitalize(shortClassName));
    }
}

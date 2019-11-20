package com.barter.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.socket.server.standard.ServerEndpointRegistration;

/**
 *  以websocketConfig.java注册的bean是由自己管理的，需要使用配置托管给spring管理
 */
public class MySpringConfigurator extends ServerEndpointRegistration.Configurator implements ApplicationContextAware {
 
    private static volatile BeanFactory context;
 
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        MySpringConfigurator.context = applicationContext;
    }
 
    @Override
    public <T> T getEndpointInstance(Class<T> clazz) throws InstantiationException {
        return context.getBean(clazz);
    }

}
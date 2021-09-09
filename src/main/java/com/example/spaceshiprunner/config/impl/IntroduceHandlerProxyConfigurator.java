package com.example.spaceshiprunner.config.impl;

import com.example.spaceshiprunner.annotation.Introduce;
import com.example.spaceshiprunner.config.ProxyConfigurator;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Заключает класс в прокси, если класс аннотирован @Introduce.
 * Перед вызовом метода выдается сообщение об имени класса.
 */
public class IntroduceHandlerProxyConfigurator implements ProxyConfigurator {
    @Override
    public Object replaceWithProxyIfNeeded(Object instance, Class implClass) {
        if (implClass.isAnnotationPresent(Introduce.class)) {
            return Proxy.newProxyInstance(implClass.getClassLoader(), implClass.getInterfaces(), new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    System.out.printf("С Вами работает %s (внутри меня %s): ",
                            proxy.getClass().getName(), instance.getClass().getName());
                    return method.invoke(instance);
                }
            });
        }
        return instance;
    }
}

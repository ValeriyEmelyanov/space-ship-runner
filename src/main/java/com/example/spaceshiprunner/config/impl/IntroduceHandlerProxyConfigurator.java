package com.example.spaceshiprunner.config.impl;

import com.example.spaceshiprunner.annotation.Introduce;
import com.example.spaceshiprunner.config.ProxyConfigurator;
import net.sf.cglib.proxy.Enhancer;

import java.lang.reflect.InvocationTargetException;
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
            if (implClass.getInterfaces().length == 0) {
                return Enhancer.create(implClass,
                        (net.sf.cglib.proxy.InvocationHandler) (proxy, method, args) ->
                                getInvocationHandlerLogic(instance, proxy, method, args));
            }

            return Proxy.newProxyInstance(implClass.getClassLoader(), implClass.getInterfaces(),
                    (proxy, method, args) -> getInvocationHandlerLogic(instance, proxy, method, args));
        }
        return instance;
    }

    private Object getInvocationHandlerLogic(Object instance, Object proxy, Method method, Object[] args)
            throws IllegalAccessException, InvocationTargetException {
        System.out.printf("С Вами работает %s (внутри меня %s): \n",
                proxy.getClass().getName(), instance.getClass().getName());
        return method.invoke(instance, args);
    }
}

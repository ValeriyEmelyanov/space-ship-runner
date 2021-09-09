package com.example.spaceshiprunner.core;

import com.example.spaceshiprunner.config.ObjectConfigurator;
import com.example.spaceshiprunner.config.ProxyConfigurator;
import lombok.SneakyThrows;

import javax.annotation.PostConstruct;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ObjectFactory {
    private final List<ObjectConfigurator> configurators = new ArrayList<>();
    private final List<ProxyConfigurator> proxyConfigurators = new ArrayList<>();
    private final ApplicationContext context;

    @SneakyThrows
    public ObjectFactory(ApplicationContext context) {
        this.context = context;
        for (Class<? extends ObjectConfigurator> type :
                context.getTypeConfig().getScanner().getSubTypesOf(ObjectConfigurator.class)) {
            configurators.add(type.getDeclaredConstructor().newInstance());
        }
        for (Class<? extends ProxyConfigurator> type :
                context.getTypeConfig().getScanner().getSubTypesOf(ProxyConfigurator.class)) {
            proxyConfigurators.add(type.getDeclaredConstructor().newInstance());
        }
    }

    @SneakyThrows
    public <T> T createObject(Class<T> implClass) {

        T instance = implClass.getDeclaredConstructor().newInstance();

        configure(instance);

        invokePostConstructor(implClass, instance);

        instance = wrapWithProxyIfNeeded(implClass, instance);

        return instance;
    }

    private <T> void configure(T instance) {
        configurators.forEach(configurator -> configurator.configure(instance, context));
    }

    private <T> void invokePostConstructor(Class<T> implClass, T instance)
            throws IllegalAccessException, InvocationTargetException {
        for (Method method : implClass.getMethods()) {
            if (method.isAnnotationPresent(PostConstruct.class)) {
                method.invoke(instance);
            }
        }
    }

    private <T> T wrapWithProxyIfNeeded(Class<T> implClass, T instance) {
        for (ProxyConfigurator proxyConfigurator : proxyConfigurators) {
            instance = (T) proxyConfigurator.replaceWithProxyIfNeeded(instance, implClass);
        }
        return instance;
    }
}

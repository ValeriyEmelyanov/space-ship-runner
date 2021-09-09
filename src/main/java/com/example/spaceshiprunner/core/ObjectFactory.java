package com.example.spaceshiprunner.core;

import com.example.spaceshiprunner.config.ObjectConfigurator;
import lombok.SneakyThrows;

import javax.annotation.PostConstruct;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ObjectFactory {
    private final List<ObjectConfigurator> configurators = new ArrayList<>();
    private final ApplicationContext context;

    @SneakyThrows
    public ObjectFactory(ApplicationContext context) {
        this.context = context;
        for (Class<? extends ObjectConfigurator> type :
                context.getTypeConfig().getScanner().getSubTypesOf(ObjectConfigurator.class)) {
            configurators.add(type.getDeclaredConstructor().newInstance());
        }
    }

    @SneakyThrows
    public <T> T createObject(Class<T> implClass) {

        T instance = implClass.getDeclaredConstructor().newInstance();

        configurators.forEach(configurator -> configurator.configure(instance, context));

        invokePostConstructor(implClass, instance);

        return instance;
    }

    private <T> void invokePostConstructor(Class<T> implClass, T instance)
            throws IllegalAccessException, InvocationTargetException {
        for (Method method : implClass.getMethods()) {
            if (method.isAnnotationPresent(PostConstruct.class)) {
                method.invoke(instance);
            }
        }
    }
}

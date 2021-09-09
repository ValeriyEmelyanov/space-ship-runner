package com.example.spaceshiprunner.core;

import com.example.spaceshiprunner.config.ObjectConfigurator;
import lombok.SneakyThrows;

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

        return instance;
    }
}

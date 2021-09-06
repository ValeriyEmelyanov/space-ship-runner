package com.example.spaceshiprunner;

import com.example.spaceshiprunner.config.ObjectConfigurator;
import com.example.spaceshiprunner.config.TypeConfig;
import com.example.spaceshiprunner.config.impl.TypeConfigImpl;
import com.example.spaceshiprunner.service.Refueller;
import com.example.spaceshiprunner.service.impl.RobotRefuellerImpl;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ObjectFactory {
    private static final ObjectFactory instance = new ObjectFactory();
    private final TypeConfig typeConfig;
    private final List<ObjectConfigurator> configurators = new ArrayList<>();

    public static ObjectFactory getInstance() {
        return instance;
    }

    @SneakyThrows
    private ObjectFactory() {
        this.typeConfig = new TypeConfigImpl(
                "com.example",
                new ConcurrentHashMap<>(Map.of(Refueller.class, RobotRefuellerImpl.class)));
        for (Class<? extends ObjectConfigurator> type :
                typeConfig.getScanner().getSubTypesOf(ObjectConfigurator.class)) {
            configurators.add(type.getDeclaredConstructor().newInstance());
        }
    }

    @SneakyThrows
    public <T> T createObject(Class<T> type) {
        Class<? extends T> implClass = type;
        if (type.isInterface()) {
            implClass = typeConfig.getImplClass(type);
        }

        T instance = implClass.getDeclaredConstructor().newInstance();

        configurators.forEach(configurator -> configurator.configure(instance));

        return instance;
    }
}

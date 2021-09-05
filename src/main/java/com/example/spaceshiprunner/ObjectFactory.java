package com.example.spaceshiprunner;

import com.example.spaceshiprunner.config.TypeConfig;
import com.example.spaceshiprunner.config.impl.TypeConfigImpl;
import com.example.spaceshiprunner.service.Refueller;
import com.example.spaceshiprunner.service.impl.RobotRefuellerImpl;
import lombok.SneakyThrows;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ObjectFactory {
    private static final ObjectFactory instance = new ObjectFactory();
    private final TypeConfig typeConfig;

    public static ObjectFactory getInstance() {
        return instance;
    }

    private ObjectFactory() {
        this.typeConfig = new TypeConfigImpl(
                "com.example",
                new ConcurrentHashMap<>(Map.of(Refueller.class, RobotRefuellerImpl.class)));
    }

    @SneakyThrows
    public <T> T createObject(Class<T> type) {
        Class<? extends T> implClass = type;
        if (type.isInterface()) {
            implClass = typeConfig.getImplClass(type);
        }
        return implClass.getDeclaredConstructor().newInstance();
    }
}

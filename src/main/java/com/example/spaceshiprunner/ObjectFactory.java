package com.example.spaceshiprunner;

import com.example.spaceshiprunner.config.TypeConfig;
import com.example.spaceshiprunner.config.impl.TypeConfigImpl;
import lombok.SneakyThrows;

public class ObjectFactory {
    private static final ObjectFactory instance = new ObjectFactory();
    private final TypeConfig typeConfig = new TypeConfigImpl("com.example");

    public static ObjectFactory getInstance() {
        return instance;
    }

    private ObjectFactory() {
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

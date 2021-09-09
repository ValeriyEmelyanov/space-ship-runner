package com.example.spaceshiprunner.core;

import com.example.spaceshiprunner.annotation.NewInstatnce;
import com.example.spaceshiprunner.config.TypeConfig;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ApplicationContext {
    @Setter
    private ObjectFactory objectFactory;
    private final Map<Class, Object> cache = new ConcurrentHashMap<>();
    @Getter
    private final TypeConfig typeConfig;

    public ApplicationContext(TypeConfig typeConfig) {
        this.typeConfig = typeConfig;
    }

    public <T> T getObject(Class<T> type) {
        if (cache.containsKey(type)) {
            return (T) cache.get(type);
        }

        Class<? extends T> implClass = type;
        if (type.isInterface()) {
            implClass = typeConfig.getImplClass(type);
        }

        T instance = objectFactory.createObject(implClass);

        if (!implClass.isAnnotationPresent(NewInstatnce.class)) {
            cache.put(type, instance);
        }

        return instance;
    }
}

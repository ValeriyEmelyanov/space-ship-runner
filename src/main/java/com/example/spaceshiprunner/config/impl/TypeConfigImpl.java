package com.example.spaceshiprunner.config.impl;

import com.example.spaceshiprunner.config.TypeConfig;
import org.reflections.Reflections;

import java.util.Set;

public class TypeConfigImpl implements TypeConfig {

    private final Reflections scanner;

    public TypeConfigImpl(String packageToScan) {
        this.scanner = new Reflections(packageToScan);
    }

    @Override
    public <T> Class<? extends T> getImplClass(Class<T> type) {
        Set<Class<? extends T>> types = scanner.getSubTypesOf(type);
        if (types.size() != 1) {
            throw new RuntimeException(type + " has 0 or more than one implementation");
        }
        return types.iterator().next();
    }
}

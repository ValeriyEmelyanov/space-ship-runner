package com.example.spaceshiprunner.config.impl;

import com.example.spaceshiprunner.config.TypeConfig;
import lombok.Getter;
import org.reflections.Reflections;

import java.util.Map;
import java.util.Set;

/**
 * Конфигуратор, отвечающий за сканирование пакетов
 * и предоставление имплементации интерфеса или просто возврат класса.
 */
public class TypeConfigImpl implements TypeConfig {

    @Getter
    private final Reflections scanner;
    private final Map<Class, Class> ifcToImpl;

    public TypeConfigImpl(String packageToScan,
                          Map<Class, Class> ifcToImpl) {
        this.scanner = new Reflections(packageToScan);
        this.ifcToImpl = ifcToImpl;
    }

    @Override
    public <T> Class<? extends T> getImplClass(Class<T> ifc) {
        return ifcToImpl.computeIfAbsent(ifc, type -> {
            Set<Class<? extends T>> types = scanner.getSubTypesOf(ifc);
            if (types.size() != 1) {
                throw new RuntimeException(ifc + " has 0 or more than one implementation, please update your config");
            }
            return types.iterator().next();
        });
    }
}

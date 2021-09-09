package com.example.spaceshiprunner.core;

import com.example.spaceshiprunner.config.impl.TypeConfigImpl;

import java.util.Map;

/**
 * Отвечает за первоначальную настройку приложения.
 */
public class Application {
    public static ApplicationContext run(String packageToScan, Map<Class, Class> ifcToImpl) {
        TypeConfigImpl typeConfig = new TypeConfigImpl(packageToScan, ifcToImpl);
        ApplicationContext context = new ApplicationContext(typeConfig);
        ObjectFactory objectFactory = new ObjectFactory(context);
        context.setObjectFactory(objectFactory);
        return context;
    }
}

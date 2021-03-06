package com.example.spaceshiprunner.config.impl;

import com.example.spaceshiprunner.annotation.Autoinject;
import com.example.spaceshiprunner.config.ObjectConfigurator;
import com.example.spaceshiprunner.core.ApplicationContext;
import lombok.SneakyThrows;

import java.lang.reflect.Field;

/**
 * Конфигуратор, отвественный за инициализацию полей объекта,
 * аннотированных @Autoinject
 */
public class AutoinjectAnnotationObjectConfigurator implements ObjectConfigurator {
    @Override
    @SneakyThrows
    public void configure(Object instance, ApplicationContext context) {
        for (Field field : instance.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Autoinject.class)) {
                field.setAccessible(true);
                Object object = context.getObject(field.getType());
                field.set(instance, object);
            }
        }
    }
}

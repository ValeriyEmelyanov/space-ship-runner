package com.example.spaceshiprunner.config;

import com.example.spaceshiprunner.core.ApplicationContext;

/**
 * Интерфейс, отвечающий за настройку объекта.
 */
public interface ObjectConfigurator {
    void configure(Object instance, ApplicationContext context);
}

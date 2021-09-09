package com.example.spaceshiprunner.config;

/**
 * Интерфейс отвечает за обертку класса прокси при необходимости.
 */
public interface ProxyConfigurator {
    Object replaceWithProxyIfNeeded(Object instance, Class implClass);
}

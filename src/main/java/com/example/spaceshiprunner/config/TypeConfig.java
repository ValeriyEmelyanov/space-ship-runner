package com.example.spaceshiprunner.config;

import org.reflections.Reflections;

/**
 * Интерфейс, отвечающий за сканирование пакетов
 * и предоставление имплементации интерфеса или просто возврат класса.
 */
public interface TypeConfig {
    <T> Class<? extends T> getImplClass(Class<T> type);
    Reflections getScanner();
}

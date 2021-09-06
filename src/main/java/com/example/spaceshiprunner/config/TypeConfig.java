package com.example.spaceshiprunner.config;

import org.reflections.Reflections;

public interface TypeConfig {
    <T> Class<? extends T> getImplClass(Class<T> type);
    Reflections getScanner();
}

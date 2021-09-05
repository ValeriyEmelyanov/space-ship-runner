package com.example.spaceshiprunner.config;

public interface TypeConfig {
    <T> Class<? extends T> getImplClass(Class<T> type);
}

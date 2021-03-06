package com.example.spaceshiprunner.config.impl;

import com.example.spaceshiprunner.annotation.Property;
import com.example.spaceshiprunner.config.ObjectConfigurator;
import com.example.spaceshiprunner.core.ApplicationContext;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

/**
 * Конфигуратор, отвественный за настройку полей объекта,
 * аннотированных @Property.
 */
public class PropertyAnnotationObjectConfigurator implements ObjectConfigurator {

    private final Map<String, String> propertiesMap;

    @SneakyThrows
    public PropertyAnnotationObjectConfigurator() {
        //String path = ClassLoader.getSystemClassLoader().getResource("application.properties").getPath();
        //Stream<String> lines = new BufferedReader(new FileReader(path)).lines();
        InputStream resourceAsStream = ClassLoader.getSystemClassLoader().getResourceAsStream("application.properties");
        Stream<String> lines = new BufferedReader(new InputStreamReader(resourceAsStream)).lines();
        propertiesMap = lines.map(line -> line.split("=")).collect(toMap(arr -> arr[0], arr -> arr[1]));
    }

    @Override
    @SneakyThrows
    public void configure(Object instance, ApplicationContext context) {
        Class<?> implClass = instance.getClass();
        for (Field field : implClass.getDeclaredFields()) {
            Property annotation = field.getAnnotation(Property.class);
            if (annotation == null) {
                continue;
            }
            String value = annotation.value().isEmpty()
                    ? propertiesMap.get(field.getName()) : propertiesMap.get(annotation.value());
            field.setAccessible(true);
            field.set(instance, value);
        }
    }
}

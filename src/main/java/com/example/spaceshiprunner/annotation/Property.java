package com.example.spaceshiprunner.annotation;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Аннотация, которая отвечает за настройку поля объекта.
 * Значение берется из файла Application.property по значению value, еали оно указано
 * или по имени поля.
 */
@Retention(RUNTIME)
public @interface Property {
    String value() default "";
}

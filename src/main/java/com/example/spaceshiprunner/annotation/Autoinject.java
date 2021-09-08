package com.example.spaceshiprunner.annotation;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Аннотация, которая отвечает за настройку поля из контекста
 * - присваивание ссылки на соотвествующий объект.
 */
@Retention(RUNTIME)
public @interface Autoinject {
}

package com.example.spaceshiprunner.annotation;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Аннотация, которая указывает фабрике объектов, что нужно создать новый класс.
 */
@Retention(RUNTIME)
public @interface NewInstatnce {
}

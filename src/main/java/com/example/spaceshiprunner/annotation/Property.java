package com.example.spaceshiprunner.annotation;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
public @interface Property {
    String value() default "";
}

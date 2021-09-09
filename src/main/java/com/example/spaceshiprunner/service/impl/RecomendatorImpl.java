package com.example.spaceshiprunner.service.impl;

import com.example.spaceshiprunner.annotation.Introduce;
import com.example.spaceshiprunner.annotation.Property;
import com.example.spaceshiprunner.service.Recomendator;

@Introduce
public class RecomendatorImpl implements Recomendator {
    @Property
    private String recomendation;

    @Override
    public void recomend() {
        System.out.println(recomendation);
    }
}

package com.example.spaceshiprunner.service.impl;

import com.example.spaceshiprunner.service.Refueller;

import javax.annotation.PostConstruct;

public class RobotRefuellerImpl implements Refueller {

    @PostConstruct
    public void init() {
        System.out.println("Контрольная проверка робота-заправщика");
    }

    @Override
    public void fillup() {
        System.out.println("Робот-заправщик выполнил заправку");
    }
}

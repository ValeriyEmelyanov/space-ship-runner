package com.example.spaceshiprunner.service.impl;

import com.example.spaceshiprunner.service.Refueller;

public class RobotRefuellerImpl implements Refueller {
    @Override
    public void fillup() {
        System.out.println("Робот-заправщик выполнил заправку");
    }
}

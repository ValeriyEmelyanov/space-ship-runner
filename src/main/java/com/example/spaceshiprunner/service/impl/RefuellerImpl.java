package com.example.spaceshiprunner.service.impl;

import com.example.spaceshiprunner.service.Refueller;

public class RefuellerImpl implements Refueller {
    @Override
    public void fillup() {
        System.out.println("Заправка выполнена");
    }
}

package com.example.spaceshiprunner;

import com.example.spaceshiprunner.controller.SpaceShipRunner;
import com.example.spaceshiprunner.core.Application;
import com.example.spaceshiprunner.core.ApplicationContext;
import com.example.spaceshiprunner.model.SpaceShip;
import com.example.spaceshiprunner.service.Refueller;
import com.example.spaceshiprunner.service.impl.RobotRefuellerImpl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Отвечает за старт приложения и основной бизнес-логики приложения.
 */
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = Application.run("com.example",
                new ConcurrentHashMap<>(Map.of(Refueller.class, RobotRefuellerImpl.class)));
        SpaceShipRunner runner = context.getObject(SpaceShipRunner.class);
        runner.start(new SpaceShip("Викинг"));
    }
}

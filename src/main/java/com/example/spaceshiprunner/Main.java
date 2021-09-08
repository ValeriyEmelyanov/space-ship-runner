package com.example.spaceshiprunner;

public class Main {
    public static void main(String[] args) {
        SpaceShipRunner runner = ObjectFactory.getInstance().createObject(SpaceShipRunner.class);
        runner.start(new SpaceShip("Викинг"));
    }
}

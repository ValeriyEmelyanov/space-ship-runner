package com.example.spaceshiprunner;

import com.example.spaceshiprunner.annotation.Autoinject;
import com.example.spaceshiprunner.service.Announcer;
import com.example.spaceshiprunner.service.Refueller;

public class SpaceShipRunner {
    @Autoinject
    private Refueller refueller;
    @Autoinject
    private Announcer announcer;

    public void start(SpaceShip ship) {
        refueller.fillup();
        announcer.announce("Необходимо очистить стартовую площадку от персонала");
        run(ship);
        announcer.announce("Необходимо выполнить уборку стартовой площадки");
    }

    private void run(SpaceShip ship) {
        System.out.printf("Корабль \"%s\" полетел\n", ship.getName());
    }
}

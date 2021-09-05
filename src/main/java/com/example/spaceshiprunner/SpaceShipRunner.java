package com.example.spaceshiprunner;

import com.example.spaceshiprunner.service.Announcer;
import com.example.spaceshiprunner.service.impl.ConsoleAnnouncer;
import com.example.spaceshiprunner.service.Refueller;
import com.example.spaceshiprunner.service.impl.RefuellerImpl;

public class SpaceShipRunner {
    private Refueller refueller = ObjectFactory.getInstance().createObject(Refueller.class);
    private Announcer announcer = ObjectFactory.getInstance().createObject(Announcer.class);

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

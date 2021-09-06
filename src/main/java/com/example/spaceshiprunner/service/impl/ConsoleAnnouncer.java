package com.example.spaceshiprunner.service.impl;

import com.example.spaceshiprunner.ObjectFactory;
import com.example.spaceshiprunner.service.Announcer;
import com.example.spaceshiprunner.service.Recomendator;

public class ConsoleAnnouncer implements Announcer {
    private Recomendator recomendator = ObjectFactory.getInstance().createObject(Recomendator.class);

    @Override
    public void announce(String message) {
        System.out.println(message);
        recomendator.recomend();
    }
}

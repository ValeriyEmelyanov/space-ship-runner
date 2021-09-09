package com.example.spaceshiprunner.service.impl;

import com.example.spaceshiprunner.annotation.Autoinject;
import com.example.spaceshiprunner.service.Announcer;
import com.example.spaceshiprunner.service.Recomendator;

public class ConsoleAnnouncer implements Announcer {
    @Autoinject
    private Recomendator recomendator;

    @Override
    public void announce(String message) {
        System.out.println(message);
        recomendator.recomend();
    }
}

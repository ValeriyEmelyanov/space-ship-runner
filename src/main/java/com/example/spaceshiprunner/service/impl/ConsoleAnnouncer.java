package com.example.spaceshiprunner.service.impl;

import com.example.spaceshiprunner.service.Announcer;

public class ConsoleAnnouncer implements Announcer {
    @Override
    public void announce(String message) {
        System.out.println(message);
    }
}

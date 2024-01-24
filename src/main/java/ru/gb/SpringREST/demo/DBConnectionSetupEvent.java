package ru.gb.SpringREST.demo;

import org.springframework.context.ApplicationEvent;

public class DBConnectionSetupEvent extends ApplicationEvent {
    public DBConnectionSetupEvent(Object source) {
        super(source);
    }
}

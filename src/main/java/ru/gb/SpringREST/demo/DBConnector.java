package ru.gb.SpringREST.demo;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Slf4j
@Component
public class DBConnector {

    private final ApplicationEventPublisher eventPublisher;

    @Autowired
    public DBConnector(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public String getData() {
        //connect to db and select
        return "data";
    }

    //init-method
    @SneakyThrows
    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        log.info("connecting to db...");
        Thread.sleep(Duration.ofSeconds(1));
        log.info("connection to db successfully established");

        eventPublisher.publishEvent(new DBConnectionSetupEvent(this));
    }
}

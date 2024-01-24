package ru.gb.SpringREST.demo;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MyBean {

    @Value("${application.max-allowed-books:1}") // по умолчанию значение 1
    private int myProperty;

    public MyBean(MyService myService) {
        log.info("constructor");
    }

    @Autowired
    public void setMyService(MyService myService) {
        log.info("set-injection");
    }

    @PostConstruct
    public void postConstruct() {
        log.info("postConstruct");
    }

    @EventListener(DBConnectionSetupEvent.class)
    public void onDBConnectionSetup() {
        log.info("onDBConnectionSetup");

        log.info("property = {}", myProperty);
    }

    @PreDestroy
    public void preDestroy() {
        log.info("preDestroy");
    }
}

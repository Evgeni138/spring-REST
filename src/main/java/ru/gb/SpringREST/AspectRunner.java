package ru.gb.SpringREST;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import ru.gb.SpringREST.aspect.LoggableAspect;
//import ru.gb.SpringREST.aspect.TimingAspect;

import java.util.List;

@Slf4j
@Order(Ordered.LOWEST_PRECEDENCE)
@Component
@RequiredArgsConstructor
public class AspectRunner {

    private final List<Brother> brothers;

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {
        for (Brother brother : brothers) {
            try {
                brother.method1("1", 2);
                brother.method2();
                brother.method3();
            } catch (Throwable e) {
                log.error(e.getMessage());
            }
        }
    }
}

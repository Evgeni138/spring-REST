package ru.gb.SpringREST;

import org.slf4j.event.Level;
import org.springframework.stereotype.Component;
import ru.gb.SpringREST.aspect.Loggable;

@Component
public class Louiggi implements Brother{

    @Timed
    @Loggable(level = Level.WARN)
    public void method1(String arg1, int arg2) {

    }

    @Timed
    @Loggable(level = Level.WARN)
    public String method2() {
        return "value";
    }

    @Timed
    public String method3() {
        throw new RuntimeException("runtimeexceptionmsg");
    }

}

package ru.gb.SpringREST;

import org.slf4j.event.Level;
import org.springframework.stereotype.Component;
import ru.gb.SpringREST.aspect.Loggable;

@Loggable(level = Level.INFO)
@Component
public class Mario implements Brother{

    @Timed
    public void method1(String arg1, int arg2) {

    }

    @Timed
    public String method2() {
        return "value";
    }

    @Timed
    public String method3() {
        throw new RuntimeException("runtimeexceptionmsg");
    }

}

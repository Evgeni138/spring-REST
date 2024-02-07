package ru.gb.SpringREST.demo;

import org.springframework.stereotype.Component;

@Component
public class MyServiceBean {

    public String doSomething(String arg) {
        // do something
        return "Result: [" + arg + "]";
    }

    public String doSomething2(String arg) {
        // do something
        throw new RuntimeException("err");
    }
}

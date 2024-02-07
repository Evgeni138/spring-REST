package ru.gb.SpringREST.demo;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
//@Aspect
@Component
public class DemoAspect {

    //pointcut
    @Pointcut("execution(* ru.gb.SpringREST.demo.MyServiceBean.*(..))")
    public void MyServiceBeanMethodsPointcut() {

    }

    @Before("execution(* ru.gb.SpringREST.*.*(..))")
    public void beforeMyServiceBean(JoinPoint joinPoint) {
        log.info("Argument name: {}", joinPoint.getArgs()[0]);
    }

    @AfterReturning(value ="MyServiceBeanMethodsPointcut()", returning = "result")
    public void beforeMyServiceBean(JoinPoint joinPoint, Object result) {
        log.info("Result: {}", result);
    }

    @AfterThrowing(value = "MyServiceBeanMethodsPointcut()", throwing = "e")
    public void afterTrowingMyServiceBean(Throwable e) {
        log.error("Exception!!! {} - {}", e.getClass(), e.getMessage());
    }

    @Around(value = "MyServiceBeanMethodsPointcut()")
    public Object aroundMyServiceBeanMethodsPointcut(ProceedingJoinPoint joinPoint) {
        try {
            Object procced = joinPoint.proceed();
            return procced;
        } catch (Throwable e) {
            return "Exception was thrown: [" + e.getMessage() + "]";
        }
    }
}

package com.holdon.user.interceptor;

import com.alibaba.fastjson.JSON;
import lombok.extern.log4j.Log4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * Created by wd on 2018/2/9.
 */
@Aspect
@Component
@Log4j
public class LogTraceAspect {

    @Pointcut("execution(* com.holdon.user.controller..*(..))")
    public void controllerAspectPackage() {
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.RestController)")
    public void controllerAspect() {
    }

    @Pointcut("@annotation(org.springframework.stereotype.Service)")
    public void serviceAspect() {
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.Mapping)")
    public void mapperAspect() {
    }

    @Before("controllerAspectPackage()")
    public void befor(JoinPoint joinPoint) {
        log.info("before1 =======");
    }

    @Before("controllerAspect()")
    public void befor2(JoinPoint joinPoint) {
        log.info("before2 =======");
    }

    @Around("serviceAspect()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String methodName = methodSignature.getMethod().getName();
        String className = joinPoint.getTarget().getClass().getName();

        StringBuilder info = new StringBuilder("请求");
        info.append(className);
        info.append(".");
        info.append(methodName);
        info.append("()：");
        info.append("args：");
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            info.append(JSON.toJSONString(arg));
        }
        log.info(info);
        Object obj;
        try {
            obj = joinPoint.proceed(args);
            if (obj != null) {
                log.info("返回结果：" + JSON.toJSONString(obj));
            }
        } catch (Throwable throwable) {
            log.error("", throwable);
            throw throwable;
        }
        return obj;
    }
}

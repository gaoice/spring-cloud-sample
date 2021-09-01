package com.gaoice.cloud.nebula.common.aspect;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author gaoice
 */
@Aspect
@Component
public class LogSpentTimeAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogSpentTimeAspect.class);

    @Around("@annotation(com.gaoice.cloud.nebula.common.annotation.LogSpentTime)")
    public Object spentTimeAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long t1 = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long t2 = System.currentTimeMillis();
        LOGGER.info("{}.{} spent time : {}", joinPoint.getSignature().getDeclaringType(), joinPoint.getSignature().getName(), t2 - t1);
        return result;
    }
}

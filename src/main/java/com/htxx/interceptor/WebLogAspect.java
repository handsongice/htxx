package com.htxx.interceptor;

/**
 * Created by 123 on 2018-07-08
 */

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * Web层日志切面
 * 切面在传送报文到对应接口之前对报文进行解析
 */
@Aspect
//@Order(5)
@Component
@Slf4j
public class WebLogAspect {

    ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Pointcut("execution(* com.htxx.controller.*.*(..))")
    public void webLog() {
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        startTime.set(System.currentTimeMillis());

        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 记录下请求内容
        log.info("【**** URL ****】: " + request.getRequestURL().toString());
        log.info("【**** HTTP_METHOD ****】: " + request.getMethod());
        log.info("【**** IP ****】: " + request.getRemoteAddr());
        String classMethod = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        log.info("【**** CLASS_METHOD ****】: " + classMethod);
        Object[] args = joinPoint.getArgs();
        log.info("【**** ARGS ****】: \n" + Arrays.toString(args));
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        log.info("【**** RESPONSE ****】: " + (ret == null ? null : ret.toString()));
        log.info("【**** SPEND TIME ****】: " + (System.currentTimeMillis() - startTime.get()) + "ms");
    }

//    /**
//     * 异常通知
//     */
//    @AfterThrowing(value = "webLog()", throwing = "ex")
//    public void throwingMethod(Throwable ex) {
//        log.info("=====================EXCEPTION START=======================");
//        log.info("EXCEPTION : " +ex.getMessage());
//        log.info("=====================EXCEPTION END=========================");
//    }

}




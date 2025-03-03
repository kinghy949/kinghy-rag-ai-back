package com.kinghy.rag.aop;

    import com.kinghy.rag.entity.LogInfo;
    import com.kinghy.rag.service.LogInfoService;
    import org.aspectj.lang.JoinPoint;
    import org.aspectj.lang.annotation.AfterReturning;
    import org.aspectj.lang.annotation.Aspect;
    import org.aspectj.lang.annotation.Before;
    import org.aspectj.lang.annotation.Pointcut;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Component;

    import java.util.Arrays;
    import java.util.Date;

    @Aspect
    @Component
    public class LoggingAspect {

        @Autowired
        private LogInfoService logInfoService;

        // 定义切点，拦截所有com.kinghy.rag.controller包下的方法
//        @Pointcut("execution(* com.kinghy.rag.controller.*.*(..))")
//        public void controllerMethods() {}
        // 定义切点：拦截所有被@Loggable注解标记的方法
        @Pointcut("@annotation(com.kinghy.rag.annotation.Loggable)")
        public void loggableMethods() {}

//        @Before("controllerMethods()")
        @Before("loggableMethods()")
        public void logBefore(JoinPoint joinPoint) {
            LogInfo logInfo = new LogInfo();
            logInfo.setMethodName(joinPoint.getSignature().getName());
            logInfo.setClassName(joinPoint.getTarget().getClass().getName());
            logInfo.setRequestTime(new Date());
            logInfo.setRequestParams(Arrays.toString(joinPoint.getArgs()));
            logInfoService.save(logInfo);
        }

    }
    
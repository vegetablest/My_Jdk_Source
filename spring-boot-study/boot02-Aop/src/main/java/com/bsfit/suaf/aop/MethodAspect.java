package com.bsfit.suaf.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * <br>
 * 前面类，用来打印日志
 *
 * @author bangsun
 * @data 2021/6/29 12:01
 * @verson 1.0
 */
@Component
@Aspect
public class MethodAspect {

    private static final Logger logger = LoggerFactory.getLogger(MethodAspect.class);

    @Pointcut("@annotation(com.bsfit.suaf.anno.LogPrint)")
    public void methodPointcut() {

    }

    /**
     * 环绕通知
     */
    @Around("methodPointcut()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        try {
            /*执行方法*/
            Object result = joinPoint.proceed();
            long end = System.currentTimeMillis();
            logger.info("执行{}方法，参数是：{},耗时:{} ms!", joinPoint.getSignature().getDeclaringTypeName(), parseParames(joinPoint.getArgs()), end - start);
            return result;
        } catch (Throwable e) {
            long end = System.currentTimeMillis();
            logger.error("{},耗时{}ms，抛出异常{}", joinPoint, end - start, e.getMessage());
            throw e;
        }
    }

    @AfterReturning(returning = "ret", pointcut = "methodPointcut()")
    public void doAfterReturning(Object ret) throws Throwable {
        logger.info("返回值:" + ret);
    }

    private String parseParames(Object[] parames) {

        if (null == parames || parames.length <= 0) {
            return "该方法没有参数";
        }
        StringBuffer param = new StringBuffer("请求参数 # 个:[ ");
        int i = 0;
        for (Object obj : parames) {
            i++;
            if (i == 1) {
                param.append(obj.toString());
                continue;
            }
            param.append(" ,").append(obj.toString());
        }
        return param.append(" ]").toString().replace("#", String.valueOf(i));
    }
}
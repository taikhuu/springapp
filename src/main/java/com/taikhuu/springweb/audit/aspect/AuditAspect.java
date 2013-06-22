package com.taikhuu.springweb.audit.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
@Component
@Aspect
public class AuditAspect {
    private final static Logger logger=LoggerFactory.getLogger(AuditAspect.class);
    @Before("@within(com.taikhuu.springweb.audit.annotation.Auditable)&&@annotation(com.taikhuu.springweb.audit.annotation.AuditBeforeExcution)")
    public void auditBeforeExcution(JoinPoint joinPoint){
        logger.debug("Excuting :"+joinPoint.getSignature().toString());
        logger.debug("With Parameter"+joinPoint.getArgs().toString());
    }
    @After("@within(com.taikhuu.springweb.audit.annotation.Auditable)&&@annotation(com.taikhuu.springweb.audit.annotation.AuditAfterExcution)")
    public void auditAfterExcution(JoinPoint joinPoint){
        logger.debug("After Excuting :"+joinPoint.getSignature().toString());
        logger.debug("With Parameter"+joinPoint.getArgs().toString());
    }
    
}

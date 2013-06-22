package com.taikhuu.springweb.security.aspect;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.taikhuu.springweb.security.PermisionEnum;
import com.taikhuu.springweb.security.annotation.Authorization;
@Component
@Aspect
public class SecurityAspect {
    @Autowired
    HttpSession session;
    public HttpSession getSession() {
        return session;
    }
    public void setSession(HttpSession session) {
        this.session = session;
    }
    @Around("@within(com.taikhuu.springweb.security.annotation.RequiredAuthorization)&&@annotation(authorization)")
    public Object authorization(ProceedingJoinPoint proceedingJoinPoint,Authorization authorization) throws Throwable{
        Object result= new ModelAndView("redirect:login.html");
                if(authorization.requiredPermision()==PermisionEnum.LOGIN){
                    if(session.getAttribute("user")!=null){
                    result=proceedingJoinPoint.proceed();
                    }
                }
                return result;
    }
}

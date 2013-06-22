package com.taikhuu.springweb.security.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.taikhuu.springweb.security.PermisionEnum;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Authorization {
   PermisionEnum requiredPermision() default PermisionEnum.LOGIN;
}

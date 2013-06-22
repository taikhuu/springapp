package com.taikhuu.springweb.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.taikhuu.springweb.security.PermisionEnum;
import com.taikhuu.springweb.security.annotation.Authorization;
import com.taikhuu.springweb.security.annotation.RequiredAuthorization;
@RequiredAuthorization
@Controller
public class Welcome {
    @Value("classpath:logback.xml")
    private Resource logBackConfig;
    
	public Resource getLogBackConfig() {
        return logBackConfig;
    }

    public void setLogBackConfig(Resource logBackConfig) {
        this.logBackConfig = logBackConfig;
    }
    @Authorization(requiredPermision=PermisionEnum.LOGIN)
    @RequestMapping(value="/welcome",method=RequestMethod.GET)
	public ModelAndView welcome() throws IOException{
		Map<String, String> modelMap=new HashMap<String, String>();
		Scanner scanner= new Scanner(logBackConfig.getInputStream());
		StringBuilder builder=new StringBuilder();
		while(scanner.hasNext()){
		    builder.append(scanner.next());
		}
		modelMap.put("hellomessage", builder.toString());
		
		return new ModelAndView("welcome",modelMap);
	}
}

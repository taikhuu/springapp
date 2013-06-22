package com.taikhuu.springweb.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.taikhuu.springweb.audit.annotation.AuditAfterExcution;
import com.taikhuu.springweb.audit.annotation.AuditBeforeExcution;
import com.taikhuu.springweb.audit.annotation.Auditable;
import com.taikhuu.springweb.bean.LoginFormBean;
import com.taikhuu.springweb.user.UserDao;
import com.taikhuu.springweb.user.model.User;

@Controller
@SessionAttributes("user")
@Auditable
public class LoginController {
    private static final Logger LOGGER=LoggerFactory.getLogger(LoginController.class);
    @Resource(name = "userDao")
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
    @AuditAfterExcution
    @AuditBeforeExcution
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView displayLoginPage() {
        LOGGER.info("begin to show loggin");
        return new ModelAndView("login","login",new User());
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login( @Valid @ModelAttribute (value="login") LoginFormBean login,Errors errors) {
        String userName=login.getEmail();
        String passWord=login.getPassword();
        LOGGER.info("login user name:"+userName);
        LOGGER.info("Password :" +passWord);
        if(errors.hasErrors()){
            return new ModelAndView("login");
        }
        Map<String, String> hashMap = new HashMap<String, String>();
        try{
        User user=userDao.authenticateUser(userName, passWord);
        if(user!=null){
        return new ModelAndView("redirect:welcome","user",user);
        }
        }
        catch (SQLException e) {
            // TODO: handle exception
            hashMap.put("result", "false");
        }
        return new ModelAndView("login", hashMap);
    }
    @RequestMapping("/logout")
    public ModelAndView logout(HttpSession session){
        session.invalidate();
        return new ModelAndView("redirect:login.html");
    }

}

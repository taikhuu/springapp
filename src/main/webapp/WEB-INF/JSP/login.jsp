<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form:form modelAttribute="login"  action="" method="POST" class="custom">
  <div class="row">
    <div class="small-8">
      <div class="row">
        <div class="small-3 columns">
          <label for="email" class="right inline">Email${result}</label>
        </div>
        <div class="small-9 columns">
          <form:input type="text" path="email" id="email" placeholder=""></form:input>
          <form:errors  path="email" cssClass="Error"></form:errors>
        </div>
      </div>
      <div class="row">
        <div class="small-3 columns">
          <label for="password" class="right inline">Password</label>
        </div>
        <div class="small-9 columns">
          <form:password  id="password" path="password"></form:password>
          <form:errors  path="password" cssClass="Error"></form:errors>
        </div>
      </div>
      <div class="row">
        <div class="small-3 columns">
        </div>
        <div class="small-9 columns">
          <label for="remember_me">
            <input type="checkbox" id="remember_me" name="remember_me" style="display: none;">
            <span class="custom checkbox"></span> Remember me?
          </label>
        </div>
      </div>
      <div class="row">
        <div class="small-3 columns">
        </div>
        <div class="small-9 columns">
          <hr>
          <input type="submit" class="small button" value="Submit">
        </div>  
      </div>
    </div>
  </div>
</form:form>
</body>
</html>
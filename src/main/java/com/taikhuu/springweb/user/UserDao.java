package com.taikhuu.springweb.user;

import java.sql.SQLException;

import com.taikhuu.springweb.user.model.User;

public interface UserDao {
     User authenticateUser(String userName,String passWord) throws SQLException;
}

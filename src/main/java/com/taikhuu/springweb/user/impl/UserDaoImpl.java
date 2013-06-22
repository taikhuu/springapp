package com.taikhuu.springweb.user.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.sql.DataSource;
import javax.sql.rowset.CachedRowSet;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.sun.rowset.CachedRowSetImpl;
import com.taikhuu.springweb.user.UserDao;
import com.taikhuu.springweb.user.model.User;
import com.taikhuu.springweb.util.StringUtilities;

@Component(value = "userDao")
public class UserDaoImpl implements UserDao {
    @Resource(name = "smartDrinkDataSource")
    DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public User authenticateUser(String userName, String passWord)
            throws SQLException {
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("select * from Users where email=? and password=?");
            String passwordHash = StringUtilities.hmacSha1(passWord,
                    "d8c9a9245fd928afde5cb2b53e8882bba3f9e273");
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, passwordHash);
            ResultSet resultSet = preparedStatement.executeQuery();
            CachedRowSet cachedRowSet = new CachedRowSetImpl();
            cachedRowSet.populate(resultSet);
            User user=null;
            if (cachedRowSet.size() == 1) {
                user=new User();
                cachedRowSet.next();
                user.setUserId(cachedRowSet.getString("id"));
                user.setEmail(cachedRowSet.getString("email"));
                user.setFirstName(cachedRowSet.getString("first_name"));
                user.setLastName(cachedRowSet.getString("name"));
            }
            return user;
        } catch (SQLException e) {
            // TODO: handle exception
            throw e;
        }
    };
}

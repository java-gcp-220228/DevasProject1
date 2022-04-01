package com.revature.service;

import com.revature.Exception.myError;
import com.revature.dao.UserDao;
import com.revature.model.User;

import javax.security.auth.login.FailedLoginException;
import java.sql.SQLException;

public class UserService {

    UserDao userDao;
    UserDao mockUserDao;

    public UserService() {
        userDao= new UserDao();
    }

    public UserService(UserDao mockUserDao) {
        userDao = mockUserDao;
    }

    public User getLoggedInDetails( String userName) throws SQLException, FailedLoginException {

        try{
            User user= userDao.getLoginUserDetails(userName);
            if (user == null) {
                throw new FailedLoginException("Invalid username and/or password was provided");

            }
            return user;
        }
        catch(NullPointerException ex)
        {

        }

    return null;

    }
}

package com.example.kk.callstatusdemo.Local;

import com.example.kk.callstatusdemo.Database.IUserDataSource;
import com.example.kk.callstatusdemo.Model.User;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by KK on 3/23/2018.
 */

public class UserDataSource implements IUserDataSource {

    private UserDAO userDAO;
    private static UserDataSource mInstance;

    public UserDataSource(UserDAO userDAO)
    {
        this.userDAO = userDAO;
    }

    public static UserDataSource getInstance(UserDAO userDAO)
    {
        if(mInstance == null)
        {
            mInstance = UserDataSource.getInstance(userDAO);
        }
        return mInstance;
    }

    @Override
    public Flowable<User> getUserById(int userId) {
        return userDAO.getUserById(userId);
    }

    @Override
    public Flowable<List<User>> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    public void insertUser(User... users) {
        userDAO.insertUser(users);
    }

    @Override
    public void updateUser(User... users) {
        userDAO.updateUser(users);
    }

    @Override
    public void deleteUser(User... users) {
        userDAO.deleteUser(users);
    }

    @Override
    public void deleteAllUsers() {
        userDAO.deleteAllUsers();
    }
}

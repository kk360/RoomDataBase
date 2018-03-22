package com.example.kk.callstatusdemo.Database;

import com.example.kk.callstatusdemo.Model.User;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by KK on 3/23/2018.
 */

public interface IUserDataSource {
    Flowable<User> getUserById(int userId);
    Flowable<List<User>> getAllUsers();
    void insertUser(User... users);
    void updateUser(User... users);
    void deleteUser(User... users);
    void deleteAllUsers();
}

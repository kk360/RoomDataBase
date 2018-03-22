package com.example.kk.callstatusdemo.Database;

import com.example.kk.callstatusdemo.Local.UserDAO;
import com.example.kk.callstatusdemo.Model.User;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by KK on 3/23/2018.
 */

public class UserRepository implements IUserDataSource {

    private IUserDataSource mLocalDataSource;

    private static UserRepository mInstance;

    public UserRepository(IUserDataSource mLocalDataSource)
    {
        this.mLocalDataSource = mLocalDataSource;
    }

    public static UserRepository getInstance(IUserDataSource mLocalDataSource)
    {
        if(mInstance == null)
        {
            mInstance = UserRepository.getInstance(mLocalDataSource);
        }
        return mInstance;
    }

    @Override
    public Flowable<User> getUserById(int userId) {
        return mLocalDataSource.getUserById(userId);
    }

    @Override
    public Flowable<List<User>> getAllUsers() {
        return mLocalDataSource.getAllUsers();
    }

    @Override
    public void insertUser(User... users) {
        mLocalDataSource.insertUser(users);
    }

    @Override
    public void updateUser(User... users) {
        mLocalDataSource.updateUser(users);
    }

    @Override
    public void deleteUser(User... users) {
        mLocalDataSource.deleteUser(users);
    }

    @Override
    public void deleteAllUsers() {
        mLocalDataSource.deleteAllUsers();
    }
}

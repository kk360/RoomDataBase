package com.example.kk.callstatusdemo.Local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.kk.callstatusdemo.Model.User;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by KK on 3/23/2018.
 */

@Dao
public interface UserDAO {

    @Query("SELECT * FROM user WHERE id=:userId")
    Flowable<User> getUserById(int userId);

    @Query("SELECT * FROM user")
    Flowable<List<User>> getAllUsers();

    @Insert
    void insertUser(User... users);

    @Update
    void updateUser(User... users);

    @Delete
    void deleteUser(User... users);

    @Query("DELETE FROM User")
    void deleteAllUsers();
}

package com.example.kk.callstatusdemo;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.kk.callstatusdemo.Database.UserRepository;
import com.example.kk.callstatusdemo.Local.UserDataSource;
import com.example.kk.callstatusdemo.Local.UserDatabase;
import com.example.kk.callstatusdemo.Model.User;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private FloatingActionButton btnfab;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    public static List<User> users = new ArrayList<>();

    //database
    private CompositeDisposable compositeDisposable;
    private UserRepository userRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //init rxjava componenet
        compositeDisposable = new CompositeDisposable();

        bindLayout();

        layoutManager = new LinearLayoutManager(this);
        adapter = new PlayerAdapter(users);

        recyclerView.setAdapter(adapter);

        //database
        UserDatabase userDatabase = UserDatabase.getInstance(this);
        userRepository = UserRepository.getInstance(UserDataSource.getInstance(userDatabase.userDAO()));

        //load all data
        loadData();

        btnfab.setOnClickListener(this);


    }

    public void loadData() {
        Disposable disposable = userRepository.getAllUsers()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<List<User>>() {
                    @Override
                    public void accept(List<User> users) throws Exception {
                        onGetAllUserSuccess(users);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(MainActivity.this, ""+throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        compositeDisposable.add(disposable);
    }

    private void onGetAllUserSuccess(List<User> users) {
        users.clear();
        users.addAll(users);
        adapter.notifyDataSetChanged();
    }

    private void bindLayout(){
        recyclerView = findViewById(R.id.rvPlayer);
        btnfab = findViewById(R.id.btnfab);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnfab:
            {
                startActivity(new Intent(MainActivity.this, AddPlayer.class));
            }
            break;
        }
    }
}


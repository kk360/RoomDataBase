package com.example.kk.callstatusdemo;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.kk.callstatusdemo.Database.UserRepository;
import com.example.kk.callstatusdemo.Local.UserDataSource;
import com.example.kk.callstatusdemo.Local.UserDatabase;
import com.example.kk.callstatusdemo.Model.User;

import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static com.example.kk.callstatusdemo.MainActivity.users;

public class AddPlayer extends AppCompatActivity implements View.OnClickListener{

    private TextInputEditText etfname;
    private TextInputEditText etlname;
    private TextInputEditText etemail;
    private Button btnAdd;
    private UserRepository userRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_player);

        //database
        UserDatabase userDatabase = UserDatabase.getInstance(this);
        userRepository = UserRepository.getInstance(UserDataSource.getInstance(userDatabase.userDAO()));

        bindLayout();

        btnAdd.setOnClickListener(this);
    }

    private void bindLayout(){
        etfname = findViewById(R.id.etfname);
        etlname = findViewById(R.id.etlname);
        etemail = findViewById(R.id.etemail);
        btnAdd = findViewById(R.id.btnadd);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnadd:
            {
                //TODO : add into database here
                Disposable disposable = io.reactivex.Observable.create(new ObservableOnSubscribe<Object>() {

                    @Override
                    public void subscribe(ObservableEmitter<Object> e) throws Exception {
                        User user = new User(etfname.getText().toString(), etlname.getText().toString(), etemail.getText().toString());
                        userRepository.insertUser(user);
                        e.onComplete();
                    }
                })
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(new Consumer<Object>() {
                            @Override
                            public void accept(Object o) throws Exception {
                                Toast.makeText(AddPlayer.this, "Player Added!", Toast.LENGTH_SHORT).show();
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                Toast.makeText(AddPlayer.this, ""+throwable.getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        }/*, new Action() {
                            @Override
                            public void run() throws Exception {
                                loadData();
                            }*/);
            }
            break;
        }
    }
}

package com.example.roomdatabasecrud;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.roomdatabasecrud.Room.UserDao;
import com.example.roomdatabasecrud.Room.UserDatabase;
import com.example.roomdatabasecrud.Room.Users;

public class updateActivity extends AppCompatActivity {

    private EditText nameID, emailID;
    private Button updatebtn;
    private Users users;
    private UserDatabase userDatabase;
    private UserDao userDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        nameID = findViewById(R.id.name);
        emailID = findViewById(R.id.email);
        updatebtn = findViewById(R.id.update);

        userDatabase = UserDatabase.getInstance(this);
        userDao = userDatabase.getDao();

        users=(Users) getIntent().getSerializableExtra("model");

        nameID.setText(users.getName());
        emailID.setText(users.getEmail());
        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Users userModel = new Users(users.getId(), nameID.getText().toString(),emailID.getText().toString());

                userDao.update(userModel);

                finish();

            }
        });

    }
}
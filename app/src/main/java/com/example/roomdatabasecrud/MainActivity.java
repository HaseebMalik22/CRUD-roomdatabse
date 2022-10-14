package com.example.roomdatabasecrud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.roomdatabasecrud.Room.UserDao;
import com.example.roomdatabasecrud.Room.UserDatabase;
import com.example.roomdatabasecrud.Room.Users;

import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterListener {

    EditText nameID,emailID;
    Button insertbtn;
    RecyclerView myrecycler;

    private UserDatabase userDatabase;
    private UserDao userDao;

    private UserAdapter userAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userDatabase = UserDatabase.getInstance(this);
        userDao=userDatabase.getDao();


        nameID = findViewById(R.id.name);
        emailID = findViewById(R.id.email);
        insertbtn = findViewById(R.id.insert);
        myrecycler = findViewById(R.id.userRecyler);

        userAdapter= new UserAdapter(this,this);
        myrecycler.setAdapter(userAdapter);
        myrecycler.setLayoutManager(new LinearLayoutManager(this));




        insertbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = nameID.getText().toString();
                String email =emailID.getText().toString();
                Users users = new Users(0,name,email);

                userAdapter.addUser(users);

                userDao.insert(users);
                nameID.setText("");
                emailID.setText("");
                Toast.makeText(MainActivity.this, "inserted", Toast.LENGTH_SHORT).show();

            }
        });


    }

    private void fetchData(){
        userAdapter.clearData();
        List<Users> usersList= userDao.getAllUsers();
        for ( int i=0; i<usersList.size() ;i++ ){
            Users users = usersList.get(i);
            userAdapter.addUser(users);


        }
    }

    @Override
    public void OnUpdate(Users users) {

        Intent intent = new Intent(this, updateActivity.class);
        intent.putExtra("model",users);
        startActivity(intent);

    }

    @Override
    public void OnDelete(int id, int pos) {
        userDao.delete(id);
        userAdapter.removeUser(pos);


    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchData();
    }
}
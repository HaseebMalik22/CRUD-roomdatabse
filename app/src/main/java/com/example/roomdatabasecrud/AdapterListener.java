package com.example.roomdatabasecrud;

import com.example.roomdatabasecrud.Room.Users;

public interface AdapterListener {

    void OnUpdate(Users users);

    void OnDelete(int id, int pos);
}

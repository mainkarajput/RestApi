package com.mainka.restapi.DaoClass;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.mainka.restapi.Entity.Model;

import java.util.ArrayList;
import java.util.List;


@Dao

public interface User_Dao {
    @Query("SELECT * FROM model")
    List<Model> getAll();


    @Insert
    void insertAll(ArrayList<Model> models);

    @Delete
    void delete(ArrayList<Model> models);
}
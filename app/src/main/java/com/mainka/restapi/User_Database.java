package com.mainka.restapi;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.mainka.restapi.DaoClass.User_Dao;
import com.mainka.restapi.Entity.Model;

@Database(entities = {Model.class}, version = 1)
public abstract class User_Database extends RoomDatabase {
       public abstract User_Dao userDao();

       private static volatile User_Database user_database_instance;
        static User_Database getDatabase(Context context) {
            if(user_database_instance==null){
                synchronized (User_Database.class){
                    if(user_database_instance==null){
                        user_database_instance = Room.databaseBuilder(context.getApplicationContext(),
                                User_Database.class, "database-name").allowMainThreadQueries().build();

                    }
                }
            }
             return user_database_instance;
        }
}

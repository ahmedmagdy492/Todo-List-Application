package com.allahakaber.myapplication.pojo;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Todo.class} ,version = 1)
public abstract class TodoDatabase extends RoomDatabase {

    private static TodoDatabase todoDatabase;

    public abstract TodoDao todoDao();

    public static TodoDatabase getInstance(Context context) {
        if(todoDatabase == null)
            todoDatabase = Room.databaseBuilder(context, TodoDatabase.class, "todoDB")
                    .fallbackToDestructiveMigration()
                    .build();
        return todoDatabase;
    }
}

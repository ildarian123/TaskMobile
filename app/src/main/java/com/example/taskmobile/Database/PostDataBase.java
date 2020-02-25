package com.example.taskmobile.Database;
import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.taskmobile.Database.dao.PostDao;
import com.example.taskmobile.Database.entity.PostDb;

@Database(entities = {PostDb.class}, version = 1)
public abstract class PostDataBase extends RoomDatabase {

    private static PostDataBase db = null;

    public static PostDataBase getDataBase(Context applicationContext) {
        if (db == null) {
            db = Room.databaseBuilder(applicationContext,
                    PostDataBase.class, "post_data_base")
                    .allowMainThreadQueries()
                    .build();
        }
        return db;
    }

    public abstract PostDao postDao();
}

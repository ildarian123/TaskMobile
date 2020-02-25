package com.example.taskmobile.Database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "posts")
public class PostDb {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "author")
    public String author;

    @ColumnInfo(name = "post")
    public String post;

    @ColumnInfo(name = "date")
    public long date;
}

package com.example.taskmobile.database;

import androidx.lifecycle.LiveData;

import com.example.taskmobile.database.entity.PostDb;

import java.util.List;

public interface DataBaseManager {

    void insertPostToDataBase(List<PostDb> postDb);
    void deleteAllPosts();
    List<PostDb> getAllPosts();
    List<PostDb> getLastPosts(int limit);
    List<PostDb> getNewPosts(int maxItem);
    int getCountOfRows();
    LiveData<Integer> getCountOfNewPosts();

}

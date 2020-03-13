package com.example.taskmobile.Database;

import androidx.lifecycle.LiveData;

import com.example.taskmobile.Database.entity.PostDb;

import java.util.List;

public interface DataBaseManager {

    void insertPostToDataBase(List<PostDb> postDb);
    void deleteAllPosts();
    List<PostDb> getNextPosts(int maxItem, int limit);
    List<PostDb> getAllPosts();
    List<PostDb> getlastPosts(int limit);
    List<PostDb> getNewPosts(int maxItem);
    int getCountOfRows();
    LiveData<Integer> getCountOfNewPosts();

}

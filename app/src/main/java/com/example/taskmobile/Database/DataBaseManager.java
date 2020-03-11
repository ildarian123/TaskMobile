package com.example.taskmobile.Database;

import com.example.taskmobile.Database.entity.PostDb;

import java.util.List;

public interface DataBaseManager {

    void insertPostToDataBase(List<PostDb> postDb);
    void deleteAllPosts();
    List<PostDb> getNewPosts(int maxItem, int limit);
    List<PostDb> getAllPosts();
    List<PostDb> getlastPosts(int limit);
    int getCountOfRows();

}

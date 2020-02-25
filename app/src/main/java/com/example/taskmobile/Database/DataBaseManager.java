package com.example.taskmobile.Database;

import com.example.taskmobile.Database.entity.PostDb;

import java.util.List;

public interface DataBaseManager {

    void insertPostToDataBase(List<PostDb> postDb);
    void deleteAllPosts();
    void getNewPosts();
    List<PostDb> getAll();

}

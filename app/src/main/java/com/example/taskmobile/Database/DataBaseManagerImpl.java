package com.example.taskmobile.Database;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.taskmobile.Database.dao.PostDao;
import com.example.taskmobile.Database.entity.PostDb;
import com.example.taskmobile.ui.ActivityManager;

import java.util.List;

public class DataBaseManagerImpl implements DataBaseManager {

    private Context context;
    private PostDataBase dataBase;
    private PostDao postDao;
    private ActivityManager activityManager;

    public DataBaseManagerImpl(Context context) {
        this.context = context;
        dataBase = PostDataBase.getDataBase(context);
        postDao = dataBase.postDao();
    }

    @Override
    public void insertPostToDataBase(List<PostDb> postDb) {
        postDao.insertAll(postDb);
    }

    @Override
    public void deleteAllPosts() {
        postDao.deleteAllPosts();
    }

    @Override
    public List<PostDb> getNextPosts(int maxItem, int limit) {
        return postDao.getNextPosts(maxItem, limit);
    }

    @Override
    public List<PostDb> getAllPosts() {
        return postDao.getAll();
    }

    @Override
    public List<PostDb> getlastPosts(int limit) {
        return postDao.getLastPosts(limit);
    }

    @Override
    public List<PostDb> getNewPosts(int maxItem) {
        return postDao.getNewPosts(maxItem);
    }

    @Override
    public int getCountOfRows() {
        return postDao.getCountOfRows();
    }

    @Override
    public LiveData<Integer> getCountOfNewPosts() {
        return postDao.getCountOfNewPosts();
    }

    public void myMethod() {

    }
}

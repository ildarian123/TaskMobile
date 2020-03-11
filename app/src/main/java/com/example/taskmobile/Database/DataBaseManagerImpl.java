package com.example.taskmobile.Database;

import android.content.Context;

import com.example.taskmobile.Database.dao.PostDao;
import com.example.taskmobile.Database.entity.PostDb;
import com.example.taskmobile.ui.ActivityManager;
import com.example.taskmobile.ui.MainActivity;

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
    public List<PostDb> getNewPosts(int maxItem, int limit) {
        return postDao.getNewPosts(maxItem, limit);
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
    public int getCountOfRows() {
        return postDao.getCountOfRows();
    }
}

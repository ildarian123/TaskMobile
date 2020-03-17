package com.example.taskmobile.database;

import android.content.Context;
import androidx.lifecycle.LiveData;
import com.example.taskmobile.database.dao.PostDao;
import com.example.taskmobile.database.entity.PostDb;

import java.util.List;

import javax.inject.Inject;

public class DataBaseManagerImpl implements DataBaseManager {

    private PostDao postDao;

    @Inject
    public DataBaseManagerImpl(Context context) {
        PostDataBase dataBase = PostDataBase.getDataBase(context);
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
    public List<PostDb> getAllPosts() {
        return postDao.getAll();
    }

    @Override
    public List<PostDb> getLastPosts(int limit) {
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

}

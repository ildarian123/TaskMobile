package com.example.taskmobile.ui.main;

import androidx.lifecycle.LiveData;
import com.example.taskmobile.database.DataBaseManager;
import com.example.taskmobile.database.entity.PostDb;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class MainPresenterImpl implements MainPresenter {

    private DataBaseManager dataBaseManager;

    @Inject
    public MainPresenterImpl (DataBaseManager dataBaseManager) {
        this.dataBaseManager = dataBaseManager;
    }

    @Override
    public void deleteAllPosts() {
        dataBaseManager.deleteAllPosts();
    }

    @Override
    public List<PostDb> getAllPosts() {
        return dataBaseManager.getAllPosts();
    }

    @Override
    public void createListOfPosts(int count, int firstPost) {
        ArrayList<PostDb> posts = new ArrayList<>();
        for (int i = firstPost; i <= firstPost + count; i++) {
            PostDb post = new PostDb();
            post.author = "author " + i;
            post.post = "post " + i;
            post.date = System.currentTimeMillis();
            post.newPost = false;
            posts.add(post);
        }
        dataBaseManager.insertPostToDataBase(posts);
    }

    @Override
    public List<PostDb> getlastPosts(int count) {
        return dataBaseManager.getLastPosts(count);
    }

    @Override
    public List<PostDb> getNewPosts(int maxID) {
        return dataBaseManager.getNewPosts(maxID);
    }

    @Override
    public LiveData<Integer> getCountOfNewPosts() {
        return dataBaseManager.getCountOfNewPosts();
    }

    @Override
    public int getCountOfRows() {
        return dataBaseManager.getCountOfRows();
    }

}

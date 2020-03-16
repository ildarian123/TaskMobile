package com.example.taskmobile.ui;

import androidx.recyclerview.widget.RecyclerView;

import com.example.taskmobile.Database.DataBaseManager;
import com.example.taskmobile.Database.entity.PostDb;

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
    public void onLastItemShown() {
        dataBaseManager.getNewPosts(10);
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
        return dataBaseManager.getlastPosts(count);
    }

}

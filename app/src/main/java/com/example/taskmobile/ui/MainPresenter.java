package com.example.taskmobile.ui;

import androidx.lifecycle.LiveData;
import com.example.taskmobile.Database.entity.PostDb;
import java.util.List;

public interface MainPresenter {
    void onLastItemShown();
    void deleteAllPosts();
    List<PostDb> getAllPosts();
    void createListOfPosts(int count, int firstPost);
    List<PostDb>getlastPosts(int count);
    List<PostDb> getNewPosts(int maxID);
    LiveData<Integer> getCountOfNewPosts();
    int getCountOfRows();
}

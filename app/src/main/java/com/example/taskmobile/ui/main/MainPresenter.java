package com.example.taskmobile.ui.main;

import androidx.lifecycle.LiveData;
import com.example.taskmobile.database.entity.PostDb;
import java.util.List;

public interface MainPresenter {
    void deleteAllPosts();
    List<PostDb> getAllPosts();
    void createListOfPosts(int count, int firstPost);
    List<PostDb>getlastPosts(int count);
    List<PostDb> getNewPosts(int maxID);
    LiveData<Integer> getCountOfNewPosts();
    int getCountOfRows();
}

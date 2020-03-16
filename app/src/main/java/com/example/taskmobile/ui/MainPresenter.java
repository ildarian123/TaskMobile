package com.example.taskmobile.ui;

import androidx.recyclerview.widget.RecyclerView;
import com.example.taskmobile.Database.entity.PostDb;
import java.util.List;

public interface MainPresenter {
    void onLastItemShown();
    void deleteAllPosts();
    List<PostDb> getAllPosts();
    void createListOfPosts(int count, int firstPost);
    List<PostDb>getlastPosts(int count);
}

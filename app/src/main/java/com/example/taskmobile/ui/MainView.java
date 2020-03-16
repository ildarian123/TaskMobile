package com.example.taskmobile.ui;

import androidx.recyclerview.widget.RecyclerView;

import com.example.taskmobile.Database.entity.PostDb;

import java.util.List;

public interface MainView {

    MainPresenter getMainPresenter();
    void setPostAdapter(PostAdapter adapter);
    void setRecyclerList(RecyclerView recyclerList);
    void setListOfPostsToAdapter(List<PostDb> listOfPosts);
}

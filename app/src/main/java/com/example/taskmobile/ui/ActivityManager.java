package com.example.taskmobile.ui;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskmobile.Database.entity.PostDb;

import java.util.List;

public interface ActivityManager {
    void onLastItemShown();
    void OnNewPostsAddedToDataBase(List<PostDb> listOFNewPosts);
    void setRecyclerList(RecyclerView recyclerList);
    void setPostAdapter(PostAdapter adapter);
}

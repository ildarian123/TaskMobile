package com.example.taskmobile.ui;

import com.example.taskmobile.Database.entity.PostDb;

import java.util.List;

public interface ActivityManager {
    void setPostsToUi(List<PostDb> ListOfPosts);
}

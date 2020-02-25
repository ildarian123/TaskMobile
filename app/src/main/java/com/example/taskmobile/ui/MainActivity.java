package com.example.taskmobile.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.provider.ContactsContract;

import com.example.taskmobile.Database.DataBaseManager;
import com.example.taskmobile.Database.DataBaseManagerImpl;
import com.example.taskmobile.Database.entity.PostDb;
import com.example.taskmobile.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ActivityManager {

    private DataBaseManager dataBaseManager;
    private List<PostDb> listOfPosts;
    private RecyclerView recyclerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listOfPosts = new ArrayList<PostDb>();

        for (int i = 1; i < 10; i++) {
            PostDb post = new PostDb();
            post.author = "author " + i;
            post.post = "post " + i;
            post.date = System.currentTimeMillis();

            listOfPosts.add(post);
        }

        dataBaseManager = new DataBaseManagerImpl(this);
        dataBaseManager.insertPostToDataBase(listOfPosts);

        recyclerList = findViewById(R.id.rv_posts);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerList.setLayoutManager(layoutManager);
        recyclerList.setHasFixedSize(true);

        listOfPosts = dataBaseManager.getAll();
        PostAdapter adapter = new PostAdapter(listOfPosts);
        recyclerList.setAdapter(adapter);
    }

    @Override
    public void setPostsToUi(List<PostDb> ListOfPosts) {

    }


}

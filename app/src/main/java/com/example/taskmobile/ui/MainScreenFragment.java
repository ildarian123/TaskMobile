package com.example.taskmobile.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.taskmobile.App;
import com.example.taskmobile.Database.DataBaseManager;
import com.example.taskmobile.Database.entity.PostDb;
import com.example.taskmobile.R;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static com.example.taskmobile.ui.MainActivity.APP_PREFERENCES;
import static com.example.taskmobile.ui.MainActivity.FIRST_START_OF_THE_APP;

public class MainScreenFragment extends Fragment implements ActivityManager {

    private PostAdapter adapter;
    private RecyclerView recyclerList;
    public SharedPreferences mSettings;
    private List<PostDb> newPosts;
    private Button clearButton;
    private Button reloadButton;
    private ActivityManager activityManager;

    @Inject
    DataBaseManager dataBaseManager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_screen, container, false);

        App.mainComponent.injectsMainScreenFragment(this);
        mSettings = getActivity().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        boolean isFirstStart = mSettings.getBoolean(FIRST_START_OF_THE_APP, true);


        if (isFirstStart) {
            ArrayList<PostDb> posts = createListOfPosts(100, 0);
            SharedPreferences.Editor editor = mSettings.edit();
            editor.putBoolean(FIRST_START_OF_THE_APP, false);
            editor.apply();
            dataBaseManager.insertPostToDataBase(posts);
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    int maxNumber = dataBaseManager.getCountOfRows();

                    List<PostDb> posts = createListOfPosts(5, maxNumber);
                    dataBaseManager.insertPostToDataBase(posts);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Log.e("asdasdasd", "asd");
                }
            }
        }).start();

        clearButton = view.findViewById(R.id.button_clear);
        clearButton.setOnClickListener(new View.OnClickListener() { //TODO
            @Override
            public void onClick(View view) {

                dataBaseManager.deleteAllPosts();
                List<PostDb> allPosts = dataBaseManager.getAllPosts();
                adapter = new PostAdapter(allPosts, getActivityManager());
                recyclerList.setAdapter(adapter);
            }
        });

        recyclerList = view.findViewById(R.id.rv_posts);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerList.setLayoutManager(layoutManager);
        recyclerList.setHasFixedSize(true);

        List<PostDb> posts = dataBaseManager.getlastPosts(20);
        adapter = new PostAdapter(posts, this);
        recyclerList.setAdapter(adapter);

        return view;
    }

    private ArrayList<PostDb> createListOfPosts(int count, int firstPost) {
        ArrayList<PostDb> posts = new ArrayList<>();
        for (int i = firstPost; i <= firstPost + count; i++) {
            PostDb post = new PostDb();
            post.author = "author " + i;
            post.post = "post " + i;
            post.date = System.currentTimeMillis();
            post.newPost = false;
            posts.add(post);
        }
        return posts;
    }

    @Override
    public void onLastItemShown() {
        getMorePosts();
    }

    private void getMorePosts() {
        List<PostDb> newPosts = dataBaseManager.getNewPosts(adapter.getListOfPosts().get(adapter.getListOfPosts().size() - 1).id, 15);
        adapter.getListOfPosts().addAll(newPosts);

        recyclerList.post(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
            }
        });
    }

    public ActivityManager getActivityManager() {
        return this;
    }

}

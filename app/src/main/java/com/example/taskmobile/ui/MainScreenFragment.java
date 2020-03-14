package com.example.taskmobile.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import static com.example.taskmobile.ui.MainActivity.APP_PREFERENCES;
import static com.example.taskmobile.ui.MainActivity.FIRST_START_OF_THE_APP;

public class MainScreenFragment extends Fragment implements ActivityManager{

    private PostAdapter adapter;
    private RecyclerView recyclerList;
    public SharedPreferences mSettings;
    private List<PostDb> newPosts;
    private Button clearButton;
    private Button countOfPostsButton;
    private ActivityManager activityManager;
    private LiveData<Integer> liveData;

    @Inject
    DataBaseManager dataBaseManager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_screen, container, false);

        App.mainComponent.injectsMainScreenFragment(this);
        mSettings = getActivity().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        boolean isFirstStart = mSettings.getBoolean(FIRST_START_OF_THE_APP, true);
        activityManager = (ActivityManager) getActivity();

        countOfPostsButton = view.findViewById(R.id.button_CountOFPosts);
        countOfPostsButton.setAllCaps(false);

        if (isFirstStart) {
            ArrayList<PostDb> posts = createListOfPosts(100, 0);
            SharedPreferences.Editor editor = mSettings.edit();
            editor.putBoolean(FIRST_START_OF_THE_APP, false);
            editor.apply();
            dataBaseManager.insertPostToDataBase(posts);
        }

        liveData = dataBaseManager.getCountOfNewPosts();
        liveData.observe(getActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                int adapterCountOFPosts = adapter.getListOfPosts().size();
                countOfPostsButton.setText(String.valueOf(""+String.valueOf(integer - adapterCountOFPosts))+ " new posts");

                if (integer == 0) {
                    countOfPostsButton.setVisibility(View.INVISIBLE);
                } else {
                    countOfPostsButton.setVisibility(View.VISIBLE);
                }
            }
        });

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


        countOfPostsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<PostDb> newPosts;
                int maxItemId = adapter.getListOfPosts().size();

                if (maxItemId == 0) {
                    List<PostDb> allPosts = dataBaseManager.getAllPosts();
                    Collections.reverse(allPosts);
                    adapter.setListOfPosts(allPosts);
                } else {
                    newPosts = dataBaseManager.getNewPosts(adapter.getListOfPosts().get(0).id);
                    Collections.reverse(newPosts);
                    List<PostDb> listOfPosts = adapter.getListOfPosts();
                    List<PostDb> asd = new ArrayList<>();
                    asd.addAll(newPosts);
                    asd.addAll(listOfPosts);
                    adapter.setListOfPosts(asd);
                }
                adapter.notifyDataSetChanged();
            }
        });

//        clearButton = view.findViewById(R.id.button_clear);
//        clearButton.setAllCaps(false);
//        clearButton.setOnClickListener(new View.OnClickListener() { //TODO
//            @Override
//            public void onClick(View view) {
//
//                dataBaseManager.deleteAllPosts();
//                List<PostDb> allPosts = dataBaseManager.getAllPosts();
//                adapter = new PostAdapter(allPosts, getActivityManager());
//                recyclerList.setAdapter(adapter);
//            }
//        });

        recyclerList = view.findViewById(R.id.rv_posts);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerList.setLayoutManager(layoutManager);
        recyclerList.setHasFixedSize(true);

        List<PostDb> posts = dataBaseManager.getlastPosts(20);
        adapter = new PostAdapter(posts, this);
        recyclerList.setAdapter(adapter);

        activityManager.setRecyclerList(recyclerList);
        activityManager.setPostAdapter(adapter);

        if (adapter.getListOfPosts().size() == 0) {
            //countOfPostsButton.setVisibility(View.INVISIBLE);

        } else {
//            countOfPostsButton.setText("" + (dataBaseManager.getCountOfNewPosts().getValue() - adapter.getListOfPosts().size()) + " NEW POSTS");

        }

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

    @Override
    public void OnNewPostsAddedToDataBase(List<PostDb> listOFNewPosts) {

    }

    @Override
    public void setRecyclerList(RecyclerView recyclerList) {

    }

    @Override
    public void setPostAdapter(PostAdapter adapter) {

    }

    private void getMorePosts() {
        List<PostDb> newPosts = dataBaseManager.getNextPosts(adapter.getListOfPosts().get(adapter.getListOfPosts().size() - 1).id, 15);
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

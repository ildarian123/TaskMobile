package com.example.taskmobile.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.example.taskmobile.App;
import com.example.taskmobile.Database.entity.PostDb;
import com.example.taskmobile.R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import javax.inject.Inject;
import static com.example.taskmobile.ui.MainActivity.APP_PREFERENCES;
import static com.example.taskmobile.ui.MainActivity.FIRST_START_OF_THE_APP;

public class MainScreenFragment extends Fragment {

    private PostAdapter adapter;
    private SharedPreferences mSettings;
    private Button countOfPostsButton;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Inject
    MainPresenter mainPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)  {
        View view = inflater.inflate(R.layout.fragment_main_screen, container, false);

        App.mainComponent.injectsMainScreenFragment(this);
        mSettings = Objects.requireNonNull(getActivity()).getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        boolean isFirstStart = mSettings.getBoolean(FIRST_START_OF_THE_APP, true);

        countOfPostsButton = view.findViewById(R.id.button_CountOFPosts);
        countOfPostsButton.setAllCaps(false);

        swipeRefreshLayout = view.findViewById(R.id.swipe_container);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                addNewPostsToAdapter();
            }
        });

        if (isFirstStart) {
            addPostsToDataBaseOnFirstStartOfTheApp();
        }

        LiveData<Integer> liveData = mainPresenter.getCountOfNewPosts();
        liveData.observe(getActivity(), new Observer<Integer>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onChanged(Integer integer) {
                int adapterCountOFPosts = adapter.getListOfPosts().size();
                countOfPostsButton.setText("" + (integer - adapterCountOFPosts) + " new posts");

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
                    int maxNumber = mainPresenter.getCountOfRows();

                    mainPresenter.createListOfPosts(5, maxNumber);

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
               addNewPostsToAdapter();
            }
        });

        RecyclerView recyclerList = view.findViewById(R.id.rv_posts);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerList.setLayoutManager(layoutManager);
        recyclerList.setHasFixedSize(true);

        List<PostDb> posts = mainPresenter.getlastPosts(20);
        adapter = new PostAdapter(posts, mainPresenter);
        recyclerList.setAdapter(adapter);
        ((MainActivity) getActivity()).setFragment(this);
        return view;
    }

    void clearList() {
        if (adapter != null) {
            mainPresenter.deleteAllPosts();
            List<PostDb> allPosts = mainPresenter.getAllPosts();
            adapter.setListOfPosts(allPosts);
            adapter.notifyDataSetChanged();
        }
    }

    private void addPostsToDataBaseOnFirstStartOfTheApp(){

        mainPresenter.createListOfPosts(100, 0);

        SharedPreferences.Editor editor = mSettings.edit();
        editor.putBoolean(FIRST_START_OF_THE_APP, false);
        editor.apply();
    }


    private void addNewPostsToAdapter(){
        List<PostDb> newPosts;
        int maxItemId = adapter.getListOfPosts().size();

        if (maxItemId == 0) {
            List<PostDb> allPosts = mainPresenter.getAllPosts();
            Collections.reverse(allPosts);
            adapter.setListOfPosts(allPosts);
        } else {
            newPosts = mainPresenter.getNewPosts(adapter.getListOfPosts().get(0).id);
            Collections.reverse(newPosts);
            List<PostDb> listOfPosts = adapter.getListOfPosts();
            List<PostDb> asd = new ArrayList<>();
            asd.addAll(newPosts);
            asd.addAll(listOfPosts);
            adapter.setListOfPosts(asd);
        }
        adapter.notifyDataSetChanged();
        countOfPostsButton.setVisibility(View.INVISIBLE);
        swipeRefreshLayout.setRefreshing(false);
    }
}

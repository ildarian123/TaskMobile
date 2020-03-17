package com.example.taskmobile.ui.main;

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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.example.taskmobile.App;
import com.example.taskmobile.database.entity.PostDb;
import com.example.taskmobile.R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import javax.inject.Inject;
import static com.example.taskmobile.ui.main.MainActivity.APP_PREFERENCES;
import static com.example.taskmobile.ui.main.MainActivity.FIRST_START_OF_THE_APP;

public class MainScreenFragment extends Fragment implements MainScreenView {

    private PostAdapter adapter;
    private SharedPreferences mSettings;
    private Button countOfPostsButton;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerList;
    private boolean isFirstStart;

    @Inject
    MainPresenter mainPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)  {
        View view = inflater.inflate(R.layout.fragment_main_screen, container, false);
        App.mainComponent.injectsMainScreenFragment(this);
        findViews(view);
        setListeners();
        anableSharedPrefereces();
        if (isFirstStart) {
            addPostsToDataBaseOnFirstStartOfTheApp();
        }
        addLiveDataToCountOfPostsButton();
        startThreadToAddNewPostsToDb();
        anableRecyclerList();
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

    private void findViews (View view){
        countOfPostsButton = view.findViewById(R.id.button_CountOFPosts);
        countOfPostsButton.setAllCaps(false);
        swipeRefreshLayout = view.findViewById(R.id.swipe_container);
        recyclerList = view.findViewById(R.id.rv_posts);
    }

    private void setListeners(){
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                addNewPostsToAdapter();
            }
        });

        countOfPostsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewPostsToAdapter();
            }
        });
    }

    private void addLiveDataToCountOfPostsButton(){
        LiveData<Integer> liveData = mainPresenter.getCountOfNewPosts();
        liveData.observe(Objects.requireNonNull(getActivity()), new Observer<Integer>() {
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
    }

    private void startThreadToAddNewPostsToDb(){
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
                }
            }
        }).start();
    }

    private void anableRecyclerList(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerList.setLayoutManager(layoutManager);
        recyclerList.setHasFixedSize(true);
        List<PostDb> posts = mainPresenter.getlastPosts(20);
        adapter = new PostAdapter(posts, this);
        recyclerList.setAdapter(adapter);
        ((MainActivity) Objects.requireNonNull(getActivity())).setFragment(this);
    }
    private void anableSharedPrefereces(){
        mSettings = Objects.requireNonNull(getActivity())
                .getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        isFirstStart = mSettings.getBoolean(FIRST_START_OF_THE_APP, true);
    }

    @Override
    public void onLastItemShown() {
        mainPresenter.getNewPosts(10);
    }
}

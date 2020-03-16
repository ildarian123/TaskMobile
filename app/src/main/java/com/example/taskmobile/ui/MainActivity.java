package com.example.taskmobile.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.taskmobile.App;
import com.example.taskmobile.Database.DataBaseManager;
import com.example.taskmobile.Database.entity.PostDb;
import com.example.taskmobile.R;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MainView {

    @Inject
    DataBaseManager dataBaseManager;

    @Inject
    MainPresenter mainPresenter;

    private List<PostDb> listOfPosts;
    private RecyclerView recyclerList;
    public static final String APP_PREFERENCES = "mysettings";
    public static final String FIRST_START_OF_THE_APP = "FIRST_START_OF_THE_APP";
    public SharedPreferences mSettings;
    private List<PostDb> newPosts;
    NavController navController;
    private TextView clearButton;
    private PostAdapter adapter;

    private MainScreenFragment mainScreenFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        clearButton = findViewById(R.id.button_clear);
        clearButton.setAllCaps(false);

        clearButton.setOnClickListener(new View.OnClickListener() { //TODO
            @Override
            public void onClick(View view) {
               mainScreenFragment.clearList();
            }
        });

        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        navController.navigate(R.id.ErorScreenFragment);
        App.mainComponent.injectsMainActivity(this);
    }

    public void setFragment(MainScreenFragment fragment) {
        mainScreenFragment = fragment;
    }

    @Override
    public MainPresenter getMainPresenter() {
        return mainPresenter;
    }

    @Override
    public void setPostAdapter(PostAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public void setRecyclerList(RecyclerView recyclerList) {

    }

    @Override
    public void setListOfPostsToAdapter(List<PostDb> listOfPosts) {

    }

}

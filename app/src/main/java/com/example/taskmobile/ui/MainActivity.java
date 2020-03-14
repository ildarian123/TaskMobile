package com.example.taskmobile.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.taskmobile.App;
import com.example.taskmobile.Database.DataBaseManager;
import com.example.taskmobile.Database.entity.PostDb;
import com.example.taskmobile.R;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements ActivityManager {

    @Inject
    DataBaseManager dataBaseManager;

    private List<PostDb> listOfPosts;
    private RecyclerView recyclerList;
    public static final String APP_PREFERENCES = "mysettings";
    public static final String FIRST_START_OF_THE_APP = "FIRST_START_OF_THE_APP";
    public SharedPreferences mSettings;
    private List<PostDb> newPosts;
    NavController navController;
    private TextView clearButton;
    private PostAdapter adapter;

    private Fragment mainScreenFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clearButton = findViewById(R.id.button_clear);
        clearButton.setAllCaps(false);

        clearButton.setOnClickListener(new View.OnClickListener() { //TODO
            @Override
            public void onClick(View view) {
                if (adapter != null) {
                    dataBaseManager.deleteAllPosts();
                    List<PostDb> allPosts = dataBaseManager.getAllPosts();
                    adapter.setListOfPosts(allPosts);
                    adapter.notifyDataSetChanged();
                }
            }
        });

        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        navController.navigate(R.id.ErorScreenFragment);


        App.mainComponent.injectsMainActivity(this);

    }

    @Override
    public void onLastItemShown() {

    }

    @Override
    public void OnNewPostsAddedToDataBase(List<PostDb> listOFNewPosts) {

    }

    @Override
    public void setRecyclerList(RecyclerView recyclerList) {
        this.recyclerList = recyclerList;
    }

    @Override
    public void setPostAdapter(PostAdapter adapter) {
        this.adapter = adapter;
    }


    public ActivityManager getActivityManager() {
        return this;
    }


}

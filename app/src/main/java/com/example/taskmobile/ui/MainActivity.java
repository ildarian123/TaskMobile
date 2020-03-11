package com.example.taskmobile.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.taskmobile.App;
import com.example.taskmobile.Database.DataBaseManager;
import com.example.taskmobile.Database.entity.PostDb;
import com.example.taskmobile.R;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity  {

    @Inject
    DataBaseManager dataBaseManager;

    private List<PostDb> listOfPosts;
    private RecyclerView recyclerList;
    private PostAdapter adapter;
    public static final String APP_PREFERENCES = "mysettings";
    public static final String FIRST_START_OF_THE_APP = "FIRST_START_OF_THE_APP";
    public SharedPreferences mSettings;
    private List<PostDb> newPosts;
    NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        fragment = new MainScreenFragment();

        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        navController.navigate(R.id.ErorScreenFragment);


        App.mainComponent.injectsMainActivity(this);

    }

}

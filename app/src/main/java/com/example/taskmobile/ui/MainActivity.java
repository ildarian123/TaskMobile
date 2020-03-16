package com.example.taskmobile.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.example.taskmobile.App;
import com.example.taskmobile.R;
import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    MainPresenter mainPresenter;
    public static final String APP_PREFERENCES = "mysettings";
    public static final String FIRST_START_OF_THE_APP = "FIRST_START_OF_THE_APP";
    private MainScreenFragment mainScreenFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView clearButton = findViewById(R.id.button_clear);
        clearButton.setAllCaps(false);

        clearButton.setOnClickListener(new View.OnClickListener() { //TODO
            @Override
            public void onClick(View view) {
               mainScreenFragment.clearList();
            }
        });

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        navController.navigate(R.id.ErorScreenFragment);
        App.mainComponent.injectsMainActivity(this);
    }

    public void setFragment(MainScreenFragment fragment) {
        mainScreenFragment = fragment;
    }

}

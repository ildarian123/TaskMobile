package com.example.taskmobile;

import androidx.fragment.app.Fragment;

import com.example.taskmobile.ui.ErorScreenFragment;
import com.example.taskmobile.ui.MainActivity;
import com.example.taskmobile.ui.MainScreenFragment;

import dagger.Component;

@Component(modules = {DepsManager.class})
public interface MainComponent {

    void injectsMainActivity(MainActivity mainActivity);
    void injectsMainScreenFragment(MainScreenFragment mainScreenFragment);
    void injectsErorScreenFragment(ErorScreenFragment erorScreenFragment);
//    void injectsDataBaseManager(DataBaseManager dataBaseManager);
}

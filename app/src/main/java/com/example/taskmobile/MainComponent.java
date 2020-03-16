package com.example.taskmobile;

import com.example.taskmobile.ui.MainActivity;
import com.example.taskmobile.ui.MainScreenFragment;

import dagger.Component;

@Component(modules = {MainModule.class})
public interface MainComponent {

    void injectsMainActivity(MainActivity mainActivity);
    void injectsMainScreenFragment(MainScreenFragment mainScreenFragment);
}

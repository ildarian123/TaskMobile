package com.example.taskmobile.di;

import com.example.taskmobile.ui.main.MainActivity;
import com.example.taskmobile.ui.main.MainScreenFragment;

import dagger.Component;

@Component(modules = {MainModule.class})
public interface MainComponent {

    void injectsMainActivity(MainActivity mainActivity);
    void injectsMainScreenFragment(MainScreenFragment mainScreenFragment);
}

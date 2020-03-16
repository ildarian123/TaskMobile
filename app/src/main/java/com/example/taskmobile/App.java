package com.example.taskmobile;

import android.app.Application;

public class App extends Application {


    public static MainComponent mainComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initDagger();
    }

    private void initDagger() {
        mainComponent = DaggerMainComponent.builder()
                .mainModule(new MainModule(this))
                .build();
    }
}

package com.example.taskmobile;


import android.content.Context;

import com.example.taskmobile.Database.DataBaseManager;
import com.example.taskmobile.Database.DataBaseManagerImpl;
import com.example.taskmobile.ui.ActivityManager;

import dagger.Module;
import dagger.Provides;

@Module
public class DepsManager {

    private Context context;

    public DepsManager(Context context) {
        this.context = context;
    }

    @Provides
    public DataBaseManager providesDataBaseManager() {
        return new DataBaseManagerImpl(context);
    }

}

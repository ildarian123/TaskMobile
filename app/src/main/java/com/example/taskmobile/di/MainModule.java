package com.example.taskmobile.di;


import android.content.Context;
import com.example.taskmobile.database.DataBaseManager;
import com.example.taskmobile.database.DataBaseManagerImpl;
import com.example.taskmobile.ui.main.MainPresenter;
import com.example.taskmobile.ui.main.MainPresenterImpl;

import dagger.Module;
import dagger.Provides;

@Module
public
class MainModule {

    private Context context;

    public MainModule(Context context) {
        this.context = context;
    }

    @Provides
    DataBaseManager providesDataBaseManager() {
        return new DataBaseManagerImpl(context);
    }

    @Provides
    MainPresenter providesMainPresenter(DataBaseManager dataBaseManager) {
        return new MainPresenterImpl(dataBaseManager);
    }

}

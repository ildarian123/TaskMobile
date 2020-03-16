package com.example.taskmobile;


import android.content.Context;
import com.example.taskmobile.Database.DataBaseManager;
import com.example.taskmobile.Database.DataBaseManagerImpl;
import com.example.taskmobile.ui.MainPresenter;
import com.example.taskmobile.ui.MainPresenterImpl;

import dagger.Module;
import dagger.Provides;

@Module
class MainModule {

    private Context context;

    MainModule(Context context) {
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

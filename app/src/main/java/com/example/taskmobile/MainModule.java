package com.example.taskmobile;


import android.content.Context;
import android.provider.ContactsContract;

import com.example.taskmobile.Database.DataBaseManager;
import com.example.taskmobile.Database.DataBaseManagerImpl;
import com.example.taskmobile.ui.MainPresenter;
import com.example.taskmobile.ui.MainPresenterImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

    private Context context;

    public MainModule(Context context) {
        this.context = context;
    }

    @Provides
    public DataBaseManager providesDataBaseManager() {
        return new DataBaseManagerImpl(context);
    }

    @Provides
    public MainPresenter providesMainPresenter(DataBaseManager dataBaseManager) {
        return new MainPresenterImpl(dataBaseManager);
    }

}

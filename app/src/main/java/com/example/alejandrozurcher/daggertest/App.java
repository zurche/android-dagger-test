package com.example.alejandrozurcher.daggertest;

import android.app.Application;

import com.example.alejandrozurcher.daggertest.component.ApplicationComponent;
import com.example.alejandrozurcher.daggertest.component.DaggerApplicationComponent;
import com.example.alejandrozurcher.daggertest.module.ApplicationModule;

/**
 * Created by alejandro.zurcher on 12/27/2016.
 */

public class App extends Application {

    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getComponent() {
        return component;
    }
}

package com.example.alejandrozurcher.daggertest.component;

import com.example.alejandrozurcher.daggertest.MainActivity;
import com.example.alejandrozurcher.daggertest.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by alejandro.zurcher on 12/27/2016.
 */
@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    void inject(MainActivity activity);

}

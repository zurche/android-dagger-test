package com.example.alejandrozurcher.daggertest;

import android.net.ConnectivityManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Arrays;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    ConnectivityManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inject dependencies to MainActivity
        ((App) getApplication()).getComponent().inject(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //Use the dependencies
            System.out.println("Active Network: " + manager.getActiveNetwork());
            System.out.println("Active Network Info: " + manager.getActiveNetworkInfo());
            System.out.println("Networks: " + Arrays.toString(manager.getAllNetworks()));
        }
    }
}

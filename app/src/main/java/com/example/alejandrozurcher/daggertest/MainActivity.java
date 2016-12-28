package com.example.alejandrozurcher.daggertest;

import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.Arrays;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    public static final String USER_NAME_KEY = "USER_NAME_KEY";

    @Inject
    ConnectivityManager manager;
    @Inject
    SharedPreferences sharedPreferences;

    @BindView(R.id.network_text)
    TextView network_text;
    @BindView(R.id.user_text)
    TextView user_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        //Inject dependencies to MainActivity
        ((App) getApplication()).getComponent().inject(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //Use the dependencies
            System.out.println("Active Network: " + manager.getActiveNetwork());
            System.out.println("Active Network Info: " + manager.getActiveNetworkInfo());
            System.out.println("Networks: " + Arrays.toString(manager.getAllNetworks()));

            network_text.setText(manager.getActiveNetworkInfo().toString());
        }

        //Using injected SharedPreferences
        sharedPreferences.edit().putString(USER_NAME_KEY, "zurche").apply();

        //Read preference
        user_text.setText(sharedPreferences.getString(USER_NAME_KEY, ""));
    }
}

package com.example.alejandrozurcher.daggertest;

import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.alejandrozurcher.daggertest.api.OWMapsEndpoints;

import java.util.Arrays;

import javax.inject.Inject;

import az.openweatherapi.model.gson.current_day.CurrentWeather;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    public static final String USER_NAME_KEY = "USER_NAME_KEY";

    @Inject
    ConnectivityManager manager;
    @Inject
    SharedPreferences sharedPreferences;
    @Inject
    Retrofit retrofit;

    @BindView(R.id.network_text)
    TextView network_text;
    @BindView(R.id.user_text)
    TextView user_text;
    @BindView(R.id.temp_text)
    TextView temp_text;

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

        //Use retrofit to get temperature for Córdoba, Argentina
        retrofit.create(OWMapsEndpoints.class)
                .getCurrentWeather(-31.4166, -64.183, "b97081fb50c5b5c19841ec6ae4f5daec", "metric", "en")
                .enqueue(new Callback<CurrentWeather>() {
                    @Override
                    public void onResponse(Response<CurrentWeather> response, Retrofit retrofit) {
                        Log.d(TAG, "Got Weather info: "
                                + response.body().getName() + "\n"
                                + response.body().getMain().getTemp());

                        temp_text.setText(response.body().getName() + " | " + response.body().getMain().getTemp() + "º");
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        Log.e(TAG, "Getting Weather info failed");
                    }
                });

    }
}

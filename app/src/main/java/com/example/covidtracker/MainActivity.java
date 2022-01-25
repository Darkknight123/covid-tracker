package com.example.covidtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView totalConfirmed,totalRecovered,totalActive,totalDeaths,confirmed,recovered,died;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();


    }
    private void init(){
        totalConfirmed = findViewById(R.id.totalconfirmed);
        totalRecovered = findViewById(R.id.recovered);
        totalActive =findViewById(R.id.ActiveCases);
        totalDeaths =findViewById(R.id.totalDeaths);
        confirmed= findViewById(R.id.confirmedToday);
        recovered =findViewById(R.id.recoveredToday);
        died = findViewById(R.id.deathsToday);

    }
}
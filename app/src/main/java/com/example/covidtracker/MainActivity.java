package com.example.covidtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private TextView totalConfirmed,totalRecovered,totalActive,totalDeaths,confirmed,recovered,died;

    private List<Country> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = new ArrayList<>();

        init();
        ApiUtilities.getApiInterface().getCountry()
                .enqueue(new Callback<List<Country>>() {
                    @Override
                    public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {
                        list.addAll(response.body());

                        for (int i = 0; i<list.size();i++){
                            if (list.get(i).getCountry().equals("Kenya")){
                                int confirm = Integer.parseInt(list.get(i).getCases());
                                int active = Integer.parseInt(list.get(i).getActive());
                                int recovered = Integer.parseInt(list.get(i).getRecovered());
                                int deaths = Integer.parseInt(list.get(i).getDeaths());

                                totalConfirmed.setText(NumberFormat.getInstance().format(confirm));
                                totalActive.setText(NumberFormat.getInstance().format(active));
                                totalRecovered.setText(NumberFormat.getInstance().format(recovered));
                                totalDeaths.setText(NumberFormat.getInstance().format(deaths));
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Country>> call, Throwable t) {

                        Toast.makeText(MainActivity.this, "Error : " + t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });


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
package com.example.covidtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private TextView totalConfirmed,totalRecovered,totalActive,totalDeaths,confirmed,recovered,died, dateTv,totatTests;

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
                        assert response.body() != null;
                        list.addAll(response.body());

                        for (int i = 0; i<list.size();i++){
                            if (list.get(i).getCountry().equals("Kenya")){
                                int confirm = Integer.parseInt(list.get(i).getCases());
                                int active = Integer.parseInt(list.get(i).getActive());
                                int recover = Integer.parseInt(list.get(i).getRecovered());
                                int deaths = Integer.parseInt(list.get(i).getDeaths());

                                totalConfirmed.setText(NumberFormat.getInstance().format(confirm));
                                totalActive.setText(NumberFormat.getInstance().format(active));
                                totalRecovered.setText(NumberFormat.getInstance().format(recover));
                                totalDeaths.setText(NumberFormat.getInstance().format(deaths));

                                setText(list.get(i).getUpdated());


                                confirmed.setText(NumberFormat.getInstance().format(Integer.parseInt(list.get(i).getTodayCases())));
                                recovered.setText(NumberFormat.getInstance().format(Integer.parseInt(list.get(i).getTodayRecovered())));
                                died.setText(NumberFormat.getInstance().format(Integer.parseInt(list.get(i).getTodayDeaths())));
                                totatTests.setText(NumberFormat.getInstance().format(Integer.parseInt(list.get(i).getTests())));

                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Country>> call, Throwable t) {

                        Toast.makeText(MainActivity.this, "Error : " + t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });


    }

    private void setText(String updated) {
        DateFormat format = new SimpleDateFormat("MM dd, yyyy");

        long milliseconds = Long.parseLong(updated);

        Calendar calendar = Calendar.getInstance();
        calendar .setTimeInMillis(milliseconds);

        dateTv.setText("Updated on "+ format.format(calendar.getTime()));

    }

    private void init(){
        totalConfirmed = findViewById(R.id.totalconfirmed);
        totalRecovered = findViewById(R.id.recovered);
        totalActive =findViewById(R.id.ActiveCases);
        totalDeaths =findViewById(R.id.totalDeaths);
        confirmed= findViewById(R.id.confirmedToday);
        recovered =findViewById(R.id.recoveredToday);
        died = findViewById(R.id.deathsToday);
        dateTv =findViewById(R.id.update);
        totatTests =findViewById(R.id.totalTest);

    }
}
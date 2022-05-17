package com.example.finalproject;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CovidActivity extends AppCompatActivity{


    String country = "";
    Date date = new Date();
    Date dayBeforeDate = new Date(date.getTime() - 86400000L);


    @SuppressLint("SimpleDateFormat")
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    String strDate = formatter.format(dayBeforeDate);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        TextView covidInfo = findViewById(R.id.covid_info_view);
        EditText countryText = findViewById(R.id.country_text);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://covid-193.p.rapidapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CovidInfoService service = retrofit.create(CovidInfoService.class);
        Button getInfoButton = findViewById(R.id.get_info_btn);

        getInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                country = countryText.getText().toString();
                Call<CovidHistory> call = service.covidHistory(country, strDate);
                call.enqueue(new Callback<CovidHistory>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onResponse(Call<CovidHistory> call, Response<CovidHistory> response) {
                        CovidHistory  covidHistory = (CovidHistory)response.body();
                        Log.d("RESPONSE", country + strDate);
                        assert covidHistory != null;
                        String covidInfoText = "Covid Info: \n" + "Total cases: " + covidHistory.getResponse().get(0).getCases().getTotal() +
                                "\nRecent cases: " + covidHistory.getResponse().get(0).getCases().getNew() +
                                "\nTotal Deaths: " + covidHistory.getResponse().get(0).getDeaths().getTotal() +
                                "\nRecent deaths: " + covidHistory.getResponse().get(0).getDeaths().getNew();
                        covidInfo.setText(covidInfoText);
                    }

                    @Override
                    public void onFailure(Call<CovidHistory> call, Throwable t) {
                        Log.d("ERROR", "onFailure called");
                        Toast.makeText(CovidActivity.this, "Check the 'country' field for mistakes", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}

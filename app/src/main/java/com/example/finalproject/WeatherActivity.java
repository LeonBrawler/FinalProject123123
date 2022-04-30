package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherActivity extends AppCompatActivity {

    private EditText userField;
    private Button mainButton;
    private TextView resultInformation;

    // Booleans for checkboxes
    boolean showCurrentTemperature, showTemperatureBorders,
            showHumidity, showPressure, showDescription, showWindSpeed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        userField = findViewById(R.id.user_field);
        mainButton = findViewById(R.id.main_btn);
        resultInformation = findViewById(R.id.result_info);

        mainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userField.getText().toString().trim().equals("")){
                    Toast.makeText(WeatherActivity.this, R.string.no_user_input, Toast.LENGTH_LONG).show();
                } else {
                    String city = userField.getText().toString();
                    String key = "f48370c17dbd630c3e693a92455b5ef2";
                    String url = "https://api.openweathermap.org/data/2.5/weather?q=" +
                            city + "&appid=" + key + "&units=metric";
                    new getURLData().execute(url);
                }
            }
        });
    }

    @SuppressLint("NonConstantResourceId")
    public void onCheckboxClicked(View v){
        boolean checked = ((CheckBox) v).isChecked();

        switch (v.getId()){
            case R.id.current_temperature_cb:
                showCurrentTemperature = checked;
                break;
            case R.id.border_temperature_cb:
                showTemperatureBorders = checked;
                break;
            case R.id.humidity_cb:
                showHumidity = checked;
                break;
            case R.id.wind_speed_cb:
                showWindSpeed = checked;
                break;
            case R.id.pressure_cb:
                showPressure = checked;
                break;
        }
    }

    private class getURLData extends AsyncTask<String, String, String> {
        String waitText = "Requesting...";
        protected void onPreExecute(){
            super.onPreExecute();
            resultInformation.setText(waitText);
        }
        @Override
        protected String doInBackground(String... strings) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            try {
                URL url = new URL(strings[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();
                String line = "";

                while((line = reader.readLine()) != null){
                    buffer.append(line).append("\n");
                }
                return buffer.toString();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if(connection != null){
                    connection.disconnect();
                }
                try{
                    if(reader != null){
                        reader.close();
                    }
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
            return null;
        }

        @SuppressLint("SetTextI18n")
        @Override
        protected void onPostExecute(String result){
            super.onPostExecute(result);

            try {
                JSONObject jsonObject = new JSONObject(result);

                // Receiving all data
                double temperature = jsonObject.getJSONObject("main").getDouble("temp");
                String description = "description"; //jsonObject.getJSONArray("weather").getString(0).;
                double windSpeed = jsonObject.getJSONObject("wind").getDouble("speed");
                double pressure = jsonObject.getJSONObject("main").getDouble("pressure");
                double tempMin = jsonObject.getJSONObject("main").getDouble("temp_min");
                double tempMax = jsonObject.getJSONObject("main").getDouble("temp_max");
                double humidity = jsonObject.getJSONObject("main").getDouble("humidity");

                String tempText = "Current Temperature: " + temperature + "°C";
                String descriptionText = "Description: " + description;
                String windSpeedText = "Wind speed: " + windSpeed + "m/s";
                String pressureText = "Pressure: " + pressure + " atm";
                String borderTempText = "Daily Temperature: " + tempMin + "°C" + " -- " + tempMax + "°C";
                String humidityText = "Humidity: " + humidity;

                String weatherText = "";
                if (showCurrentTemperature){
                    weatherText += tempText + "\n";
                }
                if(showTemperatureBorders){
                    weatherText += borderTempText + "\n";
                }
                if(showWindSpeed){
                    weatherText += windSpeedText + "\n";
                }
                if(showPressure){
                    weatherText += pressureText + "\n";
                }
                if(showHumidity){
                    weatherText += humidityText + "\n";
                }
                if(weatherText.length() == 0){
                    Toast.makeText(WeatherActivity.this, "No Params selected", Toast.LENGTH_LONG).show();
                }
                resultInformation.setText(weatherText);

//                "Temperature: " + jsonObject.getJSONObject("main").getDouble("temp")
//                "Temperature: " + jsonObject.getJSONObject("main").getDouble("temp") + "°C"

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
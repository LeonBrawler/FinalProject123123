package com.example.finalproject;

import static java.lang.Math.round;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
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
    boolean showCurrentTemperature, showDescription,
            showHumidity, showPressure, showWindSpeed;

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
                if(userField.getText(   ).toString().trim().equals("")){
                    Toast.makeText(WeatherActivity.this, R.string.no_user_input, Toast.LENGTH_LONG).show();
                } else {
                    String city = userField.getText().toString();

                    String key = "1cb4254f89044ff2ba095133222205";
                    String url = "https://api.weatherapi.com/v1/current.json?key=" + key + "&q=" + city + "&aqi=no";
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
            case R.id.description_cb:
                showDescription = checked;
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
                Log.d("RESULT", "AAA");
                URL url = new URL(strings[0]);
                Log.d("RESULT", strings[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                Log.d("RESULT", "AAA");
                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                Log.d("RESULT", "AAA");
                StringBuffer buffer = new StringBuffer();
                String line = "";

                while((line = reader.readLine()) != null){
                    buffer.append(line).append("\n");
                }
                Log.d("RESULT", "AAA");
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
                Log.d("RESULT", result);
                //resultInformation.setText(result);
                // Receiving all data
                String s = jsonObject.getJSONObject("location").getString("name");
                String description = jsonObject.getJSONObject("current").getJSONObject("condition").getString("text");
                double temperature = jsonObject.getJSONObject("current").getDouble("temp_c");
                double windSpeed = round(jsonObject.getJSONObject("current").getDouble("wind_kph") * 5 / 18 * 100.0) / 100.0;
                double pressure = jsonObject.getJSONObject("current").getDouble("pressure_mb");
                double humidity = jsonObject.getJSONObject("current").getDouble("humidity");
                Log.d("RESULT", windSpeed+"\n" + temperature + "\n" + description + "\n" + pressure + "\n" + humidity);

                String tempText = "Temperature: " + temperature + " °C";
                String descriptionText = "Weather is: " + description;
                String windSpeedText = "Wind speed: " + windSpeed + "m/s";
                String pressureText = "Pressure: " + pressure + " am";
                String humidityText = "Humidity: " + humidity + "%";
                Log.d("RESULT", tempText + "\n" + descriptionText + "\n" + windSpeedText + "\n" + pressureText + "\n" + humidityText + "\n");
                String weatherText = "";
                if (showCurrentTemperature){
                    weatherText += tempText + "\n";
                }
                if(showDescription){
                    weatherText += descriptionText + "\n";
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
            } catch (JSONException  | NullPointerException e) {
                Toast.makeText(getApplicationContext(), "Check input", Toast.LENGTH_LONG).show();
                Log.d("EXCEPTION", e+"");
            }
        }
    }
}
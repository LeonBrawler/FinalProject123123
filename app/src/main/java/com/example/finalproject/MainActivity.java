package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private EditText userField;
    private Button mainButton;
    private TextView resultInformation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userField = findViewById(R.id.user_field);
        mainButton = findViewById(R.id.main_btn);
        resultInformation = findViewById(R.id.result_info);

        mainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userField.getText().toString().trim().equals("")){
                    Toast.makeText(MainActivity.this, R.string.no_user_input, Toast.LENGTH_LONG).show();
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
                resultInformation.setText("Temperature: " + jsonObject.getJSONObject("main").getDouble("temp") + "°C");
//                "Temperature: " + jsonObject.getJSONObject("main").getDouble("temp")
//                "Temperature: " + jsonObject.getJSONObject("main").getDouble("temp") + "°C"

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
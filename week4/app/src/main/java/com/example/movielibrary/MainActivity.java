package com.example.movielibrary;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {

    Button button;
    Button back;

    EditText title;
    EditText year;
    EditText country;
    EditText genre;
    EditText cost;
    EditText keyword;

    SharedPreferences sp;



    final String TITLE = "TITLE";
    final String YEAR = "YEAR";
    final String COUNTRY = "COUNTRY";
    final String GENRE = "GENRE";
    final String COST = "COST";
    final String KEYWORD = "KEYWORD";
    final String SP_NAME ="data.dat";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.addbutton);
        back = findViewById(R.id.returnbutton);

        title = findViewById(R.id.titletext);
        year = findViewById(R.id.yeartext);
        country = findViewById(R.id.countrytext);
        genre = findViewById(R.id.genretext);
        cost = findViewById(R.id.costtext);
        keyword = findViewById(R.id.keywordtext);


        Log.i("week3", "onCreate");

        sp = getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        String title_name = sp.getString(TITLE, " ");

        //Toast.makeText(getApplicationContext(), " Last Movie was "+ "' "+title_name + " ' "+ "", Toast.LENGTH_LONG).show();

        //week4 Task 3
        /* Request permissions to access SMS */
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS, Manifest.permission.RECEIVE_SMS, Manifest.permission.READ_SMS}, 0);

        registerReceiver(new MyBroadCastReceiver(), new IntentFilter(SMSReceiver.SMS_FILTER));

    }



    @Override
    protected void onStart() {
        super.onStart();
        Log.i("week3","onStart");

        sp = getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        String title_name = sp.getString(TITLE, " ");
        int year_name = sp.getInt(YEAR,0);
        String country_name = sp.getString(COUNTRY, " ");
        String genre_name = sp.getString(GENRE, " ");
        String cost_name = sp.getString(COST, " ");
        String keyword_name = sp.getString(KEYWORD," ");

        //Log.i("week3", "onCreate - getting ");

        title.setText(title_name.toUpperCase(Locale.ROOT));
        genre.setText(genre_name.toLowerCase(Locale.ROOT));
        if (year_name == 0)
            year.setText("");
        else
            year.setText(Integer.toString(year_name));

        country.setText(country_name);
        cost.setText(cost_name);
        keyword.setText(keyword_name);

    }



    @Override
    protected void onPause() {
        super.onPause();
        Log.i("week3","onPause");

        String s_title = title.getText().toString();
        int s_year = Integer.parseInt(year.getText().toString());
        Log.i("week3", Integer.toString(s_year));
        String s_country = country.getText().toString();
        String s_genre = genre.getText().toString();
        String s_cost = cost.getText().toString();
        String s_keyword = keyword.getText().toString();

        SharedPreferences.Editor editor = sp.edit();

        editor.putString(TITLE, s_title);
        editor.putString(GENRE, s_genre);

        editor.apply();


    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("week3","onResume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("week3","onStop");

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("week3","onDestroy");
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i("week3","onRestoreInstanceState");
    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i("week3","onSaveInstanceState");
    }

    public void handleOnClick(View view) {
        if ( Integer.parseInt(year.getText().toString()) > 2002 ){
            Toast.makeText(getApplicationContext(), " Movie, "+ "' "+title.getText().toString() + " ' "+ "has been added. And it is an 'new' movie !", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(getApplicationContext(), " Movie, "+ "' "+title.getText().toString() + " ' "+ "has been added. It is an 'old' movie before 2002! ", Toast.LENGTH_LONG).show();
        }
        //Toast is to display the msg like pop up window
    }

    public  void handleBack(View view){

        SharedPreferences.Editor editor = sp.edit();

        editor.putString(TITLE, " ");
        editor.putInt(YEAR, 0);
        editor.putString(COUNTRY, "");
        editor.putString(GENRE, "");
        editor.putString(COST, "");
        editor.putString(KEYWORD, "");

        editor.apply();
        title.setText(" ");
        genre.setText("");
        year.setText(" ");
        country.setText("");
        cost.setText("");
        keyword.setText("");
        Log.i("week3","clear all");

        onStart();
    }

    class MyBroadCastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            /*
             * Retrieve the message from the intent
             * */
            String msg = intent.getStringExtra(SMSReceiver.SMS_MSG_KEY);

           StringTokenizer sT = new StringTokenizer(msg,";");

            String sms_title = sT.nextToken();
            String sms_year = sT.nextToken();
            String sms_country = sT.nextToken();
            String sms_genre = sT.nextToken();
            String sms_cost = sT.nextToken();
            String sms_keyword = sT.nextToken();

            title.setText(sms_title);
            year.setText(sms_year);
            country.setText(sms_country);
            genre.setText(sms_genre);
            cost.setText(sms_cost);
            keyword.setText(sms_keyword);
        }

    }



}




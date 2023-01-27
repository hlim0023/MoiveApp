package com.example.week2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Lab2Task2 extends AppCompatActivity {

    Button button;
    Button back;
    EditText title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        button = findViewById(R.id.button3);
        title = findViewById(R.id.editTextTextPersonName9);
        back = findViewById(R.id.returnbutton);

    }


    public void handleOnClick(View view) {
        Toast.makeText(getApplicationContext(), " Movie, "+ "' "+title.getText().toString() + " ' "+ "has been added", Toast.LENGTH_LONG).show();
        //Toast is to display the msg like pop up window
    }

    public  void handleBack(View view){
        Intent intent = new Intent(Lab2Task2.this, MainActivity.class);
        startActivity(intent);
    }

}
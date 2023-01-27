package com.example.week2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    Button button;
    EditText edittext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        edittext = findViewById(R.id.editTextTextPersonName);

        //method 1
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Hi, "+edittext.getText().toString(), Toast.LENGTH_LONG).show();
                //Toast is to display the msg like pop up window
                Intent intent = new Intent(MainActivity.this, Lab2Task2.class);
                startActivity(intent);
            }
        });

    }
    /*
    //method2: need to modify on click
    public void handleClick(View view) {
        Toast.makeText(getApplicationContext(), "Hey, "+edittext.getText().toString(), Toast.LENGTH_LONG).show();
        //Toast is to display the msg like pop up window
    }
    //either the method  the on click listener method1 or method 2
    // but use method 2 need to go attribute and search on click and change it to our own method handleclick
    */
}

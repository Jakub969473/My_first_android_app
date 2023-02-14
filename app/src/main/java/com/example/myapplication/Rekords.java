package com.example.myapplication;

import android.annotation.SuppressLint;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowInsets;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class Rekords extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_rekords);

        ListView recordList=findViewById(R.id.record_list);

        String countryList[] = {"India", "China", "australia", "Portugle", "America", "NewZealand"};

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.activity_rekords,R.id.textView,countryList);

        recordList.setAdapter(arrayAdapter);
    }

}
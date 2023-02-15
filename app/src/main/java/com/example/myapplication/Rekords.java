package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class Rekords extends AppCompatActivity {

    List records=new LinkedList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_rekords);

        ListView recordList = findViewById(R.id.record_list);

        TextView text = findViewById(R.id.Text);

        fileReader read=new fileReader(Rekords.this);

        records=read.loadData();

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.activity_rekords,
                R.id.Text, records);

        recordList.setAdapter(arrayAdapter);
    }


    }
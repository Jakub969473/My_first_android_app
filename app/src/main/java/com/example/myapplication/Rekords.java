package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import java.util.LinkedList;
import java.util.List;

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

        Button returnButton =findViewById(R.id.ReturnButton);

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent z=new Intent(Rekords.this, MainActivity.class);

                startActivity(z);
            }
        });

        fileReader read=new fileReader(Rekords.this);

        records=read.loadData();

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.activity_rekords,
                R.id.Text, records);

        recordList.setAdapter(arrayAdapter);
    }


    }
package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView text;

    TextView timer;

    String userInput;
    int counter=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text=findViewById(R.id.Text);

        Button button=findViewById(R.id.button);

        EditText input=findViewById(R.id.UserInput);

        Button accept=findViewById(R.id.button2);

        timer=findViewById(R.id.Timer);

        TextView inputNeeded=findViewById(R.id.InputNeeded);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click();
            }
        });

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userInput=input.getText().toString();

                accept.setVisibility(View.INVISIBLE);

                input.setVisibility(View.INVISIBLE);

                input.setEnabled(false);

                inputNeeded.setVisibility(View.INVISIBLE);

                text.setVisibility(View.VISIBLE);

                button.setVisibility(View.VISIBLE);

                button.setEnabled(true);

                timer.setVisibility(View.VISIBLE);

            }
        });


    }

    private void click() {

        counter++;

       text.setText(String.format("You have clicked "+ counter+" times"));

    }
}
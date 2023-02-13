package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;
import java.text.ParseException;

public class MainActivity extends AppCompatActivity {

    TextView text;

    TextView timer;

    String userInput;

    Button button;
    int counter=0;

    int time;

    Button restart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text=findViewById(R.id.Text);

        this.button=findViewById(R.id.button);

        EditText input=findViewById(R.id.UserInput);

        Button accept=findViewById(R.id.button2);

        this.timer=findViewById(R.id.Timer);

        TextView inputNeeded=findViewById(R.id.InputNeeded);

        Button start=findViewById(R.id.StartButton);

        restart=findViewById(R.id.RestartButton);

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

                start.setVisibility(View.VISIBLE);

                start.setEnabled(true);

                timer.setVisibility(View.VISIBLE);

                String s=input.getText().toString();

                try {
                    time=((Number) NumberFormat.getInstance().parse(s)).intValue();
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }

                String temp=""+time;

                timer.setText(temp);
            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button.setVisibility(View.VISIBLE);

                button.setEnabled(true);

                start.setEnabled(false);

                start.setVisibility(View.INVISIBLE);

                Timer();

            }
        });

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Restart();
            }
        });


    }

    public void Timer() {

        long time=Integer.parseInt(this.timer.getText().toString())* 1000L;

        CountDownTimer timer=new CountDownTimer(Integer.parseInt(this.timer.getText().toString())* 1000L,100) {

            TextView x=findViewById(R.id.Timer);
            double remaingTime=Integer.parseInt(x.getText().toString());

            @Override
            public void onTick(long millisUntilFinished) {

                remaingTime-=0.1;

                remaingTime*=10;

                remaingTime=Math.round(remaingTime);

                remaingTime/=10;

                x.setText(String.valueOf(remaingTime));

            }

            @Override
            public void onFinish() {

                x.setText("0");
                text.setText(String.format("In "+time/1000+" s you clicked "+counter+" times"));
                button.setEnabled(false);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                restart.setVisibility(View.VISIBLE);
                restart.setEnabled(true);
            }
        }.start();

    }

    private void click() {

        counter++;

        text.setText(String.format("You have clicked "+ counter+" times"));

    }

    public void Restart()
    {
        this.recreate();
    }

}
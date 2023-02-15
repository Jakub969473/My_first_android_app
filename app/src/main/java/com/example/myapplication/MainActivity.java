package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

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

        Button records =findViewById(R.id.recordButton);

        records.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent z=new Intent(MainActivity.this, Rekords.class);

                startActivity(z);
            }
        });

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
                button.setVisibility(View.INVISIBLE);
                restart.setVisibility(View.VISIBLE);

                final Handler handler = new Handler(Looper.getMainLooper());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                     restart.setEnabled(true);
                    }
                }, 1000);

                newRecord();
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


    public void btnSaveData(List records) {
        try {
            FileOutputStream file = openFileOutput("Data.txt", MODE_PRIVATE);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(file);

            Iterator it=records.iterator();

            while(it.hasNext()){
                outputStreamWriter.write( it.next()+ "\n");

            }

            outputStreamWriter.flush();
            outputStreamWriter.close();
            Toast.makeText(MainActivity.this, "Successfully saved", Toast.LENGTH_LONG)
                    .show();

        } catch (IOException e) {
            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG)
                    .show();
        }
    }

    private void newRecord(){

        fileReader read=new fileReader(MainActivity.this);

        List records=read.loadData();

        for(int i=0;i<records.size();i++){

            int x=Integer.parseInt((String)records.get(i));

            if(i+1>=records.size() || counter>x){
                records.add(i,counter);
                break;
            }else if(counter==Integer.parseInt((String) records.get(i+1))){
                break;
            }
        }

        btnSaveData(records);
    }

}
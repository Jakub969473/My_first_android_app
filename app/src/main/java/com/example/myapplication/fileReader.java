package com.example.myapplication;

import android.content.Context;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class fileReader extends AppCompatActivity {

    List records=new LinkedList();
    Context context;

    public fileReader(Context context){
        this.context=context;

    }


    private void setTextToTextView() {
        String text = "";

        for (int i = 0; i < records.size(); i++) {
            text = text + records.get(i) + "\n";
        }
    }


    public List loadData() {
        if(!records.isEmpty()){
            records.clear();
        }
        File file = context.getApplicationContext()
                .getFileStreamPath("Data.txt");
        String lineFromfile;

        if (file.exists()) {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(context.openFileInput("Data.txt")));

                while ((lineFromfile = reader.readLine()) != null) {
                    StringTokenizer tokenizer = new StringTokenizer(lineFromfile, ",");
                    String record = tokenizer.nextToken();
                    records.add(record);
                }
                reader.close();
                setTextToTextView();
            } catch (IOException e) {
                Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG)
                        .show();
            }
        }

        return records;
    }
}

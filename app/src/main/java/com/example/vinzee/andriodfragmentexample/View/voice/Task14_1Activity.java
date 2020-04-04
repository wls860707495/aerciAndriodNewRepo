package com.example.vinzee.andriodfragmentexample.View.voice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.vinzee.andriodfragmentexample.R;


public class Task14_1Activity extends AppCompatActivity {

    private Button task14_1_1;
    private Button task14_1_2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task14_1);
        task14_1_1=findViewById(R.id.task14_1_1);
        task14_1_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Task14_1Activity.this,Task14_1_1Activity.class);
                startActivity(intent);
            }
        });
        task14_1_2=findViewById(R.id.task14_1_2);
        task14_1_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Task14_1Activity.this,Task14_1_2Activity.class);
                startActivity(intent);
            }
        });
    }
}

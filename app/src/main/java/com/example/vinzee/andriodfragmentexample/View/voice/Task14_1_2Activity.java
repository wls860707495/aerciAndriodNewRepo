package com.example.vinzee.andriodfragmentexample.View.voice;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vinzee.andriodfragmentexample.R;


public class Task14_1_2Activity extends AppCompatActivity {

    private TextView Task14_1 = null;
    private Task14_1bean form[];
    private MediaPlayer mp=null;
    int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task14_1_2);
        LinearLayout formContainer=findViewById(R.id.content);
        Task14_1 = new TextView(this);
        Task14_1.setTextSize(20);
        Task14_1.setTextColor(Color.rgb(0, 0, 0));
        Task14_1.setText("测试14-1：正着重复数字 测试2");
        formContainer.addView(Task14_1);
        form = new  Task14_1bean[11];
        i=1;
        while (i<=8) {
            createForm(i, formContainer);
            i++;
        }
        form[1].getPlay().setEnabled(true);
    }
    private void createForm(final int formNo, LinearLayout formContainer)
    {
        form[formNo] =new Task14_1bean(this);
        form[formNo].getDescribe().setText("第"+formNo+"题");
        form[formNo].getPlay().setEnabled(false);
        form[formNo].getEdit().setEnabled(false);
        form[formNo].getSubmit().setEnabled(false);
        formContainer.addView(form[formNo].getDescribe());
        formContainer.addView(form[formNo].getPlay());
        formContainer.addView(form[formNo].getEdit());
        formContainer.addView(form[formNo].getSubmit());
        form[formNo].getPlay().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play(formNo);
            }
        });
        form[formNo].getSubmit().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit(formNo);
            }
        });
    }
    private void play(final int formNo){
        try{
            switch (formNo)
            {
                case 1:
                    mp = MediaPlayer.create(this, R.raw.task14_1_2_1);
                    break;
                case 2:
                    mp = MediaPlayer.create(this, R.raw.task14_1_2_2);
                    break;
                case 3:
                    mp = MediaPlayer.create(this, R.raw.task14_1_2_3);
                    break;
                case 4:
                    mp = MediaPlayer.create(this, R.raw.task14_1_2_4);
                    break;
                case 5:
                    mp = MediaPlayer.create(this, R.raw.task14_1_2_5);
                    break;
                case 6:
                    mp = MediaPlayer.create(this, R.raw.task14_1_2_6);
                    break;
                case 7:
                    mp = MediaPlayer.create(this, R.raw.task14_1_2_7);
                    break;
                case 8:
                    mp = MediaPlayer.create(this, R.raw.task14_1_2_8);
                    break;

                default:break;
            }
            mp.start();//开始播放
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    Toast.makeText(Task14_1_2Activity.this,"第"+formNo+"题播放结束",Toast.LENGTH_SHORT).show();
                    form[formNo].getEdit().setEnabled(true);
                    form[formNo].getSubmit().setEnabled(true);
                }
            });
            Toast.makeText(Task14_1_2Activity.this,"开始播放第"+formNo+"题",Toast.LENGTH_SHORT).show();
            form[formNo].getPlay().setEnabled(false);
        }catch(Exception e){
            e.printStackTrace();//输出异常信息
        }
    }

    private void submit(final int formNo){
        try{
            form[formNo].getEdit().setEnabled(false);
            form[formNo].getSubmit().setEnabled(false);
            if(formNo!=8) {
                form[formNo + 1].getPlay().setEnabled(true);
            }
            else
            {
                Toast.makeText(Task14_1_2Activity.this,"测试14_1 测试2完成",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Task14_1_2Activity.this,Task14_1Activity.class);
                startActivity(intent);
            }

        }catch(Exception e){
            e.printStackTrace();//输出异常信息
        }
    }
}

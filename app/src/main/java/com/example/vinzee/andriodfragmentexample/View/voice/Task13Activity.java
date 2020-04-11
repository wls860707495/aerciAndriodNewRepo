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


import com.example.vinzee.andriodfragmentexample.View.Fragment3;
import com.example.vinzee.andriodfragmentexample.R;

import UtilsHelper.SharePreferenceUtil;


public class Task13Activity extends AppCompatActivity {

    private TextView Task13 = null;
    private Task13bean form[];
    private MediaPlayer mp=null;
    public int scores = 0;
    int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task13);
        LinearLayout formContainer=findViewById(R.id.content);
        Task13 = new TextView(this);
        Task13.setTextSize(20);
        Task13.setTextColor(Color.rgb(0, 0, 0));
        Task13.setText("测试13：回忆听到的词");
        formContainer.addView(Task13);
        form = new  Task13bean[4];
        i=1;
        while (i<=3) {
            createForm(i, formContainer);
            i++;
        }
        form[1].getPlay().setEnabled(true);
    }
    private void createForm(final int formNo, LinearLayout formContainer)
    {
        form[formNo] =new Task13bean(this);
        form[formNo].getDescribe().setText("第"+formNo+"遍");
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
                    mp = MediaPlayer.create(this, R.raw.task13);
                    break;
                case 2:
                    mp = MediaPlayer.create(this, R.raw.task13);
                    break;
                case 3:
                    mp = MediaPlayer.create(this, R.raw.task13);
                    break;

                default:break;
            }
            mp.start();//开始播放
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    Toast.makeText(Task13Activity.this,"第"+formNo+"遍播放结束",Toast.LENGTH_SHORT).show();
                    form[formNo].getEdit().setEnabled(true);
                    form[formNo].getSubmit().setEnabled(true);
                }
            });
            Toast.makeText(Task13Activity.this,"开始播放第"+formNo+"遍",Toast.LENGTH_SHORT).show();
            form[formNo].getPlay().setEnabled(false);
        }catch(Exception e){
            e.printStackTrace();//输出异常信息
        }
    }

    private void submit(final int formNo){
        try{
            form[formNo].getEdit().setEnabled(false);
            form[formNo].getSubmit().setEnabled(false);
            if(formNo!=3) {
                form[formNo + 1].getPlay().setEnabled(true);
                SharePreferenceUtil.setParam(getApplicationContext(),"word"+formNo,
                        form[formNo].getEdit().getText().toString());
            }
            else
            {
                SharePreferenceUtil.setParam(getApplicationContext(),"word"+formNo,
                        form[formNo].getEdit().getText().toString());

                /*   分数在这里  */
                int m = caculatescors();
                SharePreferenceUtil.updateInfo("Part3",m,this);

                Toast.makeText(Task13Activity.this,"测试13完成,分数："+ m,Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Task13Activity.this, Fragment3.class);
                startActivity(intent);
            }

        }catch(Exception e){
            e.printStackTrace();//输出异常信息
        }
    }
    private int caculatescors() {
        String s1 = "胳膊猫斧子床飞机耳朵锤子椅子轿车眼睛马刀子钟自行车";
        String[] s2 = new String[3];

        for (int k = 1; k <= 3; k++) {
            s2[k - 1] = SharePreferenceUtil.getParam(getApplicationContext(), "word" + k).toString().replace(" ", "");
        }
        for (int p = 0; p <= 2; p++) {
            if (s2[p].equals(s1)) {
                scores += 10;
            }
        }
        return scores;
    }
}
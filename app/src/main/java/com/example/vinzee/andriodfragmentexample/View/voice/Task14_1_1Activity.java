package com.example.vinzee.andriodfragmentexample.View.voice;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vinzee.andriodfragmentexample.R;

import UtilsHelper.SharePreferenceUtil;


public class Task14_1_1Activity extends AppCompatActivity {
    private TextView Task14_1 = null;
    private Task14_1bean form[];
    private MediaPlayer mp=null;
    public int scores = 0;
    int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task14_1_1);
        LinearLayout formContainer=findViewById(R.id.content);
        Task14_1 = new TextView(this);
        Task14_1.setTextSize(20);
        Task14_1.setTextColor(Color.rgb(0, 0, 0));
        Task14_1.setText("测试14-1：正着重复数字 测试1");
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
                    mp = MediaPlayer.create(this, R.raw.task14_1_1_1);
                    break;
                case 2:
                    mp = MediaPlayer.create(this, R.raw.task14_1_1_2);
                    break;
                case 3:
                    mp = MediaPlayer.create(this, R.raw.task14_1_1_3);
                    break;
                case 4:
                    mp = MediaPlayer.create(this, R.raw.task14_1_1_4);
                    break;
                case 5:
                    mp = MediaPlayer.create(this, R.raw.task14_1_1_5);
                    break;
                case 6:
                    mp = MediaPlayer.create(this, R.raw.task14_1_1_6);
                    break;
                case 7:
                    mp = MediaPlayer.create(this, R.raw.task14_1_1_7);
                    break;
                case 8:
                    mp = MediaPlayer.create(this, R.raw.task14_1_1_8);
                    break;

                default:break;
            }
            mp.start();//开始播放
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    Toast.makeText(Task14_1_1Activity.this,"第"+formNo+"题播放结束",Toast.LENGTH_SHORT).show();
                    form[formNo].getEdit().setEnabled(true);
                    form[formNo].getSubmit().setEnabled(true);
                }
            });
            Toast.makeText(Task14_1_1Activity.this,"开始播放第"+formNo+"题",Toast.LENGTH_SHORT).show();
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
                SharePreferenceUtil.setParam(getApplicationContext(),"firgurez"+formNo,
                        form[formNo].getEdit().getText().toString());
            }
            else
            {
                SharePreferenceUtil.setParam(getApplicationContext(),"firgurez"+formNo,
                        form[formNo].getEdit().getText().toString());

                /*   分数在这里  */
                int m = caculatescors();
                SharePreferenceUtil.updateInfo("part4",m,this);
                Toast.makeText(Task14_1_1Activity.this, "测试14_1_1 测试2完成,分数为：" + m, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Task14_1_1Activity.this,Task14_1Activity.class);
                startActivity(intent);
            }

        }catch(Exception e){
            e.printStackTrace();//输出异常信息
        }
    }
    private int caculatescors() {
        String a = "582", b = "6439", c = "42731", d = "619473", e = "5917428", f = "58192647", g = "275862584",h="5274913746";
        String[] s1 = new String[8];
        String[] s2 = new String[8];
        s1[0] = a;
        s1[1] = b;
        s1[2] = c;
        s1[3] = d;
        s1[4] = e;
        s1[5] = f;
        s1[6] = g;
        s1[7] = h;
        for (int k = 1; k <= 8; k++) {
            s2[k - 1] = SharePreferenceUtil.getParam(getApplicationContext(), "firgurez" + k).toString().replace(" ", "");
        }
        for (int p = 0; p <= 7; p++) {
            if (s1[p].equals(s2[p])) {
                scores += 10;
            }
        }
        return scores;
    }
}
package com.example.vinzee.andriodfragmentexample.View.voice;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vinzee.andriodfragmentexample.R;

import UtilsHelper.SharePreferenceUtil;


public class Task14_2_2Activity extends AppCompatActivity {

    private TextView Task14_2 = null;
    private Task14_2bean form[];
    private MediaPlayer mp = null;
    public int scores = 0;
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task14_2_2);
        LinearLayout formContainer = findViewById(R.id.content);
        Task14_2 = new TextView(this);
        Task14_2.setTextSize(20);
        Task14_2.setTextColor(Color.rgb(0, 0, 0));
        Task14_2.setText("测试14-2：倒着重复数字 测试2");
        formContainer.addView(Task14_2);
        form = new Task14_2bean[11];
        i = 1;
        while (i <= 7) {
            createForm(i, formContainer);
            i++;
        }
        form[1].getPlay().setEnabled(true);
    }

    private void createForm(final int formNo, LinearLayout formContainer) {
        form[formNo] = new Task14_2bean(this);
        form[formNo].getDescribe().setText("第" + formNo + "题");
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

    private void play(final int formNo) {
        try {
            switch (formNo) {
                case 1:
                    mp = MediaPlayer.create(this, R.raw.task14_2_2_1);
                    break;
                case 2:
                    mp = MediaPlayer.create(this, R.raw.task14_2_2_2);
                    break;
                case 3:
                    mp = MediaPlayer.create(this, R.raw.task14_2_2_3);
                    break;
                case 4:
                    mp = MediaPlayer.create(this, R.raw.task14_2_2_4);
                    break;
                case 5:
                    mp = MediaPlayer.create(this, R.raw.task14_2_2_5);
                    break;
                case 6:
                    mp = MediaPlayer.create(this, R.raw.task14_2_2_6);
                    break;
                case 7:
                    mp = MediaPlayer.create(this, R.raw.task14_2_2_7);
                    break;

                default:
                    break;
            }
            mp.start();//开始播放
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    Toast.makeText(Task14_2_2Activity.this, "第" + formNo + "题播放结束", Toast.LENGTH_SHORT).show();
                    form[formNo].getEdit().setEnabled(true);
                    form[formNo].getSubmit().setEnabled(true);
                }
            });
            Toast.makeText(Task14_2_2Activity.this, "开始播放第" + formNo + "题", Toast.LENGTH_SHORT).show();
            form[formNo].getPlay().setEnabled(false);
        } catch (Exception e) {
            e.printStackTrace();//输出异常信息
        }
    }

    private void submit(final int formNo) {
//        try {
        form[formNo].getEdit().setEnabled(false);
        form[formNo].getSubmit().setEnabled(false);
        if (formNo != 7) {
            form[formNo + 1].getPlay().setEnabled(true);
            Log.i("mm",form[formNo].getEdit().getText().toString());
            SharePreferenceUtil.setParam(getApplicationContext(), "firgured2" + formNo,
                    form[formNo].getEdit().getText().toString());
        } else {
            SharePreferenceUtil.setParam(getApplicationContext(), "firgured2" + formNo,
                    form[formNo].getEdit().getText().toString());

            /*   分数在这里  */
            int m = caculatescors();
            SharePreferenceUtil.updateInfo("part5",m,this);
            Toast.makeText(Task14_2_2Activity.this, "测试14_2_2 测试2完成,分数为：" + m, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Task14_2_2Activity.this, Task14_2Activity.class);
            startActivity(intent);
        }
//        } catch (Exception e) {
//           Log.i("mmmmm","opipp");//输出异常信息
//        }
    }

    private int caculatescors() {
        String a = "85", b = "514", c = "8694", d = "34816", e = "658427", f = "8219374", g = "35691827";
        String[] s1 = new String[7];
        String[] s2 = new String[7];
        s1[0] = a;
        s1[1] = b;
        s1[2] = c;
        s1[3] = d;
        s1[4] = e;
        s1[5] = f;
        s1[6] = g;
        for (int k = 1; k <= 7; k++) {
            s2[k - 1] = SharePreferenceUtil.getParam(getApplicationContext(), "firgured2" + k).toString().replace(" ", "");
        }
        for (int p = 0; p <= 6; p++) {
            if (s1[p].equals(s2[p])) {
                scores += 10;
            }
        }
        return scores;
    }
}
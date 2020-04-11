package com.example.vinzee.andriodfragmentexample.View.voice;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.vinzee.andriodfragmentexample.R;



public class Task13PlayActivity extends AppCompatActivity {

    private MediaPlayer mp;//mediaPlayer对象
    private Button play,pause,stop;//播放 暂停/继续 停止 按钮
    private TextView hint;//显示当前播放状态
    private boolean isPause=false;//是否暂停

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        play=(Button) findViewById(R.id.button1);


        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play();
                if(isPause){
                    pause.setText("暂停");
                    isPause=false;
                }
            }
        });

        pause=(Button) findViewById(R.id.button2);
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mp.isPlaying()&&!isPause){
                    mp.pause();
                    isPause=true;
                    pause.setText("继续");
                    hint.setText("暂停播放音频...");
                    play.setEnabled(true);
                }else{
                    mp.start();
                    pause.setText("暂停");
                    hint.setText("继续播放音频...");
                    isPause=false;
                    play.setEnabled(false);
                }

        }
        });
        stop=(Button) findViewById(R.id.button3);
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.stop();
                hint.setText("停止播放音频...");
                pause.setEnabled(false);
                stop.setEnabled(false);
                play.setEnabled(true);
            }
        });
        mp=MediaPlayer.create(Task13PlayActivity.this, R.raw.task);//创建mediaplayer对象
        setContentView(R.layout.activity_task13_play);


    }

    private void play(){
        try{
            mp.reset();
            mp=MediaPlayer.create(Task13PlayActivity.this, R.raw.task);//重新设置要播放的音频
            mp.start();//开始播放
            hint.setText("正在播放音频...");
            play.setEnabled(false);
            pause.setEnabled(true);
            stop.setEnabled(true);
        }catch(Exception e){
            e.printStackTrace();//输出异常信息
        }
    }

    protected void onDestroy() {
        // TODO Auto-generated method stub
        if(mp.isPlaying()){
            mp.stop();
        }
        mp.release();//释放资源
        super.onDestroy();
    }

}



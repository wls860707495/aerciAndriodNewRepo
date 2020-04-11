package com.example.vinzee.andriodfragmentexample.View.voice;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.vinzee.andriodfragmentexample.R;

import java.io.File;
import java.util.Calendar;
import java.util.Locale;



public class RecordTestActivity extends AppCompatActivity {
    private Button play1;
    private Button start1;
    private Button stop1;
    private Button submit1;
    private MediaPlayer mp=null;
    private MediaRecorder mr=null;
    final String audioSaveDir = Environment.getDataDirectory() +"/";
    String fileName; // 录音文件的名称
    String filePath; // 录音文件存储路径




    @Override
    protected void onCreate(Bundle savedInstanceState) {


        verifyStoragePermissions(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_test);
        play1=findViewById(R.id.Play1);
        start1=findViewById(R.id.start1);
        stop1=findViewById(R.id.stop1);
        submit1=findViewById(R.id.Submit1);


        play1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play1();
            }
        });
        start1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start1();
            }
        });
        stop1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stop1();
            }
        });

        submit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    private void play1(){try{
            mp=MediaPlayer.create(this,R.raw.task);
            mp.start();//开始播放
            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    Toast.makeText(RecordTestActivity.this,"第一遍播放结束",Toast.LENGTH_SHORT).show();
                    start1.setEnabled(true);
                    submit1.setEnabled(true);
                }
            });
            play1.setEnabled(false);
            Toast.makeText(RecordTestActivity.this,"开始播放第一遍",Toast.LENGTH_SHORT).show();
        }catch(Exception e){
            e.printStackTrace();//输出异常信息
        }
    }


    private void start1(){
        try{
            mr= new MediaRecorder();
            mr.setAudioSource(MediaRecorder.AudioSource.MIC);
            mr.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
            mr.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);

            fileName = DateFormat.format("yyyyMMdd_HHmmss", Calendar.getInstance(Locale.CHINA)) + ".m4a";
            filePath = audioSaveDir + fileName;
            File file = new File(filePath);
            if (!file.exists()) {

                file.mkdirs();

            }

            /* ③准备 */
            mr.setOutputFile(filePath);
            System.out.println(filePath);
            mr.prepare();
            mr.start();
            System.out.println(filePath);
        }catch(Exception e){
            e.printStackTrace();//输出异常信息
        }
    }

    private void stop1(){
        try{
            mr.stop();
            mr.reset();
            mr.release();
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

    private  String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.RECORD_AUDIO};
    public  void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE,
                    1);
        }
    }


}

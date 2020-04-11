package com.example.vinzee.andriodfragmentexample.View;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
<<<<<<< HEAD
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
=======
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
>>>>>>> e25f8d700561f4a808557f60a7e8aad6198fd9a7
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vinzee.andriodfragmentexample.R;
import com.example.vinzee.andriodfragmentexample.View.line.LockView;
import com.hb.dialog.dialog.ConfirmDialog;

import java.util.ArrayList;
import java.util.List;

<<<<<<< HEAD
import UtilsHelper.SharePreferenceUtil;

import static android.content.Context.MODE_PRIVATE;

=======
>>>>>>> e25f8d700561f4a808557f60a7e8aad6198fd9a7

/**
 * A simple {@link
 */
public class Fragment2 extends Fragment {
    long starttime;
    long endtime;
    long testtime;
    int grades = 0;
    //private int times = 0;
    public Fragment2() {
        // Required empty public constructor
    }
    int checkGrades(long testtime){
        grades = 0;
<<<<<<< HEAD
        grades += 120 - testtime ;
        if(grades<90)
            grades = 90;
        SharePreferenceUtil.updateInfo("part2",grades,getContext());
//        Log.e("PRO","保存"+SharePreferenceUtil.GetInfo("part2",getContext()));
        return grades;
=======
        return grades += 300 - testtime ;
>>>>>>> e25f8d700561f4a808557f60a7e8aad6198fd9a7
    }

    int checkErrorGrades(int errorTimes){
        grades = 0;
<<<<<<< HEAD

        grades += 90 - errorTimes*10 ;
        SharePreferenceUtil.updateInfo("part2",grades,getContext());
//        Log.e("PRO","保存"+SharePreferenceUtil.GetInfo("part2",getContext()));
        return grades;
=======
        return grades += 90 - errorTimes*10 ;
>>>>>>> e25f8d700561f4a808557f60a7e8aad6198fd9a7
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_fragment2, container, false);
    }
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        choose();
        final LockView lockView = getActivity().findViewById(R.id.lock_view);
<<<<<<< HEAD

=======
>>>>>>> e25f8d700561f4a808557f60a7e8aad6198fd9a7
        List<Integer> intList = new ArrayList<>();
        for(int i=0;i<9;i++) {
            intList.add(i);
        }
        lockView.setStandard(intList);
        lockView.setOnDrawCompleteListener(new LockView.OnDrawCompleteListener() {
            @Override
            //判断连线是否成功并弹窗显示
            public void onComplete(boolean isSuccess) {
                endtime = System.currentTimeMillis();
                testtime = (endtime-starttime)/1000;
                ConfirmDialog confirmDialog = new ConfirmDialog(getContext());
                if (testtime>=300){
                    confirmDialog.setLogoImg(R.mipmap.dialog_notice).setMsg("您所用的时间已经超过300s,有极高风险患阿茨海默症");
                }
                else {
                    if (isSuccess ){
                        int showGrades = checkGrades(testtime);
                        confirmDialog.setLogoImg(R.mipmap.dialog_notice).setMsg("您所用的时间为:"+ testtime +"s\n得分为"+showGrades+"\n需要重新测试吗");
                        confirmDialog.setClickListener(new ConfirmDialog.OnBtnClickListener() {
                            @Override
                            public void ok() {
                                starttime = System.currentTimeMillis();
                                //times = 0;
                            }

                            @Override
                            public void cancel() {
                                starttime = System.currentTimeMillis();
                                //times = 0;
                            }
                        });
                        confirmDialog.show();
                    }else{

                        int showGrades = checkErrorGrades(lockView.errorTimes);
                        confirmDialog.setLogoImg(R.mipmap.dialog_notice).setMsg("您所用的时间为:"+ testtime +"s\n得分为"+showGrades+"\n需要重新测试吗");
                        confirmDialog.setClickListener(new ConfirmDialog.OnBtnClickListener() {
                            @Override
                            public void ok() {
                                starttime = System.currentTimeMillis();
                                //times = 0;
                            }

                            @Override
                            public void cancel() {
                                starttime = System.currentTimeMillis();
                                //times = 0;
                            }
                        });
                        confirmDialog.show();
                       // Toast.makeText(getContext(), "您的连接顺序有误,请重新连接!", Toast.LENGTH_SHORT).show();
                       // times+=1;
                    }

                }

            }
        });
<<<<<<< HEAD

=======
>>>>>>> e25f8d700561f4a808557f60a7e8aad6198fd9a7
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, Fragment2.class);
        context.startActivity(intent);
    }
    public void choose(){

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("您要开始连线测试吗？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                starttime = System.currentTimeMillis();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(getActivity(),MainActivity.class);
                startActivity(intent);
            }
        });
        final  AlertDialog dialog = builder.show();
        dialog.show();

    }
<<<<<<< HEAD

=======
>>>>>>> e25f8d700561f4a808557f60a7e8aad6198fd9a7
}

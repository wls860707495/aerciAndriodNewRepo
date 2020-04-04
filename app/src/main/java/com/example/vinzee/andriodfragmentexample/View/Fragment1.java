package com.example.vinzee.andriodfragmentexample.View;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;

import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.vinzee.andriodfragmentexample.R;
import com.example.vinzee.andriodfragmentexample.View.paint.Clock;
import com.example.vinzee.andriodfragmentexample.View.paint.DrawMode;
import com.example.vinzee.andriodfragmentexample.View.paint.DrawingBoard;
import com.hb.dialog.dialog.ConfirmDialog;

import org.opencv.android.Utils;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import static org.opencv.imgproc.Imgproc.INTER_CUBIC;
import static org.opencv.imgproc.Imgproc.cvtColor;
import static org.opencv.imgproc.Imgproc.resize;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment1 extends Fragment implements View.OnClickListener {
    private Communication mainActivity;
    public int num =1;
    private DrawingBoard mDrawingBoard;
    private ImageView mPaint;
    private ImageView mEraser;
    private ImageView mClean;
    private ImageView mLast;
    private ImageView mNext;
    private ImageView mrsult;
    double evepress;
    double evespeed;
    long starttime;
    long endtime;
    long testtime;
    public String compare;
    ArrayList<Float> speedx;
    ArrayList<Float> speedy;
    ArrayList<Float> press;
    public Fragment1() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_fragment1, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initEvent();
        choose();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mainActivity = (Communication) context;
    }

    public interface Communication {
        String foo();
    }

    @Override
    public void onStart() {
        super.onStart();

//        Log.d("onStart", mainActivity.foo());
    }

    //初始化控件
    private void initView() {
        mDrawingBoard = getActivity().findViewById(R.id.draw_board);
        mPaint = getActivity().findViewById(R.id.iv_paint);
        mEraser = getActivity().findViewById(R.id.iv_eraser);
        mClean = getActivity().findViewById(R.id.iv_clean);
        mLast = getActivity().findViewById(R.id.iv_last);
        mNext = getActivity().findViewById(R.id.iv_next);
        mrsult = getActivity().findViewById(R.id.iv_test);
    }

    //设置监听事件
    private void initEvent() {
        //设置默认选择背景,level值为1
        mPaint.getDrawable().setLevel(1);
        mPaint.getBackground().setLevel(1);
        mPaint.setOnClickListener(this);
        mEraser.setOnClickListener(this);
        mClean.setOnClickListener(this);
        mLast.setOnClickListener(this);
        mNext.setOnClickListener(this);
        mrsult.setOnClickListener(this);
    }


    //实现监听事件效果
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_paint:
                if (mDrawingBoard.getMode() != DrawMode.PaintMode) {
                    mDrawingBoard.setMode(DrawMode.PaintMode);
                }
                mPaint.getDrawable().setLevel(1);
                mPaint.getBackground().setLevel(1);
                mEraser.getDrawable().setLevel(0);
                mEraser.getBackground().setLevel(0);

                break;
            case R.id.iv_eraser:
                if (mDrawingBoard.getMode() != DrawMode.EraserMode) {
                    mDrawingBoard.setMode(DrawMode.EraserMode);
                }
                mPaint.getDrawable().setLevel(0);
                mPaint.getBackground().setLevel(0);
                mEraser.getDrawable().setLevel(1);
                mEraser.getBackground().setLevel(1);
                break;
            case R.id.iv_clean:
                alertDialogClean();
                mDrawingBoard.clearspeed();
                mDrawingBoard.clearpress();
                break;
            case R.id.iv_last:
                mDrawingBoard.lastStep();
                break;
            case R.id.iv_next:
                mDrawingBoard.nextStep();
                break;
            case R.id.iv_test:
                /*--------------------------------*/
                Bitmap bit1 = mDrawingBoard.mBufferBitmap;
                Bitmap bit2 = createViewBitmap(getView());
//                try {
//                    saveBitmap(bit2);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
                /*--------------------------------*/
                //计算平均压力及速度
               if(mDrawingBoard.press != null) {
                   press = mDrawingBoard.press;
                   evepress = caculatepress(press);
                   Log.i("1we","qqq2");
               }else {
                   Log.i("1we","213112");
               }
               if(mDrawingBoard.speedx != null) {
                   speedx = mDrawingBoard.speedx;
                   speedy = mDrawingBoard.speedy;
                   evespeed = caculatespeed(speedx, speedy);
               }else {
                   Log.i("1we","313");
               }
                //比较图画相似度
                compare = HashCompare(bit1, bit2);

                endtime = System.currentTimeMillis();
                testtime = (endtime - starttime) / 1000;
                ConfirmDialog confirmDialog = new ConfirmDialog(getContext());
                confirmDialog.setLogoImg(R.mipmap.dialog_notice).setMsg("您所用的时间为：" + testtime + " s，相似度为：" + compare+ "！，" +
                        "平均压力为:"+evepress+",平均速度为:"+evespeed+",是否重新测试？");
                confirmDialog.setClickListener(new ConfirmDialog.OnBtnClickListener() {
                    @Override
                    public void ok() {
                        mDrawingBoard.clean();
                        mDrawingBoard.clearpress();
                        mDrawingBoard.clearspeed();
                        starttime = System.currentTimeMillis();
                    }

                    @Override
                    public void cancel() {
                        starttime = System.currentTimeMillis();
                    }
                });
                confirmDialog.show();
                break;
        }
    }

    //设置画板清空对话框
    private void alertDialogClean() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("您要请空画板吗？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mDrawingBoard.clean();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        final AlertDialog dialog = builder.show();
        dialog.show();
    }

    public void choose() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("您要开始画图测试吗？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                starttime = System.currentTimeMillis();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });
        final AlertDialog dialog = builder.show();
        dialog.show();

    }

    public String HashCompare(Bitmap Bp1, Bitmap Bp2) {
        //数据定义导入部分
        Mat src1 = new Mat();
        Mat dst1 = new Mat();
        Mat src2 = new Mat();
        Mat dst2 = new Mat();

        //读取位图到MAT
        Utils.bitmapToMat(Bp1, src1);
        Utils.bitmapToMat(Bp2, src2);
        //变ARGB变灰度图，四通道变一通道

        cvtColor(src1, dst1, Imgproc.COLOR_BGR2GRAY);
        cvtColor(src2, dst2, Imgproc.COLOR_BGR2GRAY);
        //把灰度图图缩成8*8
        resize(dst1, dst1, new Size(20, 20), 0, 0, INTER_CUBIC);
        resize(dst2, dst2, new Size(20, 20), 0, 0, INTER_CUBIC);

        //核心算法部分
        //这里变成二维数组才可以用Mat.get(row,cul)去获取，二维是因为每个像素点里面可能有很多属性（ARGB）
        // 变成灰度之后就只有一个G了，这个G是Gray，前面那个G是Green。
        double[][] data1 = new double[400][1];
        double[][] data2 = new double[400][1];
        //iAvg 记录平均像素灰度值，arr记录像素灰度值，data是个跳板。
        int iAvg1 = 0, iAvg2 = 0;
        double[] arr1 = new double[400];
        double[] arr2 = new double[400];
        //get灰度给data，用data给arr充值，算平均灰度值iAvg。
        for (int i = 0; i < 20; i++) {
            int tmp = i * 20;
            for (int j = 0; j < 20; j++) {
                int tmp1 = tmp + j;
                data1[tmp1] = dst1.get(i, j);
                data2[tmp1] = dst2.get(i, j);
                arr1[tmp1] = data1[tmp1][0];
                arr2[tmp1] = data2[tmp1][0];
                iAvg1 += arr1[tmp1];
                iAvg2 += arr2[tmp1];
            }
        }
        iAvg1 /= 400;
        iAvg2 /= 400;
        //比对每个像素灰度值和平均灰度值大小
        for (int i = 0; i < 400; i++) {
            arr1[i] = (arr1[i] >= iAvg1) ? 1 : 0;
            arr2[i] = (arr2[i] >= iAvg2) ? 1 : 0;
        }
        //计算差异值
        int iDiffNum = 0;
        for (int i = 0; i < 400; i++)
            if (arr1[i] != arr2[i])
                ++iDiffNum;
        //输出什么看个人喜好
      /*
        if (iDiffNum < 13) {
            return "您的分数为100分?????";
        } else if(iDiffNum <=15 && iDiffNum>=13){
            return "您的分数为80分";
        } else if(iDiffNum <=20 && iDiffNum>15){
            return "您的分数为60分";
        } else if(iDiffNum <=25 && iDiffNum>20){
            return "您的分数为40分";
        }else{
            return "您的分数为<40分";
        }
        */
      if (num%2==1){
          num++;
          return "您的分数为70分";
      }else {
          num++;
          return "您的分数为<40分";
      }
      }


    public Bitmap createViewBitmap(View v) {
        Bitmap bitmap = Bitmap.createBitmap(mDrawingBoard.mWidth, 600,
                Bitmap.Config.ARGB_8888); //创建一个和View大小一样的Bitmap
        Canvas canvas = new Canvas(bitmap);  //使用上面的Bitmap创建canvas
        v.draw(canvas);  //把View画到Bitmap上
        return bitmap;
    }
   //计算平均速度
    public double caculatespeed(ArrayList<Float> speedx, ArrayList<Float> speedy){
        double evespeed=0.0;
        for (int i=0;i<speedx.size();i++){
            evespeed+=sqrt(pow(speedx.get(i),2)+ pow(speedy.get(i),2));
//            Log.i("ui",Double.toString(evespeed));
        }
        evespeed /= speedx.size();
        return evespeed;
    }
    //就算平均压力
    public double caculatepress(ArrayList<Float> press){
        double evepress=0.0;
        for (int i = 0;i<press.size();i++){
            evepress +=press.get(i);
//            Log.i("uu",Double.toString(evepress));
        }
        evepress /=press.size();
        return evepress;
    }
    /*--------测试bitmap 视图截取------------

    public void saveBitmap(Bitmap bitmap) throws IOException {
        File file = new File(Environment.getExternalStorageDirectory() + "/image");
        FileOutputStream out;
        try {
            out = new FileOutputStream(file);
            if (bitmap.compress(Bitmap.CompressFormat.PNG, 70, out)) {
                out.flush();
                out.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
  ------------------------------------*/
}

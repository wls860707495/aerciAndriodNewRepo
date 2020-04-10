package com.example.vinzee.andriodfragmentexample.View;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.example.vinzee.andriodfragmentexample.R;
import com.example.vinzee.andriodfragmentexample.View.Fragment1;
import com.example.vinzee.andriodfragmentexample.View.Fragment2;
import com.example.vinzee.andriodfragmentexample.View.Fragment3;

//import org.opencv.android.BaseLoaderCallback;
//import org.opencv.android.LoaderCallbackInterface;
//import org.opencv.android.OpenCVLoader;

import UtilsHelper.SharePreferenceUtil;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity implements Fragment1.Communication {



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Log.d("MainActivity", "selected: " + item.getItemId());

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    PushFragment(new Fragment1(), "画图测试");
                    return true;
                case R.id.navigation_dashboard:
                    PushFragment(new Fragment2(), "连线测试");
                    return true;
                case R.id.navigation_notifications:
                    PushFragment(new Fragment3(), "语音测试");
                    return true;
                case R.id.navigation_statistic:
                    PushFragment(new Fragment4(),"统计测试");
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        //load OpenCV engine and init OpenCV library
//        if (!OpenCVLoader.initDebug()) {
//            Log.d(TAG, "Internal OpenCV library not found. Using OpenCV Manager for initialization");
//            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_0_0, this, mLoaderCallback);
//        } else {
//            Log.d(TAG, "OpenCV library found inside package. Using it!");
//            mLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS);
//        }
    }
//    //openCV4Android 需要加载用到
//    private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(this) {
//        @Override
//        public void onManagerConnected(int status) {
//            switch (status) {
//                case LoaderCallbackInterface.SUCCESS: {
//                    Log.i(TAG, "OpenCV loaded successfully");
////                    mOpenCvCameraView.enableView();
////                    mOpenCvCameraView.setOnTouchListener(ColorBlobDetectionActivity.this);
//                }
//                break;
//                default: {
//                    super.onManagerConnected(status);
//                }
//                break;
//            }
//        }
//    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharePreferenceUtil.getSharePreference(getBaseContext());//新加

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void PushFragment(Fragment fragment, String fragmentTag){
        if (fragment == null){
            Log.d("PushFragment", "fragment is null");
            return;
        }

        FragmentManager fragmentManager = getSupportFragmentManager();

        if (fragmentManager == null){
            Log.d("PushFragment", "fragmentManager is null");
            return;
        }

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.fragment_container, fragment, fragmentTag);
        fragmentTransaction.commit();
    }

    @Override
    public String foo() {
        FragmentManager fragmentManager = getSupportFragmentManager();

        if (fragmentManager == null){
            Log.d("foo", "fragmentManager is null");
        } else {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            Fragment2 fragment2 = (Fragment2) fragmentManager.findFragmentByTag("fragment2");
           // fragment2.bar("Hello Fragment");
            fragmentTransaction.commit();
        }

        return "Hello from Main activity";
    }
}

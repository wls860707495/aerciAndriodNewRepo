package com.example.vinzee.andriodfragmentexample.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import com.example.vinzee.andriodfragmentexample.View.statisticalchart.MainActivity;

public class Fragment4 extends Fragment {
    private ViewPager viewPager;
    private List<Fragment> fragments;
    private FragmentPagerAdapter adapter;
    // 设置是否显示动画，为了防止在创建时就开启动画，用以下三个参数做了判断，只有当看到视图后才会显示动画
    public static int flag1 = 2;
    public static int flag2 = 1;
    public static int flag3 = 1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Intent intent = new Intent(getContext(), MainActivity.class);
        startActivity(intent);
        return super.onCreateView(inflater, container, savedInstanceState);

    }
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

}

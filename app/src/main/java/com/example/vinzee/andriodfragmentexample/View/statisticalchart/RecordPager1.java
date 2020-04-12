package com.example.vinzee.andriodfragmentexample.View.statisticalchart;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vinzee.andriodfragmentexample.R;

/**
 * Created by liyachao on 2015/3/20.
 */
public class RecordPager1 extends Fragment {
	private View view;
	private HistogramView green;

	@Override
	public void onResume() {
		super.onResume();

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.activity_chart_record_one, null);
		green = (HistogramView) view.findViewById(R.id.green);
		green.start(MainActivity.flag1);
		MainActivity.flag1 = 3;
		return view;
	}

	public void start() {

	}
}

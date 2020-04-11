package com.example.vinzee.andriodfragmentexample.View;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.vinzee.andriodfragmentexample.R;
import com.example.vinzee.andriodfragmentexample.View.voice.RecordTestActivity;
import com.example.vinzee.andriodfragmentexample.View.voice.Task13Activity;
import com.example.vinzee.andriodfragmentexample.View.voice.Task14Activity;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment3 extends Fragment {
    private Button task13;
    private Button task14;
    private Button record_test;

   @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment3, container, false);
    }
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        task13=getView().findViewById(R.id.task13);
        task13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),Task13Activity.class);
                startActivity(intent);
            }
        });
        task14=getView().findViewById(R.id.task14);
        task14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),Task14Activity.class);
                startActivity(intent);
            }
        });

//        record_test=getView().findViewById(R.id.record_test);
//        record_test.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getContext(),RecordTestActivity.class);
//                startActivity(intent);
//            }
//        });
    }
}

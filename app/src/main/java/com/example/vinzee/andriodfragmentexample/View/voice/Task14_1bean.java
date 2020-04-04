package com.example.vinzee.andriodfragmentexample.View.voice;

import android.content.Context;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class Task14_1bean {
    private TextView describe;
    private RadioButton play;
    private EditText edit;
    private Button submit;
    public TextView getDescribe()
    {
        return describe;
    }

    public void setDescribe(TextView describe) {
        this.describe = describe;
    }

    public RadioButton getPlay()
    {
        return play;
    }

    public void setPlay(RadioButton play) {
        this.play = play;
    }

    public EditText getEdit()
    {
        return edit;
    }

    public void setEdit(EditText edit) {
        this.edit = edit;
    }

    public Button getSubmit()
    {
        return submit;
    }

    public void setSubmit(Button submit)
    {
        this.submit = submit;
    }

    Task14_1bean(Context context)
    {
        describe =new TextView(context);
        play = new RadioButton(context);
        edit = new EditText(context);
        submit =new Button(context);
        play.setText("播放");
        edit.setText("请在此输入");
        submit.setText("提交");
    }
}

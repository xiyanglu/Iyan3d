package com.smackall.animator;

import android.app.Dialog;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import com.smackall.animator.Helper.Constants;

/**
 * Created by Sabish.M on 12/3/16.
 * Copyright (c) 2015 Smackall Games Pvt Ltd. All rights reserved.
 */
public class Scale implements SeekBar.OnSeekBarChangeListener, View.OnClickListener{
    private Context mContext;
    private TextView x,y,z;
    boolean locked = false;
    private Dialog scale;

    public Scale(Context context){
        this.mContext = context;
    }

    public void showScale(View v, MotionEvent event,float x,float y,float z){
        Dialog scale = new Dialog(mContext);
        scale.requestWindowFeature(Window.FEATURE_NO_TITLE);
        scale.setContentView(R.layout.scale);
        scale.setCancelable(false);
        scale.setCanceledOnTouchOutside(true);
        scale.getWindow().setLayout(Constants.width / 2, Constants.height/2);
        Window window = scale.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity= Gravity.TOP | Gravity.START;
        wlp.dimAmount=0.0f;
        if(event != null) {
            wlp.x = (int)event.getX();
            wlp.y = (int)event.getY();
        }
        else {
            int[] location = new int[2];
            v.getLocationOnScreen(location);
            wlp.x = location[0];
            wlp.y = location[1];
        }
        window.setAttributes(wlp);
        initViews(scale);
        this.scale = scale;
        setStartingValue(x,y,z);
        updateText();
        scale.show();
    }

    private void setStartingValue(float x,float y,float z)
    {
        ((SeekBar) scale.findViewById(R.id.x)).setMax((((int)(x*10.0f)) > 10) ? ((int)(x*10.0f)) *2 : 10);
        ((SeekBar) scale.findViewById(R.id.y)).setMax((((int)(y*10.0f)) > 10) ? ((int)(y*10.0f)) *2 : 10);
        ((SeekBar) scale.findViewById(R.id.z)).setMax((((int)(z*10.0f)) > 10) ? ((int)(z*10.0f)) *2 : 10);

        ((SeekBar) scale.findViewById(R.id.x)).setProgress((int)(x*10.0f));
        ((SeekBar) scale.findViewById(R.id.y)).setProgress((int)(y*10.0f));
        ((SeekBar) scale.findViewById(R.id.z)).setProgress((int)(z*10.0f));
        setMax();
    }

    private void initViews(Dialog scale)
    {
        x = ((TextView)scale.findViewById(R.id.x_value));
        y = ((TextView)scale.findViewById(R.id.y_value));
        z = ((TextView)scale.findViewById(R.id.z_value));
        ((SeekBar)scale.findViewById(R.id.x)).setOnSeekBarChangeListener(this);
        ((SeekBar)scale.findViewById(R.id.y)).setOnSeekBarChangeListener(this);
        ((SeekBar)scale.findViewById(R.id.z)).setOnSeekBarChangeListener(this);
        ((Switch)scale.findViewById(R.id.lock_switch)).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.lock_switch) {
            locked = ((Switch) v).isChecked();
            ((ImageView)scale.findViewById(R.id.brace_img)).setVisibility((locked) ? View.VISIBLE : View.INVISIBLE);
            ((ImageView)scale.findViewById(R.id.lock_img)).setImageDrawable(ContextCompat.getDrawable(mContext,(locked) ? R.drawable.lock : R.drawable.unlock));
            if(locked)
                setMax();
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if(locked && progress < 2){
            ((SeekBar) scale.findViewById(R.id.x)).setMax(10);
            ((SeekBar) scale.findViewById(R.id.y)).setMax(10);
            ((SeekBar) scale.findViewById(R.id.z)).setMax(10);
        }
        if(progress < 2){
            seekBar.setProgress(2);
            return;
        }
        if(locked){
            ((SeekBar)scale.findViewById(R.id.x)).setProgress(progress);
            ((SeekBar)scale.findViewById(R.id.y)).setProgress(progress);
            ((SeekBar)scale.findViewById(R.id.z)).setProgress(progress);
        }
        updateText();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        updateText();
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        if(seekBar.getProgress() > 5)
            setMax();
        updateText();
    }

    private void updateText()
    {
        x.setText(String.valueOf((float) ((SeekBar) scale.findViewById(R.id.x)).getProgress() / 10.0f));
        y.setText(String.valueOf((float) ((SeekBar) scale.findViewById(R.id.y)).getProgress() / 10.0f));
        z.setText(String.valueOf((float) ((SeekBar) scale.findViewById(R.id.z)).getProgress() / 10.0f));
    }

    private void setMax()
    {
        int x = ((SeekBar) scale.findViewById(R.id.x)).getProgress();
        int y = ((SeekBar) scale.findViewById(R.id.y)).getProgress();
        int z = ((SeekBar) scale.findViewById(R.id.z)).getProgress();
        int max = Math.max(Math.max(x,y),z);
        if(locked){
            ((SeekBar)scale.findViewById(R.id.x)).setProgress(max);
            ((SeekBar)scale.findViewById(R.id.y)).setProgress(max);
            ((SeekBar)scale.findViewById(R.id.z)).setProgress(max);
        }
        if(max < 6) return;
        ((SeekBar) scale.findViewById(R.id.x)).setMax(max*2);
        ((SeekBar) scale.findViewById(R.id.y)).setMax(max*2);
        ((SeekBar) scale.findViewById(R.id.z)).setMax(max*2);

    }
}
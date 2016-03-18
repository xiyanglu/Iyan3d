package com.smackall.animator;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;

import com.smackall.animator.Helper.Constants;

/**
 * Created by Sabish.M on 11/3/16.
 * Copyright (c) 2015 Smackall Games Pvt Ltd. All rights reserved.
 */

public class LightProps implements View.OnTouchListener,SeekBar.OnSeekBarChangeListener,View.OnClickListener{

    private Context mContext;
    LinearLayout colorPreview;
    Bitmap bitmap;

    public LightProps(Context mContext)
    {
        this.mContext = mContext;
    }

    public void showLightProps(View v,MotionEvent event)
    {
        Dialog light_prop = new Dialog(mContext);
        light_prop.requestWindowFeature(Window.FEATURE_NO_TITLE);
        light_prop.setContentView(R.layout.light_props);
        light_prop.setCancelable(false);
        light_prop.setCanceledOnTouchOutside(true);
        light_prop.getWindow().setLayout(Constants.width / 3, (int) (Constants.height/1.5));
        Window window = light_prop.getWindow();
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
        ImageView color_picker = (ImageView)light_prop.findViewById(R.id.color_picker);
        bitmap = ((BitmapDrawable)color_picker.getDrawable()).getBitmap();
        initViews(light_prop);
        light_prop.show();
    }

    private void initViews(Dialog light_prop)
    {
        ((ImageView)light_prop.findViewById(R.id.color_picker)).setOnTouchListener(this);
        colorPreview = (LinearLayout)light_prop.findViewById(R.id.color_preview);
        ((SeekBar)light_prop.findViewById(R.id.shadow_darkness)).setOnSeekBarChangeListener(this);
        ((SeekBar)light_prop.findViewById(R.id.distance)).setOnSeekBarChangeListener(this);
        ((Button)light_prop.findViewById(R.id.delete_animation)).setOnClickListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        float eventX = event.getX();
        float eventY = event.getY();
        float[] eventXY = new float[]{eventX, eventY};
        Matrix invertMatrix = new Matrix();
        ((ImageView) v).getImageMatrix().invert(invertMatrix);
        invertMatrix.mapPoints(eventXY);
        int x = (int) eventXY[0];
        int y = (int) eventXY[1];
        //Limit x, y range within bitmap
        if (x < 0) {
            x = 0;
        } else if (x > (bitmap.getWidth() - 1)) {
            x = (bitmap.getWidth() - 1);
        }
        if (y < 0) {
            y = 0;
        } else if (y > (bitmap.getHeight() - 1)) {
            y = (bitmap.getHeight() - 1);
        }
        int touchedRGB = bitmap.getPixel(x, y);
        int red = Color.red(touchedRGB);
        int green = Color.green(touchedRGB);
        int blue = Color.blue(touchedRGB);
        colorPreview.setBackgroundColor(touchedRGB);
        return true;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onClick(View v) {

    }
}
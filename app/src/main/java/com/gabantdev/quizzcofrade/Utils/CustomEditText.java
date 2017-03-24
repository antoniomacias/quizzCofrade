package com.gabantdev.quizzcofrade.Utils;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by macias on 19/03/2017.
 */


public class CustomEditText extends EditText {

    public CustomEditText(Context context) {
        super(context);

        applyCustomFont(context);
    }

    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);

        applyCustomFont(context);
    }

    public CustomEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        applyCustomFont(context);
    }

    private void applyCustomFont(Context context) {
        Typeface type = Typeface.createFromAsset(context.getAssets(),"CardenioModernBold.ttf");
        setTypeface(type);
    }
}
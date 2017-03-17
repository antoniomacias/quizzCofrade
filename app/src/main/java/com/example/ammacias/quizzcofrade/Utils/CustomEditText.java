package com.example.ammacias.quizzcofrade.Utils;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by ammacias on 17/03/2017.
 */

public class CustomEditText extends TextView {

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
        Typeface type = Typeface.createFromAsset(context.getAssets(),"Dosmilcatorce.ttf");
        setTypeface(type);
    }
}
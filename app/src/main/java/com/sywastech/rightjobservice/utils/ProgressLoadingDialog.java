package com.sywastech.rightjobservice.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Window;
import android.widget.ProgressBar;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Circle;
import com.sywastech.rightjobservice.R;

public class ProgressLoadingDialog extends Dialog {

    private final Context context;

    public ProgressLoadingDialog(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setDimAmount(0.3f);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        setContentView(R.layout.dialog_progressbar);
        ProgressBar progressBar = (ProgressBar)findViewById(R.id.progress);
        Sprite doubleBounce = new Circle();
        doubleBounce.setScale(1);
        doubleBounce.setColor(context.getResources().getColor(R.color.teal_700));
        doubleBounce.setAnimationDelay(0);
        progressBar.setIndeterminateDrawable(doubleBounce);
        setCancelable(false);
    }
}

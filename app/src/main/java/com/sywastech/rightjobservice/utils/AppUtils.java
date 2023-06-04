package com.sywastech.rightjobservice.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.sywastech.rightjobservice.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AppUtils {

    // validate phone number
    public static boolean isValidMobile(String phone) {
        if(TextUtils.isEmpty(phone)){
            return false;
        } else {
            String expression = "\\d{10}";
            Pattern pattern = Pattern.compile(expression);
            Matcher matcher = pattern.matcher(phone);
            return matcher.matches();
        }
    }

    // validate email
    private boolean isValidMail(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static ProgressLoadingDialog showProgressDialog(Context context){
        ProgressLoadingDialog progressLoadingDialog = new ProgressLoadingDialog(context);
        if(!progressLoadingDialog.isShowing()){
            progressLoadingDialog.show();
        }
        return progressLoadingDialog;
    }

    public static void setImageUsingGlide(Context context, String imageUrl, ImageView view){
        Glide.with(context)
                .load(imageUrl)
                .apply(getRequestOptionGlide())
                .into(view);
    }

    @SuppressLint("CheckResult")
    public static RequestOptions getRequestOptionGlide(){
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.ic_baseline_error_24);
        requestOptions.error(R.drawable.ic_baseline_error_24);
        return requestOptions;
    }
}

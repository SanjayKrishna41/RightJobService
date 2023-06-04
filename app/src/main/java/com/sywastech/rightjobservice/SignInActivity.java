package com.sywastech.rightjobservice;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Paint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.sywastech.rightjobservice.databinding.ActivitySignInBinding;
import com.sywastech.rightjobservice.utils.AppUtils;
import com.sywastech.rightjobservice.utils.Constants;
import com.sywastech.rightjobservice.utils.ProgressLoadingDialog;

public class SignInActivity extends AppCompatActivity {

    ActivitySignInBinding signInBinding;
    ProgressLoadingDialog progressLoadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //view binding
        signInBinding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(signInBinding.getRoot());

        signInBinding.jobSeekerImageView.setOnClickListener(view -> setUpOtpScreen(Constants.JOB_SEEKER));

        signInBinding.recruiterImageView.setOnClickListener(view -> setUpOtpScreen(Constants.RECRUITER));

        signInBinding.getOtpButton.setOnClickListener(view -> {
            progressLoadingDialog = AppUtils.showProgressDialog(this);
            String phoneNumber = signInBinding.phoneNumberEditText.getText().toString();
            // check if mobile number is correct or not.
            if(AppUtils.isValidMobile(phoneNumber)){
                //TODO api call to send otp
                progressLoadingDialog.dismiss();
                signInBinding.verifyTextView.setText("Otp sent to "+phoneNumber+"\n"+Constants.VERIFY_OTP);
                startTimer();
                // hide phone number and otp button
                signInBinding.otpScreenGroup.setVisibility(View.INVISIBLE);
                signInBinding.verifyOtpGroup.setVisibility(View.VISIBLE);
            } else {
                progressLoadingDialog.dismiss();
                Toast.makeText(this, "Please enter valid phone number"+AppUtils.isValidMobile(phoneNumber), Toast.LENGTH_SHORT).show();
            }
        });

        signInBinding.verifyOtpButton.setOnClickListener(view -> {
            //TODO send otp to server and verify.
            String otp = signInBinding.enterOtpEditText.getText().toString().trim();
            if(TextUtils.isEmpty(otp)){
                Toast.makeText(this, Constants.PLEASE_ENTER_VALID_OTP, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, otp, Toast.LENGTH_SHORT).show();
                //TODO verify otp and navigate to home activity
            }
        });

        signInBinding.resendOtpTextView.setOnClickListener(view -> {
            // send otp to server and verify.
            signInBinding.resendOtpTextView.setText(Constants.OTP_SENT_SUCCESS);
            signInBinding.resendOtpTextView.setEnabled(false);
        });
    }

    public void setUpOtpScreen(String userType){
        // hide UI elements respect to choosing
        signInBinding.jobSeekerGroup.setVisibility(View.INVISIBLE);
        signInBinding.recruiterGroup.setVisibility(View.INVISIBLE);
        signInBinding.dividerView.setVisibility(View.INVISIBLE);

        // un-hide UI elements of OTP screen
        signInBinding.otpTextView.setVisibility(View.VISIBLE);
        signInBinding.otpScreenGroup.setVisibility(View.VISIBLE);

        // make OTP screen visible
        if(userType.equals(Constants.JOB_SEEKER)){
            signInBinding.otpTextView.setText(Constants.OTP_TEXT_JOB_SEEKER);
        } else {
            signInBinding.otpTextView.setText(Constants.OTP_TEXT_RECRUITER);
        }
    }

    /**
     * Function to start timer for resend OTP
     */
    private void startTimer() {
        signInBinding.resendOtpTextView.setEnabled(false);
        // underline text
        signInBinding.resendOtpTextView.setPaintFlags(signInBinding.resendOtpTextView.getPaintFlags()
                |   Paint.UNDERLINE_TEXT_FLAG);
        CountDownTimer cTimer = new CountDownTimer(30000, 1000) {
            public void onTick(long millisUntilFinished) {
                signInBinding.resendOtpTextView.setText(Constants.RESEND_OTP+ millisUntilFinished / 1000+"s");
            }
            public void onFinish() {
                signInBinding.resendOtpTextView.setText(Constants.RESEND_OTP);
                signInBinding.resendOtpTextView.setEnabled(true);
            }
        };
        cTimer.start();
    }
}
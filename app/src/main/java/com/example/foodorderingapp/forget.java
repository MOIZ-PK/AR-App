package com.example.foodorderingapp;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forget extends AppCompatActivity implements View.OnClickListener{


    EditText enterEmailEditText;
    Button resetPwBtn;
    TextView returnToLoginScr;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);
        firebaseAuth = FirebaseAuth.getInstance();

        enterEmailEditText = (EditText) findViewById(R.id.fpResetEditText);
        resetPwBtn = (Button) findViewById(R.id.fpResetButton);
        resetPwBtn.setOnClickListener(this);
        returnToLoginScr = (TextView) findViewById(R.id.fpReturnToLoginTextView);
        returnToLoginScr.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        // default method for handling onClick Events..
        switch (v.getId()) {

            case R.id.fpResetButton:
                resetPassword();
                break;

            case R.id.fpReturnToLoginTextView:
                Intent i = new Intent(forget.this, login.class);
                startActivity(i);
                finish();
                break;

            default:
                break;
        }
    }



    public void resetPassword(){

        String resetEmailStr = enterEmailEditText.getText().toString().trim();

        if (TextUtils.isEmpty(resetEmailStr)) {
            Toast.makeText(getApplicationContext(), "Enter your email!", Toast.LENGTH_SHORT).show();
            return;
        }

        firebaseAuth.sendPasswordResetEmail(resetEmailStr)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(forget.this, "Check email to reset your password!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(forget.this, "Fail to send reset password email!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


    }




}

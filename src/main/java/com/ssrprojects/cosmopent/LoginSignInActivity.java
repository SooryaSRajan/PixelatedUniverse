package com.ssrprojects.cosmopent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class LoginSignInActivity extends AppCompatActivity {
    String mEmail, mPasswordOne;
    int flag;
    EditText mEmailText, mPasswordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_sign_in);

        if(FirebaseAuth.getInstance().getCurrentUser() != null){
            Intent intent = new Intent(LoginSignInActivity.this, HomePageActivity.class);
            startActivity(intent);
            finish();
        }

        mEmailText = findViewById(R.id.user_email_text);
        mPasswordText = findViewById(R.id.user_password_text);

        getSupportActionBar().hide();
        findViewById(R.id.forgot_password).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginSignInActivity.this, "Forgot Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.sign_in).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPasswordOne = mPasswordText.getText().toString();
                mEmail = mEmailText.getText().toString();

                Pattern pattern = Patterns.EMAIL_ADDRESS;

                if (mEmail.length() == 0 || mPasswordOne.length() == 0) {
                    flag++;
                    Toast.makeText(LoginSignInActivity.this, "Fiels(s) Empty", Toast.LENGTH_SHORT).show();
                }

                else {
                    if (!pattern.matcher(mEmail).matches()) {
                        Toast.makeText(LoginSignInActivity.this, "Invalid Email ID", Toast.LENGTH_SHORT).show();
                        flag++;
                    }
                }

                if (flag == 0) {
                    SignInWithEmailAndPassword();
                }

            }
        });

        findViewById(R.id.sign_up_email).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginSignInActivity.this, SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    void SignInWithEmailAndPassword(){
        FirebaseAuth.getInstance().signInWithEmailAndPassword(mEmail, mPasswordOne).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
            if(task.isSuccessful()){
                Intent intent = new Intent(LoginSignInActivity.this, HomePageActivity.class);
                startActivity(intent);
                finish();
            }
            }

        });
    }

}

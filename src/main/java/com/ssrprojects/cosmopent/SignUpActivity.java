package com.ssrprojects.cosmopent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    String mEmail, mPasswordOne, mPasswordTwo, mName;
    int flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        final EditText mEmailText = findViewById(R.id.email_signup_text);
        final EditText mPasswordText = findViewById(R.id.password_signup_text);
        final EditText mPasswordTextTwo = findViewById(R.id.password2_signup_text);
        final EditText mNameText = findViewById(R.id.username_signup_text);

        getSupportActionBar().hide();
        findViewById(R.id.sign_in_email).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, LoginSignInActivity.class);
                startActivity(intent);
                finish();
            }
        });

        findViewById(R.id.sign_up).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = 0;
                mEmail = mEmailText.getText().toString();
                mPasswordOne = mPasswordText.getText().toString();
                mPasswordTwo = mPasswordTextTwo.getText().toString();
                mName = mNameText.getText().toString();

                Log.e("TAG", "onClick:  " + mName + mEmail + mPasswordOne + mPasswordTwo );

                Pattern pattern = Patterns.EMAIL_ADDRESS;

                if (mEmail.length() == 0 || mPasswordOne.length() == 0 || mPasswordTwo.length() == 0 || mName.length() == 0) {
                    flag++;
                    Toast.makeText(SignUpActivity.this, "Fiels(s) Empty", Toast.LENGTH_SHORT).show();
                }

                else {
                    if (!pattern.matcher(mEmail).matches()) {
                        Toast.makeText(SignUpActivity.this, "Invalid Email ID", Toast.LENGTH_SHORT).show();
                        flag++;
                    }

                    if (!mPasswordOne.equals(mPasswordTwo)) {
                        Toast.makeText(SignUpActivity.this, "Passwords Don't Match", Toast.LENGTH_SHORT).show();
                        flag++;
                    }

                }

                if (flag == 0) {
                    SignInWithEmailAndPassword();
                }
            }
        });
    }

    void SignInWithEmailAndPassword(){
            mAuth.createUserWithEmailAndPassword(mEmail,mPasswordOne).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseDatabase.getInstance().getReference().child("USER").child(mAuth.getUid()).setValue(mName);
                    Intent intent = new Intent(SignUpActivity.this, HomePageActivity.class);
                    startActivity(intent);
                    finish();
                }
                }
            });
    }

}
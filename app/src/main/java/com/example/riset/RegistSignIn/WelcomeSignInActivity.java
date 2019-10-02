package com.example.riset.RegistSignIn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.riset.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WelcomeSignInActivity extends AppCompatActivity {

    @BindView(R.id.btn_sign_in_google)
    Button btnSignInGoogle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_sign_in);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_masuk)
    void fungsi_masuk(){
        Intent i = new Intent(WelcomeSignInActivity.this, SignInActivity.class);
        startActivity(i);
    }

    @OnClick(R.id.btn_daftar)
    void fungsi_daftar(){
        Intent i = new Intent(WelcomeSignInActivity.this, RegisterSatuActivity.class);
        startActivity(i);
    }
}

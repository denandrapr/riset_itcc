package com.example.riset.RegistSignIn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.riset.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterSatuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawableResource(R.drawable.bg_all);
        setContentView(R.layout.activity_register_satu);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_selanjutnya)
    void fungsi_btn_selanjutnya(){
        Intent i = new Intent(RegisterSatuActivity.this, RegisterDuaActivity.class);
        startActivity(i);
    }
}

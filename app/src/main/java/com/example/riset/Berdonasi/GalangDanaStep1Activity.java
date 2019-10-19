package com.example.riset.Berdonasi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.riset.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class GalangDanaStep1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galang_dana_step1);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_selanjutnya)
    void btnSelanjutnya(){
        Intent i = new Intent(GalangDanaStep1Activity.this, GalangDanaStep2Activity.class);
        startActivity(i);
    }
}

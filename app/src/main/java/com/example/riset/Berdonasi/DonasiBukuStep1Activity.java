package com.example.riset.Berdonasi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.riset.Donasi_Uang_Step2;
import com.example.riset.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class DonasiBukuStep1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donasi_uang_step1);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_selanjutnya)
    void tahap2Gakanguang(){
        Intent i = new Intent(DonasiBukuStep1Activity.this , Donasi_Uang_Step2.class);
                startActivity(i);
    }
}

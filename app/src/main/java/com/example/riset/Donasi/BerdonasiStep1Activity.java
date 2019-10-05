package com.example.riset.Donasi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.riset.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class BerdonasiStep1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_berdonasi_step1);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.lanjutkan_pembayaran)
    void lanjut_pembayaran(){
        Intent i = new Intent(BerdonasiStep1Activity.this, BerdonasiStep2Activity.class);
        startActivity(i);
    }
}

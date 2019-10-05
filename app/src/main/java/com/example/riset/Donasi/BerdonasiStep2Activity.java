package com.example.riset.Donasi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.riset.R;

import butterknife.ButterKnife;

public class BerdonasiStep2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_berdonasi_step2);
        ButterKnife.bind(this);
    }
}

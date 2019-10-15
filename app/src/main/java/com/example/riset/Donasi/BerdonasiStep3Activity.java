package com.example.riset.Donasi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.riset.MainActivity;
import com.example.riset.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class BerdonasiStep3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_berdonasi_step3);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_selesai)
    void btn_selesai(){
        BerdonasiStep3Activity.this.getSharedPreferences("Settings", 0).edit().clear().commit();
        Intent i = new Intent(BerdonasiStep3Activity.this, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }
}

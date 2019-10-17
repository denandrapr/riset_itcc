package com.example.riset.Berdonasi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.riset.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class DonasiRuanganStep1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donasi_ruangan_step1);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_selanjutnya)
    void btn_selanjutnya(){
        Intent i = new Intent(DonasiRuanganStep1Activity.this, DonasiRuanganStep2Activity.class);
        startActivity(i);
    }
}

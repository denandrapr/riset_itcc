package com.example.riset.Berdonasi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.riset.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class DonasiBarangStep2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donasi_barang_step2);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnSelesai)
    void btnSelesai(){
        Intent i = new Intent(DonasiBarangStep2Activity.this, DonasiRuanganStep3Activity.class);
        startActivity(i);
    }
}

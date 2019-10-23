package com.example.riset.Berdonasi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.riset.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class DonasiBarangStep0Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donasi_barang_step0);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.selanjutnya)
    void selanjutnya(){
        Intent i = new Intent(DonasiBarangStep0Activity.this, DonasiBarangStep1Activity.class);
        startActivity(i);
    }

}

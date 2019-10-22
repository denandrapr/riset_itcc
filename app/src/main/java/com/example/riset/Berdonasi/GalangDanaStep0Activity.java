package com.example.riset.Berdonasi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.riset.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class GalangDanaStep0Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galang_dana_step0);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.selanjutnya)
    void selanjutnya(){
        Intent i = new Intent(GalangDanaStep0Activity.this, GalangDanaStep1Activity.class);
        startActivity(i);
    }
}

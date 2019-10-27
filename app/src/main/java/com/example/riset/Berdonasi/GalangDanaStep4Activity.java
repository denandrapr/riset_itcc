package com.example.riset.Berdonasi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.riset.MainActivity;
import com.example.riset.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GalangDanaStep4Activity extends AppCompatActivity {
//@BindView(R.id.)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galang_dana_step4);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_selesai)
    void btn_selesai(){
        Intent i = new Intent(GalangDanaStep4Activity.this, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }
}

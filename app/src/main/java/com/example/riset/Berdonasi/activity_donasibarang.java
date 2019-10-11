package com.example.riset.Berdonasi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.riset.R;

import butterknife.ButterKnife;

public class activity_donasibarang extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donasibarang);
        ButterKnife.bind(this);
    }


}

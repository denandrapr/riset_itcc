package com.example.riset.Donasi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.riset.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BerdonasiStep2Activity extends AppCompatActivity {

    private SharedPreferences mSettings;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.txtNominal)
    TextView textNominal;

    @BindView(R.id.bca_logo)
    ImageView bca_logo;
    @BindView(R.id.bri_logo)
    ImageView bri_logo;
    @BindView(R.id.mandiri_logo)
    ImageView mandiri_logo;
    @BindView(R.id.alfa_logo)
    ImageView alfa_logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_berdonasi_step2);
        ButterKnife.bind(this);

        mSettings = BerdonasiStep2Activity.this.getSharedPreferences("Settings", Context.MODE_PRIVATE);

        String get_nominal_donasi = mSettings.getString("nominal_donasi", "missing");
        String get_keterangan = mSettings.getString("keterangan", "missing");
        String metode = mSettings.getString("pilihMetode", "missing");
        int nominal = Integer.parseInt(get_nominal_donasi.replace(",", ""));

        if (metode.equals("BCA")){
            alfa_logo.setVisibility(View.GONE);
            bca_logo.setVisibility(View.VISIBLE);
            bri_logo.setVisibility(View.GONE);
            mandiri_logo.setVisibility(View.GONE);
        }else if (metode.equals("BRI")){
            alfa_logo.setVisibility(View.GONE);
            bca_logo.setVisibility(View.GONE);
            bri_logo.setVisibility(View.VISIBLE);
            mandiri_logo.setVisibility(View.GONE);
        }else if (metode.equals("Mandiri")){
            alfa_logo.setVisibility(View.GONE);
            bca_logo.setVisibility(View.GONE);
            bri_logo.setVisibility(View.GONE);
            mandiri_logo.setVisibility(View.VISIBLE);
        }else if (metode.equals("Alfamart")){
            alfa_logo.setVisibility(View.VISIBLE);
            bca_logo.setVisibility(View.GONE);
            bri_logo.setVisibility(View.GONE);
            mandiri_logo.setVisibility(View.GONE);
        }

        textNominal.setText("Rp "+get_nominal_donasi);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @OnClick(R.id.btn_selesai)
    void selesai(){
        Intent i = new Intent(BerdonasiStep2Activity.this, BerdonasiStep3Activity.class);
        startActivity(i);
    }
}

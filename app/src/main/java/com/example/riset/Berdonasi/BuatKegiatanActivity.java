package com.example.riset.Berdonasi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.riset.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BuatKegiatanActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buat_kegiatan);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @OnClick(R.id.donasiRuangan)
    void donasiRuangan(){
//        Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(BuatKegiatanActivity.this, DonasiRuanganStep0Activity.class);
        startActivity(i);
    }

    @OnClick(R.id.donasi_barang)
    void donasiBarang(){
        Intent i = new Intent(BuatKegiatanActivity.this, DonasiBarangStep0Activity.class);
        startActivity(i);
    }

    @OnClick(R.id.galang_dana)
    void galangDana(){
        Intent i = new Intent(BuatKegiatanActivity.this, GalangDanaStep0Activity.class);
        startActivity(i);
    }
}

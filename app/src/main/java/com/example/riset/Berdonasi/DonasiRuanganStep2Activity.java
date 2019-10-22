package com.example.riset.Berdonasi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.riset.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class DonasiRuanganStep2Activity extends AppCompatActivity {

    String judulKegiatan;
    String batasWaktu;
    String jadwalKegiatan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donasi_ruangan_step2);
        ButterKnife.bind(this);

        Intent i = getIntent();
        jadwalKegiatan = i.getStringExtra("jadwalKegiatan");
        batasWaktu = i.getStringExtra("batasWaktu");
        judulKegiatan = i.getStringExtra("judulKegiatan");
        Log.d("TAG", "Hasil => "+jadwalKegiatan+" "+batasWaktu+" "+judulKegiatan);
    }

    @OnClick(R.id.btnSelesai)
    void btnSelesai(){
        Intent i = new Intent(DonasiRuanganStep2Activity.this, DonasiRuanganStep3Activity.class);
        startActivity(i);
    }
}

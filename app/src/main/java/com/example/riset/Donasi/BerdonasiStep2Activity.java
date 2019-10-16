package com.example.riset.Donasi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.riset.Berdonasi.Model.BerdonasiUangModel;
import com.example.riset.R;
import com.github.florent37.expansionpanel.ExpansionLayout;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.github.ybq.android.spinkit.style.FoldingCube;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BerdonasiStep2Activity extends AppCompatActivity {

    private SharedPreferences mSettings;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

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
    @BindView(R.id.expansionLayout)
    ExpansionLayout expansionLayout;
    @BindView(R.id.spin_kit)
    ProgressBar progressBar;
    @BindView(R.id.relative2)
    RelativeLayout relativeLayout;
    @BindView(R.id.btn_selesai)
    Button btn_selesai;

    String get_nominal_donasi = "";
    String get_keterangan = "";
    String metode = "";
    int nominal = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_berdonasi_step2);
        ButterKnife.bind(this);

        relativeLayout.setVisibility(View.GONE);
        Sprite foldingCube = new FoldingCube();
        progressBar.setIndeterminateDrawable(foldingCube);
        progressBar.setVisibility(View.GONE);

        mSettings = BerdonasiStep2Activity.this.getSharedPreferences("Settings", Context.MODE_PRIVATE);

        get_nominal_donasi = mSettings.getString("nominal_donasi", "missing");
        get_keterangan = mSettings.getString("keterangan", "missing");
        metode = mSettings.getString("pilihMetode", "missing");
        nominal = Integer.parseInt(get_nominal_donasi.replace(",", ""));

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

        expansionLayout.addListener(new ExpansionLayout.Listener() {
            @Override
            public void onExpansionChanged(ExpansionLayout expansionLayout, boolean expanded) {

            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @OnClick(R.id.btn_selesai)
    void selesai(){
        toolbar.setVisibility(View.GONE);
        btn_selesai.setVisibility(View.GONE);
        relativeLayout.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.VISIBLE);
//        BerdonasiUangModel bum = new BerdonasiUangModel(get_keterangan, metode, nominal, true, "15-9-2019","Bob");
        //get date
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy HH:mm");
        String strDate = formatter.format(date);

        Map<String, Object> updates = new HashMap<>();
        updates.put("keterangan", get_keterangan);
        updates.put("metode", metode);
        updates.put("nominal", nominal);
        updates.put("anonim", true);
        updates.put("tanggal", strDate);
        updates.put("nama", "bob");
        updates.put("created_date", FieldValue.serverTimestamp());
        db.collection("Posting")
                .document("0IwGCiXsMiTPYTdPsmq6")
                .collection("berdonasi")
                .document()
                .set(updates)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        progressBar.setVisibility(View.GONE);
                        Intent i = new Intent(BerdonasiStep2Activity.this, BerdonasiStep3Activity.class);
                        startActivity(i);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(BerdonasiStep2Activity.this, "Gagal...", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}

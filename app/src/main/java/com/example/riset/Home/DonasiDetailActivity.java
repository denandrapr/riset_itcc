package com.example.riset.Home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.riset.Donasi.BerdonasiStep1Activity;
import com.example.riset.Home.Adapter.DonaturDetailDonasiAdapter;
import com.example.riset.Home.Adapter.TerdekatKamuAdapter;
import com.example.riset.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DonasiDetailActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    DonaturDetailDonasiAdapter donaturDetailDonasiAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donasi_detail);
        ButterKnife.bind(this);

        ArrayList<String> tutorMereka = new ArrayList<>();
        tutorMereka.add("Anonim");
        tutorMereka.add("Anonim");
        tutorMereka.add("Adi W");
        tutorMereka.add("Anonim");
        tutorMereka.add("Bella");

        setSupportActionBar(toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        donaturDetailDonasiAdapter = new DonaturDetailDonasiAdapter(this, tutorMereka);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(donaturDetailDonasiAdapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @OnClick(R.id.btn_donasi)
    void btn_donasi_action(){
        Intent i = new Intent(DonasiDetailActivity.this, BerdonasiStep1Activity.class);
        startActivity(i);
    }
}

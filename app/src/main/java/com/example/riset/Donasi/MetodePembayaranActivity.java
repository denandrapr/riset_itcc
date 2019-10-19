package com.example.riset.Donasi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.riset.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MetodePembayaranActivity extends AppCompatActivity {

    ArrayList<String> list;
    ArrayAdapter adapter;
    String metodeDiPilih;
    String[] bank = {"BCA", "Mandiri", "BRI", "BNI", "CIMB Niaga", "Permata Bank", "Maybank", "Bank Mega"};

    @BindView(R.id.listView)
    ListView mListView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.pilihMetode)
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metode_pembayaran);
        ButterKnife.bind(this);

        btn.setVisibility(View.GONE);

        SharedPreferences mSettings = MetodePembayaranActivity.this.getSharedPreferences("Settings", Context.MODE_PRIVATE);
        list = new ArrayList<>();
        for (int i = 0; i < bank.length; i++){
            list.add(bank[i]);
        }
        mListView.setChoiceMode(mListView.CHOICE_MODE_SINGLE);
        adapter = new ArrayAdapter(MetodePembayaranActivity.this, android.R.layout.simple_list_item_single_choice, list);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                metodeDiPilih = bank[i];
                SharedPreferences.Editor editor = mSettings.edit();
                editor.putString("pilihMetode", bank[i]);
                editor.apply();
                btn.setText("Pilih metode "+bank[i]);
                btn.setVisibility(View.VISIBLE);
            }
        });

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

    @OnClick(R.id.pilihMetode)
    void pilihMetode(){
        onBackPressed();
    }
}

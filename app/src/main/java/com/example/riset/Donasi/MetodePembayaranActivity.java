package com.example.riset.Donasi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.riset.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MetodePembayaranActivity extends AppCompatActivity {

    ArrayList<String> list;
    ArrayAdapter adapter;
    String[] bank = {"BCA", "Mandiri", "BRI", "BNI", "BNI Syariah", "Danamon", "CIMB Niaga"};

    @BindView(R.id.listView)
    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metode_pembayaran);
        ButterKnife.bind(this);

        list = new ArrayList<>();
        for (int i = 0; i < bank.length; i++){
            list.add(bank[i]);
        }
        mListView.setChoiceMode(mListView.CHOICE_MODE_SINGLE);
//        adapter = new ArrayAdapter(MetodePembayaranActivity.this, R.)
    }
}

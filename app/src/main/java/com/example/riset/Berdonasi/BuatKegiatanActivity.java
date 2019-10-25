package com.example.riset.Berdonasi;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.riset.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BuatKegiatanActivity extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_buat_kegiatan, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @OnClick(R.id.donasiRuangan)
    void donasiRuangan(){
        Intent i = new Intent(getActivity(), DonasiRuanganStep0Activity.class);
        startActivity(i);
    }

    @OnClick(R.id.donasi_barang)
    void donasiBarang(){
        Intent i = new Intent(getActivity(), DonasiBarangStep0Activity.class);
        startActivity(i);
    }

    @OnClick(R.id.galang_dana)
    void galangDana(){
        Intent i = new Intent(getActivity(), GalangDanaStep0Activity.class);
        startActivity(i);
    }
}

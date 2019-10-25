package com.example.riset.Donasi;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.riset.Berdonasi.BuatKegiatanActivity;
import com.example.riset.Donasi.Adapter.ListDonasiAdapter;
import com.example.riset.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DonasiFragment  extends Fragment {

    @BindView(R.id.recycler)
    RecyclerView recyclerView;
//    @BindView(R.id.fab_add)
//    FloatingActionButton fab;

    ListDonasiAdapter listDonasiAdapter;

    private FirebaseAuth mAuth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.donasi_fragment, container, false);
        ButterKnife.bind(this, view);

        ArrayList<String> animalNames = new ArrayList<>();
        animalNames.add("Bantu Bella agar dapat bersekolah lagi");
        animalNames.add("Bantu Alex mendapatkan buku \n" +
                "Untuk bacaan");
        animalNames.add("Bantu Bella agar dapat bersekolah lagi");
        animalNames.add("Bantu Bella agar dapat bersekolah lagi");
        animalNames.add("Bantu Bella agar dapat bersekolah lagi");
        animalNames.add("Bantu Bella agar dapat bersekolah lagi");
        animalNames.add("Bantu Bella agar dapat bersekolah lagi");

        listDonasiAdapter = new ListDonasiAdapter(getActivity(), animalNames);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(listDonasiAdapter);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getInstance().getCurrentUser();
//        if (user != null){
//            tambahKegiatan.setVisibility(View.VISIBLE);
//        }else{
//            tambahKegiatan.setVisibility(View.GONE);
//        }

        return view;
    }

//    @OnClick(R.id.tambahKegiatan)
//    void tambahKegiatan(){
//        Intent i = new Intent(getActivity(), BuatKegiatanActivity.class);
//        startActivity(i);
//    }
}

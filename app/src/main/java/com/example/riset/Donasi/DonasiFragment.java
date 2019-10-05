package com.example.riset.Donasi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.riset.Donasi.Adapter.ListDonasiAdapter;
import com.example.riset.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DonasiFragment  extends Fragment {

    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    ListDonasiAdapter listDonasiAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.donasi_fragment, container, false);
        ButterKnife.bind(this, view);

        ArrayList<String> animalNames = new ArrayList<>();
        animalNames.add("Bantu Bella agar dapat bersekolah \n" +
                "lagi untuk meraih mimpinya");
        animalNames.add("Bantu Alex mendapatkan buku \n" +
                "Untuk bacaan");
        animalNames.add("Bantu Bella agar dapat bersekolah \n" +
                "lagi untuk meraih mimpinya");
        animalNames.add("Bantu Bella agar dapat bersekolah \n" +
                "lagi untuk meraih mimpinya");
        animalNames.add("Bantu Bella agar dapat bersekolah \n" +
                "lagi untuk meraih mimpinya");

        listDonasiAdapter = new ListDonasiAdapter(getActivity(), animalNames);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(listDonasiAdapter);

        return view;
    }

}

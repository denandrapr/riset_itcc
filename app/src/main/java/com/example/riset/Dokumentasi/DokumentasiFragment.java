package com.example.riset.Dokumentasi;

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

import com.example.riset.Dokumentasi.Adapter.DokumentasiAdapter;
import com.example.riset.Home.Adapter.TerdekatKamuAdapter;
import com.example.riset.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DokumentasiFragment extends Fragment {

    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    DokumentasiAdapter dokumentasiAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dokumentasi_fragment, container, false);
        ButterKnife.bind(this, view);

        ArrayList<String> animalNames = new ArrayList<>();
        animalNames.add("Bantu Bella agar dapat bersekolah \n" +
                "lagi untuk meraih mimpinya");
        animalNames.add("Bantu Bella agar dapat bersekolah \n" +
                "lagi untuk meraih mimpinya");
        animalNames.add("Bantu Bella agar dapat bersekolah \n" +
                "lagi untuk meraih mimpinya");
        animalNames.add("Bantu Bella agar dapat bersekolah \n" +
                "lagi untuk meraih mimpinya");
        animalNames.add("Bantu Bella agar dapat bersekolah \n" +
                "lagi untuk meraih mimpinya");

        dokumentasiAdapter = new DokumentasiAdapter(getActivity(), animalNames);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(dokumentasiAdapter);

        return view;
    }

}

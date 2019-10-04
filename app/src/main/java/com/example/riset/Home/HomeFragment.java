package com.example.riset.Home;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.riset.Home.Adapter.ButuhSegeraAdapter;
import com.example.riset.Home.Adapter.JadiTutorMerekaAdapter;
import com.example.riset.Home.Adapter.TerdekatKamuAdapter;
import com.example.riset.MainActivity;
import com.example.riset.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends Fragment{


    @BindView(R.id.recycler)
    RecyclerView recyclerView;
    @BindView(R.id.recycler2)
    RecyclerView recyclerView2;
    @BindView(R.id.recycler3)
    RecyclerView recyclerView3;

    ButuhSegeraAdapter adapterButuhSegera;
    JadiTutorMerekaAdapter jadiTutorMerekaAdapter;
    TerdekatKamuAdapter terdekatKamuAdapter;

    private FirebaseAuth mAuth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);

        ButterKnife.bind(this, view);

        mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = mAuth.getInstance().getCurrentUser();
        if (user != null){
            String uid = user.getEmail();
        }

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

        ArrayList<String> duit = new ArrayList<>();
        duit.add("Rp 1.500.000");
        duit.add("Rp 1.500.000");
        duit.add("Rp 1.500.000");
        duit.add("Rp 1.500.000");
        duit.add("Rp 1.500.000");

        ArrayList<String> info = new ArrayList<>();
        info.add("terkumpul dari Rp 20.000.000");
        info.add("terkumpul dari Rp 20.000.000");
        info.add("terkumpul dari Rp 20.000.000");
        info.add("terkumpul dari Rp 20.000.000");
        info.add("terkumpul dari Rp 20.000.000");

        LinearLayoutManager horizontalLayout = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(horizontalLayout);
        adapterButuhSegera = new ButuhSegeraAdapter(getActivity(), animalNames, duit, info);
//        adapterButuhSegera.setClickListener(this);
        recyclerView.setAdapter(adapterButuhSegera);

        LinearLayoutManager horizontalLayout2 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView2.setLayoutManager(horizontalLayout2);
        jadiTutorMerekaAdapter = new JadiTutorMerekaAdapter(getActivity(), animalNames);
//        jadiTutorMerekaAdapter.setClickListener(this);
        recyclerView2.setAdapter(jadiTutorMerekaAdapter);

        terdekatKamuAdapter = new TerdekatKamuAdapter(getActivity(), animalNames);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView3.setItemAnimator(new DefaultItemAnimator());
        recyclerView3.setLayoutManager(mLayoutManager);
        recyclerView3.setAdapter(terdekatKamuAdapter);

        return view;
    }

}

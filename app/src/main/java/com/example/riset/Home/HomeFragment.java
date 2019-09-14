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

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends Fragment implements ButuhSegeraAdapter.ItemClickListener{


    @BindView(R.id.recycler)
    RecyclerView recyclerView;
    @BindView(R.id.recycler2)
    RecyclerView recyclerView2;
    @BindView(R.id.recycler3)
    RecyclerView recyclerView3;

    ButuhSegeraAdapter adapterButuhSegera;
    JadiTutorMerekaAdapter jadiTutorMerekaAdapter;
    TerdekatKamuAdapter terdekatKamuAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);

        ButterKnife.bind(this, view);

        ArrayList<String> animalNames = new ArrayList<>();
        animalNames.add("Horse");
        animalNames.add("Cow");
        animalNames.add("Camel");
        animalNames.add("Sheep");
        animalNames.add("Goat");

        LinearLayoutManager horizontalLayout = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(horizontalLayout);
        adapterButuhSegera = new ButuhSegeraAdapter(getActivity(), animalNames);
        adapterButuhSegera.setClickListener(this);
        recyclerView.setAdapter(adapterButuhSegera);

        LinearLayoutManager horizontalLayout2 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView2.setLayoutManager(horizontalLayout2);
        jadiTutorMerekaAdapter = new JadiTutorMerekaAdapter(getActivity(), animalNames);
        jadiTutorMerekaAdapter.setClickListener(this);
        recyclerView2.setAdapter(jadiTutorMerekaAdapter);

//        LinearLayoutManager horizontalLayout3 = new LinearLayoutManager(getActivity());
//        recyclerView3.setLayoutManager(horizontalLayout3);
//        terdekatKamuAdapter = new TerdekatKamuAdapter(getActivity(), animalNames);
//        recyclerView3.setAdapter(terdekatKamuAdapter);

        terdekatKamuAdapter = new TerdekatKamuAdapter(getActivity(), animalNames);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView3.setItemAnimator(new DefaultItemAnimator());
        recyclerView3.setLayoutManager(mLayoutManager);
        recyclerView3.setAdapter(terdekatKamuAdapter);

        return view;
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(getActivity(), "You clicked " + adapterButuhSegera.getItem(position) + " on item position " + position, Toast.LENGTH_SHORT).show();
    }
}

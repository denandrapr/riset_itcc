package com.example.riset.Donasi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.riset.Donasi.Adapter.ListDonasiAdapter;
import com.example.riset.Model.ButuhSegeraModel;
import com.example.riset.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DonasiFragment  extends Fragment {

    @BindView(R.id.recycler)
    RecyclerView recyclerView;
    @BindView(R.id.relativeProgress)
    RelativeLayout relativeLayout1;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.scroll)
    ScrollView scrollView;
    @BindView(R.id.btnFilter)
    Button btnFilter;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    ListDonasiAdapter listDonasiAdapter;
    private List<ButuhSegeraModel> listButuhSegera = new ArrayList<>();

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

        listDonasiAdapter = new ListDonasiAdapter(getActivity(), listButuhSegera);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(listDonasiAdapter);

        ambilTerdekatKamu();

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getInstance().getCurrentUser();
//        if (user != null){
//            tambahKegiatan.setVisibility(View.VISIBLE);
//        }else{
//            tambahKegiatan.setVisibility(View.GONE);
//        }

        return view;
    }

    private void ambilTerdekatKamu(){
        relativeLayout1.setVisibility(View.VISIBLE);
        toolbar.setVisibility(View.GONE);
        scrollView.setVisibility(View.GONE);
        btnFilter.setVisibility(View.GONE);
        db.collection("Posting")
                .limit(5)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for (QueryDocumentSnapshot doc : task.getResult()){
//                            Log.d("TAG ", "Hasil = > "+doc.getData());
                            Gson gson = new Gson();
                            JsonElement jsonElement =gson.toJsonTree(doc.getData());
                            listButuhSegera.add(gson.fromJson(jsonElement, ButuhSegeraModel.class));
                        }
                        listDonasiAdapter = new ListDonasiAdapter(getActivity(), listButuhSegera);
                        recyclerView.setAdapter(listDonasiAdapter);
                        relativeLayout1.setVisibility(View.GONE);
                        toolbar.setVisibility(View.VISIBLE);
                        scrollView.setVisibility(View.VISIBLE);
                        btnFilter.setVisibility(View.VISIBLE);
                    }
                });
    }
}

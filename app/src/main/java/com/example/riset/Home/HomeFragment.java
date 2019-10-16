package com.example.riset.Home;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
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

import com.example.riset.Berdonasi.Model.BerdonasiUangModel;
import com.example.riset.Home.Adapter.ButuhSegeraAdapter;
import com.example.riset.Home.Adapter.DonaturDetailDonasiAdapter;
import com.example.riset.Home.Adapter.JadiTutorMerekaAdapter;
import com.example.riset.Home.Adapter.TerdekatKamuAdapter;
import com.example.riset.Home.Model.ButuhSegeraModel;
import com.example.riset.MainActivity;
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

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth;

    private List<ButuhSegeraModel> listButuhSegera = new ArrayList<>();
    ButuhSegeraModel butuhSegeraModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);

        ButterKnife.bind(this, view);

        mAuth = FirebaseAuth.getInstance();

//        FirebaseUser user = mAuth.getInstance().getCurrentUser();
//        if (user != null){
//            String uid = user.getEmail();
//        }

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

        ArrayList<String> tutorMereka = new ArrayList<>();
        tutorMereka.add("Pengajar di rumah belajar Laksma Putra");
        tutorMereka.add("Pemateri pada kegiatan belajar Street childern surabaya");
        tutorMereka.add("Pengajar di rumah les bella");
        tutorMereka.add("Pengajar di rumah les bella");
        tutorMereka.add("Pengajar di rumah les bella");

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
        adapterButuhSegera = new ButuhSegeraAdapter(getActivity(), listButuhSegera);
        recyclerView.setAdapter(adapterButuhSegera);

        LinearLayoutManager horizontalLayout2 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView2.setLayoutManager(horizontalLayout2);
        jadiTutorMerekaAdapter = new JadiTutorMerekaAdapter(getActivity(), tutorMereka);
        recyclerView2.setAdapter(jadiTutorMerekaAdapter);

        terdekatKamuAdapter = new TerdekatKamuAdapter(getActivity(), animalNames);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView3.setItemAnimator(new DefaultItemAnimator());
        recyclerView3.setLayoutManager(mLayoutManager);
        recyclerView3.setAdapter(terdekatKamuAdapter);

        ambilButuhKamuSegera();

        return view;
    }

    private void ambilButuhKamuSegera() {
        db.collection("Posting")
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
                        adapterButuhSegera = new ButuhSegeraAdapter(getActivity(), listButuhSegera);
                        recyclerView.setAdapter(adapterButuhSegera);
                    }
                });
    }

}

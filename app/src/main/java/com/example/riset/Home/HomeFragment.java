package com.example.riset.Home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.riset.Home.Adapter.ButuhSegeraAdapter;
import com.example.riset.Home.Adapter.JadiTutorMerekaAdapter;
import com.example.riset.Home.Adapter.TerdekatKamuAdapter;
import com.example.riset.Model.ButuhSegeraModel;
import com.example.riset.Model.RuanganModel;
import com.example.riset.Model.UserModel;
import com.example.riset.Notifikasi.NotifikasiMainActivity;
import com.example.riset.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeFragment extends Fragment{


    @BindView(R.id.recycler)
    RecyclerView recyclerView;
    @BindView(R.id.recycler2)
    RecyclerView recyclerView2;
    @BindView(R.id.recycler3)
    RecyclerView recyclerView3;
    @BindView(R.id.scrollAll)
    ScrollView scrollView1;
    @BindView(R.id.relativeProgress)
    RelativeLayout relativeLayout1;
    @BindView(R.id.textView)
    TextView textHelo;

    ButuhSegeraAdapter adapterButuhSegera;
    JadiTutorMerekaAdapter jadiTutorMerekaAdapter;
    TerdekatKamuAdapter terdekatKamuAdapter;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth;
    FirebaseUser user = mAuth.getInstance().getCurrentUser();

    private List<ButuhSegeraModel> listButuhSegera = new ArrayList<>();
    private List<ButuhSegeraModel> listTerdekat = new ArrayList<>();
    private List<RuanganModel> ruanganModels = new ArrayList<>();
    ButuhSegeraModel butuhSegeraModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);

        ButterKnife.bind(this, view);

        mAuth = FirebaseAuth.getInstance();

        ArrayList<String> animalNames = new ArrayList<>();
        animalNames.add("Bantu Bella agar dapat bersekolah lagi");
        animalNames.add("Bantu Alex mendapatkan buku \n" +
                "Untuk bacaan");
        animalNames.add("Bantu Bella agar dapat bersekolah lagi");
        animalNames.add("Bantu Bella agar dapat bersekolah lagi");
        animalNames.add("Bantu Bella agar dapat bersekolah lagi");

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
        jadiTutorMerekaAdapter = new JadiTutorMerekaAdapter(getActivity(), ruanganModels);
        recyclerView2.setAdapter(jadiTutorMerekaAdapter);

        terdekatKamuAdapter = new TerdekatKamuAdapter(getActivity(), listTerdekat);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView3.setItemAnimator(new DefaultItemAnimator());
        recyclerView3.setLayoutManager(mLayoutManager);
        recyclerView3.setNestedScrollingEnabled(false);
        recyclerView3.setAdapter(terdekatKamuAdapter);

        ambilButuhKamuSegera();
        ambilTutorMereka();
        ambilTerdekatKamu();
        return view;
    }

    private void ambilButuhKamuSegera() {
        relativeLayout1.setVisibility(View.VISIBLE);
        scrollView1.setVisibility(View.GONE);
        if (user != null){
            db.collection("User")
                    .document(user.getEmail())
                    .get()
                    .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            if (documentSnapshot.exists()){
                                UserModel userModel = null;
                                userModel = documentSnapshot.toObject(UserModel.class);
                                textHelo.setText("Halo, "+userModel.getNama());
                            }else{
                                textHelo.setText("Halo");
                            }
                        }
                    });
        }
        db.collection("Posting")
                .limit(5)
                .orderBy("created_date", Query.Direction.ASCENDING)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for (QueryDocumentSnapshot doc : task.getResult()){
                            Gson gson = new Gson();
                            JsonElement jsonElement = gson.toJsonTree(doc.getData());
                            listButuhSegera.add(gson.fromJson(jsonElement, ButuhSegeraModel.class));
                        }
                        adapterButuhSegera = new ButuhSegeraAdapter(getActivity(), listButuhSegera);
                        recyclerView.setAdapter(adapterButuhSegera);
                        relativeLayout1.setVisibility(View.GONE);
                        scrollView1.setVisibility(View.VISIBLE);
                    }
                });
    }

    private void ambilTutorMereka(){
        db.collection("Posting")
                .limit(5)
                .whereEqualTo("tipe", 2)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for (QueryDocumentSnapshot doc : task.getResult()){
                            Gson gson = new Gson();
                            JsonElement jsonElement =gson.toJsonTree(doc.getData());
                            ruanganModels.add(gson.fromJson(jsonElement, RuanganModel.class));
                        }
                        jadiTutorMerekaAdapter = new JadiTutorMerekaAdapter(getActivity(), ruanganModels);
                        recyclerView2.setAdapter(jadiTutorMerekaAdapter);
                    }
                });
    }

    private void ambilTerdekatKamu(){
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
                            listTerdekat.add(gson.fromJson(jsonElement, ButuhSegeraModel.class));
                        }
                        terdekatKamuAdapter = new TerdekatKamuAdapter(getActivity(), listTerdekat);
                        recyclerView3.setAdapter(terdekatKamuAdapter);
                    }
                });
    }

    @OnClick(R.id.notif)
    void notifikasi(){
        Intent i = new Intent(getActivity(), NotifikasiMainActivity.class);
        startActivity(i);
    }
}
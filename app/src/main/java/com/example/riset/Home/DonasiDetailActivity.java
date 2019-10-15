package com.example.riset.Home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;
import android.widget.Button;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.riset.Berdonasi.Model.BerdonasiUangModel;
import com.example.riset.Donasi.BerdonasiStep1Activity;
import com.example.riset.Home.Adapter.DonaturDetailDonasiAdapter;
import com.example.riset.Home.Adapter.TerdekatKamuAdapter;
import com.example.riset.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
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

public class DonasiDetailActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    private List<BerdonasiUangModel> list = new ArrayList<>();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference donaturRef = db.collection("Posting")
            .document("0IwGCiXsMiTPYTdPsmq6")
            .collection("berdonasi");

    DonaturDetailDonasiAdapter donaturDetailDonasiAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donasi_detail);
        ButterKnife.bind(this);

        ArrayList<String> tutorMereka = new ArrayList<>();
        tutorMereka.add("Anonim");
        tutorMereka.add("Anonim");
        tutorMereka.add("Adi W");
        tutorMereka.add("Anonim");
        tutorMereka.add("Bella");

        setSupportActionBar(toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        donaturDetailDonasiAdapter = new DonaturDetailDonasiAdapter(this, list);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(donaturDetailDonasiAdapter);

        get_data_donatur();
    }

    private void get_data_donatur(){

        db.collection("Posting")
                .document("0IwGCiXsMiTPYTdPsmq6")
                .collection("berdonasi")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for (QueryDocumentSnapshot doc : task.getResult()){
//                            Log.d("TAG ", "Hasil = > "+doc.getData());
                            Gson gson = new Gson();
                            JsonElement jsonElement =gson.toJsonTree(doc.getData());
                            list.add(gson.fromJson(jsonElement, BerdonasiUangModel.class));
                        }
                        donaturDetailDonasiAdapter = new DonaturDetailDonasiAdapter(DonasiDetailActivity.this, list);
                        recyclerView.setAdapter(donaturDetailDonasiAdapter);
                    }
                });

//        Query q = donaturRef;
//
//        FirestoreRecyclerOptions<BerdonasiUangModel> options = new FirestoreRecyclerOptions.Builder<BerdonasiUangModel>()
//                .setQuery(q, BerdonasiUangModel.class)
//                .build();
//
//        donaturDetailDonasiAdapter = new DonaturDetailDonasiAdapter(options);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(donaturDetailDonasiAdapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @OnClick(R.id.btn_donasi)
    void btn_donasi_action(){
        Intent i = new Intent(DonasiDetailActivity.this, BerdonasiStep1Activity.class);
        startActivity(i);
    }
}

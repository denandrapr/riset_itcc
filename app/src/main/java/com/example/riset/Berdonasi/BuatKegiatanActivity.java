package com.example.riset.Berdonasi;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.riset.Profile.ProfileEditActivity;
import com.example.riset.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BuatKegiatanActivity extends Fragment {

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    AlertDialog.Builder alertDialog;
    LayoutInflater inflater;
    View dialogView;
    Button lengkapiData;
    int diKlik = 0;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_buat_kegiatan, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @OnClick(R.id.donasiRuangan)
    void donasiRuangan(){
        diKlik = 2;
        if (user != null){
            checkUser();
        }
    }

    @OnClick(R.id.donasi_barang)
    void donasiBarang(){
        diKlik = 3;
        if (user != null){
            checkUser();
        }
    }

    @OnClick(R.id.galang_dana)
    void galangDana(){
        diKlik = 1;
        if (user != null){
            checkUser();
        }
    }

    private void checkUser(){
        String email = user.getEmail();

        db.collection("User")
                .document(email)
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()){
                            if (diKlik == 1){
                                Intent i = new Intent(getActivity(), GalangDanaStep0Activity.class);
                                startActivity(i);
                            }else if(diKlik == 2){
                                Intent i = new Intent(getActivity(), DonasiRuanganStep0Activity.class);
                                startActivity(i);
                            }else if(diKlik == 3){
                                Intent i = new Intent(getActivity(), DonasiBarangStep0Activity.class);
                                startActivity(i);
                            }
                        }else{
                            dialogForm();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        dialogForm();
                    }
                });
    }

    private void dialogForm() {
        alertDialog = new AlertDialog.Builder(getActivity());
        inflater = getLayoutInflater();
        dialogView = inflater.inflate(R.layout.dialog_peringatan_belum_lengkap, null);
        alertDialog.setView(dialogView);
        alertDialog.setCancelable(true);

        lengkapiData = dialogView.findViewById(R.id.lengkapiDataBtn);
        final AlertDialog alertDialoga = alertDialog.create();

        lengkapiData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialoga.dismiss();
//                Toast.makeText(getActivity(), "Clicked", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getActivity(), ProfileEditActivity.class);
                startActivity(i);
            }
        });
        alertDialoga.show();
    }
}

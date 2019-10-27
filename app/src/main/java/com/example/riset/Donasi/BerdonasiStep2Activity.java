package com.example.riset.Donasi;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.riset.R;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.FoldingCube;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BerdonasiStep2Activity extends AppCompatActivity {

    private static final int GALLERY_INTENT = 1;

    private SharedPreferences mSettings;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    StorageReference mStoreRef = FirebaseStorage.getInstance().getReference();
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    Uri photoURI;
    Uri downloadUrl;
    ProgressDialog progressDialog;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.txtNominal)
    TextView textNominal;
    @BindView(R.id.bca_logo)
    ImageView bca_logo;
    @BindView(R.id.bri_logo)
    ImageView bri_logo;
    @BindView(R.id.mandiri_logo)
    ImageView mandiri_logo;
    @BindView(R.id.bni_logo)
    ImageView bni_logo;
    @BindView(R.id.cimb_logo)
    ImageView cimb_logo;
    @BindView(R.id.permata_logo)
    ImageView permata_logo;
    @BindView(R.id.maybank_logo)
    ImageView maybank_logo;
    @BindView(R.id.mega_logo)
    ImageView mega_logo;

    @BindView(R.id.spin_kit)
    ProgressBar progressBar;
    @BindView(R.id.relative2)
    RelativeLayout relativeLayout;
    @BindView(R.id.btn_selesai)
    Button btn_selesai;
    @BindView(R.id.imageUpload)
    ImageView imgSelected;
    @BindView(R.id.noRek)
    TextView noRek;

    String get_nominal_donasi = "";
    String get_keterangan = "";
    String metode = "qweqweqwe";
    int nominal = 0;
    String idDonasi = "";
    String check = "";
    String email = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_berdonasi_step2);
        ButterKnife.bind(this);

        relativeLayout.setVisibility(View.GONE);
        Sprite foldingCube = new FoldingCube();
        progressBar.setIndeterminateDrawable(foldingCube);
        progressBar.setVisibility(View.GONE);

        mSettings = BerdonasiStep2Activity.this.getSharedPreferences("Settings", Context.MODE_PRIVATE);

        get_nominal_donasi = mSettings.getString("nominal_donasi", "missing");
        get_keterangan = mSettings.getString("keterangan", "missing");
        idDonasi = mSettings.getString("idDetailDonasi", "missing");
        check = mSettings.getString("anonim", "missing");

        nominal = Integer.parseInt(get_nominal_donasi.replace(",", ""));
        get_bank_data();
        Log.d("bank", metode);

        textNominal.setText("Rp "+get_nominal_donasi);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void get_bank_data(){
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("mengambil data..");
        progressDialog.setCancelable(false);
        progressDialog.show();
        db.collection("Posting")
                .document(idDonasi)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()){
                            progressDialog.dismiss();
//                            Log.d("Bank", task.getResult().getString("bankPilihan"));
                            noRek.setText(task.getResult().getString("noRek"));
                            metode = task.getResult().getString("bankPilihan");
                            bankSetVisibility();
                        }
                    }
                });
    }

    private void bankSetVisibility(){
        if (metode.equals("BCA")){
            bca_logo.setVisibility(View.VISIBLE);
            bri_logo.setVisibility(View.GONE);
            bni_logo.setVisibility(View.GONE);
            mandiri_logo.setVisibility(View.GONE);
            permata_logo.setVisibility(View.GONE);
            cimb_logo.setVisibility(View.GONE);
            maybank_logo.setVisibility(View.GONE);
            mega_logo.setVisibility(View.GONE);
        }else if (metode.equals("BRI")){
            bca_logo.setVisibility(View.GONE);
            bri_logo.setVisibility(View.VISIBLE);
            bni_logo.setVisibility(View.GONE);
            mandiri_logo.setVisibility(View.GONE);
            permata_logo.setVisibility(View.GONE);
            cimb_logo.setVisibility(View.GONE);
            maybank_logo.setVisibility(View.GONE);
            mega_logo.setVisibility(View.GONE);
        }else if (metode.equals("Mandiri")){
            bca_logo.setVisibility(View.GONE);
            bri_logo.setVisibility(View.GONE);
            bni_logo.setVisibility(View.GONE);
            mandiri_logo.setVisibility(View.VISIBLE);
            permata_logo.setVisibility(View.GONE);
            cimb_logo.setVisibility(View.GONE);
            maybank_logo.setVisibility(View.GONE);
            mega_logo.setVisibility(View.GONE);
        }else if (metode.equals("BNI")){
            bca_logo.setVisibility(View.GONE);
            bri_logo.setVisibility(View.GONE);
            bni_logo.setVisibility(View.VISIBLE);
            mandiri_logo.setVisibility(View.GONE);
            permata_logo.setVisibility(View.GONE);
            cimb_logo.setVisibility(View.GONE);
            maybank_logo.setVisibility(View.GONE);
            mega_logo.setVisibility(View.GONE);
        }else if (metode.equals("CIMB Niaga")) {
            bca_logo.setVisibility(View.GONE);
            bri_logo.setVisibility(View.GONE);
            bni_logo.setVisibility(View.GONE);
            mandiri_logo.setVisibility(View.GONE);
            permata_logo.setVisibility(View.GONE);
            cimb_logo.setVisibility(View.VISIBLE);
            maybank_logo.setVisibility(View.GONE);
            mega_logo.setVisibility(View.GONE);
        }else if (metode.equals("Permata Bank")) {
            bca_logo.setVisibility(View.GONE);
            bri_logo.setVisibility(View.GONE);
            bni_logo.setVisibility(View.GONE);
            mandiri_logo.setVisibility(View.GONE);
            permata_logo.setVisibility(View.VISIBLE);
            cimb_logo.setVisibility(View.GONE);
            maybank_logo.setVisibility(View.GONE);
            mega_logo.setVisibility(View.GONE);
        }else if (metode.equals("Maybank")) {
            bca_logo.setVisibility(View.GONE);
            bri_logo.setVisibility(View.GONE);
            bni_logo.setVisibility(View.GONE);
            mandiri_logo.setVisibility(View.GONE);
            permata_logo.setVisibility(View.GONE);
            cimb_logo.setVisibility(View.GONE);
            maybank_logo.setVisibility(View.VISIBLE);
            mega_logo.setVisibility(View.GONE);
        }else if (metode.equals("Bank Mega")) {
            bca_logo.setVisibility(View.GONE);
            bri_logo.setVisibility(View.GONE);
            bni_logo.setVisibility(View.GONE);
            mandiri_logo.setVisibility(View.GONE);
            permata_logo.setVisibility(View.GONE);
            cimb_logo.setVisibility(View.GONE);
            maybank_logo.setVisibility(View.GONE);
            mega_logo.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @OnClick(R.id.imageUpload)
    void selectImage(){
        Intent i = new Intent(Intent.ACTION_PICK);
        i.setType("image/*");
        startActivityForResult(i, GALLERY_INTENT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == GALLERY_INTENT && resultCode == RESULT_OK && data != null && data.getData() != null){
            photoURI = data.getData();
            Log.d("TAG", photoURI.toString());
            Glide.with(this)
                    .load(photoURI)
                    .centerCrop()
                    .into(imgSelected);
        }
    }

    @OnClick(R.id.btn_selesai)
    void selesai(){
        if (photoURI != null){
            toolbar.setVisibility(View.GONE);
            btn_selesai.setVisibility(View.GONE);
            relativeLayout.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.VISIBLE);

            StorageReference ref = mStoreRef.child("foto_bukti/"+idDonasi+"/"+System.currentTimeMillis());
            UploadTask uploadTask = ref.putFile(photoURI);
            Task<Uri> uriTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()){
                        throw task.getException();
                    }
                    return ref.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()){
                        downloadUrl = task.getResult();
                        dataDonasi();
                    }else{
                        progressDialog.dismiss();
                        Toast.makeText(BerdonasiStep2Activity.this, "Gagal", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else{
            Toast.makeText(this, "Sertakan bukti transfer!", Toast.LENGTH_SHORT).show();
        }
    }

    private void dataDonasi(){
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy HH:mm");
        String strDate = formatter.format(date);

        Boolean checkAnonim = Boolean.valueOf(check);
        if (user != null){
            email = user.getEmail();
        }

        Map<String, Object> updates = new HashMap<>();
        updates.put("keterangan", get_keterangan);
        updates.put("nominal", nominal);
        updates.put("anonim", checkAnonim);
        updates.put("tanggal", strDate);
        updates.put("email_donatur", email);
        updates.put("buktiTransfer", downloadUrl.toString());
        updates.put("created_date", FieldValue.serverTimestamp());
        updates.put("id_donasi", "d"+System.currentTimeMillis());

        db.collection("Posting")
                .document(idDonasi)
                .collection("berdonasi")
                .document("d"+System.currentTimeMillis())
                .set(updates)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        progressBar.setVisibility(View.GONE);
                        Intent i = new Intent(BerdonasiStep2Activity.this, BerdonasiStep3Activity.class);
                        startActivity(i);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(BerdonasiStep2Activity.this, "Gagal...", Toast.LENGTH_SHORT).show();
                    }
                });

    }
}

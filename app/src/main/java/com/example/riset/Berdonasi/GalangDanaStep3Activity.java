package com.example.riset.Berdonasi;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.riset.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GalangDanaStep3Activity extends AppCompatActivity {

    //initialized code gallery
    private static final int GALLERY_INTENT = 1;

    //initialized firebase class
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    StorageReference mStoreRef = FirebaseStorage.getInstance().getReference();
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    Uri photoURI;
    ProgressDialog progressDialog;
    Uri downloadUrl;

    @BindView(R.id.pilihFoto)
    ImageView fotoPilihan;
    @BindView(R.id.btnSelesai)
    Button button_selesai;
    @BindView(R.id.textDeskripsi)
    TextView mdeskripsi;

    String targetNominalDonasi;
    String batasWaktu;
    String noRek;
    String targetPenerima;
    String namaPenerimaDonasi;
    String judulKegiatan;
    String bankPilihan;

    Date date1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galang_dana_step3);
        ButterKnife.bind(this);

        Intent i = getIntent();
        targetNominalDonasi = i.getStringExtra("targetNominalDonasi");
        batasWaktu = i.getStringExtra("batasWaktu");
        noRek = i.getStringExtra("noRek");
        targetPenerima = i.getStringExtra("targetPenerima");
        namaPenerimaDonasi =  i.getStringExtra("namaPenerimaDonasi");
        judulKegiatan = i.getStringExtra("judulKegiatan");
        bankPilihan = i.getStringExtra("bankPilihan");

//        Log.d("TAG", "Last activity result => "+targetNominalDonasi+" "+batasWaktu+" "+
//                noRek+" "+targetPenerima+" "+namaPenerimaDonasi+" "+judulKegiatan+" "+bankPilihan);
    }

    @OnClick(R.id.pilihFoto)
    void pilihFoto(){
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
                .into(fotoPilihan);
        }
    }
    @OnClick (R.id.btnSelesai)
    void selanjutnya(){

        if (mdeskripsi.getText().toString().equals("") && photoURI != null){
            Toast.makeText(this, "Tidak boleh ada field kosong!", Toast.LENGTH_SHORT).show();
        }else {
            uploadData();
        }
    }

    private void uploadData() {

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Prosessing...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        if (photoURI != null){
            StorageReference ref = mStoreRef.child("foto_post/"+System.currentTimeMillis());
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
                        dataUpload();
                    }else{
                        progressDialog.dismiss();
                        Toast.makeText(GalangDanaStep3Activity.this, "Gagal", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void dataUpload() {
//        SimpleDateFormat dates = new SimpleDateFormat("dd-MM-yyyy");
//
//        try{
//            date1 = dates.parse(batasWaktu);
//        }catch (Exception e){
//
//        }
        String get_email = "";
        if (user != null){
            get_email = user.getEmail();
        }

        String idGenerator = "u"+System.currentTimeMillis();
        Map<String, Object> updates = new HashMap<>();
        updates.put("id", idGenerator);
        updates.put("targetNominalDonasi", targetNominalDonasi);
        updates.put("batasWaktu", batasWaktu);
        updates.put("noRek", noRek);
        updates.put("targetPenerima", targetPenerima);
        updates.put("namaPenerimaDonasi", namaPenerimaDonasi);
        updates.put("judulKegiatan", judulKegiatan);
        updates.put("bankPilihan", bankPilihan);
        updates.put("linkFotoUtama", downloadUrl.toString());
        updates.put("created_date", FieldValue.serverTimestamp());
        updates.put("deskripsi", mdeskripsi.getText().toString());
        updates.put("created_by", get_email);
        updates.put("tipe", 1);

        db.collection("Posting")
                .document(idGenerator)
                .set(updates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            progressDialog.dismiss();
                            Intent i = new Intent(GalangDanaStep3Activity.this, GalangDanaStep4Activity.class);
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(i);
                        }else{
                            progressDialog.dismiss();
                            Toast.makeText(GalangDanaStep3Activity.this, "Gagal!", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(GalangDanaStep3Activity.this, "Gagal!", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}

package com.example.riset.Berdonasi;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.riset.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DonasiRuanganStep2Activity extends AppCompatActivity {

    private static final int GALLERY_INTENT = 1;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    StorageReference mStoreRef = FirebaseStorage.getInstance().getReference();

    ProgressDialog progressDialog;
    Uri photoURI;
    String judulKegiatan;
    String batasWaktu;
    String jadwalKegiatan;
    String keahliahRelawan;
    String lokasiRuangan;
    String kapasitasRuangan;
    String dekripsi;

    @BindView(R.id.imgRuangan)
    ImageView imgRuangan;
    @BindView(R.id.keahlianRelawan)
    EditText txtkeahlianRelawan;
    @BindView(R.id.lokasiRuangan)
    EditText txtLokasiRuangan;
    @BindView(R.id.kapasitasRuangan)
    EditText txtkapasitasRuangan;
    @BindView(R.id.deskripsi)
    EditText txtdeskripsi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donasi_ruangan_step2);
        ButterKnife.bind(this);

        Intent i = getIntent();
        jadwalKegiatan = i.getStringExtra("jadwalKegiatan");
        batasWaktu = i.getStringExtra("batasWaktu");
        judulKegiatan = i.getStringExtra("judulKegiatan");
    }

    @OnClick(R.id.imgRuangan)
    void imgRuangan(){
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
                    .into(imgRuangan);
        }
    }

    @OnClick(R.id.btnSelesai)
    void btnSelesai(){
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
                        photoURI = task.getResult();
                        uploadData();
                    }else{
                        progressDialog.dismiss();
                        Toast.makeText(DonasiRuanganStep2Activity.this, "Gagal", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void uploadData(){
        keahliahRelawan = txtkeahlianRelawan.getText().toString();
        lokasiRuangan = txtLokasiRuangan.getText().toString();
        kapasitasRuangan = txtkapasitasRuangan.getText().toString();
        dekripsi = txtdeskripsi.getText().toString();
        String idGenerator = "r"+System.currentTimeMillis();

        Map<String, Object> updates = new HashMap<>();
        updates.put("id", idGenerator);
        updates.put("judulKegiatan", judulKegiatan);
        updates.put("batasWaktu", batasWaktu);
        updates.put("jadwalKegiatan", jadwalKegiatan);
        updates.put("keahliahRelawan", keahliahRelawan);
        updates.put("lokasiRuangan", lokasiRuangan);
        updates.put("kapasitasRuangan", kapasitasRuangan);
        updates.put("deskripsi", dekripsi);
        updates.put("linkFotoUtama", photoURI.toString());
        updates.put("created_date", FieldValue.serverTimestamp());
        updates.put("tipe", 2);

        db.collection("Posting")
                .document(idGenerator)
                .set(updates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            progressDialog.dismiss();
                            Intent i = new Intent(DonasiRuanganStep2Activity.this, DonasiRuanganStep3Activity.class);
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(i);
                        }else{
                            progressDialog.dismiss();
                            Toast.makeText(DonasiRuanganStep2Activity.this, "Gagal", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(DonasiRuanganStep2Activity.this, "Gagal Cok", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}

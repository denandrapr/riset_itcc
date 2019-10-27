package com.example.riset.Profile;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.riset.Model.UserModel;
import com.example.riset.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProfileEditActivity extends AppCompatActivity {

    private static final int GALLERY_INTENT = 1;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ProgressDialog progressDialog;

    Uri photoURI;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.txtNamaLengkap)
    EditText textNamaLengkap;
    @BindView(R.id.txtEmail)
    EditText textEmail;
    @BindView(R.id.txtNomorTelepon)
    EditText textNomorTelepon;
    @BindView(R.id.imgUpload)
    ImageView imageUpload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (user != null){
            textEmail.setText(user.getEmail());
            textEmail.setKeyListener(null);
        }

        getProfileData();
    }

    @OnClick(R.id.imgUpload)
    void uploadImagele(){
        Intent i = new Intent(Intent.ACTION_PICK);
        i.setType("image/*");
        startActivityForResult(i, GALLERY_INTENT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == GALLERY_INTENT && resultCode == RESULT_OK && data != null && data.getData() != null){
            photoURI = data.getData();

            Glide.with(this)
                    .load(photoURI)
                    .centerCrop()
                    .circleCrop()
                    .into(imageUpload);

        }
    }

    private void getProfileData(){
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Proses...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        db.collection("User")
                .document(textEmail.getText().toString())
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        progressDialog.dismiss();
                        UserModel userModel = null;
                        userModel = documentSnapshot.toObject(UserModel.class);
                        textNamaLengkap.setText(userModel.getNama());
                        textNomorTelepon.setText(userModel.getNomorTelepon());
                    }
                });
    }

    @OnClick(R.id.simpan)
    void simpan(){
        progressDialog = new ProgressDialog(ProfileEditActivity.this);
        progressDialog.setMessage("Proses...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        String nama_lengkap = textNamaLengkap.getText().toString();
        String nomor_telepon = textNomorTelepon.getText().toString();
        String email = textEmail.getText().toString();

        Map<String, Object> updates = new HashMap<>();
        updates.put("email", email);
        updates.put("nama", nama_lengkap);
        updates.put("nomorTelepon", nomor_telepon);
        updates.put("registered_date", FieldValue.serverTimestamp());

        db.collection("User")
                .document(email)
                .set(updates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            progressDialog.dismiss();
                            Toast.makeText(ProfileEditActivity.this, "Sukses update data", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public boolean onNavigateUp() {
        onBackPressed();
        return true;
    }
}

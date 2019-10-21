package com.example.riset.Berdonasi;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GalangDanaStep3Activity extends AppCompatActivity {

    private static final int GALLERY_INTENT = 1;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String mCurrentPhotoPath;
    String imageUrl;
    Uri photoURI;

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

        Log.d("TAG", "Last activity result => "+targetNominalDonasi+" "+batasWaktu+" "+
                noRek+" "+targetPenerima+" "+namaPenerimaDonasi+" "+judulKegiatan+" "+bankPilihan);
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

        if (mdeskripsi.getText().toString().equals("") ){
            Toast.makeText(this, "Tidak boleh ada field kosong!", Toast.LENGTH_SHORT).show();
        }else {
            uploadData();
        }
    }

    private void uploadData() {
        db.collection("Posting")
                .document()
                
    }


}

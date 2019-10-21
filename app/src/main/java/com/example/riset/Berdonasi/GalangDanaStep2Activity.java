package com.example.riset.Berdonasi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.riset.Donasi.BerdonasiStep2Activity;
import com.example.riset.Donasi.MetodePembayaranActivity;
import com.example.riset.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GalangDanaStep2Activity extends AppCompatActivity {

    @BindView(R.id.btn_batas_waktu)
    Button mBtnBatasWaktu;
    @BindView(R.id.txtTargetDonasi)
    EditText mTargetDonasi;
    @BindView(R.id.btn_pilih_bank)
    Button btnPilihBank;
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
    @BindView(R.id.cardView)
    CardView cardView;
    @BindView(R.id.metodenya)
    TextView metodenya;
    @BindView(R.id.no_rek)
    TextView no_rek1;
    String namaMetode = "";
    String metode = "";

    private DatePickerDialog datePickerDialog;
    private SharedPreferences mSettings;
    String targetPenerima;
    String penerima;
    String targetBantuan;
    String judulKegiatan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galang_dana_step2);
        ButterKnife.bind(this);

        Intent i = getIntent();
        targetPenerima = i.getStringExtra("targetPenerima");
        penerima = i.getStringExtra("namaPenerimaDonasi");
        judulKegiatan = i.getStringExtra("judulKegiatan");

//        cardView.setVisibility(View.GONE);

        mTargetDonasi.addTextChangedListener(onTextChangedListener());
        mSettings = GalangDanaStep2Activity.this.getSharedPreferences("Settings", Context.MODE_PRIVATE);
    }

    @OnClick(R.id.btn_selanjutnya)
    void selanjutnya(){
        Intent i = new Intent(GalangDanaStep2Activity.this, GalangDanaStep3Activity.class);
        if (mTargetDonasi.getText().toString().equals("")
                || mBtnBatasWaktu.getText().toString().equals("")
                || no_rek1.getText().toString().equals("")){
            Toast.makeText(this, "Tidak boleh ada field kosong!", Toast.LENGTH_SHORT).show();
        }else {
            i.putExtra("targetNominalDonasi", mTargetDonasi.getText().toString());
            i.putExtra("batasWaktu", mBtnBatasWaktu.getText().toString());
            i.putExtra("noRek", no_rek1.getText().toString());
            i.putExtra("targetPenerima",targetPenerima);
            i.putExtra("namaPenerimaDonasi", penerima);
            i.putExtra("judulKegiatan", judulKegiatan);
            i.putExtra("bankPilihan", metode);
            startActivity(i);
        }
    }

    @OnClick(R.id.btn_batas_waktu)
    void BatasWaktu(){
        Calendar newCalendar = Calendar.getInstance();

        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(i,i1, i2);
                mBtnBatasWaktu.setText(i2+"-"+i1+"-"+i);
            }
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();
    }

    private TextWatcher onTextChangedListener() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mTargetDonasi.removeTextChangedListener(this);

                try {
                    String originalString = s.toString();

                    Long longval;
                    if (originalString.contains(",")) {
                        originalString = originalString.replaceAll(",", "");
                    }
                    longval = Long.parseLong(originalString);

                    DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
                    formatter.applyPattern("#,###,###,###");
                    String formattedString = formatter.format(longval);

                    //setting text after format to EditText
                    mTargetDonasi.setText(formattedString);
                    mTargetDonasi.setSelection(mTargetDonasi.getText().length());
                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                }

                mTargetDonasi.addTextChangedListener(this);
            }
        };
    }

    @OnClick(R.id.btn_pilih_bank)
    void pilihBank(){
        Intent i = new Intent(GalangDanaStep2Activity.this, MetodePembayaranActivity.class);
        startActivity(i);
    }

    @Override
    protected void onResume() {
        super.onResume();
        metode = mSettings.getString("pilihMetode", "missing");
//        Log.d("TAG", "Metode => "+metode);
        if (metode.equals("BCA")){
            metodenya.setText(metode);
            btnPilihBank.setVisibility(View.GONE);
            bca_logo.setVisibility(View.VISIBLE);
            bri_logo.setVisibility(View.GONE);
            bni_logo.setVisibility(View.GONE);
            mandiri_logo.setVisibility(View.GONE);
            permata_logo.setVisibility(View.GONE);
            cimb_logo.setVisibility(View.GONE);
            maybank_logo.setVisibility(View.GONE);
            mega_logo.setVisibility(View.GONE);
        }else if (metode.equals("BRI")){
            metodenya.setText(metode);
            btnPilihBank.setVisibility(View.GONE);
            bca_logo.setVisibility(View.GONE);
            bri_logo.setVisibility(View.VISIBLE);
            bni_logo.setVisibility(View.GONE);
            mandiri_logo.setVisibility(View.GONE);
            permata_logo.setVisibility(View.GONE);
            cimb_logo.setVisibility(View.GONE);
            maybank_logo.setVisibility(View.GONE);
            mega_logo.setVisibility(View.GONE);
        }else if (metode.equals("Mandiri")){
            metodenya.setText(metode);
            btnPilihBank.setVisibility(View.GONE);
            bca_logo.setVisibility(View.GONE);
            bri_logo.setVisibility(View.GONE);
            bni_logo.setVisibility(View.GONE);
            mandiri_logo.setVisibility(View.VISIBLE);
            permata_logo.setVisibility(View.GONE);
            cimb_logo.setVisibility(View.GONE);
            maybank_logo.setVisibility(View.GONE);
            mega_logo.setVisibility(View.GONE);
        }else if (metode.equals("BNI")){
            metodenya.setText(metode);
            btnPilihBank.setVisibility(View.GONE);
            bca_logo.setVisibility(View.GONE);
            bri_logo.setVisibility(View.GONE);
            bni_logo.setVisibility(View.VISIBLE);
            mandiri_logo.setVisibility(View.GONE);
            permata_logo.setVisibility(View.GONE);
            cimb_logo.setVisibility(View.GONE);
            maybank_logo.setVisibility(View.GONE);
            mega_logo.setVisibility(View.GONE);
        }else if (metode.equals("CIMB Niaga")) {
            metodenya.setText(metode);
            btnPilihBank.setVisibility(View.GONE);
            bca_logo.setVisibility(View.GONE);
            bri_logo.setVisibility(View.GONE);
            bni_logo.setVisibility(View.GONE);
            mandiri_logo.setVisibility(View.GONE);
            permata_logo.setVisibility(View.GONE);
            cimb_logo.setVisibility(View.VISIBLE);
            maybank_logo.setVisibility(View.GONE);
            mega_logo.setVisibility(View.GONE);
        }else if (metode.equals("Permata Bank")) {
            metodenya.setText(metode);
            btnPilihBank.setVisibility(View.GONE);
            bca_logo.setVisibility(View.GONE);
            bri_logo.setVisibility(View.GONE);
            bni_logo.setVisibility(View.GONE);
            mandiri_logo.setVisibility(View.GONE);
            permata_logo.setVisibility(View.VISIBLE);
            cimb_logo.setVisibility(View.GONE);
            maybank_logo.setVisibility(View.GONE);
            mega_logo.setVisibility(View.GONE);
        }else if (metode.equals("Maybank")) {
            metodenya.setText(metode);
            btnPilihBank.setVisibility(View.GONE);
            bca_logo.setVisibility(View.GONE);
            bri_logo.setVisibility(View.GONE);
            bni_logo.setVisibility(View.GONE);
            mandiri_logo.setVisibility(View.GONE);
            permata_logo.setVisibility(View.GONE);
            cimb_logo.setVisibility(View.GONE);
            maybank_logo.setVisibility(View.VISIBLE);
            mega_logo.setVisibility(View.GONE);
        }else if (metode.equals("Bank Mega")) {
            metodenya.setText(metode);
            btnPilihBank.setVisibility(View.GONE);
            bca_logo.setVisibility(View.GONE);
            bri_logo.setVisibility(View.GONE);
            bni_logo.setVisibility(View.GONE);
            mandiri_logo.setVisibility(View.GONE);
            permata_logo.setVisibility(View.GONE);
            cimb_logo.setVisibility(View.GONE);
            maybank_logo.setVisibility(View.GONE);
            mega_logo.setVisibility(View.VISIBLE);
        }else{
            btnPilihBank.setVisibility(View.VISIBLE);
            cardView.setVisibility(View.GONE);
        }
    }

    @OnClick(R.id.cardView)
    void cardView(){
        Intent i = new Intent(GalangDanaStep2Activity.this, MetodePembayaranActivity.class);
        startActivity(i);
    }
}

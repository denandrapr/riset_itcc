package com.example.riset.Donasi;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.riset.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BerdonasiStep1Activity extends AppCompatActivity {

    private Dialog customDialog;
    private SharedPreferences mSettings;
//    @BindView(R.id.pilihMetodeBayar)
//    Button pilihMetodeBayar;
//    @BindView(R.id.cardView)
//    CardView cardView;
    @BindView(R.id.txtNominal)
    EditText txtNominal;
    @BindView(R.id.keterangan)
    EditText txtKeterangan;
//    @BindView(R.id.metodenya)
//    TextView metodenya;
//    @BindView(R.id.bca_logo)
//    ImageView bca_logo;
//    @BindView(R.id.bri_logo)
//    ImageView bri_logo;
//    @BindView(R.id.mandiri_logo)
//    ImageView mandiri_logo;
//    @BindView(R.id.alfa_logo)
//    ImageView alfa_logo;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    String deskripsi = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_berdonasi_step1);
        ButterKnife.bind(this);
        mSettings = BerdonasiStep1Activity.this.getSharedPreferences("Settings", Context.MODE_PRIVATE);

        txtNominal.addTextChangedListener(onTextChangedListener());
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        onResumeAction();
    }

    private void onResumeAction(){
//        String getSharedPreferences = mSettings.getString("pilihMetode", "missing");
//        if (getSharedPreferences.equals("missing")){
//            cardView.setVisibility(View.GONE);
////            pilihMetodeBayar.setVisibility(View.VISIBLE);
//        }else{
//            if (getSharedPreferences.equals("Alfamart")){
//                metodenya.setText(getSharedPreferences);
//                alfa_logo.setVisibility(View.VISIBLE);
//                bca_logo.setVisibility(View.GONE);
//                bri_logo.setVisibility(View.GONE);
//                mandiri_logo.setVisibility(View.GONE);
//            }else if (getSharedPreferences.equals("BCA")){
//                alfa_logo.setVisibility(View.GONE);
//                bca_logo.setVisibility(View.VISIBLE);
//                bri_logo.setVisibility(View.GONE);
//                mandiri_logo.setVisibility(View.GONE);
//                metodenya.setText("Bank "+getSharedPreferences);
//            }else if (getSharedPreferences.equals("BRI")){
//                alfa_logo.setVisibility(View.GONE);
//                bca_logo.setVisibility(View.GONE);
//                bri_logo.setVisibility(View.VISIBLE);
//                mandiri_logo.setVisibility(View.GONE);
//                metodenya.setText("Bank "+getSharedPreferences);
//            }else if (getSharedPreferences.equals("Mandiri")){
//                alfa_logo.setVisibility(View.GONE);
//                bca_logo.setVisibility(View.GONE);
//                bri_logo.setVisibility(View.GONE);
//                mandiri_logo.setVisibility(View.VISIBLE);
//                metodenya.setText("Bank "+getSharedPreferences);
//            }
////            pilihMetodeBayar.setVisibility(View.GONE);
//            cardView.setVisibility(View.VISIBLE);
//        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    private void initViews(){
        initCustomDialog();
        initViewsComponent();
    }

    private void initCustomDialog(){
        customDialog = new Dialog(BerdonasiStep1Activity.this);
        customDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        customDialog.setContentView(R.layout.dialog_custom);
        customDialog.setCancelable(true);

//        pilihMetodeBayar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                customDialog.dismiss();
//            }
//        });
    }

    private void initViewsComponent(){
//        pilihMetodeBayar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                customDialog.show();
//            }
//        });
    }

    @OnClick(R.id.lanjutkan_pembayaran)
    void lanjut_pembayaran(){
        if (txtKeterangan.getText().toString().equals("")){
            deskripsi = " ";
        }
        int nominal = Integer.parseInt(txtNominal.getText().toString().replace(",",""));
        if (txtNominal.getText().toString().equals("")){
            Toast.makeText(this, "Silahkan mengisi nominal", Toast.LENGTH_SHORT).show();
        }else if(nominal < 10000){
            Toast.makeText(this, "Donasi minimal Rp 10.000", Toast.LENGTH_SHORT).show();
        }else{
            SharedPreferences.Editor editor = mSettings.edit();
            editor.putString("nominal_donasi", txtNominal.getText().toString());
            editor.putString("keterangan", txtKeterangan.getText().toString());
            editor.apply();

            Intent i = new Intent(BerdonasiStep1Activity.this, BerdonasiStep2Activity.class);

            startActivity(i);
        }
    }
//    @OnClick(R.id.pilihMetodeBayar)
//    void pilihMetodeBayar(){
//        Intent i = new Intent(BerdonasiStep1Activity.this, MetodePembayaranActivity.class);
//        startActivity(i);
//    }

//    @OnClick(R.id.cardView)
//    void cardViewClicked(){
//        Intent i = new Intent(BerdonasiStep1Activity.this, MetodePembayaranActivity.class);
//        startActivity(i);
//    }

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
                txtNominal.removeTextChangedListener(this);

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
                    txtNominal.setText(formattedString);
                    txtNominal.setSelection(txtNominal.getText().length());
                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                }

                txtNominal.addTextChangedListener(this);
            }
        };
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}

package com.example.riset.Donasi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.example.riset.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BerdonasiStep1Activity extends AppCompatActivity {

    private Dialog customDialog;

    @BindView(R.id.pilihMetodeBayar)
    Button pilihMetodeBayar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_berdonasi_step1);
        ButterKnife.bind(this);

        initViews();
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

        pilihMetodeBayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customDialog.dismiss();
            }
        });
    }

    private void initViewsComponent(){
        pilihMetodeBayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customDialog.show();
            }
        });
    }

    @OnClick(R.id.lanjutkan_pembayaran)
    void lanjut_pembayaran(){
        Intent i = new Intent(BerdonasiStep1Activity.this, BerdonasiStep2Activity.class);
        startActivity(i);
    }

    @OnClick(R.id.pilihMetodeBayar)
    void pilihMetodeBayar(){
        Intent i = new Intent(BerdonasiStep1Activity.this, MetodePembayaranActivity.class);
        startActivity(i);
    }
}

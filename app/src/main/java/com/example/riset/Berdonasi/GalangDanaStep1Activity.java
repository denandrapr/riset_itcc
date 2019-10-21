package com.example.riset.Berdonasi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.riset.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GalangDanaStep1Activity extends AppCompatActivity {

    AlertDialog.Builder alertDialog;
    LayoutInflater inflater;
    View dialogView;

    RadioGroup radioTargetGroup;
    RadioButton radioTargetBtn;

    @BindView(R.id.target_bantuan)
    Button buttonTargetBantuan;
    @BindView(R.id.penerimaDonasi)
    EditText penerimaDonasi;
    @BindView(R.id.judulKegiatan)
    EditText judulKegiatan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galang_dana_step1);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_selanjutnya)
    void btnSelanjutnya(){
        if (penerimaDonasi.getText().toString().equals("") || judulKegiatan.getText().toString().equals("")){
            Toast.makeText(this, "Tidak boleh ada field kosong!", Toast.LENGTH_SHORT).show();
        }else{
            Intent i = new Intent(GalangDanaStep1Activity.this, GalangDanaStep2Activity.class);
            i.putExtra("targetPenerima", buttonTargetBantuan.getText().toString());
            i.putExtra("namaPenerimaDonasi", penerimaDonasi.getText().toString());
            i.putExtra("judulKegiatan", judulKegiatan.getText().toString());
            startActivity(i);
        }
    }

    @OnClick(R.id.target_bantuan)
    void btnTargetBantuan(){
        dialogForm();
    }

    private void dialogForm() {
        alertDialog = new AlertDialog.Builder(GalangDanaStep1Activity.this);
        inflater = getLayoutInflater();
        dialogView = inflater.inflate(R.layout.dialog_target_bantuan, null);
        alertDialog.setView(dialogView);
        alertDialog.setCancelable(true);
        /*alertDialog.setIcon(R.mipmap.ic_launcher);*/
        alertDialog.setTitle("Pilih target bantuan");

        radioTargetGroup = dialogView.findViewById(R.id.radioGroupTarget);

        alertDialog.setPositiveButton("Pilih", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                int selectedId = radioTargetGroup.getCheckedRadioButtonId();
                radioTargetBtn = dialogView.findViewById(selectedId);
                buttonTargetBantuan.setText(radioTargetBtn.getText());
//                Toast.makeText(GalangDanaStep1Activity.this, radioTargetBtn.getText(), Toast.LENGTH_SHORT).show();
                dialogInterface.dismiss();
            }
        });

        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(GalangDanaStep1Activity.this, "Gagal", Toast.LENGTH_SHORT).show();
                dialogInterface.dismiss();
            }
        });
        alertDialog.show();
    }
}

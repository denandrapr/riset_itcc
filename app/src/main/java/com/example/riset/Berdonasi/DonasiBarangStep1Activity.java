package com.example.riset.Berdonasi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;

import com.example.riset.R;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DonasiBarangStep1Activity extends AppCompatActivity {

    private DatePickerDialog datePickerDialog;

    @BindView(R.id.batasWaktu)
    Button mbatasWaktu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donasi_barang_step1);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_selanjutnya)
    void btn_selanjutnya(){
        Intent i = new Intent(DonasiBarangStep1Activity.this, DonasiRuanganStep2Activity.class);
        startActivity(i);
    }

    @OnClick(R.id.batasWaktu)
    void pilihBatasWaktu(){
        Calendar newCalendar = Calendar.getInstance();

        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(i,i1, i2);
                mbatasWaktu.setText(i2+"-"+i1+"-"+i);
            }
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();
    }
}

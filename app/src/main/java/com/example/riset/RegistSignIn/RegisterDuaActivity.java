package com.example.riset.RegistSignIn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.riset.MainActivity;
import com.example.riset.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterDuaActivity extends AppCompatActivity {

    final Calendar myCalendar = Calendar.getInstance();

    @BindView(R.id.txtCalendar)
    Button textCalendar;
    @BindView(R.id.txtAlamat)
    EditText textAlamat;
    @BindView(R.id.nomorTelepon)
    EditText textNomorTelepon;
    @BindView(R.id.pekerjaan)
    EditText textPekerjaan;

    private ProgressDialog Progress;

    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    String nama;
    String email;
    String password;
    String dateFromPick;

    private DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawableResource(R.drawable.bg_all);
        setContentView(R.layout.activity_register_dua);
        ButterKnife.bind(this);

        Intent i = getIntent();
        nama = i.getStringExtra("nama");
        email = i.getStringExtra("email");
        password = i.getStringExtra("passwd");
//        Log.d("TAG", nama + " " + email + " " + password);

        textCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickDialog();
            }
        });
    }

    private void datePickDialog() {
        Calendar newCalendar = Calendar.getInstance();

        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(i,i1, i2);
                dateFromPick = i2+"-"+(i1+1)+"-"+i;
                textCalendar.setText(i2+"-"+(i1+1)+"-"+i);
            }
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }

    @OnClick(R.id.kembali)
    void kembali(){
        onBackPressed();
    }

    @OnClick(R.id.btnSelesai)
    void btnSelesai(){
        Progress = new ProgressDialog(RegisterDuaActivity.this);
        Progress.setCancelable(false);
        Progress.setMessage("Mendaftar...");
        Progress.show();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            setDataToFirestore();
                        }else{
                            Progress.dismiss();
                            Log.d("TAG", "Register gagal");
                        }
                    }
                });
    }

    private void setDataToFirestore(){
        Map<String, Object> updates = new HashMap<>();
        updates.put("email", email);
        updates.put("nama", nama);
        updates.put("tanggalLahir", dateFromPick);
        updates.put("alamat", textAlamat.getText().toString());
        updates.put("nomorTelepon", textNomorTelepon.getText().toString());
        updates.put("pekerjaan", textPekerjaan.getText().toString());
        updates.put("registered_date", FieldValue.serverTimestamp());
        
        db.collection("User")
                .document(email)
                .set(updates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Progress.dismiss();
                            Intent i = new Intent(RegisterDuaActivity.this, MainActivity.class);
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(i);
                        }else{
                            Progress.dismiss();
                            Toast.makeText(RegisterDuaActivity.this, "Gagal", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                
    }
}

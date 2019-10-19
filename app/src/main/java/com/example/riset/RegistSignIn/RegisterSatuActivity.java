package com.example.riset.RegistSignIn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.example.riset.R;
import com.google.firebase.firestore.FirebaseFirestore;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterSatuActivity extends AppCompatActivity {

    @BindView(R.id.editText)
    EditText txtnama;
    @BindView(R.id.editText2)
    EditText txtEmail;
    @BindView(R.id.editText3)
    EditText txtPassword;
    @BindView(R.id.editText4)
    EditText txtPassword2;


    private FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawableResource(R.drawable.bg_all);
        setContentView(R.layout.activity_register_satu);
        ButterKnife.bind(this);
        db = FirebaseFirestore.getInstance();

    }

    @OnClick(R.id.btn_selanjutnya)
    void fungsi_btn_selanjutnya(){
        if (txtPassword.getText().toString().equals(txtPassword2.getText().toString())){
            Intent i = new Intent(RegisterSatuActivity.this, RegisterDuaActivity.class);
            i.putExtra("nama",txtnama.getText().toString());
            i.putExtra("email",txtEmail.getText().toString());
            i.putExtra("passwd",txtPassword.getText().toString());
            startActivity(i);
        }else{
            Toast.makeText(this, "password tidak sama", Toast.LENGTH_SHORT).show();
        }
    }
}

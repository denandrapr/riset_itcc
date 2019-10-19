package com.example.riset.RegistSignIn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.riset.MainActivity;
import com.example.riset.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignInActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @BindView(R.id.txtEmail)
    EditText inputEmail;
    @BindView(R.id.txtPasswd)
    EditText inputPass;
    @BindView(R.id.btnSignIn)
    Button btnLogin;

    private ProgressDialog Progress;
    String email , pass ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawableResource(R.drawable.bg_all);
        setContentView(R.layout.activity_sign_in);
        ButterKnife.bind(this);
        mAuth = FirebaseAuth.getInstance();
    }

    @OnClick(R.id.btnSignIn)
    void login(){
        Progress = new ProgressDialog(SignInActivity.this);
        Progress.setCancelable(false);
        Progress.setMessage("Login");
        Progress.show();

        email = inputEmail.getText().toString();
        pass = inputPass.getText().toString();

        if(email.equals("")||pass.equals("")){
            Toast.makeText(this,"Email atau Password tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
        else{
            mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Progress.dismiss();
                        Intent i = new Intent(SignInActivity.this , MainActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);
                        finish();
                    }
                    else{
                        Progress.dismiss();
                        Toast.makeText(SignInActivity.this, "Gagal", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

//        Intent i = new Intent(SignInActivity.this, MainActivity.class);
//        startActivity(i);
//        finish();
    }
}

package com.example.riset.Profile;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.riset.Model.UserModel;
import com.example.riset.Notifikasi.NotifikasiMainActivity;
import com.example.riset.R;
import com.example.riset.RegistSignIn.SignInActivity;
import com.example.riset.RegistSignIn.WelcomeSignInActivity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.auth.User;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProfileFragment  extends Fragment {

    private FirebaseAuth mAuth;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseUser user = mAuth.getInstance().getCurrentUser();

    ProgressDialog progressDialog;

    @BindView(R.id.relative2)
    RelativeLayout relativeLayout;
    @BindView(R.id.linear1)
    LinearLayout linearLayout;
    @BindView(R.id.txtEmail)
    TextView textEmail;
    @BindView(R.id.profile_name)
    TextView profileName;
    @BindView(R.id.photo_profile)
    ImageView photoProfile;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_fragment, container, false);
        ButterKnife.bind(this, view);

        mAuth = FirebaseAuth.getInstance();
        if (user != null){
            textEmail.setText(user.getEmail());
            linearLayout.setVisibility(View.VISIBLE);
            relativeLayout.setVisibility(View.GONE);

            getData();
        }else{
            linearLayout.setVisibility(View.GONE);
            relativeLayout.setVisibility(View.VISIBLE);
        }

        return view;
    }

    private void getData() {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("mengambil data...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        db.collection("User")
                .document(user.getEmail())
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()){
                            progressDialog.dismiss();
                            UserModel userModel = null;
                            userModel = documentSnapshot.toObject(UserModel.class);
                            profileName.setText(userModel.getNama());
                            Glide.with(getActivity())
                                .load(userModel.getUrlProfileImage())
                                .centerCrop()
                                .circleCrop()
                                .into(photoProfile);
                        }else{
                            progressDialog.dismiss();
                        }
                    }
                });

    }

    @OnClick(R.id.outLayout)
    void log_out(){
        new AlertDialog.Builder(getActivity())
                .setTitle("Keluar")
                .setMessage("Apa anda yakin akan keluar?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mAuth.signOut();
                        Intent p = new Intent(getActivity(), WelcomeSignInActivity.class);
                        p.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(p);
                    }
                }).setNegativeButton("No", null).show();
    }

    @OnClick(R.id.signin_btn)
    void sign_button(){
        Intent i = new Intent(getActivity(), WelcomeSignInActivity.class);
        startActivity(i);
    }

    @OnClick(R.id.notif)
    void notifikasi(){
        Intent i = new Intent(getActivity(), NotifikasiMainActivity.class);
        startActivity(i);
    }

    @OnClick(R.id.editProfile)
    void editProfile(){
        Intent i = new Intent(getActivity(), ProfileEditActivity.class);
        startActivity(i);
    }

    @OnClick(R.id.relativeTentangKami)
    void tentangKami(){
        Intent i = new Intent(getActivity(), ProfileTentangEducare.class);
        startActivity(i);
    }

}

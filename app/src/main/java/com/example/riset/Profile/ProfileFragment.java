package com.example.riset.Profile;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.riset.R;
import com.example.riset.RegistSignIn.SignInActivity;
import com.example.riset.RegistSignIn.WelcomeSignInActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProfileFragment  extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.outLayout)
    void log_out(){
        new AlertDialog.Builder(getActivity())
                .setTitle("Keluar")
                .setMessage("Apa anda yakin akan keluar?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent p = new Intent(getActivity(), WelcomeSignInActivity.class);
                        startActivity(p);
                    }
                }).setNegativeButton("No", null).show();
    }
}

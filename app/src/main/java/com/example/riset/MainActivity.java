package com.example.riset;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.riset.Berdonasi.BuatKegiatanActivity;
import com.example.riset.Donasi.DonasiFragment;
import com.example.riset.Home.HomeFragment;
import com.example.riset.Profile.ProfileFragment;
import com.example.riset.Dokumentasi.DokumentasiFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.bottom_nv)
    BottomNavigationView btm_nv;
    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        loadFragment(new HomeFragment());
        btm_nv.setOnNavigationItemSelectedListener(this);

    }

    private boolean loadFragment(Fragment fragment){
        if (fragment != null){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_root, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment = null;


        switch (menuItem.getItemId()){
            case R.id.home:
                fragment = new HomeFragment();
                break;
            case R.id.riwayat:
                fragment = new DokumentasiFragment();
                break;
            case R.id.donasi:
                fragment = new DonasiFragment();
                break;
            case R.id.saya:
                fragment = new ProfileFragment();
                break;
            case R.id.bantuMereka:
                fragment = new BuatKegiatanActivity();
                break;
        }
        return loadFragment(fragment);
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "klik lagi untuk keluar", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}

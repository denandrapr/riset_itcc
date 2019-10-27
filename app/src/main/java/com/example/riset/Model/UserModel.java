package com.example.riset.Model;

public class UserModel {
    String alamat;
    String email;
    String nama;
    String nomorTelepon;
    String pekerjaan;
    String tanggalLahir;
    String urlProfileImage;

    public UserModel(){

    }

    public UserModel(String alamat, String email, String nama, String nomorTelepon, String pekerjaan, String tanggalLahir, String urlProfileImage) {
        this.alamat = alamat;
        this.email = email;
        this.nama = nama;
        this.nomorTelepon = nomorTelepon;
        this.pekerjaan = pekerjaan;
        this.tanggalLahir = tanggalLahir;
        this.urlProfileImage = urlProfileImage;
    }

    public String getUrlProfileImage() {
        return urlProfileImage;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getEmail() {
        return email;
    }

    public String getNama() {
        return nama;
    }

    public String getNomorTelepon() {
        return nomorTelepon;
    }

    public String getPekerjaan() {
        return pekerjaan;
    }

    public String getTanggalLahir() {
        return tanggalLahir;
    }
}

package com.example.riset.Home.Model;

public class ButuhSegeraModel {

    private String deskripsi;
    private String img;
    private String judul;
    private String target;

    public ButuhSegeraModel(String deskripsi, String img, String judul, String target) {
        this.deskripsi = deskripsi;
        this.img = img;
        this.judul = judul;
        this.target = target;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public String getImg() {
        return img;
    }

    public String getJudul() {
        return judul;
    }

    public String getTarget() {
        return target;
    }
}

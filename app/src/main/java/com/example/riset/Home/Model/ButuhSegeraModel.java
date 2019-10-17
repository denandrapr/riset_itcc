package com.example.riset.Home.Model;

public class ButuhSegeraModel {

    private String deskripsi;
    private String img;
    private String judul;
    private String target;
    private String targetTanggal;
    private String id;

    public ButuhSegeraModel(){

    }

    public ButuhSegeraModel(String deskripsi, String img, String judul, String target, String targetTanggal) {
        this.deskripsi = deskripsi;
        this.img = img;
        this.judul = judul;
        this.target = target;
        this.targetTanggal = targetTanggal;
    }

    public String getId() {
        return id;
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

    public String getTargetTanggal() {
        return targetTanggal;
    }
}

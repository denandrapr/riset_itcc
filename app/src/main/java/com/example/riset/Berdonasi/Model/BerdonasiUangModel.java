package com.example.riset.Berdonasi.Model;

public class BerdonasiUangModel {
    private String keterangan;
    private String metode;
    private int nominal;
    private boolean anonim;
    private String tanggal;
    private String nama;

    public BerdonasiUangModel(String keterangan, String metode, int nominal, boolean anonim, String tanggal, String nama) {
        this.keterangan = keterangan;
        this.metode = metode;
        this.nominal = nominal;
        this.anonim = anonim;
        this.tanggal = tanggal;
        this.nama = nama;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public String getMetode() {
        return metode;
    }

    public int getNominal() {
        return nominal;
    }

    public boolean isAnonim() {
        return anonim;
    }

    public String getTanggal() {
        return tanggal;
    }

    public String getNama() {
        return nama;
    }
}

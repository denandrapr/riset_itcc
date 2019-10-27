package com.example.riset.Model;

public class BerdonasiUangModel {
    private String keterangan;
    private String metode;
    private int nominal;
    private boolean anonim;
    private String tanggal;
    private String nama;
    private String id;
    private String email_donatur;

    public BerdonasiUangModel(String keterangan, String metode, int nominal, boolean anonim, String tanggal, String nama, String id, String email_donatur) {
        this.keterangan = keterangan;
        this.metode = metode;
        this.nominal = nominal;
        this.anonim = anonim;
        this.tanggal = tanggal;
        this.nama = nama;
        this.id = id;
        this.email_donatur = email_donatur;
    }

    public String getEmail_donatur() {
        return email_donatur;
    }

    public String getId() {
        return id;
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

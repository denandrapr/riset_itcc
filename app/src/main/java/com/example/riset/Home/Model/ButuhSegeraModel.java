package com.example.riset.Home.Model;

public class ButuhSegeraModel {

    private String bankPilihan;
    private String id;
    private String batasWaktu;
    private String deskripsi;
    private String judulKegiatan;
    private String linkFotoUtama;
    private String namaPenerimaDonasi;
    private String noRek;
    private String targetNominalDonasi;
    private String targetPenerima;
    private int tipe;
    private String created_by;

    public ButuhSegeraModel(){

    }

    public ButuhSegeraModel(String bankPilihan, String batasWaktu, String deskripsi, String judulKegiatan,
                            String linkFotoUtama, String namaPenerimaDonasi, String noRek, String targetNominalDonasi,
                            String targetPenerima, int tipe, String id, String created_by) {
        this.bankPilihan = bankPilihan;
        this.batasWaktu = batasWaktu;
        this.deskripsi = deskripsi;
        this.judulKegiatan = judulKegiatan;
        this.linkFotoUtama = linkFotoUtama;
        this.namaPenerimaDonasi = namaPenerimaDonasi;
        this.noRek = noRek;
        this.targetNominalDonasi = targetNominalDonasi;
        this.targetPenerima = targetPenerima;
        this.tipe = tipe;
        this.id = id;
        this.created_by = created_by;
    }

    public String getCreated_by() {
        return created_by;
    }

    public String getId() {
        return id;
    }

    public String getBankPilihan() {
        return bankPilihan;
    }

    public String getBatasWaktu() {
        return batasWaktu;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public String getJudulKegiatan() {
        return judulKegiatan;
    }

    public String getLinkFotoUtama() {
        return linkFotoUtama;
    }

    public String getNamaPenerimaDonasi() {
        return namaPenerimaDonasi;
    }

    public String getNoRek() {
        return noRek;
    }

    public String getTargetNominalDonasi() {
        return targetNominalDonasi;
    }

    public String getTargetPenerima() {
        return targetPenerima;
    }

    public int getTipe() {
        return tipe;
    }
}

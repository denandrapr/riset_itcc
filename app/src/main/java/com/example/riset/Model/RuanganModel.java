package com.example.riset.Model;

public class RuanganModel {
    private String batasWaktu;
    private String deskripsi;
    private String id;
    private String jadwalKegiatan;
    private String judulKegiatan;
    private String kapasitasRuangan;
    private String keahlianRelawan;
    private String linkFotoUtama;
    private String lokasiRuangan;

    public RuanganModel(){

    }

    public RuanganModel(String batasWaktu, String deskripsi, String id, String jadwalKegiatan,
                        String judulKegiatan, String kapasitasRuangan, String keahlianRelawan,
                        String linkFotoUtama, String lokasiRuangan) {
        this.batasWaktu = batasWaktu;
        this.deskripsi = deskripsi;
        this.id = id;
        this.jadwalKegiatan = jadwalKegiatan;
        this.judulKegiatan = judulKegiatan;
        this.kapasitasRuangan = kapasitasRuangan;
        this.keahlianRelawan = keahlianRelawan;
        this.linkFotoUtama = linkFotoUtama;
        this.lokasiRuangan = lokasiRuangan;
    }

    public String getBatasWaktu() {
        return batasWaktu;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public String getId() {
        return id;
    }

    public String getJadwalKegiatan() {
        return jadwalKegiatan;
    }

    public String getJudulKegiatan() {
        return judulKegiatan;
    }

    public String getKapasitasRuangan() {
        return kapasitasRuangan;
    }

    public String getKeahlianRelawan() {
        return keahlianRelawan;
    }

    public String getLinkFotoUtama() {
        return linkFotoUtama;
    }

    public String getLokasiRuangan() {
        return lokasiRuangan;
    }
}

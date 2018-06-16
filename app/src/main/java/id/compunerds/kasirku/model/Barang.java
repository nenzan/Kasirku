package id.compunerds.kasirku.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Barang {

    @SerializedName("nama")
    @Expose
    private String nama;

    @SerializedName("stok")
    @Expose
    private Integer stok;

    @SerializedName("kode")
    @Expose
    private String kode;

    @SerializedName("harga_jual")
    @Expose
    private Integer harga_jual;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Integer getStok() {
        return stok;
    }

    public void setStok(Integer stok) {
        this.stok = stok;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public Integer getHarga_jual() {
        return harga_jual;
    }

    public void setHarga_jual(Integer harga_jual) {
        this.harga_jual = harga_jual;
    }
}

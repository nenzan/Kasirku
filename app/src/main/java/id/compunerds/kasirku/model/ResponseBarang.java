package id.compunerds.kasirku.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseBarang {

    @SerializedName("value")
    @Expose
    private Integer value;
    @SerializedName("result")
    @Expose
    private List<Result> result = null;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }

    public class Result {

        @SerializedName("nama")
        @Expose
        private String nama;
        @SerializedName("stok")
        @Expose
        private String stok;
        @SerializedName("kode")
        @Expose
        private String kode;
        @SerializedName("harga_jual")
        @Expose
        private String hargaJual;
        @SerializedName("harga_dasar")
        @Expose
        private String hargaDasar;

        public String getHargaDasar() {
            return hargaDasar;
        }

        public void setHargaDasar(String hargaDasar) {
            this.hargaDasar = hargaDasar;
        }

        public String getNama() {
            return nama;
        }

        public void setNama(String nama) {
            this.nama = nama;
        }

        public String getStok() {
            return stok;
        }

        public void setStok(String stok) {
            this.stok = stok;
        }

        public String getKode() {
            return kode;
        }

        public void setKode(String kode) {
            this.kode = kode;
        }

        public String getHargaJual() {
            return hargaJual;
        }

        public void setHargaJual(String hargaJual) {
            this.hargaJual = hargaJual;
        }

    }
}

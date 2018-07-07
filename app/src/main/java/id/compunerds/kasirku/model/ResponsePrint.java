package id.compunerds.kasirku.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponsePrint {

    @SerializedName("value")
    @Expose
    private Integer value;
    @SerializedName("result")
    @Expose
    private List<Result> result = null;
    @SerializedName("result2")
    @Expose
    private List<Result2> result2 = null;

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

    public List<Result2> getResult2() {
        return result2;
    }

    public void setResult2(List<Result2> result2) {
        this.result2 = result2;
    }


    public class Result {

        @SerializedName("id_struk")
        @Expose
        private String idStruk;
        @SerializedName("kode")
        @Expose
        private String kode;
        @SerializedName("tanggal")
        @Expose
        private String tanggal;
        @SerializedName("total")
        @Expose
        private String total;
        @SerializedName("bayar")
        @Expose
        private String bayar;
        @SerializedName("kembali")
        @Expose
        private String kembali;

        public String getIdStruk() {
            return idStruk;
        }

        public void setIdStruk(String idStruk) {
            this.idStruk = idStruk;
        }

        public String getKode() {
            return kode;
        }

        public void setKode(String kode) {
            this.kode = kode;
        }

        public String getTanggal() {
            return tanggal;
        }

        public void setTanggal(String tanggal) {
            this.tanggal = tanggal;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public String getBayar() {
            return bayar;
        }

        public void setBayar(String bayar) {
            this.bayar = bayar;
        }

        public String getKembali() {
            return kembali;
        }

        public void setKembali(String kembali) {
            this.kembali = kembali;
        }

    }

    public class Result2 {

        @SerializedName("kode")
        @Expose
        private String kode;
        @SerializedName("jumlah")
        @Expose
        private String jumlah;
        @SerializedName("harga_total")
        @Expose
        private String hargaTotal;

        public String getKode() {
            return kode;
        }

        public void setKode(String kode) {
            this.kode = kode;
        }

        public String getJumlah() {
            return jumlah;
        }

        public void setJumlah(String jumlah) {
            this.jumlah = jumlah;
        }

        public String getHargaTotal() {
            return hargaTotal;
        }

        public void setHargaTotal(String hargaTotal) {
            this.hargaTotal = hargaTotal;
        }

    }

}




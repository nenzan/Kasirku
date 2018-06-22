package id.compunerds.kasirku.data;

public class Data {

    private String nama, stok, kode, hargaDasar, hargaJual;

    public Data(String nama, String stok, String kode, String hargaDasar, String hargaJual) {
        this.nama = nama;
        this.stok = stok;
        this.kode = kode;
        this.hargaDasar = hargaDasar;
        this.hargaJual = hargaJual;
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

    public String getHargaDasar() {
        return hargaDasar;
    }

    public void setHargaDasar(String hargaDasar) {
        this.hargaDasar = hargaDasar;
    }

    public String getHargaJual() {
        return hargaJual;
    }

    public void setHargaJual(String hargaJual) {
        this.hargaJual = hargaJual;
    }
}

package id.compunerds.kasirku.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("result")
    @Expose
    private Barang result;

    public Barang getResult() {
        return result;
    }

    public void setResult(Barang result) {
        this.result = result;
    }
}

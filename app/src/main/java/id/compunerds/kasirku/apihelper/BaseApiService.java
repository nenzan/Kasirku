package id.compunerds.kasirku.apihelper;

import id.compunerds.kasirku.model.ResponseBarang;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface BaseApiService {
    @FormUrlEncoded
    @POST("login.php")
    Call<ResponseBody> loginRequest(@Field("email") String email,
                                    @Field("password") String password);


    @GET("barang.php")
    Call<ResponseBarang> getSemuaBarang();

    @FormUrlEncoded
    @POST("barangadd.php")
    Call<ResponseBody> tambahBarang(@Field("id_toko") String id_toko,
                                    @Field("nama") String nama,
                                    @Field("stok") String stok,
                                    @Field("kode") String kode,
                                    @Field("harga_dasar") String harga_dasar,
                                    @Field("harga_jual") String harga_jual);

    @FormUrlEncoded
    @POST("barangdel.php")
    Call<ResponseBody> deleteBarang(@Field("kode") String kode);

    @FormUrlEncoded
    @POST("barangupdate.php")
    Call<ResponseBody> updateBarang(@Field("nama") String nama,
                                    @Field("stok") String stok,
                                    @Field("kode") String kode,
                                    @Field("harga_dasar") String harga_dasar,
                                    @Field("harga_jual") String harga_jual
                                    );
}

package id.compunerds.kasirku.apihelper;

import id.compunerds.kasirku.model.Result;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface BaseApiService {
    @FormUrlEncoded
    @POST("login.php")
    Call<ResponseBody> loginRequest(@Field("email") String email,
                                    @Field("password") String password);

    @GET("barang.php")
    Call<Result> getResultInfo();

    @GET("barang.php")
    Call<ResponseBody> getResultAsJSON();

    @FormUrlEncoded
    @POST("barangadd.php")
    Call<ResponseBody> addBarang(@Field("nama") String nama,
                                 @Field("stok") String stok,
                                 @Field("kode") String kode,
                                 @Field("harga_dasar") String harga_dasar,
                                 @Field("harga_jual") String harga_jual);
}

package id.compunerds.kasirku.apihelper;

public class UtilsApi {
    public static final String BASE_URL_API = "http://sistec.co.id/apikasir/";

    // Mendeklarasikan Interface BaseApiService
    public static BaseApiService getAPIService(){
        return RetrofitClient.getClient(BASE_URL_API).create(BaseApiService.class);
    }
}

package retrofitdata;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface Api {
    String BASE_URL = "https://api.androidhive.info/";
    String BASE_URL2 = "https://api.androidhive.info/contacts";
//    @GET("contacts")
//    Call<Results> getsuperHeroes();

    @GET("contacts")
    Call<String> getStringResponse();
}

package async.shah.com.retrofittest.restservice;

import java.util.List;

import async.shah.com.retrofittest.model.MovieResponse;
import async.shah.com.retrofittest.model.Table;
import async.shah.com.retrofittest.model.UserList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by shah on 4/27/2017.
 */

public interface ApiInterface {
    @GET("/api/users?")
    Call<UserList> doGetUserList(@Query("page") String page);

    @GET("/CSSWebservice/CSSWebServices.asmx/GetTable")
    Call<List<Table>> doGetTables();

    @GET("movie/top_rated")
    Call<MovieResponse> getTopRatedMovies(@Query("api_key") String apiKey);
}

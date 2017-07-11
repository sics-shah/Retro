package async.shah.com.retrofittest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.List;


import async.shah.com.retrofittest.adapter.TableAdapter;
import async.shah.com.retrofittest.model.Movie;
import async.shah.com.retrofittest.model.MovieResponse;
import async.shah.com.retrofittest.model.Table;
import async.shah.com.retrofittest.model.UserList;
import async.shah.com.retrofittest.restservice.ApiClient;

import async.shah.com.retrofittest.restservice.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {
    ApiInterface apiInterface1,apiInterface2;
    private final static String API_KEY = "ba37b8cb2e81dd8d40f4ba3c4aaef4ec";
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         recyclerView = (RecyclerView) findViewById(R.id.recylerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        apiInterface1 = ApiClient.getClient1().create(ApiInterface.class);
        apiInterface2 = ApiClient.getClient2().create(ApiInterface.class);

        getRetrofitArray();
//        /**
//         GET List Users
//         **/
        Call<UserList> call2 = apiInterface2.doGetUserList("2");
        call2.enqueue(new Callback<UserList>() {
            @Override
            public void onResponse(Call<UserList> call, Response<UserList> response) {

                UserList userList = response.body();
                Integer text = userList.page;
                Integer total = userList.total;
                Integer totalPages = userList.totalPages;
//                List<UserList.Datum> datumList = userList.data;
//                Toast.makeText(getApplicationContext(), text + " page\n" + total + " total\n" + totalPages + " totalPages\n", Toast.LENGTH_SHORT).show();
//
//                for (UserList.Datum datum : datumList) {
//                    Toast.makeText(getApplicationContext(), "id : " + datum.id + " name: " + datum.first_name + " " + datum.last_name + " avatar: " + datum.avatar, Toast.LENGTH_SHORT).show();
//                }


            }

            @Override
            public void onFailure(Call<UserList> call, Throwable t) {
                call.cancel();
            }
        });
//

        /**
//         GET TAbles
//         **/
//        Call<Table> call3 = apiInterface1.doGetTables();
//        call3.enqueue(new Callback<Table>() {
//            @Override
//            public void onResponse(Call<Table> call, Response<Table> response) {
//
//                Table table = response.body();
////                String SNo = table.getSNo;
////                String total = table.total;
////                String totalPages = table.totalPages;
////                List<UserList.Datum> datumList = userList.data;
////                Toast.makeText(getApplicationContext(), text + " page\n" + total + " total\n" + totalPages + " totalPages\n", Toast.LENGTH_SHORT).show();
////
////                for (UserList.Datum datum : datumList) {
////                    Toast.makeText(getApplicationContext(), "id : " + datum.id + " name: " + datum.first_name + " " + datum.last_name + " avatar: " + datum.avatar, Toast.LENGTH_SHORT).show();
////                }
//
//
//            }
//
//            @Override
//            public void onFailure(Call<Table> call, Throwable t) {
//                call.cancel();
//            }
//        });
//





        if (API_KEY.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please obtain your API KEY first from themoviedb.org", Toast.LENGTH_LONG).show();
            return;
        }

        ApiInterface apiService =
                ApiClient.getClient3().create(ApiInterface.class);

        Call<MovieResponse> call = apiService.getTopRatedMovies(API_KEY);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse>call, Response<MovieResponse> response) {
                List<Movie> movies = response.body().getResults();
                Log.d("SIZE OF ARRRAY", "Number of movies received: " + movies.size());
            }

            @Override
            public void onFailure(Call<MovieResponse>call, Throwable t) {
                // Log error here since request failed
                Log.e("ERROR", t.toString());
            }
        });

    }

    void getRetrofitArray() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://213.42.60.86/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface service = retrofit.create(ApiInterface.class);

        Call<List<Table>> call = service.doGetTables();

        call.enqueue(new Callback<List<Table>>() {

            @Override
            public void onResponse(Call<List<Table>> call, Response<List<Table>> response) {
                List<Table> StudentData = response.body();
                recyclerView.setAdapter(new TableAdapter(StudentData, R.layout.list_item_table, getApplicationContext()));


        }

            @Override
            public void onFailure(Call<List<Table>>call,Throwable t) {
                Log.d("onFailure", t.toString());
            }
        });
    }
}

package com.mobile.fatah.learnrequestapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Adapter;

import com.mobile.fatah.learnrequestapi.adapter.AdapterBerita;
import com.mobile.fatah.learnrequestapi.model.BeritaItem;
import com.mobile.fatah.learnrequestapi.model.ResponseBerita;
import com.mobile.fatah.learnrequestapi.network.ApiService;
import com.mobile.fatah.learnrequestapi.network.ConfigRetrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.mobile.fatah.learnrequestapi.network.ConfigRetrofit.getInstance;

public class MainActivity extends AppCompatActivity {
 RecyclerView listBerita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listBerita = findViewById(R.id.recyclerview);

        listBerita.setLayoutManager(new LinearLayoutManager(this));

        getData1();
    }

    private void getData1() {
        ApiService apiService = getInstance();
        retrofit2.Call<ResponseBerita> call = apiService.getDataBerita();
        call.enqueue(new Callback<ResponseBerita>() {
            @Override
            public void onResponse(Call<ResponseBerita> call, Response<ResponseBerita> response) {
                Log.d("Response", "Succes" + response.body().toString());
                List<BeritaItem> dataBerita = response.body().getBerita();
                boolean status = response.body().isStatus();

                if (status) {
                    Log.d("Data Berita", dataBerita.toString());

                    AdapterBerita adapter = new AdapterBerita(MainActivity.this, dataBerita);
                    listBerita.setAdapter(adapter);
                }




            }

            @Override
            public void onFailure(Call<ResponseBerita> call, Throwable t) {

            }
        });


    }
}

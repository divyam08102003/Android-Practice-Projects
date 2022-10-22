package com.divyam.retrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView tv1;
    RecyclerView rv_list;
    List<Model> data;
    String url = "https://jsonplaceholder.typicode.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv1 = findViewById(R.id.header_title);
        rv_list = findViewById(R.id.rv_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        MyAdapter myAdapter = new MyAdapter(MainActivity.this);
        myAdapter.setData(data);
        rv_list.setLayoutManager(linearLayoutManager);
        rv_list.setAdapter(myAdapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Myapi myapi = retrofit.create(Myapi.class);
        Call<List<Model>> call = myapi.getmodels();

        call.enqueue(new Callback<List<Model>>() {
            @Override
            public void onResponse(Call<List<Model>> call, Response<List<Model>> response) {
                data = response.body();
                myAdapter.setData(data);

//                tv1.setText(String.valueOf(data.get(0).getId()));
                myAdapter.notifyDataSetChanged();


            }

            @Override
            public void onFailure(Call<List<Model>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "No Data Available", Toast.LENGTH_SHORT).show();
            }
        });



    }
}
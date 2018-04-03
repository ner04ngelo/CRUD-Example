package com.example.user.crudexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.user.crudexample.adapter.DepartmentAdapter;
import com.example.user.crudexample.api.Api;
import com.example.user.crudexample.models.DepartmentModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by USER on 2/4/2018.
 */

public class AllData extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Button buttonBack;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_content);
        initViews();
        configureRecyclerView();
        fetchHttpRequest();

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AllData.this, MainActivity.class );
                startActivity(intent);
                finish();
            }
        });
    }

    private void initViews() {

        recyclerView = findViewById(R.id.recycler_view);
        buttonBack = findViewById(R.id.buttonBack);
    }

    private void configureRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

    }

    private void fetchHttpRequest() {
        Call<List<DepartmentModel>> call = Api.instance().getDepartment();
        call.enqueue(new Callback<List<DepartmentModel>>() {
            @Override
            public void onResponse(@NonNull Call<List<DepartmentModel>> call, Response<List<DepartmentModel>> response) {
                if (response.body() != null) {
                    DepartmentAdapter departmentAdapter = new DepartmentAdapter(response.body());
                    recyclerView.setAdapter(departmentAdapter);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<DepartmentModel>> call, @NonNull Throwable t) {
                Log.i("Debug: ", t.getMessage());
            }
        });
    }
}

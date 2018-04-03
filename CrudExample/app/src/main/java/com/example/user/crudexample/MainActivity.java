package com.example.user.crudexample;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.user.crudexample.adapter.DepartmentAdapter;
import com.example.user.crudexample.api.Api;
import com.example.user.crudexample.models.DepartmentModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private final String TAG= "MainActivity";


    private Button buttonSelectAll;
    private Button buttonCreate;
    private Button buttonDelete;
    private Button buttonUpdate;
    private EditText departmentname;
    private EditText idDepartment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();


        buttonSelectAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AllData.class);
                startActivity(intent);
                finish();
            }
        });

        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendHttpRequest();
            }
        });

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateData();
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteData();
            }
        });
    }

    private void initViews() {

        buttonSelectAll = findViewById(R.id.buttonSelect);
        buttonCreate = findViewById(R.id.buttonCreate);
        buttonDelete = findViewById(R.id.buttonDelete);
        buttonUpdate= findViewById(R.id.buttonUpdate);
        departmentname = findViewById(R.id.nameDepartment);
        idDepartment = findViewById(R.id.idDepartment);
    }





    private void sendHttpRequest() {
        DepartmentModel departmentModel = new DepartmentModel();
        departmentModel.setId_department(Integer.valueOf(idDepartment.getText().toString()));
        departmentModel.setDepartmentname(departmentname.getText().toString());


        Call<DepartmentModel> call = Api.instance().createDepartment(departmentModel);
        call.enqueue(new Callback<DepartmentModel>() {
            @Override
            public void onResponse(Call<DepartmentModel> call, Response<DepartmentModel> response) {
                Log.i(TAG, response.body().getDepartmentname());

            }

            @Override
            public void onFailure(Call<DepartmentModel> call, Throwable throwable) {
                Log.e(TAG, throwable.getMessage());

            }
        });
    }


    private void updateData(){
        DepartmentModel departmentModel = new DepartmentModel();
        departmentModel.setId_department(Integer.valueOf(idDepartment.getText().toString()));
        departmentModel.setDepartmentname(departmentname.getText().toString());

        Call<DepartmentModel> call = Api.instance().updateDepartment(departmentModel.getId_department(),departmentModel);
        call.enqueue(new Callback<DepartmentModel>() {
            @Override
            public void onResponse(Call<DepartmentModel> call, Response<DepartmentModel> response) {
                if(response != null){ Log.i(TAG, response.body().getDepartmentname());}

            }

            @Override
            public void onFailure(Call<DepartmentModel> call, Throwable throwable) {
                Log.e(TAG, throwable.getMessage());
            }
        });
    }

    private void deleteData(){
        DepartmentModel departmentModel = new DepartmentModel();
        departmentModel.setId_department(Integer.valueOf(idDepartment.getText().toString()));
        departmentModel.setDepartmentname(departmentname.getText().toString());

        Call<DepartmentModel> call = Api.instance().deleteDepartment(departmentModel.getId_department());
        call.enqueue(new Callback<DepartmentModel>() {
            @Override
            public void onResponse(Call<DepartmentModel> call, Response<DepartmentModel> response) {
                if(response != null){ Log.i(TAG, response.body().getDepartmentname());}

            }

            @Override
            public void onFailure(Call<DepartmentModel> call, Throwable throwable) {
                Log.e(TAG, throwable.getMessage());
            }
        });

    }
}

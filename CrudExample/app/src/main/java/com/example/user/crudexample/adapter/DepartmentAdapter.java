package com.example.user.crudexample.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.crudexample.R;
import com.example.user.crudexample.models.DepartmentModel;

import java.util.List;

/**
 * Created by USER on 2/4/2018.
 */

public class DepartmentAdapter extends RecyclerView.Adapter<DepartmentAdapter.ViewHolder > {

    private List<DepartmentModel> departments;

    public DepartmentAdapter(List<DepartmentModel> departments) {
        this.departments = departments;
    }

    @Override
    public DepartmentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_department, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DepartmentAdapter.ViewHolder holder, int position) {
        DepartmentModel DepartmentModel = departments.get(position);
        holder.id.setText(String.valueOf(DepartmentModel.getId_department()));
        holder.name.setText(DepartmentModel.getDepartmentname());
    }

    @Override
    public int getItemCount() {
        return departments.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView id;
        public ViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            id = itemView.findViewById(R.id.id);
        }
    }
}

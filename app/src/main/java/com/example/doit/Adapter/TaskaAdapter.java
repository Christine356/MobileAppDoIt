package com.example.doit.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doit.AddNewTask;
import com.example.doit.MainActivity;
import com.example.doit.Model.TasksModel;
import com.example.doit.R;
import com.example.doit.utils.DatabaseHandler;

import java.util.List;

public class TaskaAdapter extends RecyclerView.Adapter<TaskaAdapter.ViewHolder> {


    private List<TasksModel> todoList;
    private MainActivity activity;
    private DatabaseHandler db;

    public TaskaAdapter(DatabaseHandler db, MainActivity activity){
        this.db = db;
        this.activity = activity;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.content_main, parent, false);
        return new ViewHolder(itemView);
    }


    public void onBindViewHolder (ViewHolder holder, int position){
        db.openDatabase();
        TasksModel item = todoList.get(position);
        holder.tasks.setText(item.getTasks());
        holder.tasks.setChecked(toBoolean(item.getStatus()));
        holder.tasks.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    db.updateStatus(item.getId(), 1);
                }
                else{
                    db.updateStatus(item.getId(), 0);
                }

            }
        });
    }

    public int getItemCount(){
        return todoList.size();
    }




    private boolean toBoolean (int n){
        return n!=0;
    }

    public void setTask (List<TasksModel>todoList){
        this.todoList = todoList;
        notifyDataSetChanged();
    }

    public Context getContext(){
        return activity;
    }


    public void editItem(int position){
        TasksModel item = todoList.get(position);
        Bundle bundle = new Bundle();
        bundle.putInt("id", item.getId());
        bundle.putString("task", item.getTasks());
        AddNewTask fragment = new AddNewTask();
        fragment.setArguments(bundle);
        fragment.show(activity.getSupportFragmentManager(), AddNewTask.TAG);
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox tasks;

        ViewHolder(View view){
            super (view);
            tasks = view.findViewById(R.id.Checkbox);
        }
    }


    }



package com.example.doit.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doit.MainActivity;
import com.example.doit.Model.TasksModel;
import com.example.doit.R;

import java.util.List;

public class TaskaAdapter extends RecyclerView.Adapter<TaskaAdapter.ViewHolder> {


    private List<TasksModel> todolist;
    private MainActivity activity;

    public TaskaAdapter(MainActivity activity){
        this.activity = activity
                ;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.content_main, parent, false);
        return new ViewHolder(itemView);
    }


    public void onBindViewHolder (ViewHolder holder, int position){
        TasksModel item = todolist.get(position);
        holder.tasks.setText(item.getTasks());
        holder.tasks.setChecked(toBoolean(item.getStatus()));
    }

    public int getItemCount(){
        return todolist.size();
    }




    private boolean toBoolean (int n){
        return n!=0;
    }

    public void setTask (List<TasksModel>todoList){
        this.todolist = todoList;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox tasks;

        ViewHolder(View view){
            super (view);
            tasks = view.findViewById(R.id.Checkbox);
        }
    }


    }



package com.example.doit;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doit.Adapter.TaskaAdapter;
import com.example.doit.Model.TasksModel;
import com.example.doit.utils.DatabaseHandler;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DialogCloseListener {

    private RecyclerView tasksRecyclerView;
    private TaskaAdapter todoAdapter;
    private FloatingActionButton fab;

    private List<TasksModel> taskList;
    private DatabaseHandler db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        db = new DatabaseHandler(this);
        db.openDatabase();

        taskList = new ArrayList<>();

        tasksRecyclerView = findViewById(R.id.TasksRecyclerView);
        tasksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        todoAdapter = new TaskaAdapter(db, this);
        tasksRecyclerView.setAdapter(todoAdapter);

        fab = findViewById(R.id.fab);

       taskList = db.getAllTasks();
       Collections.reverse(taskList);
       todoAdapter.setTask(taskList);

       fab.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               AddNewTask.newInstance().show(getSupportFragmentManager(), AddNewTask.TAG);
           }
       });
    }
    @Override
    public void handleDialogClose(DialogInterface dialog){
        taskList = db.getAllTasks();
        Collections.reverse(taskList);
        todoAdapter.setTask(taskList);
        todoAdapter.notifyDataSetChanged();
    }
}

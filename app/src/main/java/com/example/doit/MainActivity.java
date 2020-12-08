package com.example.doit;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doit.Adapter.TaskaAdapter;
import com.example.doit.Model.TasksModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView tasksRecyclerView;
    private TaskaAdapter todoAdapter;

    private List<TasksModel> taskList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        taskList = new ArrayList<>();

        tasksRecyclerView = findViewById(R.id.TasksRecyclerView);
        tasksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        todoAdapter = new TaskaAdapter(this);
        tasksRecyclerView.setAdapter(todoAdapter);

        TasksModel tasks = new TasksModel();
        tasks.setTasks("Test");
        tasks.setStatus(0);
        tasks.setId(1);

        taskList.add(tasks);
        taskList.add(tasks);
        taskList.add(tasks);
        taskList.add(tasks);
        taskList.add(tasks);

        todoAdapter.setTask(taskList);
    }
}

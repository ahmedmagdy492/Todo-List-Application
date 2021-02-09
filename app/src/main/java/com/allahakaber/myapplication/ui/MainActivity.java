package com.allahakaber.myapplication.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.allahakaber.myapplication.R;
import com.allahakaber.myapplication.adapters.TodoAdapter;
import com.allahakaber.myapplication.pojo.Todo;

import java.util.List;

public class MainActivity extends AppCompatActivity implements AddTodoView {

    private RecyclerView rview;
    private TodoPresenter presenter;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rview = findViewById(R.id.rview);

        this.presenter = new TodoPresenter(this);

        presenter.setTodoList(this);

        rview.smoothScrollBy(0, -50000);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_options, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.btnDeleteAll) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setTitle("Are you sure you want to delete all items ?");
            alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    presenter.deleteAll();
                }
            });
            alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            alertDialog.create().show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void getTodoList(List<Todo> todoList) {
        TodoAdapter adapter = new TodoAdapter(this, presenter);
        adapter.setTodoList(todoList);
        rview.setAdapter(adapter);
        rview.setLayoutManager(new LinearLayoutManager(this));
    }


    public void onClick(View v) {
        Log.d(TAG, "onClick: ");
        Intent intent = new Intent(this, AddAddTodoActivity.class);
        startActivity(intent);
        this.finish();
    }
}
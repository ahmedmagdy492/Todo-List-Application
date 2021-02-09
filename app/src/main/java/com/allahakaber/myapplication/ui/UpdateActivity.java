package com.allahakaber.myapplication.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.allahakaber.myapplication.R;
import com.allahakaber.myapplication.pojo.Todo;

public class UpdateActivity extends AppCompatActivity implements UpdateTodoView {

    private TodoPresenter presenter;
    private int id;
    private EditText txtTitle, txtContent;
    private Todo todo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        
        txtTitle = findViewById(R.id.editTxtTitle);
        txtContent = findViewById(R.id.editTxtContent);
        
        id = getIntent().getIntExtra("id", 10);
        presenter = new TodoPresenter(this);
        
        presenter.getById(id, this);
    }
    
    private boolean validateInput() {
        return !txtTitle.getText().toString().equals("") && !txtContent.getText().toString().equals("");
    }

    public void btnSaveClicked(View view) {
        
        if(validateInput()) {
            todo.setContent(txtContent.getText().toString());
            todo.setTitle(txtTitle.getText().toString());
            presenter.updateTodo(todo);

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            this.finish();
        }
        else {
            Toast.makeText(this, "Content and title cannot be empty", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onGetById(Todo todo) {
        txtTitle.setText(todo.getTitle());
        txtContent.setText(todo.getContent());
        
        this.todo = todo;
    }
}
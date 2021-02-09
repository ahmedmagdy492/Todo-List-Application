package com.allahakaber.myapplication.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.allahakaber.myapplication.R;
import com.allahakaber.myapplication.pojo.Todo;

public class AddAddTodoActivity extends AppCompatActivity implements InsertTodoView {

    private TodoPresenter presenter;
    private EditText editTextTitle, editTextContent;

    private boolean validateInput() {
        return !editTextTitle.getText().toString().equals("") && !editTextContent.getText().toString().equals("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo);

        editTextTitle = findViewById(R.id.editTextTextPersonName);
        editTextContent = findViewById(R.id.editTextTextPersonName2);

        presenter = new TodoPresenter(this);
    }

    public void btnAddClicked(View view) {

        if(validateInput()) {
            presenter.insert(this, new Todo(editTextTitle.getText().toString(), editTextContent.getText().toString()));
            Intent intent = new Intent(this, MainActivity.class);
            this.finish();
            startActivity(intent);
        }
    }

    @Override
    public void insertTodo() {

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        this.finish();
        startActivity(intent);
    }
}
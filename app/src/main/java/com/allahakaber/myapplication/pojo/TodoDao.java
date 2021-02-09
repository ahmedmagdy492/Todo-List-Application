package com.allahakaber.myapplication.pojo;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;


@Dao()
public interface TodoDao {

    @Query("Select * From todo_table")
    Single<List<Todo>> getTodoList();

    @Insert()
    Completable insertTodo(Todo todo);

    @Delete()
    Completable deleteTodo(Todo todo);

    @Update()
    Completable updateTodo(Todo todo);

    @Query("Delete From todo_table")
    Completable deleteAll();

    @Query("Select * From todo_table Where id = :id")
    Single<Todo> getTodoById(int id);
}

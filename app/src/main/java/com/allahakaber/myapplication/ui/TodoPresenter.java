package com.allahakaber.myapplication.ui;

import android.content.Context;

import com.allahakaber.myapplication.pojo.Todo;
import com.allahakaber.myapplication.pojo.TodoDao;
import com.allahakaber.myapplication.pojo.TodoDatabase;

import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.SingleObserver;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiConsumer;
import io.reactivex.schedulers.Schedulers;

public class TodoPresenter {

    private TodoDao todoDao;
    private static final String TAG = "TodoPresenter";

    public TodoPresenter(Context context) {
        todoDao = TodoDatabase.getInstance(context).todoDao();
    }

    public void setTodoList(AddTodoView addTodoView) {
        todoDao.getTodoList()
                .subscribeOn(Schedulers.computation())
                .subscribe(new SingleObserver<List<Todo>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull List<Todo> todoList) {
                        addTodoView.getTodoList(todoList);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });
    }

    public void insert(InsertTodoView insertTodoView, Todo todo) {
        todoDao.insertTodo(todo)
                .subscribeOn(Schedulers.computation())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        insertTodoView.insertTodo();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });
    }

    public void deleteAll() {
        todoDao.deleteAll()
                .subscribeOn(Schedulers.computation())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });
    }

    public void deleteTodo(Todo todo) {
        todoDao.deleteTodo(todo)
                .subscribeOn(Schedulers.computation())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });
    }

    public void updateTodo(Todo todo) {
        todoDao.updateTodo(todo)
                .subscribeOn(Schedulers.computation())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });
    }

    public void getById(int id, UpdateTodoView view) {
        todoDao.getTodoById(id)
                .subscribeOn(Schedulers.computation())
                .subscribe(new SingleObserver<Todo>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull Todo todo) {
                        view.onGetById(todo);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });
    }
}

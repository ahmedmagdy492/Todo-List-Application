package com.allahakaber.myapplication.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.allahakaber.myapplication.R;
import com.allahakaber.myapplication.pojo.Todo;
import com.allahakaber.myapplication.ui.TodoPresenter;
import com.allahakaber.myapplication.ui.UpdateActivity;

import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoViewHolder> {

    private Context context;
    private List<Todo> todoList;
    private TodoPresenter presenter;

    public TodoAdapter(Context context, TodoPresenter todoPresenter) {
        this.context = context;
        this.presenter = todoPresenter;
    }

    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_item, parent, false);
        return new TodoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoViewHolder holder, int position) {
        holder.txtTitle.setText(todoList.get(position).getTitle());
        holder.txtContent.setText(todoList.get(position).getContent());
        holder.card_item.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                PopupMenu popupMenu = new PopupMenu(context, v);

                popupMenu.getMenu().add("Delete Todo");
                popupMenu.getMenu().add("Update Todo");

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        if(item.getTitle() == "Delete Todo") {
                            presenter.deleteTodo(todoList.get(position));
                            return true;
                        }
                        else if(item.getTitle() == "Update Todo") {
                            Intent intent = new Intent(context, UpdateActivity.class);
                            intent.putExtra("id", todoList.get(position).getId());
                            context.startActivity(intent);
                            return true;
                        }
                        return false;
                    }
                });

                popupMenu.show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return todoList.size();
    }

    public void setTodoList(List<Todo> todoList) {
        this.todoList = todoList;
        notifyDataSetChanged();
    }

    public static class TodoViewHolder extends RecyclerView.ViewHolder {

        private TextView txtTitle, txtContent;
        private CardView card_item;

        public TodoViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtContent = itemView.findViewById(R.id.txtContent);
            card_item = itemView.findViewById(R.id.card_item);
        }
    }
}

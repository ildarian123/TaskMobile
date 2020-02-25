package com.example.taskmobile.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskmobile.Database.entity.PostDb;
import com.example.taskmobile.R;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    private List<PostDb> listOfPosts;
    public PostAdapter(List<PostDb> listOfPosts) {
        this.listOfPosts = listOfPosts;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layotIdForListItem = R.layout.post_list;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layotIdForListItem, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        holder.titleOfThePost.setText(listOfPosts.get(position).author);
        holder.authorOfThePost.setText(String.valueOf(listOfPosts.get(position).post));
        holder.publishedAt.setText(String.valueOf(listOfPosts.get(position).date));

    }

    @Override
    public int getItemCount() {
        return listOfPosts.size();
    }

    class PostViewHolder extends RecyclerView.ViewHolder {

        TextView titleOfThePost;
        TextView authorOfThePost;
        TextView publishedAt;

        PostViewHolder(@NonNull View itemView) {
            super(itemView);
            titleOfThePost = itemView.findViewById(R.id.titleOfThePost);
            authorOfThePost = itemView.findViewById(R.id.authorOfThePost);
            publishedAt = itemView.findViewById(R.id.publishedAt);
        }
    }
}


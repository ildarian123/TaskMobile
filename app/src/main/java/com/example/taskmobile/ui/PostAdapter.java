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
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    void setListOfPosts(List<PostDb> listOfPosts) {
        this.listOfPosts = listOfPosts;
    }

    private List<PostDb> listOfPosts;
    private MainPresenter manager;

    PostAdapter(List<PostDb> listOfPosts, MainPresenter manager) {
        this.listOfPosts = listOfPosts;
        this.manager = manager;
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

        if (position == listOfPosts.size() - 1) {
            manager.onLastItemShown();
        }

        holder.titleOfThePost.setText(listOfPosts.get(position).author);
        holder.authorOfThePost.setText(String.valueOf(listOfPosts.get(position).post));

        SimpleDateFormat date = new SimpleDateFormat("MMMM dd, yyyy hh:mm", Locale.ENGLISH);
        String format = date.format(listOfPosts.get(position).date);
        holder.publishedAt.setText(format);

    }

    @Override
    public int getItemCount() {
        return listOfPosts.size();
    }

    static class PostViewHolder extends RecyclerView.ViewHolder {

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

    List<PostDb> getListOfPosts() {
        return this.listOfPosts;
    }

}


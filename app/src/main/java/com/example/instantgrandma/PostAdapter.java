package com.example.instantgrandma;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.instantgrandma.models.Post;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    private List<Post> mPosts;
    Context context;

    public PostAdapter(List<Post> posts) {
        mPosts = posts;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //get context
        context = parent.getContext();
        //create inflater
        LayoutInflater inflater = LayoutInflater.from(context);
        View tweetView = inflater.inflate(R.layout.item_post, parent, false);
        //return that view wrapped by a ViewHolder
        return new ViewHolder(tweetView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        //get movie at specified position
        Post post = mPosts.get(position);
        //populate the view w movie info
        viewHolder.tvDescription.setText(post.getDescription());

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    //create the viewholder as a static inner class
    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvDescription;
        ImageView ivImage;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            ivImage = itemView.findViewById(R.id.ivImage);
        }



    }


}

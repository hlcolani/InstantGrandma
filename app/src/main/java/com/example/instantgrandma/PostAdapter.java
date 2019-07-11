package com.example.instantgrandma;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
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
        viewHolder.tvTime.setText(post.getCreatedAt().toString());
        viewHolder.tvUser.setText(post.getUser().getUsername());
        Glide.with(context).load(post.getImage().getUrl()).into(viewHolder.ivImage);


    }

    @Override
    public int getItemCount() {
        return mPosts.size();
    }

    //create the viewholder as a static inner class
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tvDescription;
        ImageView ivImage;
        TextView tvTime;
        TextView tvUser;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvTime = itemView.findViewById(R.id.tvTime);
            tvUser = itemView.findViewById(R.id.tvUser);
            ivImage = itemView.findViewById(R.id.ivImage);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            // gets item position
            int position = getAdapterPosition();
            // make sure the position is valid, i.e. actually exists in the view
            if (position != RecyclerView.NO_POSITION) {
                // get the movie at the position, this won't work if the class is static
                Post post = mPosts.get(position);
                // create intent for the new activity
                Intent intent = new Intent(context, PostDetailActivity.class);
                // serialize the movie using parceler, use its short name as a key
                intent.putExtra(Post.class.getSimpleName(), post);
                // show the activity
                context.startActivity(intent);
            }
        }



    }

    public void clear() {
        mPosts.clear();
        notifyDataSetChanged();
    }


}

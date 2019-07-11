package com.example.instantgrandma;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.instantgrandma.models.Post;

public class PostDetailActivity extends AppCompatActivity {
    TextView tvDescription;
    ImageView ivImage;
    TextView tvTime;
    Post post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);
        tvDescription = findViewById(R.id.tvDetailDescription);
        ivImage = findViewById(R.id.ivDetail);
        tvTime = findViewById(R.id.tvDetailTime);

        post = (Post) getIntent().getExtras().get(Post.class.getSimpleName());

        tvDescription.setText(post.getDescription());
        if (post.getCreatedAt() != null) {
            tvTime.setText(post.getCreatedAt().toString());
        }


        Glide.with(this).load(post.getImage().getUrl()).into(ivImage);
    }
}

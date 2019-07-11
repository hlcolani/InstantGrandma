package com.example.instantgrandma;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.instantgrandma.models.Post;
import com.parse.ParseFile;

public class PostDetailActivity extends AppCompatActivity {
    TextView tvDescription;
    ImageView ivImage;
    TextView tvTime;
    TextView tvUser;
    ImageView ivProPic;
    Post post;
    Button btnLike;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);
        tvDescription = findViewById(R.id.tvDetailDescription);
        ivImage = findViewById(R.id.ivDetail);
        tvTime = findViewById(R.id.tvDetailTime);
        tvUser = findViewById(R.id.tvDetailUser);
        ivProPic = findViewById(R.id.ivDetailProPic);
        btnLike = findViewById(R.id.btnLike);

        post = (Post) getIntent().getExtras().get(Post.class.getSimpleName());

        tvDescription.setText(post.getDescription());
        if (post.getCreatedAt() != null) {
            tvTime.setText(post.getCreatedAt().toString());
        }
        tvUser.setText(post.getUser().getUsername());

        ParseFile image = (ParseFile) post.getUser().get("image");
        if (image != null) {
            Glide.with(this).load(image.getUrl()).into(ivProPic);
        }
        Glide.with(this).load(post.getImage().getUrl()).into(ivImage);
    }
}

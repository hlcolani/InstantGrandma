package com.example.instantgrandma;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.instantgrandma.models.Post;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.SaveCallback;

public class PostDetailActivity extends AppCompatActivity {
    TextView tvDescription;
    ImageView ivImage;
    TextView tvTime;
    TextView tvUser;
    ImageView ivProPic;
    Post post;
    Button btnLike;
    TextView tvLikes;

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
        tvLikes = findViewById(R.id.tvLikes);

        post = (Post) getIntent().getExtras().get(Post.class.getSimpleName());

        tvDescription.setText(post.getDescription());
        if (post.getCreatedAt() != null) {
            tvTime.setText(post.getCreatedAt().toString());
        }
        tvUser.setText(post.getUser().getUsername());
        tvLikes.setText(Integer.toString(post.getLikes()));

        btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int likes = post.getLikes() + 1;
                post.setLikes(likes);
                post.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        tvLikes.setText(Integer.toString(post.getLikes()));
                    }
                });
            }
        });

        ParseFile image = (ParseFile) post.getUser().get("image");
        if (image != null) {
            Glide.with(this).load(image.getUrl()).into(ivProPic);
        }
        Glide.with(this).load(post.getImage().getUrl()).into(ivImage);
    }
}

package com.example.instantgrandma.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.instantgrandma.PostAdapter;
import com.example.instantgrandma.R;
import com.example.instantgrandma.models.Post;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    ArrayList<Post> mPosts;
    RecyclerView rvPosts;
    PostAdapter postAdapter;
    SwipeRefreshLayout swipeContainer;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        swipeContainer = (SwipeRefreshLayout) view.findViewById(R.id.swipeContainer);
        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to refresh the list here.
                // Make sure you call swipeContainer.setRefreshing(false)
                // once the network request has completed successfully.
                queryPosts();
            }
        });
        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        rvPosts = (RecyclerView) view.findViewById(R.id.rvPosts);
        mPosts = new ArrayList<Post>();
        postAdapter = new PostAdapter(mPosts);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rvPosts.setLayoutManager(linearLayoutManager);
        rvPosts.setAdapter(postAdapter);
        queryPosts();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    private void queryPosts() {
        mPosts.clear();
        postAdapter.clear();
        ParseQuery<Post> postQuery = new ParseQuery<Post>(Post.class);
        postQuery.orderByDescending("createdAt");
        postQuery.include("user");
        postQuery.setLimit(10);
        postQuery.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {
                for (int i = 0; i < posts.size(); i++) {
                    mPosts.add(posts.get(i));
                    postAdapter.notifyItemInserted(mPosts.size() - 1);
                }
                swipeContainer.setRefreshing(false);
            }
        });

    }
}

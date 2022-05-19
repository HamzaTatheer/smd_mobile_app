package com.example.therapychannel.presentation.forum.ProblemPostsList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.therapychannel.R;
import com.example.therapychannel.service.forum.entities.ProblemPost;

import java.util.ArrayList;

public class ProblemPostsRvAdapter extends RecyclerView.Adapter<ProblemPostsRvAdapter.ViewHolder> implements Filterable {

    private Context context;
    private ArrayList<ProblemPost> dataset;

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView postTitle;
        private TextView postAnswer;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            postTitle = (TextView) itemView.findViewById(R.id.post_title);
            postAnswer = (TextView)itemView.findViewById(R.id.post_content);
        }

        public TextView getPostAnswer() {
            return postAnswer;
        }

        public TextView getPostTitle() {
            return postTitle;
        }


    }

    ProblemPostsRvAdapter(Context context, ArrayList<ProblemPost> problemPosts){
        this.context = context;
        this.dataset = problemPosts;
    }


    @Override
    public Filter getFilter() {
        return null;
    }

    @NonNull
    @Override
    public ProblemPostsRvAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.problem_post_card, parent, false);

        return new ProblemPostsRvAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProblemPostsRvAdapter.ViewHolder holder, int position) {
        ProblemPost p = dataset.get(position);
        holder.getPostTitle().setText(p.getTitle());
        holder.getPostAnswer().setText(p.getContent());
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}

package com.example.therapychannel.presentation.forum.ProblemsList;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.therapychannel.R;
import com.example.therapychannel.service.forum.entities.Problem;

import java.util.ArrayList;

public class ProblemsListRvAdapter extends RecyclerView.Adapter<ProblemsListRvAdapter.ViewHolder> implements Filterable {

    private Context context;
    private ArrayList<Problem> dataset;

    public interface onClickListener{
        void onClick(int position);
    };

    private onClickListener clickCallback;

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ConstraintLayout problem_card;
        private TextView problem_name;
        private TextView problem_posts_count;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            problem_card = (ConstraintLayout)itemView.findViewById(R.id.problem_card_container);
            problem_posts_count = (TextView)itemView.findViewById(R.id.problem_card_count);

            problem_name = (TextView)itemView.findViewById(R.id.problem_card_text);
            problem_card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(clickCallback != null)
                        clickCallback.onClick(getAdapterPosition());
                }


            });
        }

        public TextView getProblemName(){
            return this.problem_name;
        }

        public TextView getProblemPostsCount(){
            return this.problem_posts_count;

        }

        public ConstraintLayout getProblemCard() {
            return problem_card;
        }


        public void changeCardColor(int id){
            ViewCompat.setBackgroundTintList(
                    problem_card,
                    ContextCompat.getColorStateList(context,
                            id
                    )
            );
        }


    }

    ProblemsListRvAdapter(Context context,ArrayList<Problem> problems,onClickListener clickCallback){
        this.context = context;
        this.dataset = problems;
        this.clickCallback = clickCallback;
    }


    @Override
    public Filter getFilter() {
        return null;
    }

    @NonNull
    @Override
    public ProblemsListRvAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.problem_card, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProblemsListRvAdapter.ViewHolder holder, int position) {
        Problem p = dataset.get(position);
        holder.getProblemName().setText(p.getName());

        int count = p.getPostsCount();
        if(count > 200){
            holder.getProblemPostsCount().setText("200+");
        }
        else{
            holder.getProblemPostsCount().setText(String.valueOf(count));
        }


        if(position%2 == 0)
            holder.changeCardColor(R.color.pink_200);
        else
            holder.changeCardColor(R.color.orange_200);

    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}

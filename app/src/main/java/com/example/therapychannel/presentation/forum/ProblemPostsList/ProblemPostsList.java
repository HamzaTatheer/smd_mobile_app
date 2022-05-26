package com.example.therapychannel.presentation.forum.ProblemPostsList;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.text.Html;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.therapychannel.R;
import com.example.therapychannel.presentation.forum.common.InternetStatus;
import com.example.therapychannel.service.forum.createPostForProblem;
import com.example.therapychannel.service.forum.entities.Problem;
import com.example.therapychannel.service.forum.entities.ProblemPost;
import com.example.therapychannel.service.forum.getProblemPosts;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProblemPostsList#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProblemPostsList extends Fragment {

    //arguments
    private static final String ARG_PROBLEM = "PROBLEM_OBJ";

    //parameters
    private Problem PARAM_PROBLEM;

    public ProblemPostsList() {
        // Required empty public constructor
        Log.v("TherapyChannel","Empty called");
    }


    // TODO: Rename and change types and number of parameters
    public static ProblemPostsList newInstance(Problem problem) {
        Log.v("TherapyChannel",String.valueOf(Boolean.valueOf(problem == null)));
        ProblemPostsList fragment = new ProblemPostsList();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PROBLEM, problem);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            PARAM_PROBLEM = (Problem) getArguments().getSerializable(ARG_PROBLEM);
            Log.v("TherapyChannel","aa "+String.valueOf(getArguments().size()));
        }
    }


    ArrayList<ProblemPost> posts;
    RecyclerView.Adapter adapter;
    SwipeRefreshLayout swipeRefreshLayout;

    private void refreshPosts(){
        //api call to get data
        //notify item changed

        //for deleting all posts
        //Line below wont work as adapter only checks the reference it orignally got
        //posts=new ArrayList<>();
        //instead use
        //posts.clear();

        posts.clear();
        Thread t = new Thread(new getProblemPosts(PARAM_PROBLEM, new getProblemPosts.Listener() {
            @Override
            public void onSuccess(ArrayList<ProblemPost> response) {
                posts.addAll(response);
                adapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);

            }
        }));
        t.start();



    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_problem_posts_list, container, false);
        TextView heading = (TextView)view.findViewById(R.id.fragment_problem_posts_heading);
        heading.setText(Html.fromHtml("Showing Results for <u>" + PARAM_PROBLEM.getName() + "</u>"));
        RecyclerView postsRv = (RecyclerView) view.findViewById(R.id.fragment_problems_posts_rv);
        postsRv.setLayoutManager(new LinearLayoutManager(getActivity()));

        //send api call here
        Thread t = new Thread(new getProblemPosts(PARAM_PROBLEM, new getProblemPosts.Listener() {
            @Override
            public void onSuccess(ArrayList<ProblemPost> response) {
                posts = response;
                adapter = new ProblemPostsRvAdapter(getContext(),posts);
                postsRv.setAdapter(adapter);
            }
        }));
        t.start();

        swipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.problem_list_refresher);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(InternetStatus.isOnline(getContext()))
                    refreshPosts();
                else{
                    Toast toast = Toast.makeText(getContext(),
                            "Make sure your internet is working",
                            Toast.LENGTH_SHORT);
                    toast.show();
                    swipeRefreshLayout.setRefreshing(false);
                }

            }
        });


        view.findViewById(R.id.add_problem_post_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.add_problem_post_dialog,container,false);
                builder.setView(dialogView);

                builder.setPositiveButton("add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        TextView titleView = (TextView)dialogView.findViewById(R.id.post_title_to_add);
                        TextView bodyView = (TextView)dialogView.findViewById(R.id.post_body_to_add);
                        //send call to api


                        //Only the original thread that created a view hierarchy can touch its views.
                        //This was the error when the thread below ran the onSuccess function
                        Thread t = new Thread(new createPostForProblem(PARAM_PROBLEM,new ProblemPost(String.valueOf(titleView.getText()), String.valueOf(bodyView.getText())),
                                new createPostForProblem.Listener() {
                                    @Override
                                    public void onSuccess() {
                                        swipeRefreshLayout.setRefreshing(true);
                                        refreshPosts();
                                        adapter.notifyDataSetChanged();

                                    }
                                }));
                        t.start();
                        //refresh list when done using call back
                    }
                });

                builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        TextView titleView = (TextView)dialogView.findViewById(R.id.post_title_to_add);
                        TextView bodyView = (TextView)dialogView.findViewById(R.id.post_body_to_add);
                        titleView.setText("");
                        bodyView.setText("");
                    }
                });

                builder.show();

            }
        });

        return view;
    }
}
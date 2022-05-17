package com.example.therapychannel.presentation.forum.ProblemPostsList;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.therapychannel.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProblemPostsList#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProblemPostsList extends Fragment {

    //arguments
    private static final String ARG_PROBLEM_NAME = "PROBLEM_NAME";

    //parameters
    private String PARAM_PROBLEM_NAME;

    public ProblemPostsList() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static ProblemPostsList newInstance(String problem_name) {
        ProblemPostsList fragment = new ProblemPostsList();
        Bundle args = new Bundle();
        args.putString(ARG_PROBLEM_NAME, problem_name);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            PARAM_PROBLEM_NAME = getArguments().getString(ARG_PROBLEM_NAME);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_problem_posts_list, container, false);
        TextView heading = (TextView)view.findViewById(R.id.fragment_problem_posts_heading);
        heading.setText(Html.fromHtml("Showing Results for <u>" + PARAM_PROBLEM_NAME + "</u>"));
        return view;
    }
}
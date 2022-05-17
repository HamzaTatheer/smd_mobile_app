package com.example.therapychannel.presentation.forum.ProblemsList;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.therapychannel.DAO.forum.DTO.ProblemDTO;
import com.example.therapychannel.R;
import com.example.therapychannel.service.forum.entities.Problem;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProblemsList#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProblemsList extends Fragment  implements ProblemsListRvAdapter.onClickListener{

    public interface Listener{
        void onProblemSelect(String problem_name);
    };

    Listener listener;
    ArrayList<Problem> dataset;

    public ProblemsList(Listener listener) {
        // Required empty public constructor
        this.listener = listener;
    }


    // TODO: Rename and change types and number of parameters
    public static ProblemsList newInstance(Listener listener) {
        ProblemsList fragment = new ProblemsList(listener);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //may be needed later
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_problems_list, container, false);

        RecyclerView rv = (RecyclerView) view.findViewById(R.id.fragment_problems_rv);

        if(rv == null){
            Log.e("TherapyChannel","rv is null");
        }


        if(getActivity() == null){
            Log.e("TherapyChannel","activity is null");
        }

        rv.setLayoutManager(new LinearLayoutManager(getActivity()));

        dataset = new ArrayList<>();
        dataset.add(new Problem("weight",10));
        dataset.add(new Problem("weight",201));
        dataset.add(new Problem("weight",400));
        dataset.add(new Problem("weight",20));

        ProblemsListRvAdapter adapter = new ProblemsListRvAdapter(getContext(),dataset,this);
        rv.setAdapter(adapter);

        return view;
    }

    @Override
    public void onClick(int position) {
        Log.v("TherapyChannel","Clicked");
        Problem p = dataset.get(position);
        if(listener != null)
            listener.onProblemSelect(p.getName());
    }

}



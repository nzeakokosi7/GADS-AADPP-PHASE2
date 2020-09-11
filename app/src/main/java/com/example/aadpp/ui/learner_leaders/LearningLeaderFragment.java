package com.example.aadpp.ui.learner_leaders;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.aadpp.Models.LearningLeaderResponseModel;
import com.example.aadpp.R;
import com.example.aadpp.ui.adapters.LearningLeaderAdapter;

import java.util.List;

public class LearningLeaderFragment extends Fragment {

    private LearningLeaderViewModel mViewModel;
    private RecyclerView recyclerView;
    private LearningLeaderAdapter adapter;

    public static LearningLeaderFragment newInstance() {
        return new LearningLeaderFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.learning_leader_fragment, container, false);
        mViewModel = ViewModelProviders.of(this).get(LearningLeaderViewModel.class);

        recyclerView = view.findViewById(R.id.recyclerview);
        adapter = new LearningLeaderAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Fetching Data");
        progressDialog.setCancelable(false);
        progressDialog.show();

        mViewModel.initData();
        mViewModel.getHourLeaders().observe(getActivity(), new Observer<List<LearningLeaderResponseModel>>() {
            @Override
            public void onChanged(List<LearningLeaderResponseModel> learningLeaderResponse) {
                if (learningLeaderResponse !=null) {
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    Log.d("FirstFragment", "onChanged: " + learningLeaderResponse.size());
                    adapter.setLeaderList(learningLeaderResponse);
                }

            }
        });
    }
}
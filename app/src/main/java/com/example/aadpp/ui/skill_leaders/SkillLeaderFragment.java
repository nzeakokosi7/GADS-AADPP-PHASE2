package com.example.aadpp.ui.skill_leaders;

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

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.aadpp.Models.SkillLeaderResponseModel;
import com.example.aadpp.R;
import com.example.aadpp.ui.adapters.SkillLeaderAdapter;

import java.util.List;

public class SkillLeaderFragment extends Fragment {

    private SkillLeaderViewModel mViewModel;
    private RecyclerView recyclerView;
    private SkillLeaderAdapter adapter;

    public static SkillLeaderFragment newInstance() {
        return new SkillLeaderFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.skill_leader_fragment, container, false);
        mViewModel = ViewModelProviders.of(this).get(SkillLeaderViewModel.class);

        recyclerView = view.findViewById(R.id.recyclerview);
        adapter = new SkillLeaderAdapter(getActivity());
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
        mViewModel.getSkillLeaders().observe(getActivity(), new Observer<List<SkillLeaderResponseModel>>() {
            @Override
            public void onChanged(List<SkillLeaderResponseModel> skillLeaderResponseModels) {
                if(skillLeaderResponseModels !=null) {
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    adapter.setLeaderList(skillLeaderResponseModels);
                }
            }
        });
    }
}
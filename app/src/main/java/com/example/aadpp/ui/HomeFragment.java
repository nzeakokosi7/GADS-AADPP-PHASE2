package com.example.aadpp.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.viewpager.widget.ViewPager;

import com.example.aadpp.R;
import com.example.aadpp.ui.adapters.HomeTabsPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class HomeFragment extends Fragment {
    private TextView mSubmitAction;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        HomeTabsPagerAdapter tabsPagerAdapter = new HomeTabsPagerAdapter(getActivity(), getChildFragmentManager());

        ViewPager viewPager = root.findViewById(R.id.view_pager);
        viewPager.setAdapter(tabsPagerAdapter);

        TabLayout tabs = root.findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mSubmitAction = view.findViewById(R.id.submit_action);

        mSubmitAction.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_FirstFragment_to_submissionFragment));
    }
}
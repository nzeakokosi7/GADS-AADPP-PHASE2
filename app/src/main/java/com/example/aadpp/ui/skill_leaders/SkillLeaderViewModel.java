package com.example.aadpp.ui.skill_leaders;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.aadpp.DataRepository;
import com.example.aadpp.Models.LearningLeaderResponseModel;
import com.example.aadpp.Models.SkillLeaderResponseModel;

import java.util.List;

public class SkillLeaderViewModel extends ViewModel {
    private DataRepository dataRepository;
    private MutableLiveData<List<SkillLeaderResponseModel>> responseLiveData;


    public void initData() {
        if (responseLiveData !=null) {
            return;
        }
        dataRepository = DataRepository.getInstance();
        responseLiveData = dataRepository.getSkillLeaders();
    }

    public LiveData<List<SkillLeaderResponseModel>> getSkillLeaders() {
        return responseLiveData;
    }
}
package com.example.aadpp.ui.learner_leaders;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.aadpp.DataRepository;
import com.example.aadpp.Models.LearningLeaderResponseModel;

import java.util.List;

public class LearningLeaderViewModel extends ViewModel {
    private DataRepository dataRepository;
    private MutableLiveData<List<LearningLeaderResponseModel>> responseLiveData;


    public void initData() {
        if (responseLiveData !=null) {
            return;
        }
        dataRepository = DataRepository.getInstance();
        responseLiveData = dataRepository.getHourLeaders();
    }

    public LiveData<List<LearningLeaderResponseModel>> getHourLeaders() {
        return responseLiveData;
    }
}
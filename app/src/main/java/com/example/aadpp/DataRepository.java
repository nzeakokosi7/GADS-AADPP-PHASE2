package com.example.aadpp;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.aadpp.Models.LearningLeaderResponseModel;
import com.example.aadpp.Models.SkillLeaderResponseModel;
import com.example.aadpp.Retrofit.ApiInterface;
import com.example.aadpp.Retrofit.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataRepository {
    private ApiInterface apiInterface;

    private static DataRepository dataRepository;

    public static DataRepository getInstance(){
        if (dataRepository == null){
            dataRepository = new DataRepository();
        }
        return dataRepository;
    }

    public DataRepository() {
        apiInterface = RetrofitService.createService(ApiInterface.class);
    }

    public MutableLiveData<List<LearningLeaderResponseModel>> getHourLeaders() {
        final MutableLiveData<List<LearningLeaderResponseModel>> data = new MutableLiveData<>();

        Call<List<LearningLeaderResponseModel>> hourResponseCall = apiInterface.getHourLeaders();

       hourResponseCall.enqueue(new Callback<List<LearningLeaderResponseModel>>() {
           @Override
           public void onResponse(Call<List<LearningLeaderResponseModel>> call, Response<List<LearningLeaderResponseModel>> response) {
               data.setValue(response.body());
           }

           @Override
           public void onFailure(Call<List<LearningLeaderResponseModel>> call, Throwable t) {
               Log.d("FirstFragment", "onFailure: " + t.getMessage());
           }
       });

        return data;
    }

    public MutableLiveData<List<SkillLeaderResponseModel>> getSkillLeaders() {
        final MutableLiveData<List<SkillLeaderResponseModel>> data = new MutableLiveData<>();

        Call<List<SkillLeaderResponseModel>> skillResponseCall = apiInterface.getSkillLeaders();

        skillResponseCall.enqueue(new Callback<List<SkillLeaderResponseModel>>() {
            @Override
            public void onResponse(Call<List<SkillLeaderResponseModel>> call, Response<List<SkillLeaderResponseModel>> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<SkillLeaderResponseModel>> call, Throwable t) {

            }
        });

        return data;
    }
}

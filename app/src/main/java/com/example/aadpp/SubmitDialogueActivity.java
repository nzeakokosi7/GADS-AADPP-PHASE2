package com.example.aadpp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aadpp.Retrofit.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SubmitDialogueActivity extends AppCompatActivity {

    public static final String BASE_URL = "https://docs.google.com/forms/d/e/";
    private static Retrofit retrofit = null;
    private String firstNameString, lastNameString, emailString, urlString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFinishOnTouchOutside(false);
        setContentView(R.layout.activity_submit_dialogue);
        TextView mSubmitBtn = findViewById(R.id.submit_btn);
        setTitle("");
        ImageButton mCloseBtn = findViewById(R.id.close_btn);

        if (retrofit == null) {

            retrofit = new Retrofit.Builder()

                    .baseUrl(BASE_URL)

                    .addConverterFactory(GsonConverterFactory.create())

                    .build();

        }

        firstNameString = getIntent().getExtras().getString("first_name");
        lastNameString = getIntent().getExtras().getString("last_name");
        emailString = getIntent().getExtras().getString("email");
        urlString = getIntent().getExtras().getString("url");

        mSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitResponse(firstNameString, lastNameString, emailString, urlString);
            }
        });

        mCloseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void submitResponse(String firstName, String lastName, String email, String url) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Submitting response");
        progressDialog.setCancelable(false);
        progressDialog.show();

        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        Call<Void> submitCall = apiInterface.submitResponse(firstName, lastName, email, url);
        submitCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                if(response.isSuccessful()) {
                    showDialogue(true);
                } else {
                    showDialogue(false);
                }


            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                Log.d("Submit", "onFailure: " + t.getMessage());
                showDialogue(false);
            }
        });
    }

    private void showDialogue(boolean status) {
        Intent intent = new Intent(SubmitDialogueActivity.this, SubmissionProgressDialogActivity.class);
        intent.putExtra("status", status);
        startActivity(intent);
        finish();
    }

}
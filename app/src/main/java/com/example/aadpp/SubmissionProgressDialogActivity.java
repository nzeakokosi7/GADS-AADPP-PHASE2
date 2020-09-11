package com.example.aadpp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;

public class SubmissionProgressDialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submission_progress);
        setTitle("");
        ImageView mAlertIcon = findViewById(R.id.alert_image);
        TextView mAlertNotice = findViewById(R.id.alert_notice);

         boolean isSuccessful = Objects.requireNonNull(getIntent().getExtras()).getBoolean("status", false);

         if(isSuccessful) {
             mAlertIcon.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.tick, null));
             mAlertNotice.setText("Submission Successful");
         } else {
             mAlertIcon.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.risk, null));
             mAlertNotice.setText("Submission not Successful");
         }
    }
}
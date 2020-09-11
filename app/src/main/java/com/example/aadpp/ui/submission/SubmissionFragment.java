package com.example.aadpp.ui.submission;

import androidx.core.content.res.ResourcesCompat;
import androidx.lifecycle.ViewModelProviders;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.aadpp.R;
import com.example.aadpp.Retrofit.ApiInterface;
import com.example.aadpp.SubmitDialogueActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SubmissionFragment extends Fragment {

    private SubmissionViewModel mViewModel;
    private EditText mFirstNameInput, mLastNameInput, mEmailInput, mUrlInput;
    private TextView mSubmitBtn;
    private ImageButton mBackBtn;
    public LinearLayout mInputLayout;

    public static SubmissionFragment newInstance() {
        return new SubmissionFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.submission_fragment, container, false);


        mFirstNameInput = view.findViewById(R.id.first_name_input);
        mLastNameInput = view.findViewById(R.id.last_name_input);
        mEmailInput = view.findViewById(R.id.email_input);
        mUrlInput = view.findViewById(R.id.github_input);
        mSubmitBtn = view.findViewById(R.id.submit_btn);

        mBackBtn = view.findViewById(R.id.back_btn);
        mInputLayout = view.findViewById(R.id.input_layout);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        mSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateForm()) {
                    String firstName = mFirstNameInput.getText().toString().trim();
                    String lastName =  mLastNameInput.getText().toString().trim();
                    String email = mEmailInput.getText().toString().trim();
                    String url = mUrlInput.getText().toString().trim();

                    mInputLayout.setVisibility(View.INVISIBLE);
//                    mBgOverlay.setVisibility(View.VISIBLE);

                    Intent popup = new Intent(getActivity(), SubmitDialogueActivity.class);
                    popup.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                    popup.putExtra("first_name", firstName);
                    popup.putExtra("last_name", lastName);
                    popup.putExtra("email", email);
                    popup.putExtra("url", url);
                    startActivity(popup);

                }
            }
        });

        mBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

    }



    private boolean validateForm() {
        boolean valid = true;

        //first name validation
        String name = mFirstNameInput.getText().toString();
        if (TextUtils.isEmpty(name)) {
            mFirstNameInput.setError("Required");
            valid = false;
        } else {
            mFirstNameInput.setError(null);
        }

        //last name validation
        String lastName = mLastNameInput.getText().toString();
        if (TextUtils.isEmpty(lastName)) {
            mLastNameInput.setError("Required");
            valid = false;
        } else {
            mLastNameInput.setError(null);
        }


        //email validation
        String email = mEmailInput.getText().toString();
        if (TextUtils.isEmpty(email) | !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmailInput.setError("Not a valid email address.");
            valid = false;
        } else {
            mEmailInput.setError(null);
        }

        //phone validation
        String url = mUrlInput.getText().toString();
        if (TextUtils.isEmpty(url) | !Patterns.WEB_URL.matcher(url).matches()) {
            mUrlInput.setError("Not a valid url");
            valid = false;
        } else {
            mUrlInput.setError(null);
        }


        return valid;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(isAdded()) {
            mInputLayout.setVisibility(View.VISIBLE);
        }
    }
}
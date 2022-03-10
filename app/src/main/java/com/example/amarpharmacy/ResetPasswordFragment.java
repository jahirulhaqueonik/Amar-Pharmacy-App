package com.example.amarpharmacy;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;


public class ResetPasswordFragment extends Fragment {
    // variables

    private EditText registerEmail;
    private Button resetPasswordButton;
    private TextView goBack;

    private FrameLayout parentFrameLayout;

    private ViewGroup emailIconContainer;
    private ImageView emailIcon;
    private TextView emailIconText;
    private ProgressBar progressBar;

    private FirebaseAuth firebaseAuth;

    public ResetPasswordFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reset_password, container, false);

        registerEmail = view.findViewById(R.id.forgot_password_email);
        resetPasswordButton = view.findViewById(R.id.forgot_password_btn);
        goBack = view.findViewById(R.id.forgot_password_go_back);
        parentFrameLayout = getActivity().findViewById(R.id.register_frame_layout);

        emailIconContainer = view.findViewById(R.id.forgot_password_email__icon_container);
        emailIcon = view.findViewById(R.id.forgot_password_email_icon);
        emailIconText = view.findViewById(R.id.forgot_password_email_icon_text);
        progressBar = view.findViewById(R.id.forgot_password_progrssbar);

        firebaseAuth = FirebaseAuth.getInstance();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @NonNull Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        registerEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        resetPasswordButton.setOnClickListener(v -> {

            TransitionManager.beginDelayedTransition(emailIconContainer);
            emailIconText.setVisibility(View.GONE);

            TransitionManager.beginDelayedTransition(emailIconContainer);
            emailIcon.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.VISIBLE);

            resetPasswordButton.setEnabled(false);
            resetPasswordButton.setTextColor(Color.argb(50, 255, 255, 255));

            firebaseAuth.sendPasswordResetEmail(registerEmail.getText().toString())
                    .addOnCompleteListener(this::onComplete);
        });

        goBack.setOnClickListener(view1 -> setFragment(new SignInFragment()));
    }

    private void checkInputs() {
        if (TextUtils.isEmpty(registerEmail.getText())) {
            resetPasswordButton.setEnabled(false);
            resetPasswordButton.setTextColor(Color.argb(50, 255, 255, 255));
        } else {
            resetPasswordButton.setEnabled(true);
            resetPasswordButton.setTextColor(Color.rgb(255, 255, 255));
        }
    }

    private void setFragment(Fragment fragment) {

        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_from_left, R.anim.slideout_from_right);
        fragmentTransaction.replace(parentFrameLayout.getId(), fragment);
        fragmentTransaction.commit();
    }

    private void onComplete(Task<Void> task) {
        if (task.isSuccessful()) {

            ScaleAnimation scaleAnimation = new ScaleAnimation(1, 0, 1, 0, emailIcon.getWidth() / 2, emailIcon.getHeight() / 2);
            scaleAnimation.setDuration(100);
            scaleAnimation.setInterpolator(new AccelerateInterpolator());
            scaleAnimation.setRepeatMode(Animation.REVERSE);
            scaleAnimation.setRepeatCount(1);



            emailIcon.startAnimation(scaleAnimation);


        } else {
            String error = task.getException().getMessage();
//Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();

            resetPasswordButton.setEnabled(true);
            resetPasswordButton.setTextColor(Color.rgb(255,255,255));

            resetPasswordButton.setEnabled(true);
            resetPasswordButton.setTextColor(Color.rgb(255, 255, 255));

            emailIconText.setText(error);
            emailIconText.setTextColor(getResources().getColor(R.color.purple_700));
            TransitionManager.beginDelayedTransition(emailIconContainer);
            emailIconText.setVisibility(View.VISIBLE);
        }
        progressBar.setVisibility(View.GONE);

    }
}
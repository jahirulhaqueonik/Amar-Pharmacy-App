package com.example.amarpharmacy;

import static com.example.amarpharmacy.RegisterActivity.onResetPasswordFragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;


public class SignInFragment extends Fragment {

    public SignInFragment () {

    }

    private TextView dontHaveAnAccount;
    private FrameLayout parentFrameLayout;
    private EditText email;
    private EditText passWord;
    private ImageButton closeButton;
    private Button signInButton;
    private TextView forgotPassword;
    private ProgressBar progressBar;
    private FirebaseAuth firebaseAuth;
    private final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment0

        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);
        dontHaveAnAccount = view.findViewById(R.id.tv_dont_have_an_account);
        parentFrameLayout = getActivity().findViewById(R.id.register_frame_layout);

        forgotPassword = view.findViewById(R.id.sign_in_forgot_password);

        email = view.findViewById(R.id.sign_in_email);
        passWord = view.findViewById(R.id.sign_in_password);

        progressBar = view.findViewById(R.id.sign_in_progressbar);

        closeButton = view.findViewById(R.id.sign_in_close_btn);
        signInButton = view.findViewById(R.id.sign_in_btn);

        firebaseAuth = FirebaseAuth.getInstance();

        return view;
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle saveInstanceState){
        super.onViewCreated(view, saveInstanceState);

        dontHaveAnAccount.setOnClickListener(v ->{
            setFragment(new SignUpFragment());
        } );

        //forgot password
        forgotPassword.setOnClickListener(view12 -> {
            onResetPasswordFragment = true;
            setFragment(new ResetPasswordFragment());
        });

        //close btn
        closeButton.setOnClickListener(view1 -> { mainIntent(); });

        email.addTextChangedListener(new TextWatcher() {
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
        passWord.addTextChangedListener(new TextWatcher() {
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
        signInButton.setOnClickListener(view13 -> checkEmailAndPassword());

        
    }

    private void setFragment(Fragment fragment) {

        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_from_right,R.anim.slideout_from_left);
        fragmentTransaction.replace(parentFrameLayout.getId(),fragment);
        fragmentTransaction.commit();
    }

    private void checkInputs(){
        if (!TextUtils.isEmpty(email.getText())){
            if (!TextUtils.isEmpty(passWord.getText())){
                signInButton.setEnabled(true);
                signInButton.setTextColor(Color.rgb(255,255,255));

            }else {
                signInButton.setEnabled(false);
                signInButton.setTextColor(Color.argb(50,255,255,255));
            }

        }else{
            signInButton.setEnabled(false);
            signInButton.setTextColor(Color.argb(50,255,255,255));
        }
    }
    private void checkEmailAndPassword(){
        if (email.getText().toString().matches(emailPattern)){
            if (passWord.length()>=8){
                progressBar.setVisibility(View.VISIBLE);
                signInButton.setEnabled(false);
                signInButton.setTextColor(Color.argb(50,255,255,255));

                firebaseAuth.signInWithEmailAndPassword(email.getText().toString(),passWord.getText().toString())
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()){
                                mainIntent();
                            }else {
                                progressBar.setVisibility(View.INVISIBLE);
                                signInButton.setEnabled(true);
                                signInButton.setTextColor(Color.rgb(255,255,255));
                                String error = task.getException().getMessage();
                                Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
                            }
                        });
            }else{
                Toast.makeText(getActivity(),"Incorrect email or password", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(getActivity(),"Incorrect email or password", Toast.LENGTH_SHORT).show();
        }
    }

    private void mainIntent(){

        Intent mainIntent = new Intent(getActivity(), HomeActivity.class);
        startActivity(mainIntent);
        getActivity().finish();
    }

}
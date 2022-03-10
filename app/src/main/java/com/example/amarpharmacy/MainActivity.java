package com.example.amarpharmacy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.window.SplashScreen;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();

        SystemClock.sleep(3000);

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = firebaseAuth.getCurrentUser();

        if (currentUser == null ){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent registerIntent = new Intent(MainActivity.this,RegisterActivity.class);
                    startActivity(registerIntent);
                    finish();
                }
            }, 0);
        }else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent mainIntent = new Intent(MainActivity.this,HomeActivity.class);
                    startActivity(mainIntent);
                    finish();
                }
            }, 0);
        }
    }
}
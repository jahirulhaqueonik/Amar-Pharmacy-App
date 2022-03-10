package com.example.amarpharmacy;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.amarpharmacy.databinding.ActivityHomeBinding;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private FrameLayout frameLayout;
    private static final int MAIN_FRAGMENT = 0;
    private static final int CART_FRAGMENT = 1;
    private static final int ORDERS_FRAGMENT = 2;
    private static final int ACCOUNT_FRAGMENT = 3;

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityHomeBinding binding;
    private NavigationView navigationView;
    private static int currentFragment = -1;
    private ImageView actionBarLogo;
    public  DrawerLayout drawer;
    private FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.appBarHome.toolbar);

        //my code
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        actionBarLogo = findViewById(R.id.actionbar_logo);

        drawer = binding.drawerLayout;
        navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
     /*   mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();*/

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.main_frame_layout);
        NavController navController = Objects.requireNonNull(navHostFragment).getNavController();
        //   NavController navController = findNavController(this, R.id.nav_host_fragment_content_home);
        //   NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        //   NavigationUI.setupWithNavController(navigationView, navController);

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(0).setChecked(true);
        //  setSupportActionBar(Objects.requireNonNull(binding.appBarHome).toolbar);
        frameLayout = findViewById(R.id.main_frame_layout);
        setFragment(new MainFragment(), MAIN_FRAGMENT);
        //setSupportActionBar(Objects.requireNonNull(binding.appBarHome).toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        if (currentFragment == MAIN_FRAGMENT) {
            Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
            getMenuInflater().inflate(R.menu.home, menu);
        }
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //statements
        if (id == R.id.main_search_icon) {
            //todo: search
            invalidateOptionsMenu();

            return true;
        } else if (id == R.id.main_notification_icon) {
            //todo: notification system
            return true;
        } else if (id == R.id.main_cart_icon) {
            gotoFragment("My Cart", new MyCartFragment(), CART_FRAGMENT);
            //myCart();
            return true;

        }

        return super.onOptionsItemSelected(item);
    }

    private void gotoFragment(String title, Fragment fragment, int fragmentNo) {
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        actionBarLogo.setVisibility(View.GONE);
        invalidateOptionsMenu();
        getSupportActionBar().setTitle(title);
        setFragment(fragment, fragmentNo);
        if (fragmentNo == CART_FRAGMENT) {
            navigationView.getMenu().getItem(3).setChecked(true);
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    //@Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        //Handle Navigation View Item Clicks Here.

        int id = item.getItemId();

        if (id == R.id.nav_amar_pharmacy) {
            actionBarLogo.setVisibility(View.VISIBLE);
            invalidateOptionsMenu();
            setFragment(new MainFragment(), MAIN_FRAGMENT);
        } else if (id == R.id.nav_my_account) {
            gotoFragment("My Account", new MYAccountFragment(), ACCOUNT_FRAGMENT);
        } else if (id == R.id.nav_my_orders) {
            gotoFragment("My Orders", new MyOrdersFragment(), ORDERS_FRAGMENT);
            // myCart();

        } else if (id == R.id.nav_my_cart) {
            gotoFragment("My Cart", new MyCartFragment(), CART_FRAGMENT);
            // myCart();
        } else if (id == R.id.nav_my_wishlist) {

        } else if (id == R.id.nav_sign_out) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setFragment(Fragment fragment, int fragmentNo) {

        if (fragmentNo != currentFragment) {
            currentFragment = fragmentNo;
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            FragmentTransaction replace = fragmentTransaction.replace(frameLayout.getId(), fragment);
            fragmentTransaction.commit();
        }
    }

/*    public boolean onSupportNavigateUp() {
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.main_frame_layout);
        NavController navController = Objects.requireNonNull(navHostFragment).getNavController();
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }*/


}
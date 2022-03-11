package com.example.amarpharmacy;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

//import android.widget.Toolbar;

public class ProductDetailsActivity extends AppCompatActivity {

    ProductDetailsAdapter productDetailsAdapter;
    private ViewPager productImagesViewPager;
    private TabLayout viewpagerIndicator;
    private ViewPager2 productDetailsViewpager;
    private TabLayout productDetailsTablayout;
    private String[] titles = new String[]{"Description","Other Details"};

    private Button buyNowBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // getSupportActionBar().getDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String title = getIntent().getStringExtra("productTitle");
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowTitleEnabled(true);
        //getSupportActionBar().setTitle("Delivery");
        /*ActivityProductDetailsBinding binding = ActivityProductDetailsBinding.inflate(getLayoutInflater());
        setSupportActionBar(Objects.requireNonNull(Objects.requireNonNull(binding.appBar).toolbar));
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(true);*/

     /*   @NonNull ActivityProductDetailsBinding binding = ActivityProductDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());*/
      //  setSupportActionBar(binding.appBarHome.toolbar);

       // getSupportActionBar().hide();
        productImagesViewPager = findViewById(R.id.product_images_viewpager);
        viewpagerIndicator = findViewById(R.id.viewpager_indicator);
        productDetailsViewpager = findViewById(R.id.product_details_viewpager);
        productDetailsTablayout = findViewById(R.id.product_details_layout);
        buyNowBtn=findViewById(R.id.buy_now_btn);


        List<Integer> productImages = new ArrayList<>();
        productImages.add(R.drawable.imron_hem_7120);
        productImages.add(R.drawable.imron_hem_7120);
        productImages.add(R.drawable.imron_hem_7120);
        productImages.add(R.drawable.imron_hem_7120);
        productImages.add(R.drawable.imron_hem_7120);

        productDetailsAdapter = new ProductDetailsAdapter(this);

        ProductImagesAdapter productImagesAdapter = new ProductImagesAdapter(productImages);
        productImagesViewPager.setAdapter(productImagesAdapter);
        viewpagerIndicator.setupWithViewPager(productImagesViewPager, true);

        productDetailsViewpager.setAdapter(productDetailsAdapter);
        // productDetailsViewpager.setAdapter(new ProductDetailsAdapter(getSupportFragmentManager(),productDetailsTablayout.getTabCount()));
        new TabLayoutMediator(productDetailsTablayout, productDetailsViewpager, ((tab, position) -> tab.setText(titles[position]))).attach();

        productDetailsTablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                productDetailsViewpager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        buyNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buyNowBtn.setText("Thank You For Your Order Request.We Will Process");
              //  Intent deliveryIntent = new Intent(ProductDetailsActivity.this,HomeActivity.class);
            //    startActivity(deliveryIntent);
            }
        });

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_and_cart_icon, menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //statements
        if (id == android.R.id.home) {
            finish();
            return true;
        } else if (id == R.id.main_cart_icon) {
            //todo: cart system
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}





/*
else if (id == R.id.main_search_icon) {
        //todo search
        return true;
        }
*/



























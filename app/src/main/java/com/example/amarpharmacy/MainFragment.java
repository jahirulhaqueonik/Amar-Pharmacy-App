package com.example.amarpharmacy;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;


public class MainFragment extends Fragment {

    public MainFragment() {
        // Required empty public constructor
    }

    private CategoryAdapter categoryAdapter;
    private HomePageAdapter adapter;
    private List<CategoryModel> categoryModelList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        RecyclerView categoryRecyclerView = view.findViewById(R.id.category_recylerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        categoryRecyclerView.setLayoutManager(layoutManager);
        categoryModelList = new ArrayList<>();
        categoryAdapter = new CategoryAdapter(Collections.unmodifiableList(categoryModelList));
        categoryRecyclerView.setAdapter(categoryAdapter);

        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseFirestore.collection("CATEGORIES").orderBy("index").get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                            categoryModelList.add(new CategoryModel(Objects.requireNonNull(documentSnapshot.get("icon")).toString(),
                                    Objects.requireNonNull(documentSnapshot.get("categoryName")).toString()));
                        }
                        categoryAdapter.notifyDataSetChanged();
                    } else {
                        String error = Objects.requireNonNull(task.getException()).getMessage();
                        Toast.makeText(getContext(), error, Toast.LENGTH_LONG).show();
                    }
                });


        //List<HorizontalProductModel> horizontalProductModelList = new ArrayList<>();
        //   horizontalProductModelList.add(new HorizontalProductModel(R.drawable.imron_hem_7120, "Clungene Cov...", "৳699.00"));
        //   horizontalProductModelList.add(new HorizontalProductModel(R.drawable.imron_hem_7120, "Clungene Cov...", "৳699.00"));
        //   horizontalProductModelList.add(new HorizontalProductModel(R.drawable.imron_hem_7120, "Clungene Cov...", "৳699.00"));
        //    horizontalProductModelList.add(new HorizontalProductModel(R.drawable.imron_hem_7120, "Clungene Cov...", "৳699.00"));
        //     horizontalProductModelList.add(new HorizontalProductModel(R.drawable.imron_hem_7120, "Clungene Cov...", "৳699.00"));
        //     horizontalProductModelList.add(new HorizontalProductModel(R.drawable.imron_hem_7120, "Clungene Cov...", "৳699.00"));
        //     horizontalProductModelList.add(new HorizontalProductModel(R.drawable.imron_hem_7120, "Clungene Cov...", "৳699.00"));
        //     horizontalProductModelList.add(new HorizontalProductModel(R.drawable.imron_hem_7120, "Clungene Cov...", "৳699.00"));
        //    horizontalProductModelList.add(new HorizontalProductModel(R.drawable.imron_hem_7120, "Clungene Cov...", "৳699.00"));
        //     horizontalProductModelList.add(new HorizontalProductModel(R.drawable.imron_hem_7120, "Clungene Cov...", "৳699.00"));
        //////Horizontal Product Layout

        //////////////////////////////////////
        RecyclerView homePageRecyclerView = view.findViewById(R.id.home_page_recycler_view);
        LinearLayoutManager testingLayoutManager = new LinearLayoutManager(getContext());
        testingLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        homePageRecyclerView.setLayoutManager(testingLayoutManager);
        List<HomePageModel> homePageModelList = new ArrayList<>();
        adapter = new HomePageAdapter(homePageModelList);
        homePageRecyclerView.setAdapter(adapter);


        firebaseFirestore.collection("CATEGORIES")
                .document("HOME").collection("TOP_DEALS").orderBy("index").get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                            // long view_type = (long) documentSnapshot.get("view_type");
                            if ((long) documentSnapshot.get("view_type") == 0) {
                                List<SliderModel> sliderModelList = new ArrayList<>();
                                long no_of_banners = (long) documentSnapshot.get("no_of_banners");
                                for (long x = 1; x < no_of_banners + 1; x++) {
                                    sliderModelList.add(new SliderModel(Objects.requireNonNull(documentSnapshot.get("banner_" + x)).toString()
                                            , Objects.requireNonNull(documentSnapshot.get("banner_" + x + "_background")).toString()));
                                }
                                homePageModelList.add(new HomePageModel(0, sliderModelList));
                            } else if ((long) documentSnapshot.get("view_type") == 1) {
                                List<HorizontalProductModel> horizontalProductModelList = new ArrayList<>();
                                long no_of_products = (long) documentSnapshot.get("no_of_products");
                                for (long x = 1; x < no_of_products + 1; x++) {

                                    horizontalProductModelList.add(new HorizontalProductModel(documentSnapshot.get("product_ID_" + x).toString()
                                            , documentSnapshot.get("product_image_" + x).toString()
                                            , documentSnapshot.get("product_title_" + x).toString()
                                            , documentSnapshot.get("product_price_" + x).toString()));
                                }
                                homePageModelList.add(new HomePageModel(1, documentSnapshot.get("layout_title").toString()
                                        , documentSnapshot.get("layout_background").toString(), horizontalProductModelList));

                            } else if ((long) documentSnapshot.get("view_type") == 2) {
                            } else {
                                return;
                            }
                        }
                        adapter.notifyDataSetChanged();
                    } else {
                        String error = Objects.requireNonNull(task.getException()).getMessage();
                        Toast.makeText(getContext(), error, Toast.LENGTH_LONG).show();
                    }

                });

        return view;
    }
}
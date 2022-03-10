package com.example.amarpharmacy;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyCartFragment extends Fragment {



    public MyCartFragment() {
        // Required empty public constructor
    }

    private RecyclerView cartItemsRecyclerView;

    protected Button continueBtn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_my_cart, container, false);

        continueBtn =view.findViewById(R.id.cart_continue_btn);

        cartItemsRecyclerView = view.findViewById(R.id.cart_items_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        cartItemsRecyclerView.setLayoutManager(layoutManager);

        List<CartItemModel> cartItemModelList = new ArrayList<>();
        cartItemModelList.add(new CartItemModel(0,R.drawable.imron_hem_7120,"Covid 19 Device","৳699.00",1));
        cartItemModelList.add(new CartItemModel(0,R.drawable.imron_hem_7120,"Covid 19 Device","৳699.00",1));
        cartItemModelList.add(new CartItemModel(0,R.drawable.imron_hem_7120,"Covid 19 Device","৳699.00",1));
        cartItemModelList.add(new CartItemModel(1,"Price (3)","৳699.00","Free","৳2100"));

        CartAdapter cartAdapter = new CartAdapter(cartItemModelList);
        cartItemsRecyclerView.setAdapter(cartAdapter);
        cartAdapter.notifyDataSetChanged();


        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent deliveryIntent = new Intent(getContext(),DeliveryActivity.class);
                getContext().startActivity(deliveryIntent);
            }
        });

        return view;
    }
}
package com.example.amarpharmacy;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class MyOrdersFragment extends Fragment {

    // TODO: Rename and change types and number of parameters
    public MyOrdersFragment() {



    }

    private RecyclerView myOrderRecyclerView;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_my_orders, container, false);

        myOrderRecyclerView = view.findViewById(R.id.my_order_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        myOrderRecyclerView.setLayoutManager(layoutManager);
        List<MyOrderItemModel> myOrderItemModelList = new ArrayList<>();
        myOrderItemModelList.add(new MyOrderItemModel(R.drawable.imron_hem_7120, "Blood Pressure Monitor", "Delivered on Monday,15th March 2022"));
        myOrderItemModelList.add(new MyOrderItemModel(R.drawable.imron_hem_7120, "Blood Pressure Monitor", "Delivered on Monday,15th March 2022"));
        myOrderItemModelList.add(new MyOrderItemModel(R.drawable.imron_hem_7120, "Blood Pressure Monitor", "Cancelled"));
        myOrderItemModelList.add(new MyOrderItemModel(R.drawable.imron_hem_7120, "Blood Pressure Monitor", "Delivered on Monday,15th March 2022"));

        MyOrderAdapter myOrderAdapter = new MyOrderAdapter(myOrderItemModelList);
        myOrderRecyclerView.setAdapter(myOrderAdapter);
        myOrderAdapter.notifyDataSetChanged();
        return view;
    }
}
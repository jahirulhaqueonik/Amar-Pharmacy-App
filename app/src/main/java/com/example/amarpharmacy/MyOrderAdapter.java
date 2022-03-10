package com.example.amarpharmacy;

import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyOrderAdapter extends RecyclerView.Adapter<MyOrderAdapter.Viewholder> {

    private final List<MyOrderItemModel> myOrderItemModelList;


    public MyOrderAdapter(List<MyOrderItemModel> myOrderItemModelList) {
        this.myOrderItemModelList = myOrderItemModelList;

    }

    @NonNull
    @Override
    public MyOrderAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.my_order_item_layout,viewGroup,false);
        return new Viewholder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyOrderAdapter.Viewholder viewholder, int position) {

        int resource = myOrderItemModelList.get(position).getProductImage();
        String title = myOrderItemModelList.get(position).getProductTitle();
        String deliveryDate = myOrderItemModelList.get(position).getDeliveryStatus();

        viewholder.setData(resource,title,deliveryDate);
    }

    @Override
    public int getItemCount() {
        return myOrderItemModelList.size();
    }

    public static class Viewholder extends RecyclerView.ViewHolder{

        private final ImageView productImage;
        private final ImageView orderIndicator;
        private final TextView productTitle;
        private final TextView deliveryStatus;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            productImage= itemView.findViewById(R.id.product_image);
            productTitle = itemView.findViewById(R.id.product_title);
            orderIndicator = itemView.findViewById(R.id.order_indicator);
            deliveryStatus = itemView.findViewById(R.id.order_delivered_date);

        }
        private void  setData(int resource, String title,String deliveryDate){
            productImage.setImageResource(resource);
            productTitle.setText(title);
            if(deliveryDate.equals("Cancelled")){
                orderIndicator.setImageTintList(ColorStateList.valueOf(
                        itemView.getContext().getResources().getColor(R.color.purple_700)));
            }else {
                orderIndicator.setImageTintList(ColorStateList.valueOf(
                        itemView.getContext().getResources().getColor(R.color.black)));
            }
            deliveryStatus.setText(deliveryDate);
        }
    }
}

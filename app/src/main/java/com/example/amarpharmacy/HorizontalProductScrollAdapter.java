package com.example.amarpharmacy;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import org.w3c.dom.Text;

import java.util.List;

public class HorizontalProductScrollAdapter extends RecyclerView.Adapter<HorizontalProductScrollAdapter.ViewHolder> {

    private final List<HorizontalProductModel> horizontalProductModelList;

    public HorizontalProductScrollAdapter(List<HorizontalProductModel> horizontalProductModelList) {
        this.horizontalProductModelList = horizontalProductModelList;
    }

    @NonNull
    @Override
    public HorizontalProductScrollAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.horizontal_scroll_item_layout, viewGroup, false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HorizontalProductScrollAdapter.ViewHolder viewholder, int position) {

        String resource = horizontalProductModelList.get(position).getProductImage();
        String title = horizontalProductModelList.get(position).getProductTitle();
        String price = horizontalProductModelList.get(position).getProductPrice();

        viewholder.setProductImage(resource);
        viewholder.setProductTitle(title);
        viewholder.setProductPrice(price);
    }

    @Override
    public int getItemCount() {

        if (horizontalProductModelList.size() > 8) {
            return 8;
        } else {
            horizontalProductModelList.size();
        }

        return horizontalProductModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView productImage;
        private TextView productTitle;
        private TextView productPrice;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.h_s_product_image);
            productTitle = itemView.findViewById(R.id.h_s_product_title);
            productPrice = itemView.findViewById(R.id.h_s_product_price);

            itemView.setOnClickListener(v -> {

                Intent productDetailsIntent = new Intent(itemView.getContext(),ProductDetailsActivity.class);
                itemView.getContext().startActivity(productDetailsIntent);

            });

        }

        private void setProductImage(String resource) {
            Glide.with(itemView.getContext()).load(resource).apply(new RequestOptions()
                    .placeholder(R.mipmap.home)).into(productImage);
        }

        @SuppressLint("SetTextI18n")
        private void setProductTitle(String title) {
            productTitle.setText(title);
        }

        private void setProductPrice(String price) {
            productPrice.setText(price);
        }
    }
}

























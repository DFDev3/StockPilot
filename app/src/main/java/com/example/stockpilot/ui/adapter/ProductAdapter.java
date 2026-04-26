package com.example.stockpilot.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stockpilot.R;
import com.example.stockpilot.model.Product;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Locale;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private ArrayList<Product> productList;
    private OnItemClickListener onItemClickListener;

    public ProductAdapter(ArrayList<Product> productList) {
        this.productList = productList;
    }

    public void setDataSet(ArrayList<Product> productList) {
        this.productList = productList;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View myView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ViewHolder(myView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.bind(product);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView productNameTextView;
        private final TextView productPriceTextView;
        private final ImageView productImageView;
        private final Button removeButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            productNameTextView = itemView.findViewById(R.id.tv_product_name);
            productPriceTextView = itemView.findViewById(R.id.tv_product_price);
            productImageView = itemView.findViewById(R.id.iv_product_image);
            removeButton = itemView.findViewById(R.id.btn_remove_product);
        }

        public void bind(Product product) {
            productNameTextView.setText(product.getName());
            productPriceTextView.setText(String.format(Locale.getDefault(), "$%,.2f", product.getPrice()));
            Picasso.get()
                    .load(product.getImageUrl())
                    .error(R.drawable.ic_launcher_background)
                    .into(productImageView);

            if (onItemClickListener != null) {
                itemView.setOnClickListener(view -> {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        onItemClickListener.onItemClick(product, position);
                    }
                });

                removeButton.setOnClickListener(view -> {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        onItemClickListener.onRemoveClick(product, position);
                    }
                });
            }
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Product product, int position);

        void onRemoveClick(Product product, int position);
    }
}
package com.example.stockpilot.ui;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.stockpilot.R;
import com.example.stockpilot.model.Product;
import com.squareup.picasso.Picasso;

import java.util.Locale;

public class ProductDetailActivity extends AppCompatActivity {
    public static final String EXTRA_PRODUCT = "extra_product";

    private TextView productTitleTextView;
    private TextView productPriceTextView;
    private ImageView productImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        setTitle(getString(R.string.product_detail_title));

        productTitleTextView = findViewById(R.id.tv_product_name);
        productImageView = findViewById(R.id.iv_product_image);
        productPriceTextView = findViewById(R.id.tv_product_price);

        Product selectedProduct = (Product) getIntent().getSerializableExtra(EXTRA_PRODUCT);
        if (selectedProduct == null) {
            Toast.makeText(this, getString(R.string.product_not_found), Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        productTitleTextView.setText(selectedProduct.getName());
        productPriceTextView.setText(String.format(Locale.getDefault(), "$%,.2f", selectedProduct.getPrice()));
        Picasso.get()
                .load(selectedProduct.getImageUrl())
                .error(R.drawable.ic_launcher_background)
                .into(productImageView);
    }
}
package com.example.stockpilot.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stockpilot.R;
import com.example.stockpilot.model.Product;
import com.example.stockpilot.ui.adapter.ProductAdapter;

import java.util.ArrayList;

public class ProductListActivity extends AppCompatActivity {
    private static final String PREFERENCES_NAME = "stockpilot_prefs";

    private ArrayList<Product> productList;
    private RecyclerView productsRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(getString(R.string.product_list_title));

        loadSampleData();
        productsRecyclerView = findViewById(R.id.rv_products);
        ProductAdapter adapter = new ProductAdapter(productList);
        adapter.setOnItemClickListener(new ProductAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Product product, int position) {
                Intent intent = new Intent(ProductListActivity.this, ProductDetailActivity.class);
                intent.putExtra(ProductDetailActivity.EXTRA_PRODUCT, product);
                startActivity(intent);
            }

            @Override
            public void onRemoveClick(Product product, int position) {
                productList.remove(position);
                adapter.setDataSet(productList);
                Toast.makeText(
                        ProductListActivity.this,
                        getString(R.string.product_removed_message, product.getName()),
                        Toast.LENGTH_SHORT
                ).show();
            }
        });

        productsRecyclerView.setAdapter(adapter);
        productsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void loadSampleData() {
        Product product1 = new Product(
                "Laptop Pro 14",
                3000000.0,
                "https://images.unsplash.com/photo-1496181133206-80ce9b88a853"
        );

        Product product2 = new Product(
                "Mechanical Keyboard",
                200000.0,
                "https://images.unsplash.com/photo-1511467687858-23d96c32e4ae"
        );

        Product product3 = new Product(
                "Wireless Mouse",
                150000.0,
                "https://images.unsplash.com/photo-1527814050087-3793815479db"
        );

        productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product2);
        productList.add(product3);
    }

    public void onLogoutClick(View view) {
        SharedPreferences preferences = getSharedPreferences(PREFERENCES_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();

        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    public void onAddProductClick(View view) {
        startActivity(new Intent(this, ProductFormActivity.class));
    }
}
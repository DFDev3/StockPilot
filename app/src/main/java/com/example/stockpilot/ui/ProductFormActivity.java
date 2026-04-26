package com.example.stockpilot.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.stockpilot.R;
import com.example.stockpilot.model.Product;
import com.google.firebase.firestore.FirebaseFirestore;

public class ProductFormActivity extends AppCompatActivity {
    private EditText productNameEditText;
    private EditText productImageUrlEditText;
    private EditText productPriceEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_form);

        setTitle(getString(R.string.add_product));

        productNameEditText = findViewById(R.id.et_product_name);
        productPriceEditText = findViewById(R.id.et_product_price);
        productImageUrlEditText = findViewById(R.id.et_product_image_url);
    }

    public void onSaveProductClick(View view) {
        String productName = productNameEditText.getText().toString().trim();
        String productImageUrl = productImageUrlEditText.getText().toString().trim();
        String productPriceRaw = productPriceEditText.getText().toString().trim();

        if (TextUtils.isEmpty(productName) || TextUtils.isEmpty(productImageUrl) || TextUtils.isEmpty(productPriceRaw)) {
            Toast.makeText(this, getString(R.string.fill_all_fields), Toast.LENGTH_SHORT).show();
            return;
        }

        double productPrice;
        try {
            productPrice = Double.parseDouble(productPriceRaw);
        } catch (NumberFormatException exception) {
            Toast.makeText(this, getString(R.string.invalid_price), Toast.LENGTH_SHORT).show();
            return;
        }

        Product product = new Product();
        product.setName(productName);
        product.setPrice(productPrice);
        product.setImageUrl(productImageUrl);

        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        firestore.collection("products")
                .add(product)
                .addOnSuccessListener(documentReference -> {
                    Toast.makeText(this, getString(R.string.product_saved), Toast.LENGTH_SHORT).show();
                    finish();
                })
                .addOnFailureListener(exception -> Toast.makeText(this, getString(R.string.product_save_error), Toast.LENGTH_SHORT).show());
    }
}
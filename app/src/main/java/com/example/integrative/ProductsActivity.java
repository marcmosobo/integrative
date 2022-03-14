package com.example.integrative;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.integrative.database.Database;
import com.example.integrative.models.Product;

import java.util.ArrayList;

public class ProductsActivity extends AppCompatActivity {
    private RecyclerView recyclerview;
    Database database;
    ArrayList<Product> products;
    RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        recyclerview = findViewById(R.id.recycler_view);

        database = new Database(this);
        products = database.select_all_products();
        recyclerview.setHasFixedSize(true);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ProductAdapter(products, this);
        recyclerview.setAdapter(adapter);

    }
}
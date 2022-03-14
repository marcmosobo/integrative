package com.example.integrative;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.integrative.database.Database;
import com.example.integrative.models.Product;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {
    private EditText name, buying_price, selling_price;
    private MaterialButton button_add, button_view;
    private Database database;
    private Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_main);
        button_add = findViewById(R.id.add);
        button_view = findViewById(R.id.view);
        name = findViewById(R.id.edit_text_product_name);
        buying_price = findViewById(R.id.edit_text_buying_price);
        selling_price = findViewById(R.id.edit_text_selling_price);
        database = new Database(this);

        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveProduct();

            }


        });

        button_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ProductsActivity.class);
                startActivity(intent);
            }
        });
    }
    public void saveProduct(){
        String productName = name.getText().toString();
        String buyingPrice = buying_price.getText().toString();
        String sellingPrice = selling_price.getText().toString();
        product = new Product(productName, buyingPrice, sellingPrice);
        if(name.getText().toString().isEmpty()) {
            name.setError("Please enter name");
        }
        else if(buying_price.getText().toString().isEmpty()){
            buying_price.setError("Enter buying price");
        }
        else if(selling_price.getText().toString().isEmpty()){
            selling_price.setError("Enter selling price");
        }
        else{
            boolean insert = database.insert(product);
            if (insert == true){
                Toast.makeText(this, "Product saved successfully", Toast.LENGTH_SHORT).show();
                resetProduct();
            }else{
                Toast.makeText(this, "Product save failed", Toast.LENGTH_SHORT).show();
            }
        }


    }

    public void resetProduct() {
        name.setText("");
        buying_price.setText("");
        selling_price.setText("");
    }
}
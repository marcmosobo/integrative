package com.example.integrative;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.media.Image;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.integrative.models.Product;

import java.util.ArrayList;
import java.util.Objects;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.Viewholder>{
    ArrayList<Product> products;
    Context context;

    public ProductAdapter(ArrayList<Product> products, Context context) {
        this.products = products;
        this.context = context;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item,parent,false);
        return new Viewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final Viewholder holder, final int position) {
        Product product = products.get(position);
        holder.name.setText(product.getProductName());
        holder.buying_price.setText("Ksh " + product.getBuyingPrice());
        holder.selling_price.setText("Ksh " + product.getSellingPrice());

        holder.share_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                String app_url = " https://play.google.com/store/apps/details?id=com.example.integrative";
                String text = "Buy " + product.getProductName() + " for Ksh. " + product.getBuyingPrice()+"." + " Click on " + app_url + " to buy.";
//                Intent shareIntent =   new Intent(android.content.Intent.ACTION_SEND);
                Intent whatsappIntent =   new Intent(android.content.Intent.ACTION_SEND);
                whatsappIntent.setType("text/plain");
                whatsappIntent.setPackage("com.whatsapp");
                whatsappIntent.putExtra(android.content.Intent.EXTRA_TEXT,text);
//                context.startActivity(Intent.createChooser(shareIntent, "Share using"));
                try {
                    Objects.requireNonNull(view.getContext()).startActivity(whatsappIntent);
                } catch (android.content.ActivityNotFoundException ex) {
                    context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=com.whatsapp")));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView name,buying_price,selling_price;
        Image view;
        ImageButton share_button;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.text_view_name);
            buying_price=itemView.findViewById(R.id.text_view_buying_price);
            selling_price=itemView.findViewById(R.id.text_view_selling_price);
            share_button = itemView.findViewById(R.id.share_button);

        }
    }
}

package com.example.integrative.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.integrative.models.Product;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {
    public static final String PRODUCT_TABLE = "products";
    public static final String COL_NAME = "product_name";
    public static final String COL_BUYING_PRICE = "buying_price";
    public static final String COL_SELLING_PRICE = "selling_price";

    public Database(Context context) {
        super(context,  "products.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        String create_table= "create table " + PRODUCT_TABLE + "(" + COL_NAME + " text primary key, " + COL_BUYING_PRICE + " int, "
                + COL_SELLING_PRICE + " int)";
        database.execSQL(create_table);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean insert(Product product){
        SQLiteDatabase database=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_NAME,product.getProductName());
        contentValues.put(COL_BUYING_PRICE,product.getBuyingPrice());
        contentValues.put(COL_SELLING_PRICE,product.getSellingPrice());
        long insert = database.insert(PRODUCT_TABLE, null, contentValues);
        if(insert==-1){
            return false;
        }
        else{
            return true;
        }
    }

    public ArrayList<Product> select_all_products(){
        SQLiteDatabase database=getReadableDatabase();
        ArrayList<Product> products = new ArrayList<>();
        String select_statement="select * FROM " + PRODUCT_TABLE;
        Cursor cursor = database.rawQuery(select_statement, null);
        if (cursor.moveToFirst()){
            do {
                String productName=cursor.getString(0);
                String buyingPrice=cursor.getString(1);
                String  sellingPrice=cursor.getString(2);
                Product product = new Product(productName,buyingPrice,sellingPrice);
                products.add(product);
            } while (cursor.moveToNext());
        }
        return products;
    }

}

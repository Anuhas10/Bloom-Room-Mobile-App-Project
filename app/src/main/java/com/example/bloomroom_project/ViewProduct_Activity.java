package com.example.bloomroom_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class ViewProduct_Activity extends AppCompatActivity {

    private ArrayList<ProductModel> productModelArrayList;
    private DBConnector dbConnector;
    private ProductRvAdapter ProductRvAdapter;
    private RecyclerView ProductRv;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_product);

        productModelArrayList = new ArrayList<>();
        dbConnector = new DBConnector(ViewProduct_Activity.this);

        productModelArrayList = dbConnector.readProduct();

        ProductRvAdapter = new ProductRvAdapter(productModelArrayList, ViewProduct_Activity.this);
        ProductRv = findViewById(R.id.ViewProduct_Rv);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ViewProduct_Activity.this, RecyclerView.VERTICAL, false);
        ProductRv.setLayoutManager(linearLayoutManager);

        ProductRv.setAdapter(ProductRvAdapter);
    }
}
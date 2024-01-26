package com.example.bloomroom_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class ViewOrder_Activity extends AppCompatActivity {

    private ArrayList<OrderModel> OrderModelArrayList;

    private DBConnector dbConnector;

    private com.example.bloomroom_project.OrderRvAdapter OrderRvAdapter;

    private RecyclerView OrderRV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_order);

        //Intializing all variables
        OrderModelArrayList = new ArrayList<OrderModel>();
        dbConnector = new DBConnector(ViewOrder_Activity.this);

        OrderModelArrayList = dbConnector.readOrder();

        //array list to Adapter class
        OrderRvAdapter = new OrderRvAdapter(OrderModelArrayList, ViewOrder_Activity.this);
        OrderRV = findViewById(R.id.ViewOrder_rv);

        //layout manager for Recycler view
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ViewOrder_Activity.this, RecyclerView.VERTICAL, false);
        OrderRV.setLayoutManager(linearLayoutManager);

        //Adapter to Recycler View
        OrderRV.setAdapter(OrderRvAdapter);
    }
}
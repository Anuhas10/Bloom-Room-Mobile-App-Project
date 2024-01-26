package com.example.bloomroom_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Vector;

public class Admin_activity extends AppCompatActivity {

    //Varibales declaration

    private EditText product_id, product_name, product_price, product_qty;
    private Spinner product_spinner;
    private Button add_product, cate_calss, view_product, search_product, view_orders, search_orders, backbtn;

    private DBConnector dbConnector;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        product_id = findViewById(R.id.Product_id);
        product_name = findViewById(R.id.Product_Name);
        product_price = findViewById(R.id.Product_Price);
        product_qty = findViewById(R.id.Product_Qty);

        product_spinner = findViewById(R.id.Product_Spinner);

        add_product = findViewById(R.id.Product_Add);
        cate_calss = findViewById(R.id.Category_move);
        view_product = findViewById(R.id.All_Products);
        search_product = findViewById(R.id.Search_Product);
        view_orders = findViewById(R.id.View_Orders);
        search_orders = findViewById(R.id.Search_Orders);

        backbtn = findViewById(R.id.back_Btn);

        dbConnector = new DBConnector(Admin_activity.this);

        Vector<String> vecCategory=dbConnector.getCategory_Name();

        ArrayAdapter ad = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, vecCategory);

        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        product_spinner.setAdapter(ad);

        add_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String p_id = product_id.getText().toString();
                String p_name = product_name.getText().toString();
                String p_price = product_price.getText().toString();
                String p_qty = product_qty.getText().toString();
                String Category_Id = dbConnector.getCategory_Id(product_spinner.getSelectedItem().toString());

                if (p_name.isEmpty() && p_price.isEmpty() && p_qty.isEmpty()) {
                    Toast.makeText(Admin_activity.this, "Please don't continue without filling balnks!!!", Toast.LENGTH_SHORT).show();
                    return;
                }

                dbConnector.addNewProduct(p_id, p_name, p_price, p_qty, Category_Id);

                Toast.makeText(Admin_activity.this, "Product has been added Susscessfully...", Toast.LENGTH_SHORT).show();

                product_id.setText("");
                product_name.setText("");
                product_price.setText("");
                product_qty.setText("");
                //product_spinner.getSelectedItem();
            }
        });

        cate_calss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admin_activity.this, Category_Activity.class);
                startActivity(intent);
            }
        });

        view_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admin_activity.this, ViewProduct_Activity.class);
                startActivity(intent);
            }
        });


        search_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admin_activity.this, Admin_Product_Search_Activity.class);
                startActivity(intent);
            }
        });


        view_orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admin_activity.this, ViewOrder_Activity.class);
                startActivity(intent);
            }
        });

        search_orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admin_activity.this, AdminOrderSearch_Activity.class);
                startActivity(intent);
            }
        });

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admin_activity.this, Login_AND_Reg_Activity.class);
                startActivity(intent);
            }
        });


    }
}
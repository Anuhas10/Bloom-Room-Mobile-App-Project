package com.example.bloomroom_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class AdminOrderSearch_Activity extends AppCompatActivity {

    private EditText OS_user_id, OS_product_id, OS_product_qty;
    private Button order_search;

    private DBConnector dbConnector;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_order_search);

        OS_product_id = findViewById(R.id.o_Product_id);
        OS_user_id = findViewById(R.id.o_user_id);
        OS_product_qty = findViewById(R.id.o_Qty);
        order_search = findViewById(R.id.o_SearchOrderBtn);

        dbConnector = new DBConnector(AdminOrderSearch_Activity.this);

        order_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String O_id = OS_product_id.getText().toString();

                ArrayList<Order_Class> OrderList = dbConnector.searchOrder(O_id);

                if (OrderList.size()!=0)
                {
                    Order_Class order_class = OrderList.get(0);
                    OS_user_id.setText(order_class.getOrderuserId());
                    OS_product_qty.setText((String.valueOf(order_class.getOrderProduct_qty())));

                    Toast.makeText(getApplicationContext(), "Order You Searched Found...", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "No Order Found!!!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
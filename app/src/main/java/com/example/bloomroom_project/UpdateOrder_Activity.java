package com.example.bloomroom_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateOrder_Activity extends AppCompatActivity {

    private EditText update_Order_user_id, update_Order_product_id, update_Order_product_qty;
    private Button updateorderbtn, deleteorderbtn, Ba_btn;
    private DBConnector dbConnector;

    String original_User_id, original_Product_id, original_Product_qty;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_order);


        update_Order_user_id = findViewById(R.id.Update_UId);
        update_Order_product_id = findViewById(R.id.Update_PID);
        update_Order_product_qty = findViewById(R.id.Update_PQTY);
        updateorderbtn = findViewById(R.id.UpdateOrderBtn);
        deleteorderbtn = findViewById(R.id.DeleteOrderBtn);
        Ba_btn = findViewById(R.id.b_btn);

        //Intializing dbconnector class
        dbConnector = new DBConnector(UpdateOrder_Activity.this);


        //getting data from Adapter class
        original_User_id = getIntent().getStringExtra("User_Id");
        original_Product_id = getIntent().getStringExtra("product_id");
        original_Product_qty = getIntent().getStringExtra("product_qty");


        //setting data to Update activity
        update_Order_user_id.setText(original_User_id);
        update_Order_product_id.setText(original_Product_id);
        update_Order_product_qty.setText(original_Product_qty);

        //on click listener to update
        updateorderbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dbConnector.updateOrder(original_User_id,update_Order_user_id.getText().toString(), update_Order_product_id.getText().toString(),update_Order_product_qty.getText().toString());

                Toast.makeText(UpdateOrder_Activity.this, "Order Updated Successfully...", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(UpdateOrder_Activity.this, Admin_activity.class);
                startActivity(i);

            }
        });

        //on click listerner to delete
        deleteorderbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dbConnector.deleteOrder(original_User_id);

                Toast.makeText(UpdateOrder_Activity.this, "Order Deleted Successfully...", Toast.LENGTH_SHORT).show();

                //Intent i = new Intent(UpdateOrder_Activity.this, Admin_activity.class);
                //startActivity(i);
            }
        });

        Ba_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UpdateOrder_Activity.this, Admin_activity.class);
            }
        });

    }
}
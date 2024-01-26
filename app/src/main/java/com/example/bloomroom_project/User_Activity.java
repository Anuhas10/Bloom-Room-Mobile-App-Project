package com.example.bloomroom_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class User_Activity extends AppCompatActivity {

    EditText user_id, product_id, product_qty;
    Button add_orderbtn, view_orderbtn, search_productbtn, view_productbtn, backBtn;

    private DBConnector dbConnector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        user_id = findViewById(R.id.Order_User_Id);
        product_id = findViewById(R.id.Order_Product_Id);
        product_qty = findViewById(R.id.Order_Product_Qty);

        add_orderbtn = findViewById(R.id.Add_Order_btn);
        view_orderbtn = findViewById(R.id.View_Order_btn);
        search_productbtn = findViewById(R.id.Search_Product_btn);
        view_productbtn = findViewById(R.id.View_Product_btn);

        backBtn = findViewById(R.id.Back_Btn);

        dbConnector = new DBConnector(User_Activity.this);

        add_orderbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String u_id = user_id.getText().toString();
                String p_id = product_id.getText().toString();
                String p_qty = product_qty.getText().toString();

                if (u_id.isEmpty() && p_id.isEmpty() && p_qty.isEmpty()){
                    Toast.makeText(User_Activity.this, "Please Fill in the Blanks!!!", Toast.LENGTH_SHORT).show();
                    return;
                }

                dbConnector.addNewOrder(u_id,p_id,p_qty);

                Toast.makeText(User_Activity.this, "Order has been Placed...", Toast.LENGTH_SHORT).show();

                user_id.setText("");
                product_id.setText("");
                product_qty.setText("");
            }
        });

        view_orderbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(User_Activity.this,ViewOrder_Activity.class);
                startActivity(intent);
            }
        });

        search_productbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(User_Activity.this,SearchProduct_Activity.class);
                startActivity(intent);
            }
        });

        view_productbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(User_Activity.this,ViewProduct_Activity.class);
                startActivity(intent);
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(User_Activity.this, Login_AND_Reg_Activity.class);
                startActivity(intent);
            }
        });

    }
}
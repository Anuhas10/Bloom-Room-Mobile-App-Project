package com.example.bloomroom_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateProduct_Activity extends AppCompatActivity {

    private EditText update_p_id, update_p_name, update_p_price, update_p_qty, update_p_cate_id;
    private Button updateproduct_btn, deleteproduct_btn, PRE_BTN;
    private DBConnector dbConnector;

    String org_p_id, org_p_name, org_p_price, org_p_qty, org_p_cate_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_product);

        update_p_id = findViewById(R.id.Update_P_Id);
        update_p_name = findViewById(R.id.Update_P_Name);
        update_p_price = findViewById(R.id.Update_P_Price);
        update_p_qty = findViewById(R.id.Update_P_Qty);
        update_p_cate_id = findViewById(R.id.Update_P_Cate_Id);
        updateproduct_btn = findViewById(R.id.Update_P_Btn);
        deleteproduct_btn = findViewById(R.id.Delete_P_Btn);
        PRE_BTN = findViewById(R.id.pre_btn);

        dbConnector = new DBConnector(UpdateProduct_Activity.this);

        org_p_id = getIntent().getStringExtra("P_Id");
        org_p_name = getIntent().getStringExtra("P_Name");
        org_p_price = getIntent().getStringExtra("P_Price");
        org_p_qty = getIntent().getStringExtra("P_Qty");
        org_p_cate_id = getIntent().getStringExtra("P_Cate_Name");

        update_p_id.setText(org_p_id);
        update_p_name.setText(org_p_name);
        update_p_price.setText(org_p_price);
        update_p_qty.setText(org_p_qty);
        update_p_cate_id.setText(org_p_cate_id);

        updateproduct_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dbConnector.updateProduct(org_p_id, update_p_id.getText().toString(), update_p_name.getText().toString(), update_p_price.getText().toString(), update_p_qty.getText().toString(), update_p_cate_id.getText().toString());

                Toast.makeText(UpdateProduct_Activity.this, "Product Successfully Updated...", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(UpdateProduct_Activity.this, Admin_activity.class);
                startActivity(i);
            }
        });


        deleteproduct_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dbConnector.deleteProduct(org_p_id);

                Toast.makeText(UpdateProduct_Activity.this, "Product Deleted Successfully...", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(UpdateProduct_Activity.this, ViewProduct_Activity.class);
                startActivity(i);
            }
        });


        PRE_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(UpdateProduct_Activity.this, Admin_activity.class);
                startActivity(i);
            }
        });


    }
}
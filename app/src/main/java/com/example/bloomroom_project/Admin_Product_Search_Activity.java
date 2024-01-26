package com.example.bloomroom_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class Admin_Product_Search_Activity extends AppCompatActivity {

    EditText productid, productname, pro_price, pro_category, pro_qty;
    Button searchproduct_btn;

    private DBConnector dbConnector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_product_search);

        productid = (EditText) findViewById(R.id.txt_I_ProductId);
        productname = (EditText) findViewById(R.id.txt_I_ProductName);
        pro_price = (EditText) findViewById(R.id.txt_I_Price);
        pro_qty = (EditText) findViewById(R.id.txt_I_Qty);
        pro_category = (EditText) findViewById(R.id.txt_I_Category);
        searchproduct_btn = (Button) findViewById(R.id.SearchProduct_I_Btn);

        dbConnector = new DBConnector(Admin_Product_Search_Activity.this);

        searchproduct_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pid = productid.getText().toString();
                ArrayList<Product_Class> productList = dbConnector.searchProduct(pid);
                if (productList.size()!= 0) {

                    Product_Class product_class = productList.get(0);
                    productname.setText(product_class.getProductName());
                    pro_price.setText((String.valueOf(product_class.getPrice())));
                    pro_qty.setText((String.valueOf(product_class.getQuantity())));
                    pro_category.setText(product_class.getCategoryId());

                    Toast.makeText(getApplicationContext(), "Product Found Successfully...", Toast.LENGTH_LONG).show();

                    /*Intent i = new Intent(Admin_Product_Search_Activity.this,Admin_Product_Search_Activity.class);
                    startActivity(i);*/
                }
                else {
                    Toast.makeText(getApplicationContext(), "Product Found Failure!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
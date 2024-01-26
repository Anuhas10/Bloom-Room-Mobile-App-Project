package com.example.bloomroom_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class SearchProduct_Activity extends AppCompatActivity {

    EditText productid, productname, productprice, procategory, productqty, userid, buyqty, bproductid, buyinvoiceid;
    Button searchproduct, buyproduct;

    private DBConnector dbConnector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_product);

        productid = (EditText) findViewById(R.id.I_ProductId);
        productname = (EditText) findViewById(R.id.I_ProductName);
        productprice = (EditText) findViewById(R.id.I_Price);
        procategory = (EditText) findViewById(R.id.I_Category);
        productqty = (EditText) findViewById(R.id.I_Qty);
        searchproduct = (Button) findViewById(R.id.I_SearchProduct_Btn);

        userid = (EditText) findViewById(R.id.I_UserId);
        //buyinvoiceid = (EditText) findViewById(R.id.I_InvoiceId);
        bproductid = (EditText) findViewById(R.id.I_Productid);
        buyqty = (EditText) findViewById(R.id.I_BuyQty);
        buyproduct = (Button) findViewById(R.id.I_Buy_Btn);

        dbConnector = new DBConnector(SearchProduct_Activity.this);

        searchproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pid = productid.getText().toString();
                ArrayList<Product_Class> productList = dbConnector.searchProduct(pid);
                if (productList.size() != 0) {
                    Product_Class product_class = productList.get(0);
                    productname.setText(product_class.getProductName());
                    productprice.setText((String.valueOf(product_class.getPrice())));
                    productqty.setText((String.valueOf(product_class.getQuantity())));
                    procategory.setText(product_class.getCategoryId());

                    Toast.makeText(getApplicationContext(), "Product Found Successfully...", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Something went Wrong!!!", Toast.LENGTH_LONG).show();
                }
            }
        });

        buyproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dbConnector.buyProduct(productid.getText().toString(), Integer.parseInt(buyqty.getText().toString()));

                int Total = Integer.parseInt(buyqty.getText().toString()) * Integer.parseInt(productprice.getText().toString());

                int qty = Integer.parseInt(buyqty.getText().toString());


                Invoice_Class invoice_class = new Invoice_Class(userid.getText().toString(), bproductid.getText().toString(), qty);

                if (dbConnector.insertInvoice(invoice_class)) {
                    Toast.makeText(getApplicationContext(), "Thanks for Purchasing from us...", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Something went Wrong with Purchasing!!!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
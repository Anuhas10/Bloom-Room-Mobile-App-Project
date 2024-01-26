package com.example.bloomroom_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Category_Activity extends AppCompatActivity {

    EditText EditTextCategoryId, EditTextCategoryname;
    Button ButtonRegisterCategory;
    private DBConnector dbConnector;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        EditTextCategoryId = (EditText)findViewById(R.id.CategoryId);
        EditTextCategoryname = (EditText)findViewById(R.id.CategoryName);
        ButtonRegisterCategory = (Button) findViewById(R.id.Reg_Cate_btn);

        dbConnector = new DBConnector(Category_Activity.this);

        ButtonRegisterCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id = EditTextCategoryId.getText().toString();
                String name = EditTextCategoryname.getText().toString();

                dbConnector.addnewcate(id,name);

                Toast.makeText(Category_Activity.this, "Category Added Successfully...", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(Category_Activity.this, Admin_activity.class);
                startActivity(i);
            }
        });

    }
}
package com.example.bloomroom_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class Login_AND_Reg_Activity extends AppCompatActivity {

    private EditText username, password;
    private Button login_btn, createNewAcc_btn;

    private DBConnector dbConnector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_and_reg);

        username = (EditText) findViewById(R.id.UserId);
        password = (EditText) findViewById(R.id.UserPassword);
        login_btn = (Button) findViewById(R.id.LoginBtn);
        createNewAcc_btn = (Button) findViewById(R.id.AddNewAccBtn);

        dbConnector = new DBConnector(Login_AND_Reg_Activity.this);

        createNewAcc_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login_AND_Reg_Activity.this,Register_Activity.class);
                startActivity(intent);
            }
        });


        //Login validation

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ArrayList<User_Class> userDetails = dbConnector.ValidationLogin(username.getText().toString(),password.getText().toString());

                if (userDetails.size() != 0){
                    User_Class user = userDetails.get(0);
                    String UserType = user.getUserType();

                    Toast.makeText(getApplicationContext(),"User Found..." +UserType, Toast.LENGTH_SHORT).show();

                    if (UserType.equals("Admin")){
                        Intent intentRegister = new Intent(Login_AND_Reg_Activity.this, Admin_activity.class);
                        startActivity(intentRegister);
                    }
                    else {
                        Intent intentRegister = new Intent(Login_AND_Reg_Activity.this, User_Activity.class);
                        startActivity(intentRegister);
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(), "Invalid User!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
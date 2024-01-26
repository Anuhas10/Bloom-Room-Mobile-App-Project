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

public class Register_Activity extends AppCompatActivity {

    private EditText Register_username, Register_password, Register_confirmpassword;
    private Spinner spinner;
    private Button Register, back_b;
    private DBConnector dbConnector;

    String UserType[]={"Admin","User"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);



        Register_username = findViewById(R.id.Regis_UserId);
        Register_password = findViewById(R.id.Regis_UserPassword);
        Register_confirmpassword = findViewById(R.id.Regis_ConPassword);
        spinner = findViewById(R.id.spinner);
        Register = findViewById(R.id.Regis_btn);

        back_b = findViewById(R.id.Backbtn);

        dbConnector=new DBConnector(Register_Activity.this);

        ArrayAdapter ad = new ArrayAdapter(this,android.R.layout.simple_spinner_item,UserType);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(ad);

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Register_username.getText().toString().isEmpty() || Register_password.getText().toString().isEmpty() || Register_confirmpassword.getText().toString().isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "Fields cannot be blank!!!", Toast.LENGTH_SHORT).show();
                }
                else if (Register_password.getText().toString().length()<3)
                {
                    Toast.makeText(getApplicationContext(), "Password should be more than 3 characters!!!", Toast.LENGTH_SHORT).show();
                }
                else if (!Register_password.getText().toString().equals(Register_confirmpassword.getText().toString()))
                {
                    Toast.makeText(getApplicationContext(), "Password and Confirm Password doesn't match!!!", Toast.LENGTH_SHORT).show();
                }
                else {
                    String userId = Register_username.getText().toString();
                    String user_password = Register_password.getText().toString();
                    String userType = spinner.getSelectedItem().toString();

                    dbConnector.addnewUser(userId, user_password, userType);

                    Toast.makeText(Register_Activity.this, "New User Added...", Toast.LENGTH_SHORT).show();

                    Register_username.setText("");
                    Register_password.setText("");
                    Register_confirmpassword.setText("");
                }
            }
        });

        back_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register_Activity.this, Login_AND_Reg_Activity.class);
                startActivity(intent);
            }
        });
    }
}
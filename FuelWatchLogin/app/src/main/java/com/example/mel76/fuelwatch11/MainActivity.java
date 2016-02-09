package com.example.mel76.fuelwatch11;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    private EditText emailField, passwordField;
    private TextView oilLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        emailField = (EditText) findViewById(R.id.editTextEmail);
        passwordField = (EditText) findViewById(R.id.editTextPassword);

        oilLevel = (TextView) findViewById(R.id.textViewLevel);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void loginPost(View view){
        String email = emailField.getText().toString();
        String password = passwordField.getText().toString();
        // Passes the data to the singinActivity.java
        //oil level passed to new activity, with flag set to 1 (means using a POST method)
        new SigninActivity(this,oilLevel,1).execute(email, password);
    }
}


package com.example.armanghassemi.liveupdate;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.parse.*;



public class RegisterActivity extends Activity {

    protected EditText mUsername;
    protected EditText mEmail;
    protected EditText mPassword;
    protected Button mSignUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // initialize
        mUsername = (EditText)findViewById(R.id.usernameEditText);
        mEmail = (EditText)findViewById(R.id.emailEditText);
        mPassword = (EditText)findViewById(R.id.passwordEditText);
        mSignUpButton = (Button)findViewById(R.id.signUpButton);

    }

    public void signUp(View view){

        //get the username, password, and email and convert them to string
        String username = mUsername.getText().toString().trim();
        String password = mPassword.getText().toString().trim();
        String email = mEmail.getText().toString().trim();

        // store user in Parse
        ParseUser user = new ParseUser();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {

                    // user signed up successfully
                    Toast.makeText(RegisterActivity.this, "Welcome!", Toast.LENGTH_LONG).show();

                    // Now take the user to the homepage
                    Intent takeUserHome = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(takeUserHome);

                }
                else {
                    // there was an error signing up
                }
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

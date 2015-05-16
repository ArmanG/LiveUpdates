package com.example.armanghassemi.liveupdate;

import android.app.Activity;
import android.app.AlertDialog;
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

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseUser;

import java.text.ParseException;


public class LoginActivity extends Activity {

    protected EditText mUsernameLogin;
    protected EditText mPasswordLogin;
    protected Button   mLoginBtn;
    protected Button mCreateAccountBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mUsernameLogin = (EditText) findViewById(R.id.usernameLoginTextBox);
        mPasswordLogin = (EditText) findViewById(R.id.passwordLoginTextBox);
        mLoginBtn = (Button) findViewById(R.id.loginButton);
        mCreateAccountBtn = (Button) findViewById(R.id.createAccountLoginButton);

    }

    public void login(View view) {

        String usernameLogin = mUsernameLogin.getText().toString().trim();
        String passwordLogin = mPasswordLogin.getText().toString().trim();
        System.out.println("OutSIDE DONE");

        ParseUser.logInInBackground(usernameLogin, passwordLogin, new LogInCallback() {
            @Override
            public void done(ParseUser parseUser, com.parse.ParseException e) {

                if (parseUser != null) {
                    // success, use has logged in
                    Toast.makeText(LoginActivity.this, "Welcome back!", Toast.LENGTH_LONG).show();
                    // take user to the homepage
                    Intent takeUserHome = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(takeUserHome);
                }
                else {
                    // there was a problem...
                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                    builder.setMessage("Invalid login credentials");
                    builder.setTitle("Sorry!");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // close the dialog
                            dialog.dismiss();
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }

            }
        });


    }

    public void createAccount(View view){
        Intent takeUserToRegister = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(takeUserToRegister);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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

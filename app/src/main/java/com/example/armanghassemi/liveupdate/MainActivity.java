package com.example.armanghassemi.liveupdate;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.parse.*;


public class MainActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            // show the homepage to user
        } else {
            // show the signup or login screen
            Intent takeUserToLogin = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(takeUserToLogin);
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {

            case R.id.updateStatus:
                // take user to update status activity
                Intent takeToUpdateStatus = new Intent(MainActivity.this, UpdateStatusActivity.class);
                startActivity(takeToUpdateStatus);
                break;

            case R.id.logoutUser:
                // logout the user
                System.out.println("HELLLLOOOOOOO");
                //ParseUser currentUser = ParseUser.getCurrentUser();
                ParseUser.logOut();
                // take use back to login screen
                Intent takeBackToLogin = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(takeBackToLogin);
                break;

        }

        return super.onOptionsItemSelected(item);
    }
}

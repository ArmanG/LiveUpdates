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

import com.parse.*;


public class UpdateStatusActivity extends Activity {

    protected EditText mStatusUpdate;
    protected Button mStatusUpdateBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_status);

        //initialize
        mStatusUpdate = (EditText) findViewById(R.id.updateStatusTextBox);
        mStatusUpdateBtn = (Button) findViewById(R.id.statusUpdateButton);

    }

    public void updateStatus(View view) {

        // get the current user
        ParseUser currentUser = ParseUser.getCurrentUser();
        String currentUserUsername = currentUser.getUsername();

        // get status use has entered
        // convert to string
        String newStatus = mStatusUpdate.getText().toString();

        if (newStatus.isEmpty()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(UpdateStatusActivity.this);
            builder.setMessage("Status should not be empty");
            builder.setTitle("Oops!");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // close the dialog
                    dialog.dismiss();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        } else {
            // save status in backend...Parse
            ParseObject statusObject = new ParseObject("Status");
            statusObject.put("Status", newStatus);
            statusObject.put("User", currentUserUsername);
            statusObject.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null) {
                        // successfully stored the new status
                        Toast.makeText(UpdateStatusActivity.this, "Status updated!", Toast.LENGTH_LONG).show();

                        // take user to the home page
                        Intent takeUserToHomePage = new Intent(UpdateStatusActivity.this, MainActivity.class);
                        startActivity(takeUserToHomePage);
                    } else {
                        // there was a problem storing the new status
                        // advise user
                        AlertDialog.Builder builder = new AlertDialog.Builder(UpdateStatusActivity.this);
                        builder.setMessage(e.getMessage());
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

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_update_status, menu);
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

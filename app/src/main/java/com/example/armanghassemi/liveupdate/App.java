package com.example.armanghassemi.liveupdate;

import android.app.Application;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.parse.Parse;


public class App extends Application {


    public void onCreate() {
        super.onCreate();

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "RZYQsFmmuLsti20EZGCjTVgJj9o2Gp84AOeMxAjk", "9NaaeoY2bspm7lAbeN1PEhikI8a6BHn4pkZPGX3L");

    }


}

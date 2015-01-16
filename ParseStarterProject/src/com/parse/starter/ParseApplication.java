package com.parse.starter;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseCrashReporting;
import com.parse.ParseUser;
import com.parse.ParsePush;
import com.parse.SaveCallback;
import com.parse.ParseException;


public class ParseApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();

    // Initialize Crash Reporting.
    ParseCrashReporting.enable(this);

    // Enable Local Datastore.
    Parse.enableLocalDatastore(this);

    // Add your initialization code here
    Parse.initialize(this, "SA9jwfkPdiIKsPOoqusnHkedPe2c0IkjLUFdxy1a", "QRA0WKerWCfSNKlLwL0lapuG2EZQM3XFwaVnD8kY");


    ParseUser.enableAutomaticUser();
    ParseACL defaultACL = new ParseACL();
    // Optionally enable public read access.
    // defaultACL.setPublicReadAccess(true);
    ParseACL.setDefaultACL(defaultACL, true);

    ParsePush.subscribeInBackground("pushChannel");

      for(int num = 0; num<5; num++){
          ParsePush push = new ParsePush();
          push.setChannel("pushChannel");
          push.setMessage("Push number " + num);
          push.sendInBackground();
      }


    ParsePush.subscribeInBackground("", new SaveCallback() {
      @Override
      public void done(ParseException e) {
          if (e == null) {
             // Log.d("com.parse.push", "successfully subscribed to the broadcast channel.");
          } else {
              //Log.e("com.parse.push", "failed to subscribe for push", e);
          }
      }
   });
  }
}

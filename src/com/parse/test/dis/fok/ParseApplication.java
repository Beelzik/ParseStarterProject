package com.parse.test.dis.fok;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseUser;

import android.app.Application;

public class ParseApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();

		// Add your initialization code here
		 Parse.initialize(this, "g7J6qKtb2QBOkfLGIv169L9fQprYsrP0ifoQ6ZOP", "1SGNwpn8zgC32vA7gxqzZ4WdSSqmGP924K0vKrfh");
		 


		ParseUser.enableAutomaticUser();
		ParseACL defaultACL = new ParseACL();
	    
		// If you would like all objects to be private by default, remove this line.
		defaultACL.setPublicReadAccess(true);
		
		ParseACL.setDefaultACL(defaultACL, true);
	}

}

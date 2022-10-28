package com.example.instagramclone;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("cc3v3t7ooRGo2kc7RGLjqHeoAsK8D0KpuzYWAijj")
                // if defined
                .clientKey("ztq6BMVhUtpTGiLAW38tHkK7AWOYsXTm7iDVHiEr")
                .server("https://parseapi.back4app.com/")
                .build()
        );

    }
}

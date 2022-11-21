package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ProgressCallback;

import java.util.List;

public class UsersPosts extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_posts);

        Intent receivedIntentObject = getIntent();
        String receivedUsername = receivedIntentObject.getStringExtra("Username");

        setTitle(receivedUsername+"'s Posts");

        ParseQuery<ParseObject> parseQuery = new ParseQuery<ParseObject>("Photo");
        parseQuery.whereEqualTo("Username",receivedUsername);
        parseQuery.orderByDescending("createdAt");

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        parseQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(objects.size() > 0 && e == null){
                    for(ParseObject post : objects){
                        TextView postDescription = new TextView(UsersPosts.this);
                        postDescription.setText(post.get("ImgDescription")+"");

                        ParseFile postpicture = (ParseFile) post.get("Picture");
                        postpicture.getDataInBackground(new GetDataCallback() {
                            @Override
                            public void done(byte[] data, ParseException e) {
                                if(data!=null && e==null){

                                }
                            }
                        });
                    }
                }
            }
        });
    }
}
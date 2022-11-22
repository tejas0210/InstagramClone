package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUp_Activity extends AppCompatActivity {

    private Button btnCreateAccount;
    private EditText edtEmail, edtSUsername, edtSPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        setTitle("Sign Up");

        btnCreateAccount = findViewById(R.id.btnCreateAccount);
        edtEmail = findViewById(R.id.edtEmail);
        edtSUsername = findViewById(R.id.edtSUsername);
        edtSPassword = findViewById(R.id.edtSPassword);

        btnCreateAccount.setOnClickListener(this::onClick);

        if (ParseUser.getCurrentUser() != null) {
            // ParseUser.getCurrentUser().logOut();
            transitionToHomePage1();
        }
    }


    public void onClick(View signUpPressed) {
        final ParseUser newUser = new ParseUser();
        newUser.setEmail(edtEmail.getText().toString());
        newUser.setUsername(edtSUsername.getText().toString());
        newUser.setPassword(edtSPassword.getText().toString());

        final ProgressDialog progressDialog = new ProgressDialog(SignUp_Activity.this);
        progressDialog.setMessage("Signing Up in " +newUser.getUsername());
        progressDialog.show();
        newUser.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                try {
                    if(e==null){
                        FancyToast.makeText(SignUp_Activity.this,newUser.getUsername()+" is Signed Up successfully",FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,false).show();
                        transitionToHomePage1();
                    }
                    else{
                        FancyToast.makeText(SignUp_Activity.this,e.getMessage(),FancyToast.LENGTH_SHORT,FancyToast.ERROR,false).show();
                    }

                }catch(Exception e1){
                    FancyToast.makeText(SignUp_Activity.this,e1.getMessage(),FancyToast.LENGTH_SHORT,FancyToast.ERROR,false).show();
                }
                progressDialog.dismiss();
            }
        });
    }

//    public void rootLayoutTapped(View view) {
//        try {
//            InputMethodManager inputMethodManager =
//                    (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
//            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    private void transitionToHomePage1() {
        Intent intent = new Intent(SignUp_Activity.this, Home_Page.class);
        startActivity(intent);
        finish();
    }
}
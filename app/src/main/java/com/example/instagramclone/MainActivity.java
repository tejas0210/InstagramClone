package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnLogIn, btnSignUp;
    private EditText edtUsername, edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogIn = findViewById(R.id.btnLogIn);
        btnSignUp = findViewById(R.id.btnSignUp);

        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);

        btnLogIn.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);

        if(ParseUser.getCurrentUser()!=null){
//            ParseUser.getCurrentUser().logOut();
            transitionToHomePage();
        }
    }


    @Override
    public void onClick(View buttonPressed) {
        switch (buttonPressed.getId()){
            case R.id.btnLogIn:
                try {

                    ParseUser.logInInBackground(edtUsername.getText().toString(), edtPassword.getText().toString(), new LogInCallback() {
                        @Override
                        public void done(ParseUser user, ParseException e) {
                            if(user!=null && e==null){
                                FancyToast.makeText(MainActivity.this,user.getUsername()+" is Logged In successfully",FancyToast.LENGTH_SHORT,FancyToast.SUCCESS,false).show();
                                transitionToHomePage();
                            }
                            else{
                                FancyToast.makeText(MainActivity.this,e.getMessage(),FancyToast.LENGTH_SHORT,FancyToast.ERROR,false).show();
                            }
                        }


                    });

                } catch(Exception e){
                    FancyToast.makeText(MainActivity.this,e.getMessage(),FancyToast.LENGTH_SHORT,FancyToast.ERROR,false).show();
                }
                break;

            case R.id.btnSignUp:
                Intent intent = new Intent(MainActivity.this,SignUp_Activity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    public void transitionToHomePage(){
        Intent intent = new Intent(MainActivity.this,Home_Page.class);
        startActivity(intent);
        finish();
    }

}
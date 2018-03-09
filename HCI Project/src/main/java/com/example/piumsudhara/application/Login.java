package com.example.piumsudhara.application;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText username,password;
    Button login;
    private int progressStatus = 0;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        login =(Button)findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().equals(""))
                {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Login.this);
                    alertDialogBuilder.setMessage("Please Enter Your Username");
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                }
                else if(password.getText().toString().equals(""))
                {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Login.this);
                    alertDialogBuilder.setMessage("Please Enter Your Password");
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                }
                else if(username.getText().toString().equals("10601918") && password.getText().toString().equals("Student123"))
                {
                    final ProgressDialog pd = new ProgressDialog(Login.this);
                    pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    pd.setMessage("Authenticating....");
                    pd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#2f3b4d")));
                    pd.setIndeterminate(false);
                    pd.show();
                    progressStatus = 0;

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            while(progressStatus < 100){
                                progressStatus +=1;
                                try{
                                    Thread.sleep(50);
                                }catch(InterruptedException e){
                                    e.printStackTrace();
                                }
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        pd.setProgress(progressStatus);

                                        if(progressStatus == 80){
                                            pd.dismiss();
                                            Intent in = new Intent(Login.this,MainMenu.class);
                                            startActivity(in);
                                            finish();
                                        }
                                    }
                                });
                            }
                        }
                    }).start();
                }
                else
                {
                    final ProgressDialog pd = new ProgressDialog(Login.this);
                    pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    pd.setMessage("Authenticating....");
                    pd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#2f3b4d")));
                    pd.setIndeterminate(false);
                    pd.show();
                    progressStatus = 0;

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            while(progressStatus < 100){
                                progressStatus +=1;
                                try{
                                    Thread.sleep(50);
                                }catch(InterruptedException e){
                                    e.printStackTrace();
                                }
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        pd.setProgress(progressStatus);

                                        if(progressStatus == 50){
                                            pd.dismiss();
                                            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Login.this);
                                            alertDialogBuilder.setMessage("Username or Password Is Incorrect");
                                            AlertDialog alertDialog = alertDialogBuilder.create();
                                            alertDialog.show();
                                            username.setText("");
                                            password.setText("");
                                        }
                                    }
                                });
                            }
                        }
                    }).start();
                }
            }
        });
    }
}

package com.thellog.laravelapiandroid;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.thellog.laravelapiandroid.*;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {
    TextView etName, etEmail, etPassword, etConfirnation, btnRegister, btnLogin;
    String name, email, password, confirnation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setTitle("Register");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_sign_up);

        etEmail=findViewById(R.id.etEmail);
        etPassword=findViewById(R.id.etPassword);
        etName=findViewById(R.id.etName);
        etConfirnation=findViewById(R.id.etConfirnation);
        btnRegister=findViewById(R.id.btnRegister);
        btnLogin=findViewById(R.id.btnLogin);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkRegister();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }
    private void checkRegister(){
        email=etEmail.getText().toString();
        password=etPassword.getText().toString();
        name=etName.getText().toString();
        confirnation=etConfirnation.getText().toString();

        if(email.isEmpty()||name.isEmpty()||password.isEmpty()){
            alertFail("1 buggg");
        }
        else if(!password.equals(confirnation)){
            alertFail(("pass not same"));
        }

        else{
            sendRegister();
        }
    }

    private void sendRegister() {

        JSONObject params=new JSONObject();
        try {
            params.put("name",name);
            params.put("email",email);
            params.put("password",password);
            params.put("password_confirmation",confirnation);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String data =params.toString();
        String url=getString(R.string.api_server)+"/register";
        new Thread(new Runnable() {
            @Override
            public void run() {
                Http http=new Http(RegisterActivity.this,url);
                http.setMethod("post");
                http.setData(data);
                http.send();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Integer code=http.getStatusCode();
                        if(code==201||code==200){
                            alertSuccess("Register successfuly");
                        }
                        else if(code==422){
                            try {
                                JSONObject response=new JSONObject(http.getResponse());
                                String msg=response.getString("message");
                                alertFail(msg);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
            }
        }).start();
    }

    private void alertSuccess(String s) {
        new AlertDialog.Builder(this)
                .setTitle("Success")
                .setIcon(R.drawable.ic_launcher_background)   //part1
                .setMessage(s)
                .setPositiveButton("Login", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent= new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                })
                .show();
    }

    private void alertFail(String s) {
        new AlertDialog.Builder(this)
                .setTitle("Failed")
                .setIcon(R.drawable.ic_launcher_background)   //part1
                .setMessage(s)
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }
}
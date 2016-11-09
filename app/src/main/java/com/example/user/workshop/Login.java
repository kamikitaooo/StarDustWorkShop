package com.example.user.workshop;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Login extends AppCompatActivity {

    private Button btn_login, btn_Register;
    private EditText et_password, et_username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        bindWidget();
        setEvent();
    }

    private void setEvent() {
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new LoginActive(et_username.getText().toString(), et_password.getText().toString()).execute();
            }
        });
        btn_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, Register.class);
                startActivity(i);
            }
        });
    }

    private void bindWidget() {
        btn_login = (Button) findViewById(R.id.btn_Login);
        btn_Register = (Button) findViewById(R.id.btn_Register);
        et_username = (EditText) findViewById(R.id.et_username);
        et_password = (EditText) findViewById(R.id.et_password);
    }
    public class LoginActive extends AsyncTask<Void, Void, String>{
        private String username, password;

        public LoginActive(String username, String password) {
            this.username = username;
            this.password = password;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... params) {
            OkHttpClient client = new OkHttpClient();
            Request request;
            Response response = null;

            RequestBody requestBody = new FormBody.Builder()
                    .add("username", username)
                    .add("password", password)
                    .build();
            request = new Request.Builder()
                    .url("http://kimhun55.com/pollservices/login.php")
                    .post(requestBody)
                    .build();
            try{
                response = client.newCall(request).execute();
                if(response.isSuccessful()){
                    return response.body().string();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try{
                /*{
                  "result": {
                    "result": 1,
                    "result_desc": " success"
                  },
                  "user_info": {
                    "user_id": "26",
                    "image_url": null,
                    "display_name": ""
                  }
                }*/
                JSONObject rootObj = new JSONObject(s);
                if (rootObj.has("result")) {
                    JSONObject resultOb = rootObj.getJSONObject("result");
                    if(resultOb.getInt("result") == 1){
                        Intent i = new Intent(Login.this, NewsList.class);
                        startActivity(i);
                    }else{
                        Toast.makeText(Login.this, resultOb.getString("result_desc"), Toast.LENGTH_SHORT).show();
                    }
                }
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
    }
}

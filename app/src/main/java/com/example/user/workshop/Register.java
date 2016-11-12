package com.example.user.workshop;

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

public class Register extends AppCompatActivity {

    private EditText etDis;
    private EditText etUser;
    private EditText etPass;
    private EditText etConfirm;
    private Button btReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etDis = (EditText) findViewById(R.id.etDis);
        etUser = (EditText) findViewById(R.id.etUser);
        etPass = (EditText) findViewById(R.id.etPass);
        etConfirm = (EditText) findViewById(R.id.etConfirm);
        btReg = (Button) findViewById(R.id.btReg);

        setListener();


    }


    protected void setListener() {
        btReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    //Todo
                    new Regis(etUser.getText().toString(),
                            etPass.getText().toString(),
                            etConfirm.getText().toString(),
                            etDis.getText().toString()).execute();
                } else {
                    Toast.makeText(Register.this, "กรุณากรอกข้อมุลให้ครบถ้วน", Toast.LENGTH_SHORT);
                }
            }
        });
    }
    private boolean validate() {
        String username = etUser.getText().toString();
        String password = etPass.getText().toString();
        String passwordConfirm = etConfirm.getText().toString();
        String displayName = etDis.getText().toString();

        if (username.isEmpty()){ return false; }

        if (password.isEmpty()){ return false; }

        if (passwordConfirm.isEmpty()) {
            return false;
        }
        if (!password.equals(passwordConfirm)) {
            return false;
        }
        if (displayName.isEmpty()) {
            return false;
        }
        return true;
    }


    private class Regis extends AsyncTask<Void, Void, String> {

        private String username;
        private String password;
        private String passwordCon;
        private String displayName;

        public Regis(String username, String password, String passwordCon, String displayName) {
            this.username = username;
            this.password = password;
            this.passwordCon = passwordCon;
            this.displayName = displayName;
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
                    .add("password_con", passwordCon)
                    .add("display_name", displayName)
                    .build();

            request = new Request.Builder()
                    .url("http://kimhun55.com/pollservices/signup.php")
                    .post(requestBody)
                    .build();

            try {

                response = client.newCall(request).execute();

                if(response.isSuccessful()){
                    return  response.body().string();
                }

            }catch (IOException ex){
                ex.printStackTrace();
            }



            return null;
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(Register.this, s ,Toast.LENGTH_SHORT).show();

            //{
           //     "result":{
           //     "result": 1,
           //     "result_desc":"success"
           // }
           // }



            try{
                JSONObject rootObj = new JSONObject(s);
                if(rootObj.has("result")){
                    JSONObject resultObj = rootObj.getJSONObject("result");
                    if(resultObj.getInt("result") == 1){
                        Toast.makeText(Register.this,resultObj.getString("result_desc"),Toast.LENGTH_SHORT);
                                finish();
                    }else
                        Toast.makeText(Register.this,resultObj.getString("result_desc"),Toast.LENGTH_SHORT);
                }

            }catch (JSONException ex){

            }


        }


    }



}



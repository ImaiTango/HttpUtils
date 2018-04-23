package com.example.chitose.httptest;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText et_request;
    Button btn_request;
    TextView tv_response;
    ProgressBar pb_request;

    class MyAsyncTask extends AsyncTask<Void, Void, String> {
        @Override
        protected void onPreExecute() {
            pb_request.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(Void... voids) {
            return OkHttpUtil.sendOkHttpRequest(et_request.getText().toString());
        }

        @Override
        protected void onPostExecute(String s) {
            pb_request.setVisibility(View.GONE);
            tv_response.setText(s);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        et_request = findViewById(R.id.et_request);
        btn_request = findViewById(R.id.btn_request);
        tv_response = findViewById(R.id.tv_response);
        pb_request = findViewById(R.id.pb_request);
        btn_request.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_request :
                //sendRequest();
                new MyAsyncTask().execute();
        }
    }


    private void sendRequest() {
        HttpUtil.sendHttpRequest(et_request.getText().toString(), new HttpCallBackListener() {
            @Override
            public void onFinish(final String response) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv_response.setText(response);
                    }
                });

            }

            @Override
            public void onError(Exception e) {
                Log.e("onError: ", e.toString());
            }
        });
    }

}


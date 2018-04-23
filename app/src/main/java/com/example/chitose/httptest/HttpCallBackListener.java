package com.example.chitose.httptest;

public interface HttpCallBackListener{
    void onFinish(String response);
    void onError(Exception e);
}

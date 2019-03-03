package com.example.bearcat.server;

public interface AsyncResponse<T> {
    void processFinish(T t);
}

package com.example.bearcat.server;

public interface AsyncResponse<T> {
    T processFinish(T t);
}

package com.giousa.lambda.custome;

@FunctionalInterface
public interface CustomeFunction<T,R> {

    R exec(T t);
}

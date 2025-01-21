package com.onepercent.goaltracker.Utils;


import lombok.Getter;

@Getter
public class ServiceResult<T> {
    private final boolean success;
    private final T data;
    private final String message;

    ServiceResult(boolean success, T data, String message){
        this.success = success;
        this.data = data;
        this.message = message;
    }

    public static <T> ServiceResult<T> ok(T data){
        return new ServiceResult<>(true, data, "ok");
    }

    public static <T> ServiceResult<T> error(String message){
        return new ServiceResult<>(false, null, message);
    }

}

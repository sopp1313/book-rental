package com.network.calendar_api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BaseResponse<T>{
    private int code;
    private String message;
    private T data;
}

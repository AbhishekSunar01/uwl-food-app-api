package com.uwlFood.api.dto;

import lombok.Data;

@Data
public class ApiResponseDto <T> {
    private boolean success;
    private T data;
    private String message;

    public ApiResponseDto(boolean success, T data, String message) {
        this.success = success;
        this.data = data;
        this.message = message;
    }

    public ApiResponseDto(boolean success, String message) {
        this.success = success;
        this.message = message;
        this.data = null;
    }

}

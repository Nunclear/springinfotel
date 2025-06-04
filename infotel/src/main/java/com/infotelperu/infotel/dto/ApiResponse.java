package com.infotelperu.infotel.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    
    private String message;
    private T data;
    private String error;
    
    // Constructores
    public ApiResponse() {}
    
    public ApiResponse(String message) {
        this.message = message;
    }
    
    public ApiResponse(String message, T data) {
        this.message = message;
        this.data = data;
    }
    
    // Métodos estáticos para crear respuestas
    public static <T> ApiResponse<T> success(String message) {
        return new ApiResponse<>(message);
    }
    
    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(message, data);
    }
    
    public static <T> ApiResponse<T> error(String error) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setError(error);
        return response;
    }
    
    // Getters y Setters
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    
    public T getData() { return data; }
    public void setData(T data) { this.data = data; }
    
    public String getError() { return error; }
    public void setError(String error) { this.error = error; }
}

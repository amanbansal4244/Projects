package com.sapient.crud.demo.exception;
public class ResourceNotFoundError {
 
    private String errorMessage;
 
    public ResourceNotFoundError(String errorMessage){
        this.errorMessage = errorMessage;
    }
 
    public String getErrorMessage() {
        return errorMessage;
    }
 
}
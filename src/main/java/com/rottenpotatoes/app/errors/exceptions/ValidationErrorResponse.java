package com.rottenpotatoes.app.errors.exceptions;

import com.rottenpotatoes.app.errors.BaseErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ValidationErrorResponse extends BaseErrorResponse {

    private List<Violation> violations;

    public ValidationErrorResponse(HttpStatus status){
        super(status);
        violations = new  ArrayList<>();
    }

    public ValidationErrorResponse(HttpStatus status, String message, Throwable ex) {
        super(status, message, ex);
        violations = new  ArrayList<>();
    }

    public List<Violation> getViolations(){
        return this.violations;
    }

    public void setViolations(List<Violation> violations) {
        this.violations = violations;
    }

    public void addViolation(String fieldName, String message){
        if(this.violations == null){
            this.violations = new  ArrayList<>();
        }
        this.violations.add(new Violation(fieldName,message));
    }

//    public void addViolation(List<ObjectError> globalErrors) {
//        globalErrors.forEach(this::addViolation);
//    }

    public void addViolation(List<FieldError> fieldErrors) {
        fieldErrors.forEach(this::addViolation);
    }

    private void addViolation(FieldError fieldError) {
        this.addViolation(fieldError.getField(),fieldError.getDefaultMessage());
    }

    private void addViolation(ObjectError objectError) {
        this.addViolation(objectError.getObjectName(),objectError.getDefaultMessage());
    }


    public class Violation {
        private final String fieldName;
        private final String message;

        Violation(String fieldName, String message) {
            this.fieldName = fieldName;
            this.message = message;
        }
    }
}

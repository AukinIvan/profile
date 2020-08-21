package com.testtask.profile.exception;

import com.testtask.profile.model.Error;
import com.testtask.profile.repository.ErrorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler {
    @Autowired
    private ErrorRepository errorRepository;
    private String msg;

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ExceptionInfo> handleDuplicateEmailException() {
        msg = "User with such mail exists";
        errorRepository.save(new Error(msg));
        return new ResponseEntity<>(new ExceptionInfo(msg), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionInfo> handleException(BindException error) {
        String errorFieldsMsg = error
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fe -> String.format("[%s] %s", fe.getField(), fe.getDefaultMessage()))
                .collect(Collectors.joining(" <br> "));
        errorRepository.save(new Error(errorFieldsMsg));
        return new ResponseEntity<>(new ExceptionInfo(errorFieldsMsg), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RestException.NotFoundException.class)
    public ResponseEntity<ExceptionInfo> handleExceptionNotFound() {
        msg = "Not found";
        errorRepository.save(new Error(msg));
        return new ResponseEntity<>(new ExceptionInfo(msg), HttpStatus.NOT_FOUND);
    }


    private static class ExceptionInfo {
        private String msg;

        public ExceptionInfo(String msg) {
            this.msg = msg;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }
}

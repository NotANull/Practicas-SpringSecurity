package com.notanull.SpringSecurityCourse.exceptions;

import com.notanull.SpringSecurityCourse.dto.response.ApiErrorResp;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handlerGenericException(Exception ex, HttpServletRequest request) {

        ApiErrorResp apiErrorResp = new ApiErrorResp();
        apiErrorResp.setBackendMessage(ex.getLocalizedMessage());
        apiErrorResp.setUrl(request.getRequestURL().toString());
        apiErrorResp.setMethod(request.getMethod());
        apiErrorResp.setMessage("Error interno en el servidor, vuelva a intentarlo");

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiErrorResp);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handlerMethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request) {

        ApiErrorResp apiErrorResp = new ApiErrorResp();
        apiErrorResp.setBackendMessage(ex.getLocalizedMessage());
        apiErrorResp.setUrl(request.getRequestURL().toString());
        apiErrorResp.setMethod(request.getMethod());
        apiErrorResp.setTimestamp(LocalDateTime.now());
        apiErrorResp.setMessage("Error en la petición enviada");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiErrorResp);
    }
}

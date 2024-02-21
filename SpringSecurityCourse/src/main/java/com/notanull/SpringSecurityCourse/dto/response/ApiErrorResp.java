package com.notanull.SpringSecurityCourse.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiErrorResp implements Serializable {

    private String backendMessage;

    private String message;

    private LocalDateTime timestamp;

    private String url;

    private String method;
}

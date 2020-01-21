package com.hamidur.springBootRESTfulWebservices.errors;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class InvalidRequestExceptionResponse
{
    private int status;
    private String message;

    public InvalidRequestExceptionResponse(InvalidRequestException ex)
    {
        this.status = ex.getStatus();
        this.message = ex.getMessage();
    }

    public InvalidRequestExceptionResponse(int status, String message)
    {
        this.status = status;
        this.message = message;
    }
}

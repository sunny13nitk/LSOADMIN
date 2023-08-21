package com.sap.cds.lsoadmin.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class EX_LSOADMIN extends RuntimeException
{

    public EX_LSOADMIN(String message)
    {

        super(message);
        log.error(message);
    }
}
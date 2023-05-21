package br.com.fiap.vocatalk.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class CustomerHasPlanException extends RuntimeException{
 
    public CustomerHasPlanException(String message){
        super(message);
    }
}

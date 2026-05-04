package com.exemplo.crud.exception;

public class RegraNegocioException extends RuntimeException {


    public RegraNegocioException(String mensagem){
        super(mensagem);
    }

}
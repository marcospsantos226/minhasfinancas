package com.marcosp.minhasfinancas.exception;

public class erroAutenticacao extends RuntimeException{
    public erroAutenticacao(String mensagem){
        super(mensagem);
    }
}

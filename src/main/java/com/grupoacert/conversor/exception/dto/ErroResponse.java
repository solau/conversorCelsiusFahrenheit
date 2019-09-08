package com.grupoacert.conversor.exception.dto;

public class ErroResponse {

    public String campo;
    public String mensagem;

    public ErroResponse(String campo, String message) {
        this.campo = campo;
        this.mensagem = message;
    }

    public ErroResponse(String message) {
        this.mensagem = message;
    }

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}

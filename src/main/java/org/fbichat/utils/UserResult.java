package org.fbichat.utils;


public enum UserResult {
    CREATED("CREATED", "Usuário criado."),
    USER_ALREADY_EXISTS("USER_ALREADY_EXISTS", "Já existe um usuário com mesmo nome.");

    private String value;
    private String text;

    UserResult(String value, String text){
        this.value = value;
        this.text = text;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value){
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
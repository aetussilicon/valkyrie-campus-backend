package br.com.valkyrie.campus.model.enums;

import lombok.Getter;

@Getter
public enum UsersRole {

    USER("user"),
    ADMIN("admin");

    private final String role;

    UsersRole(String role) {
        this.role = role;
    }
}

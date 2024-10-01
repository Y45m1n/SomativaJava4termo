package com.example.Model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Login {
    // private static final String DB_URL = "jdbc:mysql://localhost:3306/gerenciamento_hotel";
    // private static final String USER = "postgres"; // seu usuário do banco
    // private static final String PASSWORD = "postgres"; // sua senha do banco

    public boolean authenticate(String username, String password) {
        // Simulando uma autenticação simples
        return username.equals("adm_hotel") && password.equals("adm123");
    }
}

package com.example.dao;

import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.SQLException; 

// Classe responsável por gerenciar a conexão com o banco de dados
public class DatabaseConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/gerencia_hotel"; // URL do banco de dados
    private static final String USER = "postgres"; // Usuário do banco de dados
    private static final String PASSWORD = "postgres"; // Senha do banco de dados

    // Método estático para obter uma conexão com o banco de dados
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD); // Retorna a conexão usando o DriverManager
    }
}

package com.example.dao;

import com.example.model.Pagamento;
import com.example.model.Reserva;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PagamentoDAO {
   // Método para salvar um pagamento no banco de dados
public void salvar(Pagamento pagamento) {
    // SQL para inserir um novo pagamento na tabela 'pagamentos'
    String sql = "INSERT INTO pagamentos (numero_reserva, cpf_cliente, valor, data_pagamento) VALUES (?, ?, ?, ?)";
    try (Connection conn = DatabaseConnection.getConnection(); // Estabelece a conexão com o banco de dados
         PreparedStatement stmt = conn.prepareStatement(sql)) { // Prepara a instrução SQL
        // Define os parâmetros da consulta usando os dados do objeto pagamento
        stmt.setString(1, pagamento.getNumeroReserva()); // Número da reserva
        stmt.setString(2, pagamento.getCpfCliente()); // CPF do cliente
        stmt.setDouble(3, pagamento.getValor()); // Valor do pagamento
        stmt.setDate(4, Date.valueOf(pagamento.getDataPagamento())); // Converte LocalDate para Date e define
        stmt.executeUpdate(); // Executa a inserção no banco de dados
    } catch (SQLException e) {
        e.printStackTrace(); // Imprime a exceção em caso de erro
    }
}

// Método para listar todos os pagamentos do banco de dados
public List<Pagamento> listar() {
    List<Pagamento> pagamentos = new ArrayList<>(); // Cria uma lista para armazenar os pagamentos
    // SQL para selecionar todos os pagamentos da tabela 'pagamentos'
    String sql = "SELECT * FROM pagamentos";
    try (Connection conn = DatabaseConnection.getConnection(); // Estabelece a conexão com o banco de dados
         Statement stmt = conn.createStatement(); // Cria um Statement para executar a consulta
         ResultSet rs = stmt.executeQuery(sql)) { // Executa a consulta e armazena os resultados
        while (rs.next()) { // Itera sobre os resultados
            // Cria um novo objeto Pagamento com os dados retornados
            Pagamento pagamento = new Pagamento(
                    rs.getString("cpf_cliente"), // CPF do cliente
                    rs.getInt("numero_quarto"), // Número do quarto
                    rs.getDate("data_entrada").toLocalDate(), // Data de entrada
                    rs.getDate("data_saida").toLocalDate(), // Data de saída
                    rs.getString("numero_reserva"), // Número da reserva
                    rs.getDouble("valor"), // Valor do pagamento
                    rs.getDate("data_pagamento").toLocalDate() // Data do pagamento
            );
            pagamentos.add(pagamento); // Adiciona o pagamento à lista
        }
    } catch (SQLException e) {
        e.printStackTrace(); // Imprime a exceção em caso de erro
    }
    return pagamentos; // Retorna a lista de pagamentos
}

// Método para buscar uma reserva pelo número da reserva
public Reserva buscarReservaPorNumero(String numeroReserva) {
    // SQL para selecionar uma reserva pelo número da reserva
    String sql = "SELECT * FROM reservas WHERE numero_reserva = ?";
    try (Connection conn = DatabaseConnection.getConnection(); // Estabelece a conexão com o banco de dados
         PreparedStatement stmt = conn.prepareStatement(sql)) { // Prepara a instrução SQL
        stmt.setString(1, numeroReserva); // Define o parâmetro para o número da reserva
        ResultSet rs = stmt.executeQuery(); // Executa a consulta
        if (rs.next()) { // Se encontrar uma reserva
            // Cria e retorna um novo objeto Reserva com os dados retornados
            return new Reserva(
                    rs.getString("cpf_cliente"), // CPF do cliente
                    rs.getInt("numero_quarto"), // Número do quarto
                    rs.getDate("data_entrada").toLocalDate(), // Data de entrada
                    rs.getDate("data_saida").toLocalDate(), // Data de saída
                    rs.getString("numero_reserva") // Número da reserva
            );
        }
    } catch (SQLException e) {
        e.printStackTrace(); // Imprime a exceção em caso de erro
    }
    return null; // Retorna null se não encontrar a reserva
}

}

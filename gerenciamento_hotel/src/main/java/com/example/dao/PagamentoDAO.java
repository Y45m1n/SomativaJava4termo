package com.example.dao;

import com.example.model.Pagamento;
import com.example.model.Reserva;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PagamentoDAO {

    // Método para salvar um pagamento
    public void salvar(Pagamento pagamento) {
        String sql = "INSERT INTO pagamentos (numero_reserva, cpf_cliente, valor, data_pagamento) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, pagamento.getNumeroReserva());
            stmt.setString(2, pagamento.getCpfCliente());
            stmt.setDouble(3, pagamento.getValor());
            stmt.setDate(4, Date.valueOf(pagamento.getDataPagamento()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para listar pagamentos
    public List<Pagamento> listar() {
        List<Pagamento> pagamentos = new ArrayList<>();
        String sql = "SELECT * FROM pagamentos";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Pagamento pagamento = new Pagamento(
                        rs.getString("cpf_cliente"),
                        rs.getInt("numero_quarto"),
                        rs.getDate("data_entrada").toLocalDate(),
                        rs.getDate("data_saida").toLocalDate(),
                        rs.getString("numero_reserva"),
                        rs.getDouble("valor"),
                        rs.getDate("data_pagamento").toLocalDate()
                );
                pagamentos.add(pagamento);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pagamentos;
    }

    // Método para buscar uma reserva pelo número da reserva
    public Reserva buscarReservaPorNumero(String numeroReserva) {
        String sql = "SELECT * FROM reservas WHERE numero_reserva = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, numeroReserva);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Reserva(
                        rs.getString("cpf_cliente"),
                        rs.getInt("numero_quarto"),
                        rs.getDate("data_entrada").toLocalDate(),
                        rs.getDate("data_saida").toLocalDate(),
                        rs.getString("numero_reserva") // Pode ser o mesmo que o parâmetro
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Se não encontrar
    }
}

package com.example.dao;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.example.model.Reserva;

public class ReservaDAO {

    public void salvar(Reserva reserva) {
        String sql = "INSERT INTO reservas (cpf_cliente, numero_quarto, data_entrada, data_saida, numero_reserva) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, reserva.getCpfCliente());
            stmt.setInt(2, reserva.getNumeroQuarto());
            stmt.setDate(3, Date.valueOf(reserva.getDataEntrada()));
            stmt.setDate(4, Date.valueOf(reserva.getDataSaida()));
            stmt.setString(5, reserva.getNumeroReserva());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Reserva> listar() {
        List<Reserva> reservas = new ArrayList<>();
        String sql = "SELECT * FROM reservas";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Reserva reserva = new Reserva(
                        rs.getString("cpf_cliente"),
                        rs.getInt("numero_quarto"),
                        rs.getDate("data_entrada").toLocalDate(),
                        rs.getDate("data_saida").toLocalDate(),
                        rs.getString("numero_reserva")
                );
                reservas.add(reserva);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservas;
    }

    // Método para listar reservas por quarto (opcional)
}

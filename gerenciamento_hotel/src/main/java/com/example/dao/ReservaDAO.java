package com.example.dao;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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
            
            // Gravar a reserva em um arquivo
            gravarReservaEmArquivo(reserva);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void gravarReservaEmArquivo(Reserva reserva) {
        String nomeArquivo = "reservas.txt"; // Nome do arquivo
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo, true))) {
            // Escreve o número da reserva e o CPF do cliente
            writer.write("--------------------RECIBO DE RESERVA-------------------- \n Reserva: " + reserva.getNumeroReserva() + ", Quarto: " + reserva.getNumeroQuarto() +  " \n CPF: " + reserva.getCpfCliente()+ ", Data de Entrada: " + reserva.getDataEntrada() + ", Data de Saída: " + reserva.getDataSaida() +"\n" );
            writer.write("-------------------------------------------------------" );
            writer.newLine(); // Adiciona nova linha
        } catch (IOException e) {
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
}

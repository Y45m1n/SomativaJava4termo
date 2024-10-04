package com.example.dao;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
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
            writer.write("--------------------RECIBO DE RESERVA-------------------- \n Reserva: " + reserva.getNumeroReserva() + ", Quarto: " + reserva.getNumeroQuarto() +  " \n CPF: " + reserva.getCpfCliente() + ", Data de Entrada: " + reserva.getDataEntrada() + ", Data de Saída: " + reserva.getDataSaida() + "\n");
            writer.write("-------------------------------------------------------");
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

    public boolean isQuartoReservado(int numeroQuarto, LocalDate dataEntrada, LocalDate dataSaida) {
        String sql = "SELECT * FROM reservas WHERE numero_quarto = ? AND " +
                     "((data_entrada <= ? AND data_saida >= ?) OR " + // Se a nova entrada se sobrepõe à reserva existente
                     "(data_entrada <= ? AND data_saida >= ?))"; // Se a nova saída se sobrepõe à reserva existente
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, numeroQuarto);
            stmt.setDate(2, Date.valueOf(dataSaida)); // Verifica até quando o quarto está reservado
            stmt.setDate(3, Date.valueOf(dataEntrada)); // Verifica desde quando o quarto está reservado
            stmt.setDate(4, Date.valueOf(dataSaida)); // Verifica até quando a nova reserva está
            stmt.setDate(5, Date.valueOf(dataEntrada)); // Verifica desde quando a nova reserva está
            
            ResultSet rs = stmt.executeQuery();
            return rs.next(); // Retorna true se já houver uma reserva
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Em caso de erro, assume que o quarto não está reservado
        }
    }
}

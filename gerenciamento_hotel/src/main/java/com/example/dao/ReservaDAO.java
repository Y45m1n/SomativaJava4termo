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

 // Método para salvar uma nova reserva no banco de dados
public void salvar(Reserva reserva) {
    // SQL para inserir uma nova reserva na tabela 'reservas'
    String sql = "INSERT INTO reservas (cpf_cliente, numero_quarto, data_entrada, data_saida, numero_reserva) VALUES (?, ?, ?, ?, ?)";
    try (Connection conn = DatabaseConnection.getConnection(); // Estabelece a conexão com o banco de dados
         PreparedStatement stmt = conn.prepareStatement(sql)) { // Prepara a instrução SQL
        // Define os parâmetros da consulta usando os dados do objeto reserva
        stmt.setString(1, reserva.getCpfCliente()); // CPF do cliente
        stmt.setInt(2, reserva.getNumeroQuarto()); // Número do quarto reservado
        stmt.setDate(3, Date.valueOf(reserva.getDataEntrada())); // Data de entrada convertida para SQL Date
        stmt.setDate(4, Date.valueOf(reserva.getDataSaida())); // Data de saída convertida para SQL Date
        stmt.setString(5, reserva.getNumeroReserva()); // Número da reserva
        stmt.executeUpdate(); // Executa a inserção no banco de dados
        
        // Grava a reserva em um arquivo para fins de recibo
        gravarReservaEmArquivo(reserva);
    } catch (SQLException e) {
        e.printStackTrace(); // Imprime a exceção em caso de erro
    }
}

// Método privado para gravar detalhes da reserva em um arquivo
private void gravarReservaEmArquivo(Reserva reserva) {
    String nomeArquivo = "reservas.txt"; // Nome do arquivo onde a reserva será gravada
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo, true))) { // Abre o arquivo em modo de append
        // Escreve as informações da reserva no arquivo
        writer.write("--------------------RECIBO DE RESERVA-------------------- \n Reserva: " + reserva.getNumeroReserva() + ", Quarto: " + reserva.getNumeroQuarto() +  " \n CPF: " + reserva.getCpfCliente() + ", Data de Entrada: " + reserva.getDataEntrada() + ", Data de Saída: " + reserva.getDataSaida() + "\n");
        writer.write("-------------------------------------------------------");
        writer.newLine(); // Adiciona uma nova linha após a gravação
    } catch (IOException e) {
        e.printStackTrace(); // Imprime a exceção em caso de erro na gravação
    }
}

// Método para listar todas as reservas do banco de dados
public List<Reserva> listar() {
    List<Reserva> reservas = new ArrayList<>(); // Cria uma lista para armazenar as reservas
    // SQL para selecionar todas as reservas da tabela 'reservas'
    String sql = "SELECT * FROM reservas";
    try (Connection conn = DatabaseConnection.getConnection(); // Estabelece a conexão com o banco de dados
         Statement stmt = conn.createStatement(); // Cria um Statement para executar a consulta
         ResultSet rs = stmt.executeQuery(sql)) { // Executa a consulta e armazena os resultados
        while (rs.next()) { // Itera sobre os resultados
            // Cria um novo objeto Reserva com os dados retornados
            Reserva reserva = new Reserva(
                    rs.getString("cpf_cliente"), // CPF do cliente
                    rs.getInt("numero_quarto"), // Número do quarto
                    rs.getDate("data_entrada").toLocalDate(), // Data de entrada
                    rs.getDate("data_saida").toLocalDate(), // Data de saída
                    rs.getString("numero_reserva") // Número da reserva
            );
            reservas.add(reserva); // Adiciona a reserva à lista
        }
    } catch (SQLException e) {
        e.printStackTrace(); // Imprime a exceção em caso de erro
    }
    return reservas; // Retorna a lista de reservas
}

// Método para verificar se um quarto está reservado em um determinado intervalo de datas
public boolean isQuartoReservado(int numeroQuarto, LocalDate dataEntrada, LocalDate dataSaida) {
    // SQL para verificar se há sobreposição de reservas para o quarto e datas informadas
    String sql = "SELECT * FROM reservas WHERE numero_quarto = ? AND " +
                 "((data_entrada <= ? AND data_saida >= ?) OR " + // Se a nova entrada se sobrepõe à reserva existente
                 "(data_entrada <= ? AND data_saida >= ?))"; // Se a nova saída se sobrepõe à reserva existente
    try (Connection conn = DatabaseConnection.getConnection(); // Estabelece a conexão com o banco de dados
         PreparedStatement stmt = conn.prepareStatement(sql)) { // Prepara a instrução SQL
        stmt.setInt(1, numeroQuarto); // Define o número do quarto
        stmt.setDate(2, Date.valueOf(dataSaida)); // Define a data de saída para verificação
        stmt.setDate(3, Date.valueOf(dataEntrada)); // Define a data de entrada para verificação
        stmt.setDate(4, Date.valueOf(dataSaida)); // Repetido para verificar sobreposição
        stmt.setDate(5, Date.valueOf(dataEntrada)); // Repetido para verificar sobreposição
        
        ResultSet rs = stmt.executeQuery(); // Executa a consulta
        return rs.next(); // Retorna true se já houver uma reserva para o quarto no intervalo
    } catch (SQLException e) {
        e.printStackTrace(); // Imprime a exceção em caso de erro
        return false; // Em caso de erro, assume que o quarto não está reservado
    }
}

}

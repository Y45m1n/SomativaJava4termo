package com.example.dao;

import com.example.model.Cliente;

import java.sql.*; 
import java.util.ArrayList; 
import java.util.List; 


public class ClienteDAO {
    private List<Cliente> clientes; // Lista local de clientes

    // Construtor que inicializa a lista de clientes e a popula ao instanciar
    public ClienteDAO() {
        this.clientes = new ArrayList<>();
        listar(); // Carrega clientes da base de dados na inicialização
    }

    // Método para salvar um novo cliente no banco de dados
    public void salvar(Cliente cliente) {
        String sql = "INSERT INTO clientes (nome, cpf, telefone, email, endereco) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection(); // Estabelece a conexão com o banco
             PreparedStatement stmt = conn.prepareStatement(sql)) { // Prepara a instrução SQL
            // Define os parâmetros da consulta
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setString(3, cliente.getTelefone());
            stmt.setString(4, cliente.getEmail());
            stmt.setString(5, cliente.getEndereco());
            stmt.executeUpdate(); // Executa a inserção
            clientes.add(cliente); // Adiciona o cliente à lista local
        } catch (SQLException e) {
            e.printStackTrace(); // Imprime a exceção em caso de erro
        }
    }

    // Método para listar todos os clientes do banco de dados
    public List<Cliente> listar() {
        clientes.clear(); // Limpa a lista local antes de popular
        String sql = "SELECT * FROM clientes"; // Consulta SQL para selecionar todos os clientes
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) { // Executa a consulta
            while (rs.next()) { // Itera sobre os resultados
                // Cria um novo objeto Cliente com os dados retornados
                Cliente cliente = new Cliente(
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getString("telefone"),
                        rs.getString("email"),
                        rs.getString("endereco")
                );
                clientes.add(cliente); // Adiciona o cliente à lista local
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Imprime a exceção em caso de erro
        }
        return clientes; // Retorna a lista de clientes
    }

    // Método para buscar um cliente pelo CPF
    public Cliente buscarPorCpf(String cpf) {
        for (Cliente cliente : clientes) {
            if (cliente.getCpf().equals(cpf)) {
                return cliente; // Retorna o cliente se o CPF corresponder
            }
        }
        return null; // Retorna null se não encontrar o cliente
    }

    // Método para excluir um cliente do banco de dados pelo CPF
    public void excluir(String cpf) {
        String sql = "DELETE FROM clientes WHERE cpf = ?"; // Consulta SQL para exclusão
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cpf); // Define o CPF do cliente a ser excluído
            stmt.executeUpdate(); // Executa a exclusão
        } catch (SQLException e) {
            e.printStackTrace(); // Imprime a exceção em caso de erro
        }
    }

    // Método para atualizar as informações de um cliente no banco de dados
    public void atualizar(Cliente cliente) {
        String sql = "UPDATE clientes SET nome = ?, telefone = ?, email = ?, endereco = ? WHERE cpf = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            // Define os parâmetros da consulta para atualização
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getTelefone());
            stmt.setString(3, cliente.getEmail());
            stmt.setString(4, cliente.getEndereco());
            stmt.setString(5, cliente.getCpf());
            stmt.executeUpdate(); // Executa a atualização
        } catch (SQLException e) {
            e.printStackTrace(); // Imprime a exceção em caso de erro
        }
    }
}

package com.example.controller;

import java.util.List;
import com.example.dao.ClienteDAO;
import com.example.dao.PagamentoDAO;
import com.example.dao.ReservaDAO;
import java.time.LocalDate;
import com.example.model.Cliente;
import com.example.model.Pagamento;
import com.example.model.Reserva;

// Controlador principal para gerenciar operações relacionadas ao hotel
public class HotelController {
    private ClienteDAO clienteDAO; // Objeto para gerenciar clientes
    private ReservaDAO reservaDAO; // Objeto para gerenciar reservas
    private PagamentoDAO pagamentoDAO; // Objeto para gerenciar pagamentos

    // Construtor que inicializa os DAOs
    public HotelController() {
        clienteDAO = new ClienteDAO();
        reservaDAO = new ReservaDAO();
        pagamentoDAO = new PagamentoDAO(); // Inicialização do DAO de pagamentos
    }

    // Método para cadastrar um novo cliente
    public void cadastrarCliente(Cliente cliente) {
        clienteDAO.salvar(cliente); // Chama o método de salvar no ClienteDAO
    }

    // Método para listar todos os clientes cadastrados
    public List<Cliente> listarClientes() {
        return clienteDAO.listar(); // Retorna a lista de clientes
    }

    // Verifica se um quarto está reservado em uma determinada data
    public boolean isQuartoReservado(int numeroQuarto, LocalDate dataEntrada, LocalDate dataSaida) {
        return reservaDAO.isQuartoReservado(numeroQuarto, dataEntrada, dataSaida);
    }

    // Método para reservar um quarto
    public void reservarQuarto(Reserva reserva) {
        reservaDAO.salvar(reserva); // Chama o método de salvar no ReservaDAO
    }

    // Método para listar todas as reservas
    public List<Reserva> listarReservas() {
        return reservaDAO.listar(); // Retorna a lista de reservas
    }

    // Verifica se um CPF já está cadastrado
    public boolean isCpfCadastrado(String cpf) {
        Cliente cliente = clienteDAO.buscarPorCpf(cpf); // Busca cliente pelo CPF
        return cliente != null; // Retorna true se o cliente existir
    }

    // Método para buscar uma reserva pelo número
    public Reserva buscarReserva(String numeroReserva) {
        return pagamentoDAO.buscarReservaPorNumero(numeroReserva); // Busca a reserva
    }

    // Método para salvar um pagamento
    public void salvarPagamento(Pagamento pagamento) {
        pagamentoDAO.salvar(pagamento); // Chama o método de salvar no PagamentoDAO
    }

    // Método para excluir um cliente pelo CPF
    public void excluirCliente(String cpf) {
        clienteDAO.excluir(cpf); // Chama o método de exclusão no ClienteDAO
    }

    // Método para editar as informações de um cliente
    public void editarCliente(Cliente cliente) {
        clienteDAO.atualizar(cliente); // Chama o método de atualização no ClienteDAO
    }
}

package com.example.controller;

import java.util.List;
import com.example.dao.ClienteDAO;
import com.example.dao.PagamentoDAO;
import com.example.dao.ReservaDAO;
import java.time.LocalDate;
import com.example.model.Cliente;
import com.example.model.Pagamento;
import com.example.model.Reserva;

public class HotelController {
    private ClienteDAO clienteDAO;
    private ReservaDAO reservaDAO;
    private PagamentoDAO pagamentoDAO; // Adicionado

    public HotelController() {
        clienteDAO = new ClienteDAO();
        reservaDAO = new ReservaDAO();
        pagamentoDAO = new PagamentoDAO(); // Inicializado
    }

    public void cadastrarCliente(Cliente cliente) {
        clienteDAO.salvar(cliente);
    }

    public List<Cliente> listarClientes() {
        return clienteDAO.listar();
    }

    public boolean isQuartoReservado(int numeroQuarto, LocalDate dataEntrada, LocalDate dataSaida) {
        return reservaDAO.isQuartoReservado(numeroQuarto, dataEntrada, dataSaida);
    }

    public void reservarQuarto(Reserva reserva) {
        reservaDAO.salvar(reserva);
    }

    public List<Reserva> listarReservas() {
        return reservaDAO.listar();
    }

    public boolean isCpfCadastrado(String cpf) {
        Cliente cliente = clienteDAO.buscarPorCpf(cpf); // Supondo que você tenha esse método no ClienteDAO
        return cliente != null; // Retorna true se o cliente existir, false caso contrário
    }

    public Reserva buscarReserva(String numeroReserva) {
        return pagamentoDAO.buscarReservaPorNumero(numeroReserva);
    }

    // Método para salvar um pagamento
    public void salvarPagamento(Pagamento pagamento) {
        pagamentoDAO.salvar(pagamento);
    }

    // Método para excluir um cliente
    public void excluirCliente(String cpf) {
        clienteDAO.excluir(cpf); // Chama o método de exclusão no ClienteDAO
    }

    // Método para editar um cliente
    public void editarCliente(Cliente cliente) {
        clienteDAO.atualizar(cliente); // Chama o método de atualização no ClienteDAO
    }
}

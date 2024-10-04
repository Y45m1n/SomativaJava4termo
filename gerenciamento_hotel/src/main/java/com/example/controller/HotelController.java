package com.example.controller;

import java.util.List;

import com.example.dao.ClienteDAO;
import com.example.dao.ReservaDAO;
import com.example.model.Cliente;
import com.example.model.Reserva;





public class HotelController {
    private ClienteDAO clienteDAO;
    private ReservaDAO reservaDAO;

    public HotelController() {
        clienteDAO = new ClienteDAO();
        reservaDAO = new ReservaDAO();
    }

    public void cadastrarCliente(Cliente cliente) {
        clienteDAO.salvar(cliente);
    }

    public List<Cliente> listarClientes() {
        return clienteDAO.listar();
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
}


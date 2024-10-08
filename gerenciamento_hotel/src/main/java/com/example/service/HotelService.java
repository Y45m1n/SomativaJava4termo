package com.example.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.example.model.Cliente;
import com.example.model.Reserva;

public class HotelService {
    private List<Cliente> clientes;
    private List<Reserva> reservas;

    public HotelService() {
        this.clientes = new ArrayList<>();
        this.reservas = new ArrayList<>();
    }

    // Adiciona um novo cliente
    public void adicionarCliente(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente não pode ser nulo.");
        }
        if (isCpfCadastrado(cliente.getCpf())) {
            throw new IllegalArgumentException("CPF já cadastrado: " + cliente.getCpf());
        }
        clientes.add(cliente);
    }

    // Lista todos os clientes
    public List<Cliente> listarClientes() {
        return new ArrayList<>(clientes);
    }

    // Edita um cliente existente
    public void editarCliente(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente não pode ser nulo.");
        }
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getCpf().equals(cliente.getCpf())) {
                clientes.set(i, cliente);
                return;
            }
        }
        throw new IllegalArgumentException("Cliente não encontrado com CPF: " + cliente.getCpf());
    }

    // Exclui um cliente pelo CPF
    public void excluirCliente(String cpf) {
        if (cpf == null || cpf.isEmpty()) {
            throw new IllegalArgumentException("CPF não pode ser nulo ou vazio.");
        }
        boolean removido = clientes.removeIf(cliente -> cliente.getCpf().equals(cpf));
        if (!removido) {
            throw new IllegalArgumentException("Cliente não encontrado com CPF: " + cpf);
        }
    }

    // Adiciona uma nova reserva
    public void reservarQuarto(Reserva reserva) {
        if (reserva == null) {
            throw new IllegalArgumentException("Reserva não pode ser nula.");
        }
        if (isQuartoReservado(reserva.getNumeroQuarto(), reserva.getDataEntrada(), reserva.getDataSaida())) {
            throw new IllegalArgumentException("Quarto já reservado para as datas selecionadas.");
        }
        reservas.add(reserva);
    }

    // Lista todas as reservas
    public List<Reserva> listarReservas() {
        return new ArrayList<>(reservas);
    }

    // Verifica se um quarto está reservado em uma data específica
    public boolean isQuartoReservado(int numeroQuarto, LocalDate dataEntrada, LocalDate dataSaida) {
        for (Reserva reserva : reservas) {
            if (reserva.getNumeroQuarto() == numeroQuarto &&
                !(dataSaida.isBefore(reserva.getDataEntrada()) || dataEntrada.isAfter(reserva.getDataSaida()))) {
                return true;
            }
        }
        return false;
    }

    // Verifica se um CPF está cadastrado
    public boolean isCpfCadastrado(String cpf) {
        if (cpf == null || cpf.isEmpty()) {
            throw new IllegalArgumentException("CPF não pode ser nulo ou vazio.");
        }
        return clientes.stream().anyMatch(cliente -> cliente.getCpf().equals(cpf));
    }

    // Busca uma reserva pelo número da reserva
    public Reserva buscarReserva(String numeroReserva) {
        if (numeroReserva == null || numeroReserva.isEmpty()) {
            throw new IllegalArgumentException("Número da reserva não pode ser nulo ou vazio.");
        }
        for (Reserva reserva : reservas) {
            if (reserva.getNumeroReserva().equals(numeroReserva)) {
                return reserva;
            }
        }
        throw new IllegalArgumentException("Reserva não encontrada com número: " + numeroReserva);
    }
}

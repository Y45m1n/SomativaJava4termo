package com.example.model;

import java.time.LocalDate;

public class Pagamento extends Reserva {
    private double valor;
    private LocalDate dataPagamento;

    public Pagamento(String cpfCliente, int numeroQuarto, LocalDate dataEntrada, LocalDate dataSaida, String numeroReserva, double valor, LocalDate dataPagamento) {
        super(cpfCliente, numeroQuarto, dataEntrada, dataSaida, numeroReserva);
        this.valor = valor;
        this.dataPagamento = dataPagamento;
    }

    // Getters e Setters
    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }
}

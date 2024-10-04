package com.example.model;


import java.time.LocalDate;

public class Pagamento {
    private String numeroReserva;
    private String cpfCliente;
    private double valor;
    private LocalDate dataPagamento;

    public Pagamento(String numeroReserva, String cpfCliente, double valor, LocalDate dataPagamento) {
        this.numeroReserva = numeroReserva;
        this.cpfCliente = cpfCliente;
        this.valor = valor;
        this.dataPagamento = dataPagamento;
    }

    // Getters e Setters
    public String getNumeroReserva() {
        return numeroReserva;
    }

    public void setNumeroReserva(String numeroReserva) {
        this.numeroReserva = numeroReserva;
    }

    public String getCpfCliente() {
        return cpfCliente;
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

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


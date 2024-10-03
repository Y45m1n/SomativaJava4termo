package com.example.model;

import java.sql.Date;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;



@AllArgsConstructor
@Getter
@Setter
public class Reserva {

    private String cpfCliente;
    private int numeroQuarto;
    private LocalDate dataEntrada;
    private LocalDate dataSaida;
    private String numeroReserva; // Gerado automaticamente

    
}


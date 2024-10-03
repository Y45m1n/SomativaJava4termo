package com.example.view;


import java.awt.GridLayout;
import java.sql.Date; // Não vai ser necessário, mas pode ser usado se você precisar
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.UUID;

import javax.swing.*;

import org.jdatepicker.JDatePicker;
import org.jdatepicker.UtilDateModel;

import com.example.controller.HotelController;
import com.example.model.Reserva;

public class TelaReservaView extends JFrame {
    private HotelController controller;
    private int numeroQuarto;

    public TelaReservaView(HotelController controller, int numeroQuarto) {
        this.controller = controller;
        this.numeroQuarto = numeroQuarto;

        setTitle("Reservar Quarto " + numeroQuarto);
        setLayout(new GridLayout(5, 2));

        JTextField cpfField = new JTextField();
        
        // Criando os modelos para os JDatePicker
        UtilDateModel modelEntrada = new UtilDateModel();
        JDatePicker datePickerEntrada = new JDatePicker(modelEntrada);
        
        UtilDateModel modelSaida = new UtilDateModel();
        JDatePicker datePickerSaida = new JDatePicker(modelSaida);

        JButton btnReservar = new JButton("Reservar");

        add(new JLabel("CPF do Cliente:"));
        add(cpfField);
        add(new JLabel("Data de Entrada:"));
        add(datePickerEntrada);
        add(new JLabel("Data de Saída:"));
        add(datePickerSaida);
        add(btnReservar);

        btnReservar.addActionListener(e -> {
            try {
                String cpfCliente = cpfField.getText();
                
                // Obter as datas escolhidas
                java.util.Date dataEntradaDate = (java.util.Date) datePickerEntrada.getModel().getValue();
                java.util.Date dataSaidaDate = (java.util.Date) datePickerSaida.getModel().getValue();

                // Converter para LocalDate
                LocalDate dataEntrada = dataEntradaDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate dataSaida = dataSaidaDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                
                String numeroReserva = UUID.randomUUID().toString(); // Gera um número de reserva único
                Reserva reserva = new Reserva(cpfCliente, numeroQuarto, dataEntrada, dataSaida, numeroReserva);
                controller.reservarQuarto(reserva);
                JOptionPane.showMessageDialog(this, "Reserva realizada com sucesso!");
                voltarParaTelaInicial();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao realizar a reserva: " + ex.getMessage());
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null); // Centraliza a janela
        setVisible(true);
    }

    private void voltarParaTelaInicial() {
        new TelaInicialView(controller);
        this.setVisible(false);
    }
}



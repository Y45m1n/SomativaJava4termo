package com.example.view;


import javax.swing.*;

import com.example.controller.HotelController;
import com.example.model.Reserva;

import java.awt.*;
import java.util.List;

public class TelaListarReservasView extends JFrame {
    private HotelController controller;

    public TelaListarReservasView(HotelController controller) {
        this.controller = controller;

        setTitle("Listar Reservas");
        setLayout(new BorderLayout());

        List<Reserva> reservas = controller.listarReservas();
        StringBuilder sb = new StringBuilder();

        for (Reserva reserva : reservas) {
            sb.append("Reserva: ").append(reserva.getNumeroReserva())
              .append(", CPF: ").append(reserva.getCpfCliente())
              .append(", Quarto: ").append(reserva.getNumeroQuarto())
              .append(", Entrada: ").append(reserva.getDataEntrada())
              .append(", Saída: ").append(reserva.getDataSaida()).append("\n");
        }

        JTextArea areaReservas = new JTextArea(sb.toString());
        areaReservas.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(areaReservas);

        add(scrollPane, BorderLayout.CENTER);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(e -> voltarParaTelaInicial());
        add(btnVoltar, BorderLayout.SOUTH);

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

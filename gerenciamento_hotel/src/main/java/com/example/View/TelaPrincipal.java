package com.example.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class TelaPrincipal {
    private JFrame frame;
    private JPanel roomPanel;
    private JButton btnCadastrarCliente;
    private JButton btnListarClientes;
    private JButton btnListarReservas;
    private JButton btnRealizarReserva;

    public TelaPrincipal() {
        frame = new JFrame("Gerenciamento de Hotel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Painel de Quartos
        roomPanel = new JPanel();
        roomPanel.setLayout(new GridLayout(2, 5)); // 2 linhas, 5 colunas

        // Adicionando os "quartos" (quadrados) ao painel
        for (int i = 1; i <= 10; i++) {
            JPanel roomCard = new JPanel();
            roomCard.setBackground(new Color((int)(Math.random() * 0x1000000))); // Cores aleatórias
            roomCard.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            
            roomCard.setLayout(new BorderLayout());

            // Rótulo do quarto
            JLabel roomLabel = new JLabel("Quarto " + i, SwingConstants.CENTER);
            roomCard.add(roomLabel, BorderLayout.CENTER);

            // Botão para realizar reserva
            JButton btnRealizarReserva = new JButton("Realizar Reserva");
            roomCard.add(btnRealizarReserva, BorderLayout.SOUTH);

            roomPanel.add(roomCard);
        }

        // Adicionando o painel de quartos ao frame
        frame.add(roomPanel, BorderLayout.CENTER);

        // Painel de Botões
        JPanel buttonPanel = new JPanel();
        btnCadastrarCliente = new JButton("Cadastrar Cliente");
        btnListarClientes = new JButton("Listar Clientes");
        btnListarReservas = new JButton("Listar Reservas");

        buttonPanel.add(btnCadastrarCliente);
        buttonPanel.add(btnListarClientes);
        buttonPanel.add(btnListarReservas);

        // Adicionando o painel de botões ao frame
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null); // Centraliza a janela
    }

    public void addCadastrarClienteListener(ActionListener listener) {
        btnCadastrarCliente.addActionListener(listener);
    }

    public void addListarClientesListener(ActionListener listener) {
        btnListarClientes.addActionListener(listener);
    }

    public void addListarReservasListener(ActionListener listener) {
        btnListarReservas.addActionListener(listener);
    }

    
    public void addRealizarReservaListener(ActionListener listener) {
               btnRealizarReserva.addActionListener(listener);
           }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(frame, message);
    }
}

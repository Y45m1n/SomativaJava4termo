package com.example.view;



import javax.swing.*;

import com.example.controller.HotelController;
import com.example.model.Cliente;
import com.example.model.Reserva;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.UUID;

public class HotelView extends JFrame {
    private HotelController controller;
    private JTextField nomeField, cpfField, telefoneField, emailField, enderecoField;
    private JTextField cpfReservaField, numeroQuartoField, dataEntradaField, dataSaidaField;
    private JButton btnCadastrarCliente, btnReservarQuarto, btnListarClientes, btnListarReservas;

    public HotelView() {
        controller = new HotelController();

        setTitle("Gerenciamento de Hotel");
        setLayout(new GridLayout(10, 2));

        // Campos para cadastrar cliente
        nomeField = new JTextField();
        cpfField = new JTextField();
        telefoneField = new JTextField();
        emailField = new JTextField();
        enderecoField = new JTextField();

        // Campos para reservar quarto
        cpfReservaField = new JTextField();
        numeroQuartoField = new JTextField();
        dataEntradaField = new JTextField();
        dataSaidaField = new JTextField();

        // Botões
        btnCadastrarCliente = new JButton("Cadastrar Cliente");
        btnReservarQuarto = new JButton("Reservar Quarto");
        btnListarClientes = new JButton("Listar Clientes");
        btnListarReservas = new JButton("Listar Reservas");

        // Adicionando elementos à interface
        add(new JLabel("Nome:"));
        add(nomeField);
        add(new JLabel("CPF:"));
        add(cpfField);
        add(new JLabel("Telefone:"));
        add(telefoneField);
        add(new JLabel("Email:"));
        add(emailField);
        add(new JLabel("Endereço:"));
        add(enderecoField);
        add(btnCadastrarCliente);
        add(btnListarClientes);

        add(new JLabel("CPF do Cliente para Reserva:"));
        add(cpfReservaField);
        add(new JLabel("Número do Quarto:"));
        add(numeroQuartoField);
        add(new JLabel("Data de Entrada (YYYY-MM-DD):"));
        add(dataEntradaField);
        add(new JLabel("Data de Saída (YYYY-MM-DD):"));
        add(dataSaidaField);
        add(btnReservarQuarto);
        add(btnListarReservas);

        btnCadastrarCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = nomeField.getText();
                String cpf = cpfField.getText();
                String telefone = telefoneField.getText();
                String email = emailField.getText();
                String endereco = enderecoField.getText();
                Cliente cliente = new Cliente(nome, cpf, telefone, email, endereco);
                controller.cadastrarCliente(cliente);
                JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");
            }
        });

        btnReservarQuarto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cpfCliente = cpfReservaField.getText();
                int numeroQuarto = Integer.parseInt(numeroQuartoField.getText());
                LocalDate dataEntrada = LocalDate.parse(dataEntradaField.getText());
                LocalDate dataSaida = LocalDate.parse(dataSaidaField.getText());
                String numeroReserva = UUID.randomUUID().toString(); // Gera um número de reserva único
                Reserva reserva = new Reserva(cpfCliente, numeroQuarto, dataEntrada, dataSaida, numeroReserva);
                controller.reservarQuarto(reserva);
                JOptionPane.showMessageDialog(null, "Reserva realizada com sucesso!");
            }
        });

        // Implementação para listar clientes e reservas será feita depois

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null); // Centraliza a janela
        setVisible(true);
    }

    public static void main(String[] args) {
        new HotelView();
    }
}



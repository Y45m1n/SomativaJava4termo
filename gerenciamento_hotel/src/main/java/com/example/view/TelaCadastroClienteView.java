package com.example.view;

import javax.swing.*;

import com.example.controller.HotelController;
import com.example.model.Cliente;

import java.awt.*;

public class TelaCadastroClienteView extends JFrame {
    private HotelController controller;

    public TelaCadastroClienteView(HotelController controller) {
        this.controller = controller;

        setTitle("Cadastrar Cliente");
        setLayout(new GridLayout(5, 2));

        JTextField nomeField = new JTextField();
        JTextField cpfField = new JTextField();
        JTextField telefoneField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField enderecoField = new JTextField();

        JButton btnCadastrar = new JButton("Cadastrar");

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
        add(btnCadastrar);

        btnCadastrar.addActionListener(e -> {
            Cliente cliente = new Cliente(
                    nomeField.getText(),
                    cpfField.getText(),
                    telefoneField.getText(),
                    emailField.getText(),
                    enderecoField.getText()
            );
            controller.cadastrarCliente(cliente);
            JOptionPane.showMessageDialog(this, "Cliente cadastrado com sucesso!");
            voltarParaTelaInicial();
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

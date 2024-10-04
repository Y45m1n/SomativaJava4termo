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
        setLayout(new GridBagLayout()); // Usando GridBagLayout para melhor controle

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5); // Espaçamento entre os componentes

        JTextField nomeField = new JTextField(20);
        JTextField cpfField = new JTextField(20);
        JTextField telefoneField = new JTextField(20);
        JTextField emailField = new JTextField(20);
        JTextField enderecoField = new JTextField(20);

        JButton btnCadastrar = new JButton("Cadastrar");
        JButton btnVoltar = new JButton("Voltar"); // Botão "Voltar"

        // Configurando o tamanho preferido dos botões
        btnCadastrar.setPreferredSize(new Dimension(150, 30)); // Botão "Cadastrar" maior
        btnVoltar.setPreferredSize(new Dimension(80, 30)); // Botão "Voltar" menor

        // Adicionando os componentes ao layout
        gbc.gridx = 0; gbc.gridy = 0; // Primeira linha
        add(new JLabel("Nome:"), gbc);
        
        gbc.gridx = 1; gbc.gridy = 0;
        add(nomeField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1; // Segunda linha
        add(new JLabel("CPF:"), gbc);
        
        gbc.gridx = 1; gbc.gridy = 1;
        add(cpfField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2; // Terceira linha
        add(new JLabel("Telefone:"), gbc);
        
        gbc.gridx = 1; gbc.gridy = 2;
        add(telefoneField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 3; // Quarta linha
        add(new JLabel("Email:"), gbc);
        
        gbc.gridx = 1; gbc.gridy = 3;
        add(emailField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 4; // Quinta linha
        add(new JLabel("Endereço:"), gbc);
        
        gbc.gridx = 1; gbc.gridy = 4;
        add(enderecoField, gbc);

        // Botão "Voltar" à esquerda
        gbc.gridx = 0; gbc.gridy = 5; 
        gbc.anchor = GridBagConstraints.WEST; // Alinhamento do botão "Voltar" à esquerda
        add(btnVoltar, gbc);

        // Botão "Cadastrar" à direita
        gbc.gridx = 1; gbc.gridy = 5; // Linha do botão "Cadastrar"
        gbc.anchor = GridBagConstraints.EAST; // Alinhamento do botão "Cadastrar" à direita
        add(btnCadastrar, gbc);

        // Ação para o botão "Cadastrar"
        btnCadastrar.addActionListener(e -> {
            // Validação dos campos
            if (nomeField.getText().isEmpty() || 
                cpfField.getText().isEmpty() || 
                telefoneField.getText().isEmpty() || 
                emailField.getText().isEmpty() || 
                enderecoField.getText().isEmpty()) {
                
                JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
                return; // Não continuar se algum campo estiver vazio
            }

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

        // Ação para o botão "Voltar"
        btnVoltar.addActionListener(e -> voltarParaTelaInicial());

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

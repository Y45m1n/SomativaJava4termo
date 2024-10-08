package com.example.view;

import javax.swing.*;
import com.example.controller.HotelController;
import com.example.model.Cliente;

import java.awt.*;

public class TelaCadastroClienteView extends JFrame {
// Declaração do controlador que gerencia a lógica de cadastro de cliente
private HotelController controller;

// Construtor da classe TelaCadastroClienteView
public TelaCadastroClienteView(HotelController controller) {
    this.controller = controller; // Inicializa o controlador

    setTitle("Cadastrar Cliente"); // Define o título da janela
    setLayout(new GridBagLayout()); // Usa GridBagLayout para melhor controle do layout

    GridBagConstraints gbc = new GridBagConstraints(); // Configurações do GridBagConstraints
    gbc.fill = GridBagConstraints.HORIZONTAL; // Preenche o espaço horizontalmente
    gbc.insets = new Insets(5, 5, 5, 5); // Espaçamento entre os componentes

    // Criação dos campos de texto para entrada de dados do cliente
    JTextField nomeField = new JTextField(20);
    JTextField cpfField = new JTextField(20);
    JTextField telefoneField = new JTextField(20);
    JTextField emailField = new JTextField(20);
    JTextField enderecoField = new JTextField(20);

    // Criação dos botões "Cadastrar" e "Voltar"
    JButton btnCadastrar = new JButton("Cadastrar");
    JButton btnVoltar = new JButton("Voltar"); // Botão "Voltar"

    // Configurando o tamanho preferido dos botões
    btnCadastrar.setPreferredSize(new Dimension(150, 30)); // Botão "Cadastrar" maior
    btnVoltar.setPreferredSize(new Dimension(80, 30)); // Botão "Voltar" menor

    // Adicionando os componentes ao layout com suas posições
    gbc.gridx = 0; gbc.gridy = 0; // Primeira linha
    add(new JLabel("Nome:"), gbc); // Adiciona o rótulo "Nome"
    
    gbc.gridx = 1; gbc.gridy = 0;
    add(nomeField, gbc); // Adiciona o campo de texto para nome
    
    gbc.gridx = 0; gbc.gridy = 1; // Segunda linha
    add(new JLabel("CPF:"), gbc); // Adiciona o rótulo "CPF"
    
    gbc.gridx = 1; gbc.gridy = 1;
    add(cpfField, gbc); // Adiciona o campo de texto para CPF
    
    gbc.gridx = 0; gbc.gridy = 2; // Terceira linha
    add(new JLabel("Telefone:"), gbc); // Adiciona o rótulo "Telefone"
    
    gbc.gridx = 1; gbc.gridy = 2;
    add(telefoneField, gbc); // Adiciona o campo de texto para telefone
    
    gbc.gridx = 0; gbc.gridy = 3; // Quarta linha
    add(new JLabel("Email:"), gbc); // Adiciona o rótulo "Email"
    
    gbc.gridx = 1; gbc.gridy = 3;
    add(emailField, gbc); // Adiciona o campo de texto para email
    
    gbc.gridx = 0; gbc.gridy = 4; // Quinta linha
    add(new JLabel("Endereço:"), gbc); // Adiciona o rótulo "Endereço"
    
    gbc.gridx = 1; gbc.gridy = 4;
    add(enderecoField, gbc); // Adiciona o campo de texto para endereço

    // Adicionando o botão "Voltar" à esquerda
    gbc.gridx = 0; gbc.gridy = 5; 
    gbc.anchor = GridBagConstraints.WEST; // Alinha o botão "Voltar" à esquerda
    add(btnVoltar, gbc);

    // Adicionando o botão "Cadastrar" à direita
    gbc.gridx = 1; gbc.gridy = 5; // Linha do botão "Cadastrar"
    gbc.anchor = GridBagConstraints.EAST; // Alinha o botão "Cadastrar" à direita
    add(btnCadastrar, gbc);

    // Ação para o botão "Cadastrar"
    btnCadastrar.addActionListener(e -> {
        // Validação dos campos para garantir que não estão vazios
        if (nomeField.getText().isEmpty() || 
            cpfField.getText().isEmpty() || 
            telefoneField.getText().isEmpty() || 
            emailField.getText().isEmpty() || 
            enderecoField.getText().isEmpty()) {
            
            // Mensagem de erro se algum campo estiver vazio
            JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
            return; // Interrompe o processo se algum campo estiver vazio
        }

        // Criação de um novo objeto Cliente com os dados inseridos
        Cliente cliente = new Cliente(
                nomeField.getText(), // Nome do cliente
                cpfField.getText(), // CPF do cliente
                telefoneField.getText(), // Telefone do cliente
                emailField.getText(), // Email do cliente
                enderecoField.getText() // Endereço do cliente
        );
        controller.cadastrarCliente(cliente); // Chama o método do controlador para cadastrar o cliente
        JOptionPane.showMessageDialog(this, "Cliente cadastrado com sucesso!"); // Mensagem de sucesso
        voltarParaTelaInicial(); // Volta para a tela inicial
    });

    // Ação para o botão "Voltar"
    btnVoltar.addActionListener(e -> voltarParaTelaInicial()); // Retorna à tela inicial

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Define o fechamento da janela
    pack(); // Ajusta o tamanho da janela para caber os componentes
    setLocationRelativeTo(null); // Centraliza a janela na tela
    setVisible(true); // Torna a janela visível
}

// Método para voltar à tela inicial
private void voltarParaTelaInicial() {
    new TelaInicialView(controller); // Cria uma nova instância da TelaInicialView
    this.setVisible(false); // Oculta a tela atual
}

}

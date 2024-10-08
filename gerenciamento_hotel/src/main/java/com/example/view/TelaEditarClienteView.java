package com.example.view;

import javax.swing.*;
import com.example.controller.HotelController;
import com.example.model.Cliente;
import java.awt.*;

public class TelaEditarClienteView extends JFrame {
// Declaração do controlador que gerencia a lógica de edição de cliente
private HotelController controller;
// Declaração do cliente a ser editado
private Cliente cliente;

// Construtor da classe TelaEditarClienteView
public TelaEditarClienteView(HotelController controller, Cliente cliente) {
    this.controller = controller; // Inicializa o controlador
    this.cliente = cliente; // Inicializa o cliente a ser editado

    setTitle("Editar Cliente"); // Define o título da janela
    setLayout(new GridLayout(6, 2)); // Define o layout em uma grade de 6 linhas e 2 colunas

    // Adiciona rótulo e campo de texto para o nome do cliente
    add(new JLabel("Nome:")); 
    JTextField txtNome = new JTextField(cliente.getNome()); // Preenche o campo com o nome atual do cliente
    add(txtNome);

    // Adiciona rótulo e campo de texto para o CPF do cliente
    add(new JLabel("CPF:"));
    JTextField txtCpf = new JTextField(cliente.getCpf()); // Preenche o campo com o CPF atual do cliente
    txtCpf.setEditable(false); // O CPF não pode ser editado
    add(txtCpf);

    // Adiciona rótulo e campo de texto para o telefone do cliente
    add(new JLabel("Telefone:"));
    JTextField txtTelefone = new JTextField(cliente.getTelefone()); // Preenche o campo com o telefone atual
    add(txtTelefone);

    // Adiciona rótulo e campo de texto para o email do cliente
    add(new JLabel("Email:"));
    JTextField txtEmail = new JTextField(cliente.getEmail()); // Preenche o campo com o email atual
    add(txtEmail);

    // Adiciona rótulo e campo de texto para o endereço do cliente
    add(new JLabel("Endereço:"));
    JTextField txtEndereco = new JTextField(cliente.getEndereco()); // Preenche o campo com o endereço atual
    add(txtEndereco);

    // Criação do botão "Salvar"
    JButton btnSalvar = new JButton("Salvar");
    btnSalvar.addActionListener(e -> {
        // Atualiza os dados do cliente com os valores inseridos
        cliente.setNome(txtNome.getText());
        cliente.setTelefone(txtTelefone.getText());
        cliente.setEmail(txtEmail.getText());
        cliente.setEndereco(txtEndereco.getText());
        
        // Chama o método do controlador para editar o cliente
        controller.editarCliente(cliente);
        
        // Mensagem de sucesso
        JOptionPane.showMessageDialog(this, "Cliente atualizado com sucesso!");
        voltarParaTelaListarClientes(); // Retorna à tela de listagem de clientes
    });
    add(btnSalvar); // Adiciona o botão "Salvar" ao layout

    // Criação do botão "Voltar"
    JButton btnVoltar = new JButton("Voltar");
    btnVoltar.addActionListener(e -> voltarParaTelaListarClientes()); // Retorna à tela de listagem de clientes ao clicar
    add(btnVoltar); // Adiciona o botão "Voltar" ao layout

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Define o fechamento da janela
    setSize(400, 300); // Define o tamanho da janela
    setLocationRelativeTo(null); // Centraliza a janela na tela
    setVisible(true); // Torna a janela visível
}

// Método para voltar à tela de listagem de clientes
private void voltarParaTelaListarClientes() {
    new TelaListarClientesView(controller); // Cria uma nova instância da TelaListarClientesView
    this.setVisible(false); // Oculta a tela atual
}

}

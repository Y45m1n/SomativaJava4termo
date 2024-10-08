package com.example.view;

import javax.swing.*;
import com.example.controller.HotelController;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaInicialView extends JFrame {
// Declaração do controlador que gerencia a lógica da tela inicial
private HotelController controller;

// Construtor da classe TelaInicialView
public TelaInicialView(HotelController controller) {
    this.controller = controller; // Inicializa o controlador

    setTitle("Gerenciamento de Hotel"); // Define o título da janela
    setLayout(new BorderLayout()); // Define o layout da janela como BorderLayout

    // Painel para os botões dos quartos
    JPanel painelQuartos = new JPanel();
    painelQuartos.setLayout(new GridLayout(2, 5)); // 2 linhas e 5 colunas para os botões dos quartos

    // Criando 10 botões para os quartos
    for (int i = 1; i <= 10; i++) {
        JButton btnQuarto = new JButton("Quarto " + i); // Cria um botão para cada quarto
        int numeroQuarto = i; // Armazena o número do quarto para uso no ActionListener
        
        // Adiciona um ActionListener ao botão para abrir a tela de reserva
        btnQuarto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarTelaReserva(numeroQuarto); // Chama o método para mostrar a tela de reserva
            }
        });
        painelQuartos.add(btnQuarto); // Adiciona o botão ao painel
    }

    // Painel para os botões de ação
    JPanel painelBotoes = new JPanel();
    painelBotoes.setLayout(new GridLayout(1, 4)); // 1 linha e 4 colunas para os botões

    // Criação dos botões de ação
    JButton btnListarReservas = new JButton("Listar Reservas");
    JButton btnFazerPagamento = new JButton("Realizar Pagamento");
    JButton btnCadastrarCliente = new JButton("Cadastrar Cliente");
    JButton btnListarClientes = new JButton("Listar Clientes"); // Novo botão para listar clientes

    // Adicionando ActionListeners aos botões
    btnListarReservas.addActionListener(e -> mostrarTelaListarReservas());
    btnFazerPagamento.addActionListener(e -> mostrarTelaPagamento());
    btnCadastrarCliente.addActionListener(e -> mostrarTelaCadastroCliente());
    btnListarClientes.addActionListener(e -> mostrarTelaListarClientes()); // Ação para listar clientes

    // Adiciona os botões ao painel
    painelBotoes.add(btnListarReservas);
    painelBotoes.add(btnFazerPagamento);
    painelBotoes.add(btnCadastrarCliente);
    painelBotoes.add(btnListarClientes); // Adiciona o botão para listar clientes ao painel

    // Adiciona os painéis ao layout principal da janela
    add(painelQuartos, BorderLayout.CENTER); // Painel dos quartos no centro
    add(painelBotoes, BorderLayout.SOUTH); // Painel dos botões na parte inferior

    // Define o tamanho da janela
    setSize(600, 400); // Tamanho da janela
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Define o comportamento ao fechar a janela
    setLocationRelativeTo(null); // Centraliza a janela na tela
    setVisible(true); // Torna a janela visível
}

// Método para mostrar a tela de reserva de um quarto específico
private void mostrarTelaReserva(int numeroQuarto) {
    new TelaReservaView(controller, numeroQuarto); // Cria uma nova tela de reserva
    this.setVisible(false); // Oculta a tela inicial
}

// Método para mostrar a tela de listagem de reservas
private void mostrarTelaListarReservas() {
    new TelaListarReservasView(controller); // Cria a tela de listagem de reservas
    this.setVisible(false); // Oculta a tela inicial
}

// Método para mostrar a tela de cadastro de cliente
private void mostrarTelaCadastroCliente() {
    new TelaCadastroClienteView(controller); // Cria a tela de cadastro de cliente
    this.setVisible(false); // Oculta a tela inicial
}

// Método para mostrar a tela de pagamento
private void mostrarTelaPagamento() {
    new TelaPagamentoView(controller); // Cria a tela de pagamento
    this.setVisible(false); // Oculta a tela inicial
}

// Método para mostrar a tela de listagem de clientes
private void mostrarTelaListarClientes() {
    new TelaListarClientesView(controller); // Cria a tela de listagem de clientes
    this.setVisible(false); // Oculta a tela inicial
}

}

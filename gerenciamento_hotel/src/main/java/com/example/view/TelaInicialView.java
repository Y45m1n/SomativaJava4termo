package com.example.view;

import javax.swing.*;
import com.example.controller.HotelController;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaInicialView extends JFrame {
    private HotelController controller;

    public TelaInicialView(HotelController controller) {
        this.controller = controller;

        setTitle("Gerenciamento de Hotel");
        setLayout(new BorderLayout());

        // Painel para os quartos
        JPanel painelQuartos = new JPanel();
        painelQuartos.setLayout(new GridLayout(2, 5)); // 2 linhas, 5 colunas

        // Criando 10 cards para os quartos
        for (int i = 1; i <= 10; i++) {
            JButton btnQuarto = new JButton("Quarto " + i);
            int numeroQuarto = i; // Para usar no ActionListener
            btnQuarto.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mostrarTelaReserva(numeroQuarto);
                }
            });
            painelQuartos.add(btnQuarto);
        }

        // Painel para os botões
        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new GridLayout(1, 4)); // 1 linha, 4 colunas

        JButton btnListarReservas = new JButton("Listar Reservas");
        JButton btnFazerPagamento = new JButton("Realizar Pagamento");
        JButton btnCadastrarCliente = new JButton("Cadastrar Cliente");
        JButton btnListarClientes = new JButton("Listar Clientes"); // Novo botão

        btnListarReservas.addActionListener(e -> mostrarTelaListarReservas());
        btnFazerPagamento.addActionListener(e -> mostrarTelaPagamento());
        btnCadastrarCliente.addActionListener(e -> mostrarTelaCadastroCliente());
        btnListarClientes.addActionListener(e -> mostrarTelaListarClientes()); // Ação para listar clientes

        painelBotoes.add(btnListarReservas);
        painelBotoes.add(btnFazerPagamento);
        painelBotoes.add(btnCadastrarCliente);
        painelBotoes.add(btnListarClientes); // Adiciona o novo botão ao painel

        add(painelQuartos, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);

        // Define o tamanho da janela
        setSize(600, 400); // Aumente o tamanho, se necessário
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela
        setVisible(true);
    }

    private void mostrarTelaReserva(int numeroQuarto) {
        new TelaReservaView(controller, numeroQuarto);
        this.setVisible(false); // Oculta a tela inicial
    }

    private void mostrarTelaListarReservas() {
        new TelaListarReservasView(controller);
        this.setVisible(false); // Oculta a tela inicial
    }

    private void mostrarTelaCadastroCliente() {
        new TelaCadastroClienteView(controller);
        this.setVisible(false); // Oculta a tela inicial
    }

    private void mostrarTelaPagamento() {
        new TelaPagamentoView(controller); // Cria a tela de pagamento
        this.setVisible(false); // Oculta a tela inicial
    }

    private void mostrarTelaListarClientes() {
        new TelaListarClientesView(controller); // Método para listar clientes
        this.setVisible(false); // Oculta a tela inicial
    }
}

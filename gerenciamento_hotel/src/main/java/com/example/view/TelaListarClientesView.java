package com.example.view;

import javax.swing.*;
import com.example.controller.HotelController;
import com.example.model.Cliente;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class TelaListarClientesView extends JFrame {
    private HotelController controller;

    public TelaListarClientesView(HotelController controller) {
        this.controller = controller;

        setTitle("Listar Clientes");
        setLayout(new BorderLayout());

        List<Cliente> clientes = controller.listarClientes(); // Obtém a lista de clientes
        JPanel panelClientes = new JPanel();
        panelClientes.setLayout(new GridLayout(clientes.size(), 1)); // Layout para lista vertical

        for (Cliente cliente : clientes) {
            JPanel clientePanel = new JPanel(new FlowLayout());
            clientePanel.add(new JLabel("Nome: " + cliente.getNome() +
                                         ", CPF: " + cliente.getCpf() +
                                         ", Telefone: " + cliente.getTelefone() +
                                         ", Email: " + cliente.getEmail() +
                                         ", Endereço: " + cliente.getEndereco()));

            JButton btnEditar = new JButton("Editar");
            btnEditar.addActionListener(e -> editarCliente(cliente));
            clientePanel.add(btnEditar);

            JButton btnExcluir = new JButton("Excluir");
            btnExcluir.addActionListener(e -> excluirCliente(cliente.getCpf())); // Excluir pelo CPF
            clientePanel.add(btnExcluir);

            panelClientes.add(clientePanel);
        }

        JScrollPane scrollPane = new JScrollPane(panelClientes);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(e -> voltarParaTelaInicial());
        buttonPanel.add(btnVoltar);

        JButton btnBaixarRelatorio = new JButton("Baixar Relatório");
        btnBaixarRelatorio.addActionListener(e -> baixarRelatorio());
        buttonPanel.add(btnBaixarRelatorio);

        add(buttonPanel, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400); // Tamanho da janela
        setLocationRelativeTo(null); // Centraliza a janela
        setVisible(true);
    }

    private void editarCliente(Cliente cliente) {
        new TelaEditarClienteView(controller, cliente); // Abre a tela de edição
        this.setVisible(false); // Oculta a tela atual
    }

    private void excluirCliente(String cpf) {
        controller.excluirCliente(cpf); // Chama o método de exclusão
        JOptionPane.showMessageDialog(this, "Cliente excluído com sucesso!");
        // Atualiza a tela para refletir as mudanças
        new TelaListarClientesView(controller);
        this.setVisible(false); // Oculta a tela atual
    }

    private void voltarParaTelaInicial() {
        new TelaInicialView(controller);
        this.setVisible(false);
    }

    private void baixarRelatorio() {
        List<Cliente> clientes = controller.listarClientes();
        StringBuilder sb = new StringBuilder();
        for (Cliente cliente : clientes) {
            sb.append("\n -------- RELATÓRIO DE HÓSPEDES -------- \n Nome: ").append(cliente.getNome())
              .append(",\n CPF: ").append(cliente.getCpf())
              .append(",\n Telefone: ").append(cliente.getTelefone())
              .append(",\n Email: ").append(cliente.getEmail())
              .append(",\n Endereço: ").append(cliente.getEndereco())
              .append("\n ---------------------------------------");
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("relatorio_clientes.txt"))) {
            writer.write(sb.toString());
            JOptionPane.showMessageDialog(this, "Relatório de clientes salvo com sucesso!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar o relatório: " + e.getMessage());
        }
    }
}

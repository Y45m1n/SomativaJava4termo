package com.example.view;

import javax.swing.*;
import com.example.controller.HotelController;
import java.awt.*;
import java.time.LocalDate;
import java.util.UUID;

public class TelaPagamentoView extends JFrame {
    private HotelController controller;

    public TelaPagamentoView(HotelController controller) {
        this.controller = controller;

        setTitle("Pagamento");
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Campos de entrada
        JTextField cpfField = new JTextField(20);
        JTextField numeroQuartoField = new JTextField(20);
        JTextField dataEntradaField = new JTextField(10);
        JTextField dataSaidaField = new JTextField(10);
        JLabel valorLabel = new JLabel("Valor a Pagar: R$ 0,00");

        JButton btnCalcular = new JButton("Calcular Pagamento");
        JButton btnPagar = new JButton("Realizar Pagamento");
        JButton btnVoltar = new JButton("Voltar");

        // Adicionando componentes ao layout
        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("CPF do Cliente:"), gbc);
        gbc.gridx = 1; gbc.gridy = 0;
        add(cpfField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        add(new JLabel("Número do Quarto:"), gbc);
        gbc.gridx = 1; gbc.gridy = 1;
        add(numeroQuartoField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        add(new JLabel("Data de Entrada (YYYY-MM-DD):"), gbc);
        gbc.gridx = 1; gbc.gridy = 2;
        add(dataEntradaField, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        add(new JLabel("Data de Saída (YYYY-MM-DD):"), gbc);
        gbc.gridx = 1; gbc.gridy = 3;
        add(dataSaidaField, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        gbc.gridwidth = 2; // Occupy two columns
        add(valorLabel, gbc);

        gbc.gridwidth = 1; // Reset gridwidth to 1

        gbc.gridx = 0; gbc.gridy = 5;
        add(btnCalcular, gbc);
        gbc.gridx = 1; gbc.gridy = 5;
        add(btnPagar, gbc);

        gbc.gridx = 0; gbc.gridy = 6;
        gbc.gridwidth = 2;
        add(btnVoltar, gbc);

        // Ação do botão "Calcular Pagamento"
        btnCalcular.addActionListener(e -> {
            String cpf = cpfField.getText();
            String numeroQuartoStr = numeroQuartoField.getText();
            String dataEntradaStr = dataEntradaField.getText();
            String dataSaidaStr = dataSaidaField.getText();

            // Validações básicas
            if (cpf.isEmpty() || numeroQuartoStr.isEmpty() || dataEntradaStr.isEmpty() || dataSaidaStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos.");
                return;
            }

            int numeroQuarto = Integer.parseInt(numeroQuartoStr);
            LocalDate dataEntrada = LocalDate.parse(dataEntradaStr);
            LocalDate dataSaida = LocalDate.parse(dataSaidaStr);

            // Calcula o valor a pagar
            long diasReservados = java.time.temporal.ChronoUnit.DAYS.between(dataEntrada, dataSaida);
            double valorTotal = diasReservados * 100; // Considerando 100 reais por diária

            valorLabel.setText(String.format("Valor a Pagar: R$ %.2f", valorTotal));
        });

        // Ação do botão "Realizar Pagamento"
        btnPagar.addActionListener(e -> {
            // Lógica para realizar o pagamento
            String cpf = cpfField.getText();
            String numeroQuartoStr = numeroQuartoField.getText();
            String dataEntradaStr = dataEntradaField.getText();
            String dataSaidaStr = dataSaidaField.getText();

            // Validações básicas
            if (cpf.isEmpty() || numeroQuartoStr.isEmpty() || dataEntradaStr.isEmpty() || dataSaidaStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos.");
                return;
            }

            // Aqui você pode implementar a lógica para registrar o pagamento
            // Por exemplo, salvar em um banco de dados ou emitir um recibo

            JOptionPane.showMessageDialog(this, "Pagamento realizado com sucesso!");
            voltarParaTelaInicial();
        });

        // Ação do botão "Voltar"
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

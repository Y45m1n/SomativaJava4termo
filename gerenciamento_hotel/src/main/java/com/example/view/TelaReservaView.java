package com.example.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.UUID;
import javax.swing.*;
import org.jdatepicker.JDatePicker;
import org.jdatepicker.UtilDateModel;
import com.example.controller.HotelController;
import com.example.model.Reserva;

public class TelaReservaView extends JFrame {
    private HotelController controller;
    private int numeroQuarto;

    public TelaReservaView(HotelController controller, int numeroQuarto) {
        this.controller = controller;
        this.numeroQuarto = numeroQuarto;

        setTitle("Reserva do quarto " + numeroQuarto);
        setLayout(new BorderLayout()); // Usando BorderLayout

        JPanel panelCentro = new JPanel(new GridLayout(5, 2)); // Painel central para os campos de entrada
        JTextField cpfField = new JTextField();

        // Criando os modelos para os JDatePicker
        UtilDateModel modelEntrada = new UtilDateModel();
        JDatePicker datePickerEntrada = new JDatePicker(modelEntrada);

        UtilDateModel modelSaida = new UtilDateModel();
        JDatePicker datePickerSaida = new JDatePicker(modelSaida);

        JButton btnReservar = new JButton("Reservar");
        JButton btnVoltar = new JButton("Voltar");

        panelCentro.add(new JLabel("CPF do Cliente:"));
        panelCentro.add(cpfField);
        panelCentro.add(new JLabel("Data de Entrada:"));
        panelCentro.add(datePickerEntrada);
        panelCentro.add(new JLabel("Data de Saída:"));
        panelCentro.add(datePickerSaida);

        add(panelCentro, BorderLayout.CENTER); // Adiciona o painel ao centro
        JPanel panelBotoes = new JPanel(); // Painel para os botões
        panelBotoes.add(btnReservar);
        panelBotoes.add(btnVoltar);
        
        add(panelBotoes, BorderLayout.SOUTH); // Adiciona os botões ao sul

        btnReservar.addActionListener(e -> {
            String cpfCliente = cpfField.getText();

            // Verifica se o CPF está vazio
            if (cpfCliente.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, preencha o CPF do hóspede.");
                return;
            }

            // Verifica se as datas foram selecionadas
            java.util.Date dataEntradaDate = (java.util.Date) datePickerEntrada.getModel().getValue();
            java.util.Date dataSaidaDate = (java.util.Date) datePickerSaida.getModel().getValue();
            if (dataEntradaDate == null || dataSaidaDate == null) {
                JOptionPane.showMessageDialog(this, "Por favor, selecione as datas de entrada e saída.");
                return;
            }

            // Verifica se o CPF está cadastrado
            if (!controller.isCpfCadastrado(cpfCliente)) {
                JOptionPane.showMessageDialog(this, "Cliente não cadastrado. Favor realize o cadastro antes de fazer a reserva.");
                return;
            }

            // Converte as datas para LocalDate
            LocalDate dataEntrada = dataEntradaDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate dataSaida = dataSaidaDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            // Verifica se o quarto já está reservado nas datas selecionadas
            if (controller.isQuartoReservado(numeroQuarto, dataEntrada, dataSaida)) {
                JOptionPane.showMessageDialog(this, "O quarto já está reservado para as datas selecionadas. Favor escolher outras datas.");
                return;
            }

            try {
                String numeroReserva = UUID.randomUUID().toString(); // Gera um número de reserva único
                Reserva reserva = new Reserva(cpfCliente, numeroQuarto, dataEntrada, dataSaida, numeroReserva);
                controller.reservarQuarto(reserva);
                JOptionPane.showMessageDialog(this, "Reserva realizada com sucesso!");
                voltarParaTelaInicial();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao realizar a reserva: " + ex.getMessage());
            }
        });

        btnVoltar.addActionListener(e -> voltarParaTelaInicial()); // Ação para o botão "Voltar"

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

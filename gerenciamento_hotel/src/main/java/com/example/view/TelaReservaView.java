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
   // Controlador que gerencia a lógica da tela de reserva
private HotelController controller;
// Número do quarto a ser reservado
private int numeroQuarto;

// Construtor da classe TelaReservaView
public TelaReservaView(HotelController controller, int numeroQuarto) {
    this.controller = controller; // Inicializa o controlador
    this.numeroQuarto = numeroQuarto; // Armazena o número do quarto

    // Define o título da janela com o número do quarto
    setTitle("Reserva do quarto " + numeroQuarto);
    setLayout(new BorderLayout()); // Define o layout da janela como BorderLayout

    // Cria um painel central com um GridLayout para os campos de entrada
    JPanel panelCentro = new JPanel(new GridLayout(5, 2)); 
    JTextField cpfField = new JTextField(); // Campo para o CPF do cliente

    // Criando os modelos para os JDatePicker (seletor de datas)
    UtilDateModel modelEntrada = new UtilDateModel();
    JDatePicker datePickerEntrada = new JDatePicker(modelEntrada); // Seletor para a data de entrada

    UtilDateModel modelSaida = new UtilDateModel();
    JDatePicker datePickerSaida = new JDatePicker(modelSaida); // Seletor para a data de saída

    // Botões para realizar a reserva e voltar
    JButton btnReservar = new JButton("Reservar");
    JButton btnVoltar = new JButton("Voltar");

    // Adiciona rótulos e campos ao painel central
    panelCentro.add(new JLabel("CPF do Cliente:"));
    panelCentro.add(cpfField);
    panelCentro.add(new JLabel("Data de Entrada:"));
    panelCentro.add(datePickerEntrada);
    panelCentro.add(new JLabel("Data de Saída:"));
    panelCentro.add(datePickerSaida);

    add(panelCentro, BorderLayout.CENTER); // Adiciona o painel central à janela
    JPanel panelBotoes = new JPanel(); // Painel para os botões
    panelBotoes.add(btnReservar); // Adiciona o botão "Reservar"
    panelBotoes.add(btnVoltar); // Adiciona o botão "Voltar"
    
    add(panelBotoes, BorderLayout.SOUTH); // Adiciona o painel de botões ao sul da janela

    // Ação do botão "Reservar"
    btnReservar.addActionListener(e -> {
        String cpfCliente = cpfField.getText(); // Obtém o CPF do cliente

        // Verifica se o CPF está vazio
        if (cpfCliente.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha o CPF do hóspede.");
            return; // Interrompe a execução se o CPF não for informado
        }

        // Verifica se as datas foram selecionadas
        java.util.Date dataEntradaDate = (java.util.Date) datePickerEntrada.getModel().getValue();
        java.util.Date dataSaidaDate = (java.util.Date) datePickerSaida.getModel().getValue();
        if (dataEntradaDate == null || dataSaidaDate == null) {
            JOptionPane.showMessageDialog(this, "Por favor, selecione as datas de entrada e saída.");
            return; // Interrompe se as datas não forem selecionadas
        }

        // Verifica se o CPF está cadastrado no sistema
        if (!controller.isCpfCadastrado(cpfCliente)) {
            JOptionPane.showMessageDialog(this, "Cliente não cadastrado. Favor realize o cadastro antes de fazer a reserva.");
            return; // Interrompe se o CPF não estiver cadastrado
        }

        // Converte as datas para LocalDate
        LocalDate dataEntrada = dataEntradaDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate dataSaida = dataSaidaDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        // Verifica se o quarto já está reservado nas datas selecionadas
        if (controller.isQuartoReservado(numeroQuarto, dataEntrada, dataSaida)) {
            JOptionPane.showMessageDialog(this, "O quarto já está reservado para as datas selecionadas. Favor escolher outras datas.");
            return; // Interrompe se o quarto já estiver reservado
        }

        try {
            // Gera um número de reserva único
            String numeroReserva = UUID.randomUUID().toString(); 
            // Cria uma nova reserva
            Reserva reserva = new Reserva(cpfCliente, numeroQuarto, dataEntrada, dataSaida, numeroReserva);
            controller.reservarQuarto(reserva); // Chama o método para reservar o quarto
            JOptionPane.showMessageDialog(this, "Reserva realizada com sucesso!"); // Mensagem de sucesso
            voltarParaTelaInicial(); // Retorna à tela inicial
        } catch (Exception ex) {
            // Mensagem de erro em caso de falha na reserva
            JOptionPane.showMessageDialog(this, "Erro ao realizar a reserva: " + ex.getMessage());
        }
    });

    // Ação do botão "Voltar"
    btnVoltar.addActionListener(e -> voltarParaTelaInicial()); // Retorna à tela inicial

    // Configurações finais da janela
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Define o comportamento ao fechar a janela
    pack(); // Ajusta o tamanho da janela de acordo com o conteúdo
    setLocationRelativeTo(null); // Centraliza a janela na tela
    setVisible(true); // Torna a janela visível
}

// Método para voltar para a tela inicial
private void voltarParaTelaInicial() {
    new TelaInicialView(controller); // Abre a tela inicial
    this.setVisible(false); // Oculta a tela atual
}

}

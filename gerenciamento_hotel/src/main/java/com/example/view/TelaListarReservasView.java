package com.example.view;


import javax.swing.*;

import com.example.controller.HotelController;
import com.example.model.Reserva;

import java.awt.*;
import java.util.List;

public class TelaListarReservasView extends JFrame {
// Declaração do controlador que gerencia a lógica da tela de listagem de reservas
private HotelController controller;

// Construtor da classe TelaListarReservasView
public TelaListarReservasView(HotelController controller) {
    this.controller = controller; // Inicializa o controlador

    setTitle("Listar Reservas"); // Define o título da janela
    setLayout(new BorderLayout()); // Define o layout da janela como BorderLayout

    // Obtém a lista de reservas do controlador
    List<Reserva> reservas = controller.listarReservas();
    StringBuilder sb = new StringBuilder(); // StringBuilder para construir a representação das reservas

    // Constrói a string com os detalhes de cada reserva
    for (Reserva reserva : reservas) {
        sb.append("Reserva: ").append(reserva.getNumeroReserva())
          .append(", CPF: ").append(reserva.getCpfCliente())
          .append(", Quarto: ").append(reserva.getNumeroQuarto())
          .append(", Entrada: ").append(reserva.getDataEntrada())
          .append(", Saída: ").append(reserva.getDataSaida()).append("\n");
    }

    // Cria uma área de texto para exibir as reservas
    JTextArea areaReservas = new JTextArea(sb.toString());
    areaReservas.setEditable(false); // A área de texto não pode ser editada
    JScrollPane scrollPane = new JScrollPane(areaReservas); // Adiciona a área de texto a um JScrollPane para permitir rolagem

    add(scrollPane, BorderLayout.CENTER); // Adiciona o JScrollPane no centro da janela

    // Botão para voltar à tela inicial
    JButton btnVoltar = new JButton("Voltar");
    btnVoltar.addActionListener(e -> voltarParaTelaInicial()); // Ação para voltar à tela inicial
    add(btnVoltar, BorderLayout.SOUTH); // Adiciona o botão na parte inferior da janela

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

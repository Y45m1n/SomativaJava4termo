package com.example.view;

import javax.swing.*;
import com.example.controller.HotelController;
import com.example.model.Reserva;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class TelaPagamentoView extends JFrame {
// Controlador que gerencia a lógica da tela de pagamento
private HotelController controller;
// Variável de controle para evitar múltiplos pagamentos simultâneos
private boolean pagamentoEmProcessamento = false;

// Construtor da classe TelaPagamentoView
public TelaPagamentoView(HotelController controller) {
    this.controller = controller; // Inicializa o controlador

    setTitle("Pagamento"); // Define o título da janela
    setLayout(new GridBagLayout()); // Define o layout da janela como GridBagLayout

    // Configurações do GridBagConstraints para o layout
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.fill = GridBagConstraints.HORIZONTAL; // Preenche a área horizontalmente
    gbc.insets = new Insets(5, 5, 5, 5); // Define espaçamento entre os componentes

    // Criação dos campos de entrada
    JTextField cpfField = new JTextField(20);
    JTextField numeroQuartoField = new JTextField(20);
    JTextField dataEntradaField = new JTextField(10);
    JTextField dataSaidaField = new JTextField(10);
    JTextField numeroReservaField = new JTextField(30);
    JLabel valorLabel = new JLabel("Valor a Pagar: R$ 0,00"); // Label para exibir o valor a pagar

    // Desabilitando campos que não podem ser editados
    cpfField.setEditable(false);
    numeroQuartoField.setEditable(false);
    dataEntradaField.setEditable(false);
    dataSaidaField.setEditable(false);

    // Botões para calcular e realizar pagamento, e voltar
    JButton btnCalcular = new JButton("Calcular Pagamento");
    JButton btnPagar = new JButton("Realizar Pagamento");
    JButton btnVoltar = new JButton("Voltar");

    // Adicionando os componentes ao layout
    gbc.gridx = 0; gbc.gridy = 0;
    add(new JLabel("Número da Reserva:"), gbc);
    gbc.gridx = 1; gbc.gridy = 0;
    add(numeroReservaField, gbc);

    gbc.gridx = 0; gbc.gridy = 1;
    add(new JLabel("CPF hóspede:"), gbc);
    gbc.gridx = 1; gbc.gridy = 1;
    add(cpfField, gbc);

    gbc.gridx = 0; gbc.gridy = 2;
    add(new JLabel("Número do Quarto:"), gbc);
    gbc.gridx = 1; gbc.gridy = 2;
    add(numeroQuartoField, gbc);

    gbc.gridx = 0; gbc.gridy = 3;
    add(new JLabel("Data de Entrada:"), gbc);
    gbc.gridx = 1; gbc.gridy = 3;
    add(dataEntradaField, gbc);

    gbc.gridx = 0; gbc.gridy = 4;
    add(new JLabel("Data de Saída:"), gbc);
    gbc.gridx = 1; gbc.gridy = 4;
    add(dataSaidaField, gbc);

    gbc.gridx = 0; gbc.gridy = 5;
    gbc.gridwidth = 2; // Ocupa duas colunas
    add(valorLabel, gbc);

    gbc.gridwidth = 1; // Reseta gridwidth para 1

    gbc.gridx = 0; gbc.gridy = 6;
    add(btnCalcular, gbc);
    gbc.gridx = 1; gbc.gridy = 6;
    add(btnPagar, gbc);

    gbc.gridx = 0; gbc.gridy = 7;
    gbc.gridwidth = 2;
    add(btnVoltar, gbc);

    // Ação do botão "Calcular Pagamento"
    btnCalcular.addActionListener(e -> {
        String numeroReserva = numeroReservaField.getText(); // Obtém o número da reserva

        // Busca os detalhes da reserva
        Reserva reserva = controller.buscarReserva(numeroReserva);
        if (reserva == null) {
            JOptionPane.showMessageDialog(this, "Reserva não encontrada."); // Mensagem de erro
            return;
        }

        // Preenche os campos com os dados da reserva
        cpfField.setText(reserva.getCpfCliente());
        numeroQuartoField.setText(String.valueOf(reserva.getNumeroQuarto()));
        dataEntradaField.setText(reserva.getDataEntrada().toString());
        dataSaidaField.setText(reserva.getDataSaida().toString());

        // Calcula o valor a pagar
        long diasReservados = java.time.temporal.ChronoUnit.DAYS.between(reserva.getDataEntrada(), reserva.getDataSaida());
        double valorTotal = diasReservados * 100; // Considerando 100 reais por diária

        valorLabel.setText(String.format("Valor a Pagar: R$ %.2f", valorTotal)); // Atualiza o label com o valor total
    });

    // Ação do botão "Realizar Pagamento"
    btnPagar.addActionListener(e -> {
        if (pagamentoEmProcessamento) { // Verifica se um pagamento já está em processamento
            JOptionPane.showMessageDialog(this, "O pagamento já está sendo processado. Por favor, aguarde.");
            return; // Não permite iniciar um novo pagamento
        }
        pagamentoEmProcessamento = true; // Marca o pagamento como em processamento

        // Coleta informações para o pagamento
        String numeroReserva = numeroReservaField.getText();
        String cpf = cpfField.getText();
        int numeroQuarto = Integer.parseInt(numeroQuartoField.getText());
        LocalDate dataEntrada = LocalDate.parse(dataEntradaField.getText());
        LocalDate dataSaida = LocalDate.parse(dataSaidaField.getText());

        // Opções de pagamento
        String[] opcoes = {"Cartão", "Dinheiro", "PIX"};
        int escolha = JOptionPane.showOptionDialog(this, "Escolha o método de pagamento:", "Método de Pagamento",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);

        // Obtém o valor total a pagar
        double valorTotal = Double.parseDouble(valorLabel.getText().replace("Valor a Pagar: R$ ", "").replace(",", "."));

        // Processa o pagamento de acordo com a opção escolhida
        if (escolha == 0) { // Cartão
            JOptionPane.showMessageDialog(this, "Pagamento realizado com cartão com sucesso!");
            salvarDadosPagamento("Cartão", valorTotal, numeroReserva, cpf, numeroQuarto, dataEntrada, dataSaida);
            pagamentoEmProcessamento = false; // Libera o processamento
            voltarParaTelaInicial();
        } else if (escolha == 1) { // Dinheiro
            String valorPagoStr = JOptionPane.showInputDialog(this, "Informe o valor dado em dinheiro:");
            if (valorPagoStr != null) {
                double valorPago = Double.parseDouble(valorPagoStr);
                
                if (valorPago < valorTotal) { // Verifica se o valor pago é suficiente
                    JOptionPane.showMessageDialog(this, "Valor insuficiente. O valor a pagar é R$ " + valorTotal);
                } else {
                    double troco = valorPago - valorTotal; // Calcula o troco
                    JOptionPane.showMessageDialog(this, "Pagamento realizado com dinheiro. Troco: R$ " + troco);
                    salvarDadosPagamento("Dinheiro", valorTotal, numeroReserva, cpf, numeroQuarto, dataEntrada, dataSaida);
                }
            }
            pagamentoEmProcessamento = false; // Libera o processamento
            voltarParaTelaInicial();
        } else if (escolha == 2) { // PIX
            String chavePix = gerarChavePix(); // Gera uma chave PIX
            JOptionPane.showMessageDialog(this, "Chave PIX gerada: " + chavePix);
            
            // Simulando a espera de 5 segundos
            JOptionPane.showMessageDialog(this, "Pagamento realizado com PIX com sucesso!");
            salvarDadosPagamento("PIX", valorTotal, numeroReserva, cpf, numeroQuarto, dataEntrada, dataSaida);
            pagamentoEmProcessamento = false; // Libera o processamento
            voltarParaTelaInicial();
        }
    });

    // Ação do botão "Voltar"
    btnVoltar.addActionListener(e -> voltarParaTelaInicial());

    // Configurações finais da janela
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Define o comportamento ao fechar a janela
    pack(); // Ajusta o tamanho da janela de acordo com o conteúdo
    setLocationRelativeTo(null); // Centraliza a janela na tela
    setVisible(true); // Torna a janela visível
}

// Método para gerar uma chave PIX aleatória
private String gerarChavePix() {
    return "chave_pix_" + System.currentTimeMillis(); // Retorna uma chave baseada no timestamp atual
}

// Método para salvar os dados do pagamento em um arquivo
private void salvarDadosPagamento(String metodoPagamento, double valorPago, String numeroReserva, String cpf, int numeroQuarto, LocalDate dataEntrada, LocalDate dataSaida) {
    String conteudo = String.format(
        "Método de Pagamento: %s\n" +
        "Valor Pago: R$ %.2f\n" +
        "Número da Reserva: %s\n" +
        "CPF Hóspede: %s\n" +
        "Número do Quarto: %d\n" +
        "Data de Entrada: %s\n" +
        "Data de Saída: %s\n" +
        "Data do Pagamento: %s\n\n",
        metodoPagamento, valorPago, numeroReserva, cpf, numeroQuarto, dataEntrada, dataSaida, LocalDate.now()
    );
    
    // Salva os dados em um arquivo texto
    try (BufferedWriter writer = new BufferedWriter(new FileWriter("pagamentos.txt", true))) {
        writer.write(conteudo); // Escreve os dados no arquivo
    } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "Erro ao salvar dados do pagamento: " + e.getMessage()); // Mensagem de erro ao salvar
    }
}

// Método para voltar para a tela inicial
private void voltarParaTelaInicial() {
    new TelaInicialView(controller); // Abre a tela inicial
    this.setVisible(false); // Oculta a tela atual
}

}

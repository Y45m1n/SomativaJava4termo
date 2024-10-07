package com.example.view;

import javax.swing.*;
import com.example.controller.HotelController;
import com.example.model.Cliente;
import java.awt.*;

public class TelaEditarClienteView extends JFrame {
    private HotelController controller;
    private Cliente cliente;

    public TelaEditarClienteView(HotelController controller, Cliente cliente) {
        this.controller = controller;
        this.cliente = cliente;

        setTitle("Editar Cliente");
        setLayout(new GridLayout(6, 2));

        add(new JLabel("Nome:"));
        JTextField txtNome = new JTextField(cliente.getNome());
        add(txtNome);

        add(new JLabel("CPF:"));
        JTextField txtCpf = new JTextField(cliente.getCpf());
        txtCpf.setEditable(false); // CPF não pode ser editado
        add(txtCpf);

        add(new JLabel("Telefone:"));
        JTextField txtTelefone = new JTextField(cliente.getTelefone());
        add(txtTelefone);

        add(new JLabel("Email:"));
        JTextField txtEmail = new JTextField(cliente.getEmail());
        add(txtEmail);

        add(new JLabel("Endereço:"));
        JTextField txtEndereco = new JTextField(cliente.getEndereco());
        add(txtEndereco);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e -> {
            cliente.setNome(txtNome.getText());
            cliente.setTelefone(txtTelefone.getText());
            cliente.setEmail(txtEmail.getText());
            cliente.setEndereco(txtEndereco.getText());
            controller.editarCliente(cliente);
            JOptionPane.showMessageDialog(this, "Cliente atualizado com sucesso!");
            voltarParaTelaListarClientes();
        });
        add(btnSalvar);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(e -> voltarParaTelaListarClientes());
        add(btnVoltar);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void voltarParaTelaListarClientes() {
        new TelaListarClientesView(controller);
        this.setVisible(false);
    }
}

package com.example.controller;

import com.example.dao.PagamentoDAO; 
import com.example.model.Pagamento; 
import java.util.List; 

// Controlador para gerenciar operações relacionadas a pagamentos
public class PagamentoController {
    private PagamentoDAO pagamentoDAO; // Objeto para gerenciar a persistência de pagamentos

    // Construtor que inicializa o DAO de pagamentos
    public PagamentoController() {
        this.pagamentoDAO = new PagamentoDAO();
    }

    // Método para realizar um pagamento
    public void realizarPagamento(Pagamento pagamento) {
        pagamentoDAO.salvar(pagamento); // Chama o método de salvar no PagamentoDAO
    }

    // Método para listar todos os pagamentos realizados
    public List<Pagamento> listarPagamentos() {
        return pagamentoDAO.listar(); // Retorna a lista de pagamentos
    }
}

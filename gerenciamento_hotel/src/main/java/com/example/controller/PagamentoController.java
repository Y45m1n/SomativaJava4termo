package com.example.controller;

import com.example.dao.PagamentoDAO;
import com.example.model.Pagamento;
import java.util.List;



public class PagamentoController {
    private PagamentoDAO pagamentoDAO;

    public PagamentoController() {
        this.pagamentoDAO = new PagamentoDAO();
    }

    public void realizarPagamento(Pagamento pagamento) {
        pagamentoDAO.salvar(pagamento);
    }

    public List<Pagamento> listarPagamentos() {
        return pagamentoDAO.listar();
    }
}


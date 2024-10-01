package com.example;


import com.example.Controller.LoginController;
import com.example.Model.Login;
import com.example.View.LoginView;

public class SistemaGerenciamentoHotel {
    public static void main(String[] args) {
    Login model = new Login();
        LoginView view = new LoginView();
        LoginController controller = new LoginController(view, model);
}
}
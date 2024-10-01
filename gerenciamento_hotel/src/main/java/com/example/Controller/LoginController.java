package com.example.Controller;


    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;

import com.example.Model.Login;
import com.example.View.LoginView;
import com.example.View.TelaPrincipal;
    
    public class LoginController {
        private LoginView view;
        private Login model;
    
        public LoginController(LoginView view, Login model) {
            this.view = view;
            this.model = model;
    
            this.view.addLoginListener(new LoginListener());
        }
    
        class LoginListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = view.getUsername();
                String password = view.getPassword();
    
                if (model.authenticate(username, password)) {
                    view.showMessage("Login bem-sucedido!");
                    openTelaPrincipal(); // Abre a nova tela
                } else {
                    view.showMessage("Usuário ou senha inválidos.");
                }
            }
           

            private void openTelaPrincipal() {
                TelaPrincipal telaPrincipal = new TelaPrincipal();
        
        
        
               
              
            }
        }
    }
    





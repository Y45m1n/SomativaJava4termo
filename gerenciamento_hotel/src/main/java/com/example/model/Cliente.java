package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
    private String nome;
    private String cpf; // Unique
    private String telefone;
    private String email;
    private String endereco;
}
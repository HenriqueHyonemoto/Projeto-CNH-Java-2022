/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto_cnh.DTO;

/**
 *
 * @author aluno
 */
public class InstrutorDTO {
    private int id_instrutor;
    private String nomeInstrutor, telefoneInstrutor, cpfInstrutor, emailInstrutor;

    public int getId_instrutor() {
        return id_instrutor;
    }

    public void setId_instrutor(int id_instrutor) {
        this.id_instrutor = id_instrutor;
    }

    public String getNomeInstrutor() {
        return nomeInstrutor;
    }

    public void setNomeInstrutor(String nomeInstrutor) {
        this.nomeInstrutor = nomeInstrutor;
    }

    public String getTelefoneInstrutor() {
        return telefoneInstrutor;
    }

    public void setTelefoneInstrutor(String telefoneInstutor) {
        this.telefoneInstrutor = telefoneInstutor;
    }

    public String getCpfInstrutor() {
        return cpfInstrutor;
    }

    public void setCpfInstrutor(String cpfInstrutor) {
        this.cpfInstrutor = cpfInstrutor;
    }

    public String getEmailInstrutor() {
        return emailInstrutor;
    }

    public void setEmailInstrutor(String emailInstrutor) {
        this.emailInstrutor = emailInstrutor;
    }

    
}

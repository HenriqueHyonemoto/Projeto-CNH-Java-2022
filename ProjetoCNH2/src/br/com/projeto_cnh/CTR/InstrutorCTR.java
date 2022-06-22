/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto_cnh.CTR;

import java.sql.ResultSet;
import br.com.projeto_cnh.DAO.InstrutorDAO;
import br.com.projeto_cnh.DAO.ConexaoDAO;
import br.com.projeto_cnh.DTO.InstrutorDTO;
/**
 *
 * @author Aluno
 */
public class InstrutorCTR {
    InstrutorDAO instrutorDAO = new InstrutorDAO();
    ConexaoDAO conexaoDAO = new ConexaoDAO();
    
    public String inserirInstrutor(InstrutorDTO instrutorDTO) {
        try {
            if (instrutorDAO.inserirInstrutor(instrutorDTO)) { // chama o metodo que esta na classe dao aguardando uma resposta true ou false

                return "Cadastrado com Sucesso";

            } else {
                return "Erro ao cadastrar";
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Erro ao cadastrar";
        }
    }
    
    public ResultSet inserirCliente(InstrutorDTO instrutorDTO, int opcao) {
        ResultSet rs = null; // É criado um atributo do tipo ResultSet, pois este metodo recebe o resultado de uma consulta

        rs = instrutorDAO.consultarInstrutor(instrutorDTO, opcao); // o atributo rs recebe a consulta realizada pelo metodo da classe DAO

        return rs;
    }
    
    public String alterarInstrutor(InstrutorDTO instrutorDTO) {
        try {
            if (instrutorDAO.alterarCliente(instrutorDTO)) {
                return "Instrutor alterado com sucesso!";
            } else {
                return "Erro ao alterar instrutor";
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Instrutor NÃO alterado";
        }
    }
    
    public String excluirCliente(InstrutorDTO instrutorDTO) {
        try {
            if (instrutorDAO.excluirCliente(instrutorDTO)) {
                return "Instrutor Exculido com sucesso! ";

            } else {
                return "Instrutor !--NAO--! excluido";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Instrutor !--NAO--! excluido";
        }
    }

    public void CloseDB() {
        ConexaoDAO.CloseDB();
    }
    
    
}

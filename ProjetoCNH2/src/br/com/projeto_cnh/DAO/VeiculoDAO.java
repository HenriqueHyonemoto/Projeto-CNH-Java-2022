/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto_cnh.DAO;

import br.com.projeto_cnh.DTO.VeiculoDTO;
import br.com.projeto_cnh.DTO.InstrutorDTO;
import br.com.projeto_cnh.DAO.ConexaoDAO;
import java.sql.*;
/**
 *
 * @author aluno
 */
public class VeiculoDAO {
    
    public VeiculoDAO(){
        
    }
    
    public ResultSet rs = null;
    Statement stmt = null;
    
    public boolean inserirVeiculo(VeiculoDTO veiculoDTO, InstrutorDTO instrutorDTO){
        try {
            
            
            return true;
        } catch(Exception err){
            System.out.println("Erro VeiculoDAO.inserirVeiculo: " + err.getMessage());
            return false;
        } finally {
            ConexaoDAO.ConectDB();
        }
    }
    
    public boolean excluirVeiculo(VeiculoDTO veiculoDTO, InstrutorDTO instrutorDTO){
        try {
            return true;
        } catch(Exception err){
            System.out.println("Erro VeiculoDAO.excluirVeiculo: " + err.getMessage());
            return false;
        } finally {
            ConexaoDAO.ConectDB();
        }
    }
    
    public boolean alterarVeiculo(VeiculoDTO veiculoDTO){
        try {
            return true;
        } catch(Exception err){
            System.out.println("Erro VeiculoDAO.alterarVeiculo: " + err.getMessage());
            return false;
        } finally {
            ConexaoDAO.ConectDB();
        }
    }
    
    public ResultSet consultarVeiculo(VeiculoDTO veiculoDTO, InstrutorDTO instrutorDTO){
        try {
            
            
            return rs;
        } catch(Exception err){
            System.out.println("Erro VeiculoDAO.alterarVeiculo: " + err.getMessage());
            return rs;
        }
    }
    
}

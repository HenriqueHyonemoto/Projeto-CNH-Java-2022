/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto_cnh.DAO;

import br.com.projeto_cnh.DTO.VeiculoDTO;
import br.com.projeto_cnh.DTO.InstrutorDTO;
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
    Statement stml = null;
    
    public boolean inserirVeiculo(VeiculoDTO veiculoDTO, InstrutorDTO instrutorDTO){
        try {
            ConexaoDAO.ConectDB();
            
            stmt = ConexaoDAO.con.createStatement();
            stml = ConexaoDAO.con.createStatement();
            
            String comandoVeiculo = "INSERT INTO veiculo (placa, modelo, tipo) "
                    + "VALUES ("
                    + "'" + veiculoDTO.getPlaca() + "'"
                    + "'" + veiculoDTO.getModelo() + "'"
                    + "'" + veiculoDTO.getTipo() + "')";
            
            stmt.execute(comandoVeiculo);
            
            
            String comandoInstrutorVeiculo = "INSERT INTO veiculoInstrutor (id_instrutor, "
                    + "placa) VALUES ("
                    + "" + instrutorDTO.getId_instrutor() + ","
                    + "'" + veiculoDTO.getPlaca() + "')";
            
            stml.execute(comandoInstrutorVeiculo);
            
            ConexaoDAO.con.commit();
            stmt.close();
            stml.close();
            
            return true;
        } catch(Exception err){
            System.out.println("Erro VeiculoDAO.inserirVeiculo: " + err.getMessage());
            return false;
        } finally {
            ConexaoDAO.CloseDB();
        }
    }
    
    public boolean excluirVeiculo(VeiculoDTO veiculoDTO){
        try {
            ConexaoDAO.ConectDB();
            
            stmt = ConexaoDAO.con.createStatement();
            stml = ConexaoDAO.con.createStatement();
            
            String comandoVeiculo = "DELETE FROM veiculo WHERE placa = '" + veiculoDTO.getPlaca() + "'"; 
            stmt.execute(comandoVeiculo);
            
            String comandoVeiculoInstrutor = "DELETE FROM veiculoInstrutor WHERE placa = '" + veiculoDTO.getPlaca() + "'";
            stml.execute(comandoVeiculoInstrutor);
            
            ConexaoDAO.con.commit();
            
            stmt.close();
            stml.close();
            
            return true;
        } catch(Exception err){
            System.out.println("Erro VeiculoDAO.excluirVeiculo: " + err.getMessage());
            return false;
        } finally {
            ConexaoDAO.CloseDB();
        }
    }
    
    public ResultSet consultarVeiculo(VeiculoDTO veiculoDTO, int opcao){
        try {
            ConexaoDAO.ConectDB();
            
            stmt = ConexaoDAO.con.createStatement();
            
            String comando = "";
            
            switch(opcao){
                case 1:
                    comando = "SELECT v.* FROM veiculo v";
                    break;
                case 2:
                    comando = "SELECT v.* FROM veiculo v WHERE "
                            + "v.placa like '" + veiculoDTO.getPlaca() + "%'";
                    break;
            }
            
            rs = stmt.executeQuery(comando);
            
            return rs;
        } catch(Exception err){
            System.out.println("Erro VeiculoDAO.alterarVeiculo: " + err.getMessage());
            return rs;
        }
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto_cnh.DAO;
import br.com.projeto_cnh.DTO.InstrutorDTO;

import java.sql.*;

/**
 *
 * @author Aluno
 */
public class InstrutorDAO {
    
    public InstrutorDAO(){
        
    }
    
    
    public ResultSet rs = null;
    private Statement stmt = null;
    
    public boolean inserirInstrutor(InstrutorDTO instrutorDTO){
        try {

            ConexaoDAO.ConectDB();//chma o metodo da classe ConexaoDAO para abertura da DB

            stmt = ConexaoDAO.con.createStatement();

            //
            String comando = "Insert into instrutor (nomeInstrutor, cpfInstrutor, emailInstrutor, telefoneInstrutor) values ( " // abre a insercao de valores
                    + "'" + instrutorDTO.getNomeInstrutor() + "', " // a primeira parte  --> "'" <-- é para abrir uma ' e logo no final --> "'," <-- para ser exibido para a DB dessa maneira --> 'nome', 'logradouro',
                    + "'" + instrutorDTO.getCpfInstrutor() + "', "
                    + "'" + instrutorDTO.getEmailInstrutor() + "', "
                    + "'" + instrutorDTO.getTelefoneInstrutor() + "') ";
                            
 // para fechar a insercao que foi aberta na linha 35

            stmt.execute(comando.toUpperCase()); //Executa o comando SQL no banco de dados

            ConexaoDAO.con.commit(); // da um commit na DB

            stmt.close();

            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            ConexaoDAO.CloseDB(); // chama o metodo da classe conezao para fechar a DB
        }
    }
    public boolean alterarInstrutor(InstrutorDTO instrutorDTO){
        try{
            ConexaoDAO.ConectDB();
            
            stmt = ConexaoDAO.con.createStatement();// criar statement responsavel por alguma coisa no banco de dados
            
            String comando = "Update instrutor set "
            + "nomeInstrutor = '" + instrutorDTO.getNomeInstrutor()+"', "
            + "cpfInstrutor = '" + instrutorDTO.getCpfInstrutor()+"', "
            + "telefoneinstrutor = '" + instrutorDTO.getTelefoneInstrutor()+"', "
            + "emailinstrutor = '" + instrutorDTO.getEmailInstrutor()+"' "           
            + "where id_instrutor = " + instrutorDTO.getId_instrutor();
                    
            stmt.execute(comando.toUpperCase());
            
            ConexaoDAO.con.commit(); //da commit no banco de dados
            
            stmt.close(); // fechar o stmt
            return true;
        }
        
        catch (Exception e){
            System.out.println("erro alterar "+e.getMessage());      
            return false;
        } // caso de erro ou nao o banco ira ser fechado
        finally{
            ConexaoDAO.CloseDB();
        }
    }
    
        public ResultSet consultarInstrutor(InstrutorDTO instrutorDTO, int opcao){
        try{
            ConexaoDAO.ConectDB(); // Chama o metodo que esta nba classe ConexaoDAO para abrir o banco de dados 
            
            stmt = ConexaoDAO.con.createStatement(); // Cria o Statement que responsavel por executar alguma coisa no banco de dados 
            
            String comando = " "; // comando sql que sera executado no banco de dados 
            
            switch(opcao){
                case 1:
                       comando = " Select i.* "+
                               "from instrutor i "+
                               "where nomeInstrutor like '"+ instrutorDTO.getNomeInstrutor()+ "%' "+
                               "order by i.nomeInstrutor";
                break;
                case 2:
                    comando = " Select i.* "+
                              "from instrutor i "+
                              "where i.id_instrutor = "+ instrutorDTO.getId_instrutor();
                break;
                case 3:
                    comando = "Select i.id_instrutor, i.nomeInstrutor "+
                            "from instrutor i ";
                break;   
                }
            
            rs = stmt.executeQuery(comando.toUpperCase());
            
            return rs; // caso tenha algum erro no codigo acima é enviado uma mensagem no console com o que esta acontecendo 
            }
        catch (Exception e){
            System.out.println("Erro ao "+e.getMessage());
            return rs;
        }
    }
        
          public boolean excluirInstrutor(InstrutorDTO instrutorDTO){
        try{
            ConexaoDAO.ConectDB();
            
            stmt = ConexaoDAO.con.createStatement();
            String comando = "Delete from instrutor where id_instrutor = "
                    +instrutorDTO.getId_instrutor();
                    stmt.execute(comando);
                    
            ConexaoDAO.con.commit();
            
            stmt.close();
            
            return true;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        finally{
            ConexaoDAO.CloseDB();
        }
    }
    
    
}

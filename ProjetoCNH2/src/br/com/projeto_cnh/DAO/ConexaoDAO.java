/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto_cnh.DAO;

import java.sql.*;

/**
 *
 * @author Aluno
 */
public class ConexaoDAO {

    public static Connection con = null; //criando variavel "con" do tipo Connection para fazer conexão com o banco de dados 

    public ConexaoDAO() {
    }

    public static void ConectDB() {
        try {
            //dados para conectar o banco de dados Postgree
            String dan = "Projeto_Cnh";// nome do banco de dados (igual ao criado no Postgree
            String user = "postgres"; // nome de usuario 
            String senha = "postdba"; // senha do usuario 

            DriverManager.registerDriver(new org.postgresql.Driver()); //linha exibira um erro no começo 

            String url = "jdbc:postgresql://localhost:5432/" + dan;

            con = DriverManager.getConnection(url, user, senha);

            con.setAutoCommit(false);

            if (con == null) {
                System.out.println("erro ao abrir a DB"); // utilizando System.out.println ao invés de JOptionPane para ajudar o programador a achar erros
            }
        } catch (Exception e) {
            System.out.println("Problema ao abrir a DB " + e.getMessage());
        }
    }


 public static void CloseDB(){
        try{
            con.close();
        }
        catch(Exception e){
            System.out.println("Problema ao fechar a DB "+e.getMessage());
        }
 }
}
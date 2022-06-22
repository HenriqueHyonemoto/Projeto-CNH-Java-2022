/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto_cnh.DAO;
import java.sql.*;
import br.com.projeto_cnh.DTO.ClienteDTO;
/**
 *
 * @author Rick
 */
public class ClienteDAO {
    public ClienteDAO() {

    }
    private ResultSet rs = null; // para realizar consultas
    private Statement stmt = null; // carrega a solicitação ao banco

    
    public boolean inserirCliente(ClienteDTO clienteDTO) { //comandos abrir fechar banco de dados
        try {

            ConexaoDAO.ConectDB();//chma o metodo da classe ConexaoDAO para abertura da DB

            stmt = ConexaoDAO.con.createStatement();

            //
            String comando = "Insert into cliente (nome, rua, numero, "
                    + "bairro, cidade, estado, cep, cpf, telefone, idade, tipoCnh, tipoCnhAtual, rg) values ( " // abre a insercao de valores
                    + "'" + clienteDTO.getNome() + "', " // a primeira parte  --> "'" <-- é para abrir uma ' e logo no final --> "'," <-- para ser exibido para a DB dessa maneira --> 'nome', 'logradouro',
                    + "'" + clienteDTO.getRua() + "', "
                    + clienteDTO.getNumero() + ", " // variavel do tipo inteiro, nao se utiliza --> "'"<--
                    + "'" + clienteDTO.getBairro() + "', "
                    + "'" + clienteDTO.getCidade() + "', "
                    + "'" + clienteDTO.getEstado() + "', "
                    + "'" + clienteDTO.getCep() + "', "
                    + "'" + clienteDTO.getCpf() + "', "
                    + "'" + clienteDTO.getTelefone() + "', "
                            
                    + clienteDTO.getIdade() + ", "
                    + "'" + clienteDTO.getTipoCnh() + "', "
                    + "'" + clienteDTO.getTipoCnhAtual() + "', "
                    + "'" + clienteDTO.getRg() + "') "; // para fechar a insercao que foi aberta na linha 35

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
    public boolean alterarCliente(ClienteDTO clienteDTO){
        try{
            ConexaoDAO.ConectDB();
            
            stmt = ConexaoDAO.con.createStatement();// criar statement responsavel por alguma coisa no banco de dados
            
            String comando = "Update cliente set "
            + "nome = '" + clienteDTO.getNome()+"', "
            + "rua = '" + clienteDTO.getRua()+"', "
            + "idade = '" + clienteDTO.getIdade()+"', "
            + "tipoCnh = '" + clienteDTO.getTipoCnh()+"', "
            + "tipoCnhAtual = '" + clienteDTO.getTipoCnhAtual()+"', "   
            + "numero = '" + clienteDTO.getNumero()+"', "        
            + "bairro = '" + clienteDTO.getBairro()+"', "        
            + "cidade = '" + clienteDTO.getCidade()+"', "        
            + "estado = '" + clienteDTO.getEstado()+"', "
            + "telefone = '" + clienteDTO.getTelefone()+"', "           
            + "cep = '" + clienteDTO.getCep()+"', "        
            + "cpf = '" + clienteDTO.getCpf()+"', "        
            + "rg = '" + clienteDTO.getRg()+"' "  //sem virgula pois é o ultimo elemento      
            + "where id_cliente = " + clienteDTO.getId_cliente();
                    
            stmt.execute(comando.toUpperCase());
            
            ConexaoDAO.con.commit(); //da commit no banco de dados
            
            stmt.close(); // fechar o stmt
            return true;
        }
        
        catch (Exception e){
            System.out.println("erro alterar"+e.getMessage());      
            return false;
        } // caso de erro ou nao o banco ira ser fechado
        finally{
            ConexaoDAO.CloseDB();
        }
    }
    
    public ResultSet consultarCliente(ClienteDTO clienteDTO, int opcao){
        try{
            ConexaoDAO.ConectDB(); // Chama o metodo que esta nba classe ConexaoDAO para abrir o banco de dados 
            
            stmt = ConexaoDAO.con.createStatement(); // Cria o Statement que responsavel por executar alguma coisa no banco de dados 
            
            String comando = " "; // comando sql que sera executado no banco de dados 
            
            switch(opcao){
                case 1:
                       comando = " Select c.* "+
                               "from cliente c "+
                               "where nome like '"+ clienteDTO.getNome()+ "%' "+
                               "order by c.nome";
                break;
                case 2:
                    comando = " Select c.* "+
                              "from cliente c "+
                              "where c.id_cliente = "+ clienteDTO.getId_cliente();
                break;
                case 3:
                    comando = "Select c.id_cliente, c.nome "+
                            "from cliente c ";
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
    
    public boolean excluirCliente(ClienteDTO clienteDTO){
        try{
            ConexaoDAO.ConectDB();
            
            stmt = ConexaoDAO.con.createStatement();
            String comando = "Delete from cliente where id_cliente = "
                    +clienteDTO.getId_cliente();
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

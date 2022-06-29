/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto_cnh.CTR;

import java.sql.ResultSet;
import br.com.projeto_cnh.DAO.ClienteDAO;
import br.com.projeto_cnh.DAO.ConexaoDAO;
import br.com.projeto_cnh.DTO.ClienteDTO;
/**
 *
 * @author Rick
 */
public class ClienteCTR {
ClienteDAO clienteDAO = new ClienteDAO();
ConexaoDAO conexaoDAO = new ConexaoDAO();


public String inserirCliente(ClienteDTO clienteDTO){
    try{
        if(clienteDAO.inserirCliente(clienteDTO)){ // chama o metodo que esta na classe dao aguardando uma resposta true ou false
            
        return "cadastrado com uscesso";
        
        }else{
        return "Erro ao cadastrar";
        }
        
        
    }
    catch(Exception e){
        System.out.println(e.getMessage());
        return "Erro ao cadastrar";
    }
  }

public ResultSet consultarCliente(ClienteDTO clienteDTO, int opcao){
ResultSet rs =  null; // É criado um atributo do tipo ResultSet, pois este metodo recebe o resultado de uma consulta

rs = clienteDAO.consultarCliente(clienteDTO, opcao); // o atributo rs recebe a consulta realizada pelo metodo da classe DAO

return rs;
}

public String alterarCLiente(ClienteDTO clienteDTO){
    try{
        if(clienteDAO.alterarCliente(clienteDTO)){
            return "Cliente alterado com sucesso!";
        }
            else{
                    return "Erro ao alterar cliente";
             }
            
        }
        catch(Exception e){
                System.out.println(e.getMessage());
                return "Cliente NÃO alterado";
                }
    }
    
public String excluirCliente(ClienteDTO clienteDTO){
    try{
        if (clienteDAO.excluirCliente(clienteDTO)){
            return "Cliente Exclido com sucesso! ";
            
        }else{
            return "cliente !--NAO--! excluido";
        }
    }catch(Exception e ){
        System.out.println(e.getMessage());
        return "cliente !--NAO--! excluido";
    }
}


public void CloseDB(){
     ConexaoDAO.CloseDB();
}    
}

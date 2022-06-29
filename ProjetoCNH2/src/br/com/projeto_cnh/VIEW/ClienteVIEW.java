/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto_cnh.VIEW;

import java.awt.Dimension;
import java.sql.ResultSet;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


import br.com.projeto_cnh.DTO.ClienteDTO;
import br.com.projeto_cnh.CTR.ClienteCTR;

/**
 *
 * @author Aluno
 */
public class ClienteVIEW extends javax.swing.JInternalFrame {
    
    ClienteDTO clienteDTO = new ClienteDTO();
    ClienteCTR clienteCTR = new ClienteCTR();
    
    int gravar_alterar; // variavel para saber se esta alterando ou incluindo
    ResultSet rs;// usada para preenchimento de tablea e campos
    DefaultTableModel modelo_jtl_consultar_cliente; // variavel para guardar o modelo da tabela

    /**
     * Creates new form ClienteVIEW
     */
    public ClienteVIEW() {
        
        initComponents();
        
        liberaCampos(false); // chama todos os metodos liberacampos
        liberaBotoes(true, false, false, false, true); // chama o metodo liberaBotoes
        modelo_jtl_consultar_cliente =  (DefaultTableModel) jtl_consultar_cliente.getModel();
    }
    
    public void setPositon(){
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width)/2, (d.height - this.getSize().height)/2);
             
    }
    
    public void gravar(){
    try{
        if((Integer.parseInt(idade.getText()))<18){
        
            JOptionPane.showMessageDialog(null, "Você não possui idade o suficiente");  
            
        }else
            
        if(tipoCnh.getSelectedItem().toString() == "C" && tipoCnhAtual.getSelectedItem().toString() != "B"){
            JOptionPane.showMessageDialog(null, "Você precisa ser habilitado na categoria B para tirar a categoria C!");      
        }else
        
        if(tipoCnh.getSelectedItem().toString() == "D" && Integer.parseInt(idade.getText()) < 21){
            JOptionPane.showMessageDialog(null, "Você precisa ter 21 anos ou mais para a categoria D!");      
        }else
        if(tipoCnh.getSelectedItem().toString() == "D" && tipoCnhAtual.getSelectedItem().toString() != "B" && tipoCnhAtual.getSelectedItem().toString() != "C"){
            JOptionPane.showMessageDialog(null, "Você precisa possuir a categoria B ou C para tirar a categoria D!");      
        }else
        
        if(tipoCnh.getSelectedItem().toString() == "E" && Integer.parseInt(idade.getText()) < 21){
            JOptionPane.showMessageDialog(null, "Você precisa ter 21 anos ou mais para a categoria e");      
        }else
            
        if(tipoCnh.getSelectedItem().toString() == "E" && tipoCnhAtual.getSelectedItem().toString() != "D" && tipoCnhAtual.getSelectedItem().toString() != "C"){
        JOptionPane.showMessageDialog(null, "Você precisa possuir a categoria C ou D para tirar a categoria D!");      
        }
        else{
        clienteDTO.setNome(nome.getText());
        clienteDTO.setRua(rua.getText());
        clienteDTO.setNumero(Integer.parseInt(numero.getText())); //valor inteiro
        clienteDTO.setTelefone(Integer.parseInt(telefone.getText()));//valor inteiro
        clienteDTO.setIdade(Integer.parseInt(idade.getText()));
        clienteDTO.setBairro(bairro.getText());
        clienteDTO.setCidade(cidade.getText());
        clienteDTO.setEstado(estado.getSelectedItem().toString());
        clienteDTO.setTipoCnh(tipoCnh.getSelectedItem().toString());
        clienteDTO.setTipoCnhAtual(tipoCnhAtual.getSelectedItem().toString());// caixa de seleção
        clienteDTO.setCep(cep.getText());
        clienteDTO.setCpf(cpf.getText());
        clienteDTO.setRg(rg.getText());
  
        
        JOptionPane.showMessageDialog(null,
                    clienteCTR.inserirCliente(clienteDTO));
        }    
        
        
        
    }
    catch(Exception e){
            System.out.println("Erro ao Gravar" +e.getMessage());
    }
} // chamar metodo

    private void liberaCampos(boolean a){ //checar erro
    nome.setEnabled(a);
    rua.setEnabled(a);
    numero.setEnabled(a);
    bairro.setEnabled(a);
    cidade.setEnabled(a);
    estado.setEnabled(a);
    cep.setEnabled(a);
    cpf.setEnabled(a);
    rg.setEnabled(a);
    
    idade.setEnabled(a);
    tipoCnh.setEnabled(a);
    tipoCnhAtual.setEnabled(a);
    telefone.setEnabled(a);        
    
}

private void limpaCampos(){
    nome.setText(null); 
    rua.setText(null);
    numero.setText(null);
    bairro.setText(null);
    cidade.setText(null);
    //estado nao vai pois é uma caixa de selecao
    cep.setText(null);
    cpf.setText(null);
    rg.setText(null);
    //tipoCnh nao vai pois é uma caixa de selecao
    telefone.setText(null);
    idade.setText(null);
    
    
    
}

private void liberaBotoes(boolean a, boolean b, boolean c, boolean d, boolean e){ //checar erro
    btnNovo.setEnabled(a);
    btnSalvar.setEnabled(b);
    btnCancelar.setEnabled(c);
    btnExcluir.setEnabled(d);
    btnSair.setEnabled(e);
}

private void preenchecampos(int id_cliente){
    try{
        clienteDTO.setId_cliente(id_cliente);
        rs = clienteCTR.consultarCliente(clienteDTO, 2);
        if(rs.next()){
            limpaCampos();
        
        nome.setText(rs.getString("nome"));
        rua.setText(rs.getString("rua"));
        numero.setText(rs.getString("numero"));
        bairro.setText(rs.getString("bairro"));
        cidade.setText(rs.getString("cidade"));
        estado.setSelectedItem(rs.getString("estado"));
        cep.setText(rs.getString("cep"));
        cpf.setText(rs.getString("cpf"));
        rg.setText(rs.getString("rg"));
        
        telefone.setText(rs.getString("telefone"));
        idade.setText(rs.getString("idade"));
        tipoCnh.setSelectedItem(rs.getString("tipoCnh"));
        tipoCnhAtual.setSelectedItem(rs.getString("tipoCnhAtual"));
        
        
        
        
        
        gravar_alterar = 2;
        liberaCampos(true);
        
        }
        
    }
    catch (Exception e){
        System.out.println("erro "+e.getMessage());
    }
    
   
    
}
 private void alterar(){
     try{
     clienteDTO.setNome(nome.getText());    
     clienteDTO.setRua(rua.getText());    
     clienteDTO.setNumero(Integer.parseInt(numero.getText()));    
     clienteDTO.setBairro(bairro.getText());    
     clienteDTO.setCidade(cidade.getText());    
     clienteDTO.setEstado(estado.getSelectedItem().toString());    
     clienteDTO.setCep(cep.getText());    
     clienteDTO.setCpf(cpf.getText());    
     clienteDTO.setRg(rg.getText());    
     
     clienteDTO.setIdade(Integer.parseInt(idade.getText())); 
     clienteDTO.setTelefone(Integer.parseInt(telefone.getText()));
     clienteDTO.setTipoCnh(tipoCnh.getSelectedItem().toString()); 
     clienteDTO.setTipoCnhAtual(tipoCnhAtual.getSelectedItem().toString()); 
     
    
     JOptionPane.showMessageDialog(null,
             clienteCTR.alterarCLiente(clienteDTO)
             );
         

       } 
     catch (Exception e){
         clienteCTR.alterarCLiente(clienteDTO);
     }
    }
 
 private void excluir(){
     if (JOptionPane.showConfirmDialog(null, "deseja realmente excluir o cliente?", "Aviso", 
             JOptionPane.YES_NO_OPTION)== JOptionPane.YES_OPTION){
         JOptionPane.showMessageDialog(null,
                 clienteCTR.excluirCliente(clienteDTO)
         );
     }
 }
 
 private void preencheTabela (String nome){
    try{
    modelo_jtl_consultar_cliente.setNumRows(0); // limpa linhas
    clienteDTO.setNome(nome); // enquanto tiver linhas faça
    rs = clienteCTR.consultarCliente(clienteDTO, 1); // 1 = é a pesquisa
    while (rs.next()){
        modelo_jtl_consultar_cliente.addRow(new Object[]{
            rs.getString("id_cliente"),
            rs.getString("nome"),
        });
        }
        
    }
        
    
    catch(Exception erTab){
        System.out.println("Erro SQL: " +erTab);
    }
    finally{
    clienteCTR.CloseDB();
    }
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        nome = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        rg = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cpf = new javax.swing.JTextField();
        idade = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cidade = new javax.swing.JTextField();
        estado = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        bairro = new javax.swing.JTextField();
        rua = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        numero = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        telefone = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        tipoCnh = new javax.swing.JComboBox<>();
        btnSalvar = new javax.swing.JButton();
        btnSair = new javax.swing.JButton();
        btnNovo = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        nomeConsulta = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        cep = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtl_consultar_cliente = new javax.swing.JTable();
        jLabel16 = new javax.swing.JLabel();
        tipoCnhAtual = new javax.swing.JComboBox<>();
        btnPesquisar = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Consulta");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 310, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Nome:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 58, -1, -1));

        nome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nomeActionPerformed(evt);
            }
        });
        getContentPane().add(nome, new org.netbeans.lib.awtextra.AbsoluteConstraints(96, 62, 282, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("RG:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(388, 93, -1, -1));

        rg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rgActionPerformed(evt);
            }
        });
        getContentPane().add(rg, new org.netbeans.lib.awtextra.AbsoluteConstraints(445, 97, 317, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("CPF:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(54, 93, -1, -1));

        cpf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cpfActionPerformed(evt);
            }
        });
        getContentPane().add(cpf, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 100, 284, -1));

        idade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idadeActionPerformed(evt);
            }
        });
        getContentPane().add(idade, new org.netbeans.lib.awtextra.AbsoluteConstraints(703, 62, 61, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Idade:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(642, 58, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("Estado:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(32, 128, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setText("Cidade:");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(203, 128, -1, -1));

        cidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cidadeActionPerformed(evt);
            }
        });
        getContentPane().add(cidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(272, 132, 208, -1));

        estado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));
        getContentPane().add(estado, new org.netbeans.lib.awtextra.AbsoluteConstraints(99, 132, 94, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setText("Bairro:");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 128, -1, -1));

        bairro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bairroActionPerformed(evt);
            }
        });
        getContentPane().add(bairro, new org.netbeans.lib.awtextra.AbsoluteConstraints(552, 132, 210, -1));

        rua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ruaActionPerformed(evt);
            }
        });
        getContentPane().add(rua, new org.netbeans.lib.awtextra.AbsoluteConstraints(99, 167, 220, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setText("Rua:");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(55, 163, -1, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setText("Numero:");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(634, 163, -1, -1));

        numero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numeroActionPerformed(evt);
            }
        });
        getContentPane().add(numero, new org.netbeans.lib.awtextra.AbsoluteConstraints(706, 167, 56, -1));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel11.setText("Telefone:");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(388, 58, -1, -1));

        telefone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                telefoneActionPerformed(evt);
            }
        });
        getContentPane().add(telefone, new org.netbeans.lib.awtextra.AbsoluteConstraints(466, 62, 161, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel12.setText("CNH Tipo: ");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 200, -1, -1));

        tipoCnh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A/B", "A", "B", "C", "D", "E", " ", " " }));
        getContentPane().add(tipoCnh, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 200, 270, -1));

        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });
        getContentPane().add(btnSalvar, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 250, 130, 40));

        btnSair.setText("Sair");
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });
        getContentPane().add(btnSair, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 250, 130, 40));

        btnNovo.setText("Novo");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });
        getContentPane().add(btnNovo, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 250, 130, 40));

        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });
        getContentPane().add(btnExcluir, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 250, 130, 40));

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel13.setText("Cadastro de CNH");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 10, -1, -1));

        nomeConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nomeConsultaActionPerformed(evt);
            }
        });
        getContentPane().add(nomeConsulta, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 350, 250, -1));

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel14.setText("Nome:");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 350, -1, -1));

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        getContentPane().add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 250, 130, 40));

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel15.setText("Cep:");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 170, -1, -1));

        cep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cepActionPerformed(evt);
            }
        });
        getContentPane().add(cep, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 170, 220, -1));

        jtl_consultar_cliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Nome"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtl_consultar_cliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtl_consultar_clienteMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jtl_consultar_cliente);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 400, 750, 280));

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel16.setText("Categoria Atual: ");
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, -1, -1));

        tipoCnhAtual.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "nenhum", "A/B", "A", "B", "C", "D", "E", "", "" }));
        tipoCnhAtual.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                tipoCnhAtualAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        getContentPane().add(tipoCnhAtual, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 200, 240, -1));

        btnPesquisar.setText("Buscar");
        btnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarActionPerformed(evt);
            }
        });
        getContentPane().add(btnPesquisar, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 350, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nomeActionPerformed

    private void rgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rgActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rgActionPerformed

    private void cpfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cpfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cpfActionPerformed

    private void idadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idadeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idadeActionPerformed

    private void cidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cidadeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cidadeActionPerformed

    private void bairroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bairroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bairroActionPerformed

    private void ruaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ruaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ruaActionPerformed

    private void numeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numeroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_numeroActionPerformed

    private void telefoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_telefoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_telefoneActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed

        // TODO add your handling code here:
        if(gravar_alterar==1){
            gravar();
            gravar_alterar=0;
        }else{
            if(gravar_alterar == 2){
                alterar();
                gravar_alterar=0;
            }else{
                JOptionPane.showMessageDialog(null, "Erro no sistema! ");
            }
            
            
        }
        
        
        
        limpaCampos();
        liberaCampos(false);
        liberaBotoes(true,false,false,false,true);        // TODO add your handling code here:
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
    this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_btnSairActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
    liberaCampos(true);
    liberaBotoes(false, true, true, false, true);
    gravar_alterar = 1;        // TODO add your handling code here:
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
    excluir();
    limpaCampos();
    liberaCampos(false);
    liberaBotoes(true, false, false, false, true);
    modelo_jtl_consultar_cliente.setNumRows(0);// TODO add your ha
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void nomeConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nomeConsultaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nomeConsultaActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
    limpaCampos();
    liberaCampos(false);
    modelo_jtl_consultar_cliente.setNumRows(0);
    liberaBotoes(true, false, false, false, true);
    gravar_alterar = 0;// TODO add your handling code here:        // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void cepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cepActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cepActionPerformed

    private void tipoCnhAtualAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_tipoCnhAtualAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_tipoCnhAtualAncestorAdded

    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed
preencheTabela(nomeConsulta.getText().toUpperCase());         // TODO add your handling code here:
    }//GEN-LAST:event_btnPesquisarActionPerformed

    private void jtl_consultar_clienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtl_consultar_clienteMouseClicked
preenchecampos(Integer.parseInt(String.valueOf(
        jtl_consultar_cliente.getValueAt(
        jtl_consultar_cliente.getSelectedRow(),0 ))));
    liberaBotoes(false, true, true, true, true);        // TODO add your handling code here:
    }//GEN-LAST:event_jtl_consultar_clienteMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField bairro;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JButton btnSair;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JTextField cep;
    private javax.swing.JTextField cidade;
    private javax.swing.JTextField cpf;
    private javax.swing.JComboBox<String> estado;
    private javax.swing.JTextField idade;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jtl_consultar_cliente;
    private javax.swing.JTextField nome;
    private javax.swing.JTextField nomeConsulta;
    private javax.swing.JTextField numero;
    private javax.swing.JTextField rg;
    private javax.swing.JTextField rua;
    private javax.swing.JTextField telefone;
    private javax.swing.JComboBox<String> tipoCnh;
    private javax.swing.JComboBox<String> tipoCnhAtual;
    // End of variables declaration//GEN-END:variables
}

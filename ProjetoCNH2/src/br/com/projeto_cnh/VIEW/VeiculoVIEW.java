/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto_cnh.VIEW;

import br.com.projeto_cnh.CTR.VeiculoCTR;
import br.com.projeto_cnh.DTO.VeiculoDTO;
import br.com.projeto_cnh.CTR.InstrutorCTR;
import br.com.projeto_cnh.DTO.InstrutorDTO;
import java.awt.Dimension;
import javax.swing.JOptionPane;
/**
 *
 * @author aluno
 */
public class VeiculoVIEW extends javax.swing.JInternalFrame {
    VeiculoCTR veiculoCTR = new VeiculoCTR();
    VeiculoDTO veiculoDTO = new VeiculoDTO();
    
    InstrutorDTO instrutorDTO = new InstrutorDTO();
    InstrutorCTR instrutorCTR = new InstrutorCTR();
    
    /**
     * Creates new form VeiculoVIEW
     */
    public VeiculoVIEW() {
        initComponents();
    }
    
    public void setPosicao() {
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
    }
    
    private void gravar(){
        JOptionPane.showMessageDialog(null, this.veiculoCTR.inserirVeiculo(veiculoDTO, instrutorDTO));
    }
    
    private void excluir(){
        JOptionPane.showMessageDialog(null, this.veiculoCTR.excluirVeiculo(veiculoDTO));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 394, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 274, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}

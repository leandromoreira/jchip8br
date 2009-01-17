/*
This file is part of JChip8BR.

JChip8BR is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

JChip8BR is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with JChip8BR.  If not, see <http://www.gnu.org/licenses/>.
 */
package jchip8br.gui;

import jchip8br.core.Disassembler;
import jchip8br.core.Emulator;

/**
 * @author dreampeppers99
 */
public class WindowDisassembler extends javax.swing.JFrame {

    private Emulator emulator;

    /** Creates new form Dissambler */
    public WindowDisassembler() {
        initComponents();
    }

    void setEmulator(Emulator emulator) {
        this.emulator = emulator;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTxtAssemblerDavidWinter = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTxtAssemblerMichaelToren = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Dissasembler");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jTxtAssemblerDavidWinter.setBackground(new java.awt.Color(0, 0, 0));
        jTxtAssemblerDavidWinter.setColumns(20);
        jTxtAssemblerDavidWinter.setForeground(new java.awt.Color(0, 255, 0));
        jTxtAssemblerDavidWinter.setRows(5);
        jScrollPane1.setViewportView(jTxtAssemblerDavidWinter);

        jTxtAssemblerMichaelToren.setBackground(new java.awt.Color(0, 0, 0));
        jTxtAssemblerMichaelToren.setColumns(20);
        jTxtAssemblerMichaelToren.setForeground(new java.awt.Color(0, 255, 0));
        jTxtAssemblerMichaelToren.setRows(5);
        jScrollPane2.setViewportView(jTxtAssemblerMichaelToren);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        Disassembler disassembler = new Disassembler();
        jTxtAssemblerDavidWinter.setText(disassembler.assemblerFromDavidWinter(emulator.getMemory()));
        jTxtAssemblerMichaelToren.setText(disassembler.assemblerFromMichaelToren(emulator.getMemory()));
        jTxtAssemblerDavidWinter.setCaretPosition(0);
        jTxtAssemblerMichaelToren.setCaretPosition(0);
    }//GEN-LAST:event_formWindowOpened

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new WindowDisassembler().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTxtAssemblerDavidWinter;
    private javax.swing.JTextArea jTxtAssemblerMichaelToren;
    // End of variables declaration//GEN-END:variables
}

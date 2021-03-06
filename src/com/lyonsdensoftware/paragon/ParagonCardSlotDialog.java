
package com.lyonsdensoftware.paragon;

/**
 *
 * @author Joshua Lyons
 */
public class ParagonCardSlotDialog extends javax.swing.JDialog {

    ParagonDeckBuilderMain myMain;              // Ref to main screen
    ParagonCardButton myBtn;                  // Ref to pane
    
    /**
     * Creates new form ParagonRemoveCardDialog
     */
    public ParagonCardSlotDialog(java.awt.Frame parent, boolean modal, ParagonCardButton button) {
        super(parent, modal);
        this.myMain = (ParagonDeckBuilderMain) parent;
        this.myBtn = button;
        initComponents();
        
        this.myInit();
    }
    
    public void myInit() {
        this.setLocation(((this.myBtn.getLocationOnScreen().x) + (this.myBtn.getWidth() / 2) - (this.getWidth() / 2)), 
                ((this.myBtn.getLocationOnScreen().y) + (this.myBtn.getHeight() / 2) - (this.getHeight() / 2)));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_RemoveCard = new javax.swing.JButton();
        btn_Upgrade = new javax.swing.JButton();
        btn_Cancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        setModal(true);
        setName("Popup"); // NOI18N
        setUndecorated(true);
        setResizable(false);

        btn_RemoveCard.setText("Remove Card");
        btn_RemoveCard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_RemoveCardActionPerformed(evt);
            }
        });

        btn_Upgrade.setText("Upgrade");
        btn_Upgrade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_UpgradeActionPerformed(evt);
            }
        });

        btn_Cancel.setText("Cancel");
        btn_Cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btn_RemoveCard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_Upgrade, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_Cancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(btn_RemoveCard)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_Upgrade)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_Cancel))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_UpgradeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_UpgradeActionPerformed
        this.myMain.upgradeCardInCardSlot(this.myBtn);
        this.dispose();
    }//GEN-LAST:event_btn_UpgradeActionPerformed

    private void btn_RemoveCardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_RemoveCardActionPerformed
        this.myMain.removeCardFromCardSlot(this.myBtn);
        this.dispose();
    }//GEN-LAST:event_btn_RemoveCardActionPerformed

    private void btn_CancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CancelActionPerformed
        this.dispose();
    }//GEN-LAST:event_btn_CancelActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ParagonCardSlotDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ParagonCardSlotDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ParagonCardSlotDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ParagonCardSlotDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ParagonCardSlotDialog dialog = new ParagonCardSlotDialog(new javax.swing.JFrame(), true, null);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Cancel;
    private javax.swing.JButton btn_RemoveCard;
    private javax.swing.JButton btn_Upgrade;
    // End of variables declaration//GEN-END:variables
}

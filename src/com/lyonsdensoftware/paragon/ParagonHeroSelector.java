
package com.lyonsdensoftware.paragon;

import java.awt.Image;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author Josh Lyons
 */
public class ParagonHeroSelector extends javax.swing.JDialog {
    
    private ParagonDeckBuilderMain mainScreen;          // Reference to the main screen     
    private final int IMGPADDING = 20;

    /**
     * Creates new form ParagonHeroSelector
     */
    public ParagonHeroSelector(javax.swing.JFrame parent, boolean modal) {
        super(parent, modal);
        this.mainScreen = (ParagonDeckBuilderMain) parent;
        initComponents();
        myInit();
        
        this.setVisible(true);
    }
    
    /**
     * Init the buttons with images
     */
    public void myInit() {
        
        try {
            // Set popup loc
            this.setLocation(this.mainScreen.getBtnHeroSelectLocation());
            
            //BufferedImage tmp;
            Image tmp;
            
            
            // Default
            tmp = ImageIO.read(Paths.get("./Art/Heroes/Default.png").toFile()).getScaledInstance(this.btn_HeroDefault.getWidth() - this.IMGPADDING, 
                    this.btn_HeroDefault.getHeight() - this.IMGPADDING, Image.SCALE_FAST);            
            this.btn_HeroDefault.setIcon(new ImageIcon(tmp));
            
            // Dekker
            tmp = ImageIO.read(Paths.get("./Art/Heroes/Dekker.png").toFile()).getScaledInstance(this.btn_HeroDefault.getWidth() - this.IMGPADDING, 
                    this.btn_HeroDefault.getHeight() - this.IMGPADDING, Image.SCALE_FAST);
            this.btn_HeroDekker.setIcon(new ImageIcon(tmp));
            
            // FengMao
            tmp = ImageIO.read(Paths.get("./Art/Heroes/FengMao.png").toFile()).getScaledInstance(this.btn_HeroDefault.getWidth() - this.IMGPADDING, 
                    this.btn_HeroDefault.getHeight() - this.IMGPADDING, Image.SCALE_FAST);
            this.btn_HeroFengMao.setIcon(new ImageIcon(tmp));
            
            // Gadget
            tmp = ImageIO.read(Paths.get("./Art/Heroes/Gadget.png").toFile()).getScaledInstance(this.btn_HeroDefault.getWidth() - this.IMGPADDING, 
                    this.btn_HeroDefault.getHeight() - this.IMGPADDING, Image.SCALE_FAST);
            this.btn_HeroGadget.setIcon(new ImageIcon(tmp));
            
            // Gideon
            tmp = ImageIO.read(Paths.get("./Art/Heroes/Gideon.png").toFile()).getScaledInstance(this.btn_HeroDefault.getWidth() - this.IMGPADDING, 
                    this.btn_HeroDefault.getHeight() - this.IMGPADDING, Image.SCALE_FAST);
            this.btn_HeroGideon.setIcon(new ImageIcon(tmp));
            
            // Grux
            tmp = ImageIO.read(Paths.get("./Art/Heroes/Grux.png").toFile()).getScaledInstance(this.btn_HeroDefault.getWidth() - this.IMGPADDING, 
                    this.btn_HeroDefault.getHeight() - this.IMGPADDING, Image.SCALE_FAST);
            this.btn_HeroGrux.setIcon(new ImageIcon(tmp));
            
            // Howitzer
            tmp = ImageIO.read(Paths.get("./Art/Heroes/Howitzer.png").toFile()).getScaledInstance(this.btn_HeroDefault.getWidth() - this.IMGPADDING, 
                    this.btn_HeroDefault.getHeight() - this.IMGPADDING, Image.SCALE_FAST);
            this.btn_HeroHowitzer.setIcon(new ImageIcon(tmp));
            
            // Kallari
            tmp = ImageIO.read(Paths.get("./Art/Heroes/Kallari.png").toFile()).getScaledInstance(this.btn_HeroDefault.getWidth() - this.IMGPADDING, 
                    this.btn_HeroDefault.getHeight() - this.IMGPADDING, Image.SCALE_FAST);
            this.btn_HeroKallari.setIcon(new ImageIcon(tmp));
            
            // Murdock
            tmp = ImageIO.read(Paths.get("./Art/Heroes/Murdock.png").toFile()).getScaledInstance(this.btn_HeroDefault.getWidth() - this.IMGPADDING, 
                    this.btn_HeroDefault.getHeight() - this.IMGPADDING, Image.SCALE_FAST);
            this.btn_HeroMurdock.setIcon(new ImageIcon(tmp));
            
            // Muriel
            tmp = ImageIO.read(Paths.get("./Art/Heroes/Muriel.png").toFile()).getScaledInstance(this.btn_HeroDefault.getWidth() - this.IMGPADDING, 
                    this.btn_HeroDefault.getHeight() - this.IMGPADDING, Image.SCALE_FAST);
            this.btn_HeroMuriel.setIcon(new ImageIcon(tmp));
            
            // Rampage
            tmp = ImageIO.read(Paths.get("./Art/Heroes/Rampage.png").toFile()).getScaledInstance(this.btn_HeroDefault.getWidth() - this.IMGPADDING, 
                    this.btn_HeroDefault.getHeight() - this.IMGPADDING, Image.SCALE_FAST);
            this.btn_HeroRampage.setIcon(new ImageIcon(tmp));
            
            // Sparrow
            tmp = ImageIO.read(Paths.get("./Art/Heroes/Sparrow.png").toFile()).getScaledInstance(this.btn_HeroDefault.getWidth() - this.IMGPADDING, 
                    this.btn_HeroDefault.getHeight() - this.IMGPADDING, Image.SCALE_FAST);
            this.btn_HeroSparrow.setIcon(new ImageIcon(tmp));
            
            // Steel
            tmp = ImageIO.read(Paths.get("./Art/Heroes/Steel.png").toFile()).getScaledInstance(this.btn_HeroDefault.getWidth() - this.IMGPADDING, 
                    this.btn_HeroDefault.getHeight() - this.IMGPADDING, Image.SCALE_FAST);
            this.btn_HeroSteel.setIcon(new ImageIcon(tmp));
            
            // TwinBlast
            tmp = ImageIO.read(Paths.get("./Art/Heroes/TwinBlast.png").toFile()).getScaledInstance(this.btn_HeroDefault.getWidth() - this.IMGPADDING, 
                    this.btn_HeroDefault.getHeight() - this.IMGPADDING, Image.SCALE_FAST);
            this.btn_HeroTwinBlast.setIcon(new ImageIcon(tmp));
            
        } catch (IOException ex) {
            Logger.getLogger(ParagonHeroSelector.class.getName()).log(Level.SEVERE, null, ex);
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

        btn_HeroDefault = new javax.swing.JButton();
        btn_HeroDekker = new javax.swing.JButton();
        btn_HeroFengMao = new javax.swing.JButton();
        btn_HeroGadget = new javax.swing.JButton();
        btn_HeroGrux = new javax.swing.JButton();
        btn_HeroGideon = new javax.swing.JButton();
        btn_HeroKallari = new javax.swing.JButton();
        btn_HeroHowitzer = new javax.swing.JButton();
        btn_HeroMurdock = new javax.swing.JButton();
        btn_HeroMuriel = new javax.swing.JButton();
        btn_HeroRampage = new javax.swing.JButton();
        btn_HeroSparrow = new javax.swing.JButton();
        btn_HeroSteel = new javax.swing.JButton();
        btn_HeroTwinBlast = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        setModal(true);
        setName("HeroSelector"); // NOI18N
        setUndecorated(true);
        setResizable(false);
        setType(java.awt.Window.Type.POPUP);

        btn_HeroDefault.setPreferredSize(new java.awt.Dimension(100, 100));
        btn_HeroDefault.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_HeroDefaultActionPerformed(evt);
            }
        });

        btn_HeroDekker.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_HeroDekkerActionPerformed(evt);
            }
        });

        btn_HeroFengMao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_HeroFengMaoActionPerformed(evt);
            }
        });

        btn_HeroGadget.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_HeroGadgetActionPerformed(evt);
            }
        });

        btn_HeroGrux.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_HeroGruxActionPerformed(evt);
            }
        });

        btn_HeroGideon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_HeroGideonActionPerformed(evt);
            }
        });

        btn_HeroKallari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_HeroKallariActionPerformed(evt);
            }
        });

        btn_HeroHowitzer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_HeroHowitzerActionPerformed(evt);
            }
        });

        btn_HeroMurdock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_HeroMurdockActionPerformed(evt);
            }
        });

        btn_HeroMuriel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_HeroMurielActionPerformed(evt);
            }
        });

        btn_HeroRampage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_HeroRampageActionPerformed(evt);
            }
        });

        btn_HeroSparrow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_HeroSparrowActionPerformed(evt);
            }
        });

        btn_HeroSteel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_HeroSteelActionPerformed(evt);
            }
        });

        btn_HeroTwinBlast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_HeroTwinBlastActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_HeroDefault, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_HeroDekker, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_HeroFengMao, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_HeroGadget, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_HeroGideon, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_HeroGrux, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_HeroHowitzer, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_HeroKallari, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_HeroMurdock, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_HeroMuriel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_HeroRampage, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_HeroSparrow, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_HeroSteel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_HeroTwinBlast, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btn_HeroDekker, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_HeroDefault, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btn_HeroGadget, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_HeroFengMao, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btn_HeroGrux, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_HeroGideon, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btn_HeroKallari, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_HeroHowitzer, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btn_HeroMuriel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_HeroMurdock, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btn_HeroSparrow, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_HeroRampage, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btn_HeroTwinBlast, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_HeroSteel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_HeroDefaultActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_HeroDefaultActionPerformed
        // First set the hero name
        this.mainScreen.setHero("Default");
        
        // Close this popop            
        this.dispose();
    }//GEN-LAST:event_btn_HeroDefaultActionPerformed

    private void btn_HeroDekkerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_HeroDekkerActionPerformed
        // First set the hero name
        this.mainScreen.setHero("Dekker");
        
        // Close this popop            
        this.dispose();
    }//GEN-LAST:event_btn_HeroDekkerActionPerformed

    private void btn_HeroFengMaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_HeroFengMaoActionPerformed
        // First set the hero name
        this.mainScreen.setHero("FengMao");
        
        // Close this popop            
        this.dispose();
    }//GEN-LAST:event_btn_HeroFengMaoActionPerformed

    private void btn_HeroGadgetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_HeroGadgetActionPerformed
        // First set the hero name
        this.mainScreen.setHero("Gadget");
        
        // Close this popop            
        this.dispose();
    }//GEN-LAST:event_btn_HeroGadgetActionPerformed

    private void btn_HeroGideonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_HeroGideonActionPerformed
        // First set the hero name
        this.mainScreen.setHero("Gideon");
        
        // Close this popop            
        this.dispose();
    }//GEN-LAST:event_btn_HeroGideonActionPerformed

    private void btn_HeroGruxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_HeroGruxActionPerformed
        // First set the hero name
        this.mainScreen.setHero("Grux");
        
        // Close this popop            
        this.dispose();
    }//GEN-LAST:event_btn_HeroGruxActionPerformed

    private void btn_HeroHowitzerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_HeroHowitzerActionPerformed
        // First set the hero name
        this.mainScreen.setHero("Howitzer");
        
        // Close this popop            
        this.dispose();
    }//GEN-LAST:event_btn_HeroHowitzerActionPerformed

    private void btn_HeroKallariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_HeroKallariActionPerformed
        // First set the hero name
        this.mainScreen.setHero("Kallari");
        
        // Close this popop            
        this.dispose();
    }//GEN-LAST:event_btn_HeroKallariActionPerformed

    private void btn_HeroMurdockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_HeroMurdockActionPerformed
        // First set the hero name
        this.mainScreen.setHero("Murdock");
        
        // Close this popop            
        this.dispose();
    }//GEN-LAST:event_btn_HeroMurdockActionPerformed

    private void btn_HeroMurielActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_HeroMurielActionPerformed
        // First set the hero name
        this.mainScreen.setHero("Muriel");
        
        // Close this popop            
        this.dispose();
    }//GEN-LAST:event_btn_HeroMurielActionPerformed

    private void btn_HeroRampageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_HeroRampageActionPerformed
        // First set the hero name
        this.mainScreen.setHero("Rampage");
        
        // Close this popop            
        this.dispose();
    }//GEN-LAST:event_btn_HeroRampageActionPerformed

    private void btn_HeroSparrowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_HeroSparrowActionPerformed
        // First set the hero name
        this.mainScreen.setHero("Sparrow");
        
        // Close this popop            
        this.dispose();
    }//GEN-LAST:event_btn_HeroSparrowActionPerformed

    private void btn_HeroSteelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_HeroSteelActionPerformed
        // First set the hero name
        this.mainScreen.setHero("Steel");
        
        // Close this popop            
        this.dispose();
    }//GEN-LAST:event_btn_HeroSteelActionPerformed

    private void btn_HeroTwinBlastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_HeroTwinBlastActionPerformed
        // First set the hero name
        this.mainScreen.setHero("TwinBlast");
        
        // Close this popop            
        this.dispose();
    }//GEN-LAST:event_btn_HeroTwinBlastActionPerformed

    
    
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
            java.util.logging.Logger.getLogger(ParagonHeroSelector.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ParagonHeroSelector.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ParagonHeroSelector.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ParagonHeroSelector.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ParagonHeroSelector dialog = new ParagonHeroSelector(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_HeroDefault;
    private javax.swing.JButton btn_HeroDekker;
    private javax.swing.JButton btn_HeroFengMao;
    private javax.swing.JButton btn_HeroGadget;
    private javax.swing.JButton btn_HeroGideon;
    private javax.swing.JButton btn_HeroGrux;
    private javax.swing.JButton btn_HeroHowitzer;
    private javax.swing.JButton btn_HeroKallari;
    private javax.swing.JButton btn_HeroMurdock;
    private javax.swing.JButton btn_HeroMuriel;
    private javax.swing.JButton btn_HeroRampage;
    private javax.swing.JButton btn_HeroSparrow;
    private javax.swing.JButton btn_HeroSteel;
    private javax.swing.JButton btn_HeroTwinBlast;
    // End of variables declaration//GEN-END:variables
}

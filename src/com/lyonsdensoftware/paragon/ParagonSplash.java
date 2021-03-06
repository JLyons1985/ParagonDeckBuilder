/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lyonsdensoftware.paragon;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 *
 * @author jlyon
 */
public class ParagonSplash extends javax.swing.JFrame {

    private String text = "";
    /**
     * Creates new form ParagonSplash
     */
    public ParagonSplash() {
        try {
            initComponents();
                        
            //this.LoaderGif.setBounds(576, 193, 34, 34);
            //this.LoaderText.setBounds(231, 199, 345, 25);
            
            File xmlFile = Paths.get("./Versions.xml").toFile();
            DocumentBuilder dBuilder;
            Document doc = null;
            try {
                dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            
                doc = dBuilder.parse(xmlFile);
            } catch (SAXException | ParserConfigurationException ex) {
                Logger.getLogger(ParagonSplash.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            doc.getDocumentElement().normalize();
            
                        
            // Create a list of all the elements with Card
            Element elem = (Element) doc.getElementsByTagName("Program").item(0);
            
            this.version.setText("Version: " + elem.getElementsByTagName("Version").item(0).getTextContent());
            
        } catch (IOException ex) {
            Logger.getLogger(ParagonSplash.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * 
     * @param text 
     */
    public void setLoaderText(String text) {
        this.loaderText.setText(text);
        this.loaderText.repaint();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pane = new javax.swing.JLayeredPane();
        loaderText = new javax.swing.JLabel();
        Splash = new javax.swing.JLabel();
        version = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        setUndecorated(true);
        setResizable(false);

        pane.setPreferredSize(new java.awt.Dimension(800, 400));

        loaderText.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        loaderText.setForeground(new java.awt.Color(255, 255, 255));
        loaderText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        loaderText.setText("jLabel1");

        Splash.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/lyonsdensoftware/paragon/SplashSmall.png"))); // NOI18N
        Splash.setMaximumSize(new java.awt.Dimension(2048, 1024));

        version.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        version.setForeground(new java.awt.Color(255, 255, 255));
        version.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        version.setText("Test");

        pane.setLayer(loaderText, javax.swing.JLayeredPane.PALETTE_LAYER);
        pane.setLayer(Splash, javax.swing.JLayeredPane.DEFAULT_LAYER);
        pane.setLayer(version, javax.swing.JLayeredPane.PALETTE_LAYER);

        javax.swing.GroupLayout paneLayout = new javax.swing.GroupLayout(pane);
        pane.setLayout(paneLayout);
        paneLayout.setHorizontalGroup(
            paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneLayout.createSequentialGroup()
                .addGap(209, 209, 209)
                .addComponent(loaderText, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(246, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, paneLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(version, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(Splash, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        paneLayout.setVerticalGroup(
            paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneLayout.createSequentialGroup()
                .addGap(182, 182, 182)
                .addComponent(loaderText, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 139, Short.MAX_VALUE)
                .addComponent(version, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
            .addGroup(paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(Splash, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(ParagonSplash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ParagonSplash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ParagonSplash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ParagonSplash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ParagonSplash().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Splash;
    private javax.swing.JLabel loaderText;
    private javax.swing.JLayeredPane pane;
    private javax.swing.JLabel version;
    // End of variables declaration//GEN-END:variables
}

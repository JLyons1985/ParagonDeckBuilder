/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lyonsdensoftware.paragonlauncher;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jlyon
 */
public class DeckBuilderLauncher {
    
    private static Updater updater;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        LauncherStatus tmp = new LauncherStatus();
        centreWindow(tmp);
        tmp.setVisible(true);
        
        tmp.setStatus("Connecting to update servers....");
        updater = new Updater(tmp);

        updater.getNewTemporaryCredentials();
            
            //updater.listBuckets();
            //updater.listKeys("paragondeckbuilder");
        try {
            tmp.setStatus("Checking for updates....");
            File testFile;
            boolean newDownload = false;
            testFile = Paths.get("./Versions.xml").toFile();
            if (!testFile.exists()) { // File doesnt exist so get it
                newDownload = true;
                System.out.println("Getting Versions.xml");
                updater.getFileFromServer("paragondeckbuilder", "Versions.xml", testFile);
            }
                      
            // Compare Art Versions
            if (!Updater.getCurrentVersion("Art").equals(Updater.getLatestVersion("Art")) || newDownload) { // Find out what files need updating
                tmp.setStatus("Updating art....");
                testFile = Paths.get("./").toFile();
                System.out.println("Getting Art Folder");
                updater.getAllFilesFromFolder("paragondeckbuilder", "Art/", testFile);
            }
            
            // Compare Data Versions
            if (!Updater.getCurrentVersion("Data").equals(Updater.getLatestVersion("Data")) || newDownload) { // Find out what files need updating
                tmp.setStatus("Updating data files....");
                testFile = Paths.get("./").toFile();
                System.out.println("Getting Data Folder");
                updater.getAllFilesFromFolder("paragondeckbuilder", "Data/", testFile);
            }
            
            // Compare App Versions
            testFile = Paths.get("./lib/ParagonDeckBuilder.jar").toFile();
            if (!testFile.exists()) { // File doesnt exist so get it
                tmp.setStatus("Updating program files....");
                System.out.println("Program Different");
                testFile = Paths.get("./").toFile();
                updater.getAllFilesFromFolder("paragondeckbuilder", "lib/", testFile);
            }
            else if (!Updater.getCurrentVersion("Program").equals(Updater.getLatestVersion("Program")) ) { // Find out what files need updating
                tmp.setStatus("Updating program files....");
                testFile = Paths.get("./").toFile();
                System.out.println("Getting Program");
                updater.getAllFilesFromFolder("paragondeckbuilder", "lib/", testFile);
            }
            
            testFile = Paths.get("./Versions.xml").toFile();
            updater.getFileFromServer("paragondeckbuilder", "Versions.xml", testFile);
            
            
        } catch (Exception ex) {
            Logger.getLogger(DeckBuilderLauncher.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Start the app
        tmp.setStatus("Satring deck builder....");
        startApp();
        System.exit(0);
        
    }
    
    /**
     * Centers the frame on the screen
     * @param frame 
     */
    public static void centreWindow(javax.swing.JFrame frame) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
    }
    
    public static void startApp() {
        try {
            String jarFile = Paths.get("./lib/ParagonDeckBuilder.jar").toString();
            Runtime.getRuntime().exec("java -Xmx1g -jar " + jarFile);
        } catch (IOException ex) {
            Logger.getLogger(DeckBuilderLauncher.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
    


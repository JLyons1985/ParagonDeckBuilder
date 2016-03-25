
package com.lyonsdensoftware.paragon;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import jxl.JXLException;
import jxl.Sheet;
import jxl.Workbook;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author Joshua Lyons
 */
public class ParagonHeroeExcelToXml {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // Run through all heroes and create their stats
        exportHeroToXml("Dekker");
        exportHeroToXml("FengMao");
        exportHeroToXml("Gadget");
        exportHeroToXml("Gideon");
        exportHeroToXml("Grux");
        exportHeroToXml("Howitzer");
        exportHeroToXml("Kallari");
        exportHeroToXml("Murdock");
        exportHeroToXml("Muriel");
        exportHeroToXml("Rampage");
        exportHeroToXml("Sparrow");
        exportHeroToXml("Steel");
        exportHeroToXml("TwinBlast");
    }
    
    public static boolean exportHeroToXml(String heroName) {
        
        try {
            // First open up the cards workbook
            Path path = Paths.get("./Data/" + heroName + ".xls");
            
            Sheet sheet = Workbook.getWorkbook(path.toFile()).getSheet(0); 
            
            // Now create the xml documents
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement(heroName);
            doc.appendChild(rootElement);
                       
            // Create the affinity nodes
            Element elem;
                
            // Looop through affinities
            for  (int row = 1; row < 6; row++) {
                String tmpStr = sheet.getCell(1, row).getContents();
                if (tmpStr.equals("X")) {
                    elem = doc.createElement("Affinity");
                    elem.appendChild(doc.createTextNode(sheet.getCell(0, row).getContents()));
                    rootElement.appendChild(elem);                    
                }
            }
            
            // Loop through roles
            for  (int row = 6; row < 13; row++) {
                String tmpStr = sheet.getCell(1, row).getContents();
                if (!tmpStr.equals("")) {
                    elem = doc.createElement("Role");
                    elem.appendChild(doc.createTextNode(sheet.getCell(0, row).getContents()));
                    rootElement.appendChild(elem);                    
                }
            }
            
            // Loop through stats
            for  (int row = 13; row < 18; row++) {
                String tmpStr = sheet.getCell(1, row).getContents();
                if (!tmpStr.equals("")) {
                    elem = doc.createElement(sheet.getCell(0, row).getContents());
                    elem.appendChild(doc.createTextNode(sheet.getCell(1, row).getContents()));
                    rootElement.appendChild(elem);                    
                }
            }
            
            // Max Level
            elem = doc.createElement("MaxLevel");
            elem.appendChild(doc.createTextNode(sheet.getCell(0, 19).getContents()));
            rootElement.appendChild(elem);
            
            // Holds the column for level 1
            int lvl1Col = 1;
            
            // Loop through first set of lvl based stats
            // O(n^2)
            for (int row = 20; row < 41; row++){
                
                String tmpStr = sheet.getCell(0, row).getContents();
                elem = doc.createElement(tmpStr);
                
                for (int col = lvl1Col; col < lvl1Col + 30; col++) {
                    Element lvlElem = doc.createElement("lvl" + col);
                    lvlElem.appendChild(doc.createTextNode(sheet.getCell(col, row).getContents()));
                    elem.appendChild(lvlElem);
                }
                
                rootElement.appendChild(elem);
            }
            
            // Passive Ability
            elem = doc.createElement("Passive");
            for (int row = 42; row < 44; row++) {
                Element passiveElem = doc.createElement(sheet.getCell(0, row).getContents());
                passiveElem.appendChild(doc.createTextNode(sheet.getCell(1, row).getContents()));
                elem.appendChild(passiveElem);
            } 
            
            rootElement.appendChild(elem);
            
            // Basic Attack
            // O(n2)
            elem = doc.createElement("BasicAttack");
            for (int row = 45; row < 50; row++){
                
                String tmpStr = sheet.getCell(0, row).getContents();
                Element basicElem = doc.createElement(tmpStr);
                
                for (int col = lvl1Col; col < lvl1Col + 30; col++) {
                    Element lvlElem = doc.createElement("lvl" + col);
                    lvlElem.appendChild(doc.createTextNode(sheet.getCell(col, row).getContents()));
                    basicElem.appendChild(lvlElem);
                }
                
                elem.appendChild(basicElem);
            }
            rootElement.appendChild(elem);
            
            // Alternate ability
            // O(n2)
            elem = doc.createElement("AlternateAbility");
            for (int row = 51; row < 56; row++){
                
                String tmpStr = sheet.getCell(0, row).getContents();
                Element basicElem = doc.createElement(tmpStr);
                
                for (int col = lvl1Col; col < lvl1Col + 30; col++) {
                    Element lvlElem = doc.createElement("lvl" + col);
                    lvlElem.appendChild(doc.createTextNode(sheet.getCell(col, row).getContents()));
                    basicElem.appendChild(lvlElem);
                }
                
                elem.appendChild(basicElem);
            }
            rootElement.appendChild(elem);
            
            // Primary ability
            // O(n2)
            elem = doc.createElement("PrimaryAbility");
            for (int row = 57; row < 61; row++){
                
                String tmpStr = sheet.getCell(0, row).getContents();
                Element basicElem = doc.createElement(tmpStr);
                
                for (int col = lvl1Col; col < lvl1Col + 30; col++) {
                    Element lvlElem = doc.createElement("lvl" + col);
                    lvlElem.appendChild(doc.createTextNode(sheet.getCell(col, row).getContents()));
                    basicElem.appendChild(lvlElem);
                }
                
                elem.appendChild(basicElem);
            }
            rootElement.appendChild(elem);
            
            // Secondary ability
            // O(n2)
            elem = doc.createElement("SecondaryAbility");
            for (int row = 62; row < 66; row++){
                
                String tmpStr = sheet.getCell(0, row).getContents();
                Element basicElem = doc.createElement(tmpStr);
                
                for (int col = lvl1Col; col < lvl1Col + 30; col++) {
                    Element lvlElem = doc.createElement("lvl" + col);
                    lvlElem.appendChild(doc.createTextNode(sheet.getCell(col, row).getContents()));
                    basicElem.appendChild(lvlElem);
                }
                
                elem.appendChild(basicElem);
            }
            rootElement.appendChild(elem);
            
            // Ultimate ability
            // O(n2)
            elem = doc.createElement("UltimateAbility");
            for (int row = 67; row < 71; row++){
                
                String tmpStr = sheet.getCell(0, row).getContents();
                Element basicElem = doc.createElement(tmpStr);
                
                for (int col = lvl1Col; col < lvl1Col + 30; col++) {
                    Element lvlElem = doc.createElement("lvl" + col);
                    lvlElem.appendChild(doc.createTextNode(sheet.getCell(col, row).getContents()));
                    basicElem.appendChild(lvlElem);
                }
                
                elem.appendChild(basicElem);
            }
            rootElement.appendChild(elem);
            
            
            // write the content into xml file
            TransformerFactory transformFactory = TransformerFactory.newInstance();
            transformFactory.setAttribute("indent-number", 2);
            Transformer transformer = transformFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(Paths.get("./Data/" + heroName + ".xml").toString()));

            // Output to console for testing
            // StreamResult result = new StreamResult(System.out);

            transformer.transform(source, result);

            System.out.println("File saved!");
            
            return true;
           
        }
        catch (IOException|JXLException e) {
            System.out.print(e);
            return false;
        }
        catch (ParserConfigurationException pce) {
		pce.printStackTrace();
                return false;
	} catch (TransformerException tfe) {
		tfe.printStackTrace();
                return false;
	}   
    }
    
}

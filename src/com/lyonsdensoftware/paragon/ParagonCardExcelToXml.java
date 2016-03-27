
package com.lyonsdensoftware.paragon;

// Imports
import java.io.File; 
import java.nio.file.Paths;
import java.nio.file.Path;
 import jxl.*;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;


import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author Joshua Lyons
 */
public class ParagonCardExcelToXml {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
                
        try {
            // First open up the cards workbook
            Path path = Paths.get("./Data/Cards.xls");
            
            Sheet sheet = Workbook.getWorkbook(path.toFile()).getSheet(0); 
            
            // Now create the xml documents
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("Cards");
            doc.appendChild(rootElement);
                       
            // Now loop through the workbood creating the xml nodes
            // O(n^2) refactor as needed
            String tmpAffinity = "Affinity.Corruption";
            String tmpRarity = "Common";
            int cardCountForAR = 0;
            
            for (int row = 1; row < sheet.getRows(); row++) {  // Rows
                
                // Create card element
                Element card = doc.createElement("Card");
                rootElement.appendChild(card);
                
                              
                for (int col = 0; col < sheet.getColumns() - 1; col++){
                
                    String test = new String(sheet.getCell(col, 0).getContents().replaceAll("\\s", ""));
                    //elemName = elemName.replaceFirst(elemName.substring(0, 0), elemName.substring(0, 0).toLowerCase());
                    
                    //System.out.println(test);
                    Element elem = doc.createElement(test);
                    elem.appendChild(doc.createTextNode(sheet.getCell(col, row).getContents()));
                    card.appendChild(elem);
                    
                }
                
            }
            
            // write the content into xml file
            TransformerFactory transformFactory = TransformerFactory.newInstance();
            transformFactory.setAttribute("indent-number", 2);
            Transformer transformer = transformFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(Paths.get("./Data/Cards.xml").toString()));

            // Output to console for testing
            // StreamResult result = new StreamResult(System.out);

            transformer.transform(source, result);

            System.out.println("File saved!");
           
        }
        catch (IOException|JXLException e) {
            System.out.print(e);
        }
        catch (ParserConfigurationException pce) {
		pce.printStackTrace();
	} catch (TransformerException tfe) {
		tfe.printStackTrace();
	}
        
    }
    
}

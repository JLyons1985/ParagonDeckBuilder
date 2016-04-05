
package com.lyonsdensoftware.paragonlauncher;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.amazonaws.services.s3.transfer.Download;
import com.amazonaws.services.s3.transfer.MultipleFileDownload;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.util.StringUtils;
import java.io.File;
import java.io.InputStream;
import java.io.StringReader;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.List;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

/**
 *
 * @author Josh Lyons
 */
public class Updater {
    
    private static final String endpoint = "https://s3-website-us-east-1.amazonaws.com";
    private static String accessId = "AKIAI4EYQEBHVXWTUDWQ";
    private static String accessKey = "WL07vGR1YxdDM1tkU3ayAWQvQW5Jlr6v4IzzOiAj";
    private static String bucketStr = "paragondeckbuilder";
    private static AmazonS3 s3;
    private LauncherStatus tmpStatus;
    
    public Updater(LauncherStatus tmp) {
        this.tmpStatus = tmp;
    }
    
    public void getNewTemporaryCredentials() {
                
        AWSCredentials basic_session_creds = new BasicAWSCredentials(
            accessId,
            accessKey);
        
        s3 = new AmazonS3Client(basic_session_creds);        
    }
    
    public void listBuckets() {
        List<Bucket> buckets = s3.listBuckets();
        for (Bucket bucket : buckets) {
            System.out.println(bucket.getName() + "\t" +
                StringUtils.fromDate(bucket.getCreationDate()));
        }
    }
    
    public void listKeys(String bucketStr) {
        Bucket bucket = new Bucket();
        bucket.setName(bucketStr);
        ObjectListing objects = s3.listObjects(bucket.getName());
        do {
            for (S3ObjectSummary objectSummary : objects.getObjectSummaries()) {
                System.out.println(objectSummary.getKey());
            }
            objects = s3.listNextBatchOfObjects(objects);
        } while (objects.isTruncated());

    }
    
    public void getAllFilesFromFolder(String bucketStr, String prefix, File destinationDirectory) {
        
        TransferManager transferManager = new TransferManager(s3);
        
        MultipleFileDownload myMultiDown = transferManager.downloadDirectory(bucketStr, prefix, destinationDirectory);
        
        while (!myMultiDown.isDone()) {
            DecimalFormat df = new DecimalFormat("#.##");
            this.tmpStatus.setPercent(df.format(myMultiDown.getProgress().getPercentTransferred()));
        }
        
        //transferManager.shutdownNow();
        
    }
    
    public void getFileFromServer(String bucketStr, String prefix, File destinationDirector) {
        
        TransferManager transferManager = new TransferManager(s3);
        Download myDownload = transferManager.download(bucketStr, prefix, destinationDirector);
        
        while (!myDownload.isDone()) {
            DecimalFormat df = new DecimalFormat("#.##");
            this.tmpStatus.setPercent(df.format(myDownload.getProgress().getPercentTransferred()));
        }
        //transferManager.shutdownNow();        
    }
    
    public void closeConnections() {
        
    }
    
    static String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

    public static String getLatestVersion(String programSection) throws Exception {
        S3Object myObject = s3.getObject(bucketStr, "Versions.xml");
        
        InputStream objectData = myObject.getObjectContent();
        String inStr = convertStreamToString(objectData);
        objectData.close();
        
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder =  factory.newDocumentBuilder();
        InputSource is = new InputSource(new StringReader(inStr));
        
        Document doc = dBuilder.parse(is);
        doc.getDocumentElement().normalize();
        
        Element elem = (Element) doc.getElementsByTagName(programSection).item(0);
        
        return elem.getElementsByTagName("Version").item(0).getTextContent();
    }
////
    public static String getCurrentVersion(String programSection) throws Exception {
        // Open xml file
            File xmlFile = Paths.get("./Versions.xml").toFile();
            DocumentBuilder dBuilder =  DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            
            doc.getDocumentElement().normalize();
            
                        
            // Create a list of all the elements with Card
            Element elem = (Element) doc.getElementsByTagName(programSection).item(0);
            
            return elem.getElementsByTagName("Version").item(0).getTextContent();
    }
//    
//    private static Document getData(String address)throws Exception
//    {
//        URL url = new URL(address);
//        
//        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
//        dbFactory.setNamespaceAware(true);
//        
//        return dbFactory.newDocumentBuilder().parse(url.openStream());
//    }

}

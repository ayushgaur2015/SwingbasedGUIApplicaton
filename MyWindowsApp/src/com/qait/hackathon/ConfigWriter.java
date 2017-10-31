package com.qait.hackathon;


import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.xml.sax.*;
import org.w3c.dom.*;

public class ConfigWriter {
	private String role1 = null;
	private String role2 = null;
	private String role3 = null;
	private String role4 = null;
	private ArrayList<String> rolev;
	
    public void writeConfigFile(Properties configProperties){
    Set parametrers;	
    parametrers = configProperties.keySet();
    File configProp = new File("./config.properties");
    try{
    	if (!configProp.exists()) {
        	configProp.createNewFile();
	    }
    	FileWriter writeProp = new FileWriter(configProp);
    	Iterator params =  parametrers.iterator();
    	while(params.hasNext()){
    		String par=(String) params.next();
    		writeProp.write( par+"=" +configProperties.getProperty(par)+"\n");
    		System.out.println(par+"=" +configProperties.getProperty(par));
    	}
    	writeProp.close();
    }
    catch(IOException io){
   	
    }
   }    
   
public File[] getTestClasses(String testClassPackage){
      	File folder = new File(testClassPackage);
        File[] listOfFiles = folder.listFiles();
          for (int i = 0; i < listOfFiles.length; i++) {
              if (listOfFiles[i].isFile()) {
                System.out.println("File " + listOfFiles[i].getName());
                return listOfFiles;
               } else if (listOfFiles[i].isDirectory()) {
                     System.out.println("Given path points to directory " + listOfFiles[i].getName()+" !!Please specify complete path to the folder");
                     return null;
               }
            }
          return null;
   } 

	public boolean readXML(String xml) {
		rolev = new ArrayList<String>();
		Document dom;
		// Make an  instance of the DocumentBuilderFactory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			// use the factory to take an instance of the document builder
			DocumentBuilder db = dbf.newDocumentBuilder();
			// parse using the builder to get the DOM mapping of the    
			// XML file
			dom = db.parse(xml);

			Element doc = dom.getDocumentElement();

			role1 = getTextValue(role1, doc, "role1");
			if (role1 != null) {
				if (!role1.isEmpty())
					rolev.add(role1);
			}
			role2 = getTextValue(role2, doc, "role2");
			if (role2 != null) {
				if (!role2.isEmpty())
					rolev.add(role2);
			}
			role3 = getTextValue(role3, doc, "role3");
			if (role3 != null) {
				if (!role3.isEmpty())
					rolev.add(role3);
			}
			role4 = getTextValue(role4, doc, "role4");
			if ( role4 != null) {
				if (!role4.isEmpty())
					rolev.add(role4);
			}
			return true;

		} catch (ParserConfigurationException pce) {
			System.out.println(pce.getMessage());
		} catch (SAXException se) {
			System.out.println(se.getMessage());
		} catch (IOException ioe) {
			System.err.println(ioe.getMessage());
		}

    return false;
}
	
public void saveToXML(String xml) {
    Document dom;
    Element e = null;

    // instance of a DocumentBuilderFactory
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    try {
        // use factory to get an instance of document builder
        DocumentBuilder db = dbf.newDocumentBuilder();
        // create instance of DOM
        dom = db.newDocument();

        // create the root element
        Element rootEle = dom.createElement("roles");
        dom.appendChild(rootEle);

//        //  supercars element
//        Element supercar = doc.createElement("supercars");
//        rootElement.appendChild(supercar);

        // setting attribute to element
//        Attr attr = doc.createAttribute("company");
//        attr.setValue("Ferrari");
//        supercar.setAttributeNode(attr);

        // create data elements and place them under root
        e = dom.createElement("role1");
        rootEle.appendChild(dom.createTextNode("role1"));
        rootEle.appendChild(e);

        e = dom.createElement("role2");
        rootEle.appendChild(dom.createTextNode("role2"));
        rootEle.appendChild(e);

        e = dom.createElement("role3");
        rootEle.appendChild(dom.createTextNode("role3"));
        rootEle.appendChild(e);

        e = dom.createElement("role4");
        rootEle.appendChild(dom.createTextNode("role4"));
        rootEle.appendChild(e);


        try {
            Transformer tr = TransformerFactory.newInstance().newTransformer();
            tr.setOutputProperty(OutputKeys.INDENT, "yes");
            tr.setOutputProperty(OutputKeys.METHOD, "xml");
            tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            tr.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "roles.dtd");
            tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

            // send DOM to file
            tr.transform(new DOMSource(dom), 
                                 new StreamResult(new  File(xml)));

        } catch (Exception te) {
            System.out.println(te.getMessage());
        } 
    } catch (ParserConfigurationException pce) {
        System.out.println("UsersXML: Error trying to instantiate DocumentBuilder " + pce);
    }
}

private String getTextValue(String def, Element doc, String tag) {
    String value = def;
    NodeList nl;
    nl = doc.getElementsByTagName(tag);
    if (nl.getLength() > 0 && nl.item(0).hasChildNodes()) {
        value = nl.item(0).getFirstChild().getNodeValue();
    }
    return value;
}

 }

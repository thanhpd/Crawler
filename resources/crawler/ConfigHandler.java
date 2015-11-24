package crawler;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ConfigHandler {
	private static ConfigHandler instance = null;
	
	private ConfigHandler() {
		createConfigFile();
	}
	
	public static ConfigHandler getInstance() {
		if(instance == null) instance = new ConfigHandler();	
		createConfigFile();
		return instance;
	}
	
	private static void createConfigFile() {
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// root elements
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("configuration");
			doc.appendChild(rootElement);
			
			// server host element
			Element serverHost = doc.createElement("serverHost");
			serverHost.appendChild(doc.createTextNode("localhost"));
			rootElement.appendChild(serverHost);
			
			// port element
			Element port = doc.createElement("port");
			port.appendChild(doc.createTextNode("8983"));
			rootElement.appendChild(port);
			
			// core element
			Element core = doc.createElement("core");
			core.appendChild(doc.createTextNode("new_core"));
			rootElement.appendChild(core);
			
			// solr path
			Element solrPath = doc.createElement("solrPath");
			solrPath.appendChild(doc.createTextNode("solr"));
			rootElement.appendChild(solrPath);
			
			// storage folder
			Element crawlStorageFolder = doc.createElement("crawlStorageFolder");
			crawlStorageFolder.appendChild(doc.createTextNode("/data/crawl/root"));
			rootElement.appendChild(crawlStorageFolder);
			
			// number of crawlers
			Element numberOfCrawler = doc.createElement("numberOfCrawler");
			numberOfCrawler.appendChild(doc.createTextNode("3"));
			rootElement.appendChild(numberOfCrawler);
			
			Element crawlers = doc.createElement("crawlers");
			rootElement.appendChild(crawlers);
			
			// first crawler
			Element crawler_1 = doc.createElement("crawler");
			crawlers.appendChild(crawler_1);
			
			Element maxPagesToFetch_1 = doc.createElement("maxPagesToFetch");
			maxPagesToFetch_1.appendChild(doc.createTextNode("1000"));
			
			Element individualFolder_1 = doc.createElement("individualFolder");
			individualFolder_1.appendChild(doc.createTextNode("crawler1"));
			
			Element delay_1 = doc.createElement("delay");
			delay_1.appendChild(doc.createTextNode("1000"));
			
			Element crawlersDomain_1 = doc.createElement("crawlersDomain");					
			crawlersDomain_1.appendChild(doc.createElement("domain").appendChild(doc.createTextNode("http:///www.ics.uci.edu/")));
			crawlersDomain_1.appendChild(doc.createElement("domain").appendChild(doc.createTextNode("http://www.cnn.com/")));
			
			Element concurrentThreads_1 = doc.createElement("concurrentThreads");
			concurrentThreads_1.appendChild(doc.createTextNode("5"));
			
			Element domainSeeders_1 = doc.createElement("domainSeeders"); 
			domainSeeders_1.appendChild(doc.createElement("seeder").appendChild(doc.createTextNode("http:///www.ics.uci.edu/")));
			domainSeeders_1.appendChild(doc.createElement("seeder").appendChild(doc.createTextNode("http://www.cnn.com/")));
			domainSeeders_1.appendChild(doc.createElement("seeder").appendChild(doc.createTextNode("http://www.ics.uci.edu/~lopes/")));
			domainSeeders_1.appendChild(doc.createElement("seeder").appendChild(doc.createTextNode("http://www.cnn.com/POLITICS/")));
			
			Element maxDepth_1 = doc.createElement("maxDepth");
			maxDepth_1.appendChild(doc.createTextNode("-1"));
			
			Element includeBinary_1 = doc.createElement("includeBinary");
			includeBinary_1.appendChild(doc.createTextNode("1"));
			
			Element maxTimeoutSocket_1 = doc.createElement("maxTimeoutSocket");
			maxTimeoutSocket_1.appendChild(doc.createTextNode("10000"));
			
			Element maxTimeoutConnection_1 = doc.createElement("maxTimeoutConnection");
			maxTimeoutConnection_1.appendChild(doc.createTextNode("10000"));
			
			crawler_1.appendChild(maxPagesToFetch_1);
			crawler_1.appendChild(maxDepth_1);
			crawler_1.appendChild(maxTimeoutConnection_1);
			crawler_1.appendChild(maxTimeoutSocket_1);
			crawler_1.appendChild(includeBinary_1);
			crawler_1.appendChild(individualFolder_1);
			crawler_1.appendChild(domainSeeders_1);
			crawler_1.appendChild(concurrentThreads_1);
			crawler_1.appendChild(delay_1);
			crawler_1.appendChild(crawlersDomain_1);
			
			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("file.xml"));

			// Output to console for testing
			// StreamResult result = new StreamResult(System.out);

			transformer.transform(source, result);

			System.out.println("File saved!");

		  } catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		  } catch (TransformerException tfe) {
			tfe.printStackTrace();
		  }		
//		Properties prop = new Properties();
//		OutputStream output = null;
//
//		try {
//			output = new FileOutputStream("config.properties");
//
//			// set the properties value to connect solr server
//			prop.setProperty("serverhost", "localhost");
//			prop.setProperty("port", "8983");
//			prop.setProperty("core", "new_core");
//			prop.setProperty("solrPath", "solr");
//			
//			prop.setProperty("crawlStorageFolder", "/data/crawl/root");
//			
//			// set the properties for multiple page crawler
//			prop.setProperty("numberOfCrawler", "3");
//			prop.setProperty("maxPagesToFetch", "30000|30000|30000");
//			prop.setProperty("individualFolder", "crawler1|crawler2|crawler3");
//			prop.setProperty("delay", "1000|1000|1000");
//			prop.setProperty("crawlersDomain", "http:///www.ics.uci.edu/ http://www.cnn.com/|http://en.wikipedia.org/|http://www2.uet.vnu.edu.vn/");
//			prop.setProperty("concurrentThreads", "5|5|5");
//			prop.setProperty("domainSeeders", "http://www.ics.uci.edu/ http://www.cnn.com/ http://www.ics.uci.edu/~lopes/ http://www.cnn.com/POLITICS/|http://en.wikipedia.org/wiki/Main_Page http://en.wikipedia.org/wiki/Obama http://en.wikipedia.org/wiki/Bing|http://www2.uet.vnu.edu.vn/" );
//			prop.setProperty("maxDepth", "-1|-1|-1");
//			prop.setProperty("includeBinaryContent", "1|0|1");
//			// save properties to project root folder
//			prop.store(output, null);
//
//		} catch (IOException io) {
//			io.printStackTrace();
//		} finally {
//			if (output != null) {
//				try {
//					output.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}	  
	}
	
	public Property getProperty() {
		Properties prop = new Properties();
		InputStream input = null;
		Property customProp = new Property();
		try {

			input = new FileInputStream("config.properties");

			// load a properties file
			prop.load(input);

			// get the property value and print it out
			String host = prop.getProperty("host");
			String port = prop.getProperty("port");
			String core = prop.getProperty("core");
			String solrPath = prop.getProperty("solrPath");		
			customProp.setProperty(host, port, core, solrPath);			

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return customProp;
	}	
}

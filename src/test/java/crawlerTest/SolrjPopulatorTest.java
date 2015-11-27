package crawlerTest;

import java.io.IOException;
import java.util.List;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;

import helperTest.ConfigHandlerTest;
import helperTest.PropertyTest;

public class SolrjPopulatorTest {
	private static SolrjPopulatorTest instance = null;
	private HttpSolrServer server;
	
	private static ConfigHandlerTest configHandler = ConfigHandlerTest.getInstance();
	private static PropertyTest prop = configHandler.getConfigInfo();
	
	private SolrjPopulatorTest() {
		String solrServer = prop.getFullServerHost();		
		server = new HttpSolrServer(solrServer);
	}
		
	public static SolrjPopulatorTest getInstance() {
		if(instance == null) instance = new SolrjPopulatorTest();
		return instance;
	}
	
	public void addNewDocument(SolrInputDocument doc) throws SolrServerException, IOException {
		server.add(doc);
	}
	
	public void addNewDocument(List<SolrInputDocument> docList) {
		try {
			server.add(docList);
			server.commit();
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void serverCommit() throws SolrServerException, IOException {
		server.commit();
	}	
}

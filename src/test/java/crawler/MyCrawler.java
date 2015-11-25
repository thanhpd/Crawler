package crawler;

import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.solr.common.SolrInputDocument;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

public class MyCrawler extends WebCrawler {
	private SolrjPopulator server = SolrjPopulator.getInstance();
	public static int i = 0;
    private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|gif|jpg"
                                                           + "|png|mp3|mp3|zip|gz))$");
    
    public static int getCounter(){
    	return i;
    }
    private String[] myCrawlDomains;

    @Override
    public void onStart() {
      myCrawlDomains = (String[]) myController.getCustomData();
    }
    /**
     * This method receives two parameters. The first parameter is the page
     * in which we have discovered this new url and the second parameter is
     * the new url. You should implement this function to specify whether
     * the given url should be crawled or not (based on your crawling logic).
     * In this example, we are instructing the crawler to ignore urls that
     * have css, js, git, ... extensions and to only accept urls that start
     * with "http://www.ics.uci.edu/". In this case, we didn't need the
     * referringPage parameter to make the decision.
     */
     @Override
     public boolean shouldVisit(Page referringPage, WebURL url) {
    	 String href = url.getURL().toLowerCase();
    	    if (FILTERS.matcher(href).matches()) {
    	      return false;
    	    }

    	    for (String crawlDomain : myCrawlDomains) {
    	      if (href.startsWith(crawlDomain)) {
    	        return true;
    	      }
    	    }

    	    return false;                  
     }

     /**
      * This function is called when a page is fetched and ready
      * to be processed by your program.
      */
     @Override
     public void visit(Page page) {
    	 i++;
    	 int docid = page.getWebURL().getDocid();
    	 String url = page.getWebURL().getURL();
    	 int parentDocid = page.getWebURL().getParentDocid();
         
         logger.debug("Docid: {}", page.getWebURL().getDocid());
         logger.info("URL: {}", url);
         logger.debug("Docid of parent page: {}", parentDocid);
         
         if (page.getParseData() instanceof HtmlParseData) {        	 
             HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();             
             String text = htmlParseData.getText();
             String html = htmlParseData.getHtml();
             String title = htmlParseData.getTitle();
             Set<WebURL> links = htmlParseData.getOutgoingUrls();                         
             
//             System.out.println("Text length: " + text.length());
//             System.out.println("Html length: " + html.length());
//             System.out.println("Number of outgoing links: " + links.size());
             
             RawDocument document = new RawDocument();
             document.setLink(url);
             document.setTitle(title);
             document.setContent(text);
             
             List<SolrInputDocument> result = SolrDocument.createSolrDocument(document);
             server.addNewDocument(result);             
         }
    }
     
     
}

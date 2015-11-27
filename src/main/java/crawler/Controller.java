package crawler;

import java.util.ArrayList;
import java.util.List;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import helper.ConfigHandler;
import helper.Crawler;
import helper.Property;


public class Controller {	
	private static ConfigHandler configHandler = ConfigHandler.getInstance();
	private static Property prop = configHandler.getConfigInfo();
	
    public static void main(String[] args) throws Exception {    	
        String crawlStorageFolder = prop.getStorageFolder();
        int numberOfTotalCrawlers = prop.getNumberOfCrawlers();        
        
        CrawlConfig[] crawlConfig = new CrawlConfig[numberOfTotalCrawlers];
        PageFetcher[] pageFetcher = new PageFetcher[numberOfTotalCrawlers];
        CrawlController[] controller = new CrawlController[numberOfTotalCrawlers];
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer;
        
        for(int i = 0; i < numberOfTotalCrawlers; i++) {
        	crawlConfig[i] = new CrawlConfig();
        	Crawler crawler = prop.crawlers.get(i);
        	
        	crawlConfig[i].setCrawlStorageFolder(crawlStorageFolder + "/" + crawler.getIndividualFolder());
        	crawlConfig[i].setPolitenessDelay(crawler.getDelay());
        	crawlConfig[i].setMaxPagesToFetch(crawler.getMaxPagesToFetch());
        	crawlConfig[i].setConnectionTimeout(crawler.getMaxTimeoutConnection());
        	crawlConfig[i].setSocketTimeout(crawler.getMaxTimeoutSocket());
        	crawlConfig[i].setMaxDepthOfCrawling(crawler.getMaxDepth());
        	crawlConfig[i].setIncludeBinaryContentInCrawling(crawler.isIncludeBinary());
        	crawlConfig[i].setIncludeHttpsPages(true);
        	
        	pageFetcher[i] = new PageFetcher(crawlConfig[i]);
        	robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher[0]);
        	
        	controller[i] = new CrawlController(crawlConfig[i], pageFetcher[i], robotstxtServer);
        	List<String> domain = crawler.getCrawlersDomain();
        	String[] array = domain.toArray(new String[domain.size()]);
        	controller[i].setCustomData(array);
        	
        	List<String> seeders = new ArrayList<>();
        	seeders = crawler.getDomainSeeders();
        	
        	for(int j = 0; j < seeders.size(); j++) {
        		controller[i].addSeed(seeders.get(j));
        	}
        }
        
        /*
         * Start the crawl. This is a blocking operation, meaning that your code
         * will reach the line after this only when crawling is finished.
         */
        for(int i = 0; i < numberOfTotalCrawlers; i++) {
        	controller[i].startNonBlocking(MyCrawler.class, prop.crawlers.get(i).getConcurrentThreads());
        }
        
        // Wait for 30 seconds
        Thread.sleep(30 * 1000);
        
        for(int i = 0; i < numberOfTotalCrawlers; i++) {
        	controller[i].waitUntilFinish();
        }                               
    }
}

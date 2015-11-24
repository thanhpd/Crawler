package crawler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Property {
	private String serverHost;
	private String port;
	private String core;
	private String solrPath;
	private String crawlStorageFolder;
	private int numberOfCrawlers;
	private List<Integer> maxPagesToFetch = new ArrayList<>();
	private List<Integer> delay = new ArrayList<>();
	private List<Integer> concurrentThreads = new ArrayList<>();
	private List<Integer> maxDepth = new ArrayList<>();
	private List<Boolean> isIncludeBinary = new ArrayList<>();
	private List<String> individualFolder = new ArrayList<>();
	private List<String> crawlersDomain = new ArrayList<>();
	private List<String> domainSeeders = new ArrayList<>();
	
	public Property() {
		
	}
	
	public Property(String host, String port, String core, String solrPath) {
		this.serverHost = host;
		this.port = port;
		this.core = core;
		this.solrPath = solrPath;
	}
	
	public void setProperty(String host, String port, String core, String solrPath) {
		this.serverHost = host;
		this.port = port;
		this.core = core;
		this.solrPath = solrPath;
	}
	
	public void setServerHost(String serverHost) {
		this.serverHost = serverHost;
	}
	
	public void setPort(String port) {
		this.port = port;
	}
	
	public void setCore(String coreName) {
		this.core = coreName;
	}
	
	public void setSolrPath(String solrPath) {
		this.solrPath = solrPath;
	}
	
	public void setStorageFolder(String input) {
		this.crawlStorageFolder = input;
	}
	
	public void setNumberOfCrawlers(String numberOfCrawlers) {
		this.numberOfCrawlers = Integer.parseInt(numberOfCrawlers);
	}
	
	public void setMaxPagesToFetch(String input) {		
		List<String> result = Arrays.asList(input.split("|"));
		for(String s : result) this.maxPagesToFetch.add(Integer.parseInt(s));
	}
	
	public void setDelay(String input) {
		List<String> result = Arrays.asList(input.split("|"));
		for(String s : result) this.delay.add(Integer.parseInt(s));
	}
	
	public void setConcurrentThreads(String input) {
		List<String> result = Arrays.asList(input.split("|"));
		for(String s : result) this.concurrentThreads.add(Integer.parseInt(s));
	}
	
	public void setMaxDepth(String input) {
		List<String> result = Arrays.asList(input.split("|"));
		for(String s : result) this.maxDepth.add(Integer.parseInt(s));
	}
	
	public void setIncludeBinary(String input) {
		List<String> result = Arrays.asList(input.split("|"));
		for(String s : result) this.maxDepth.add(Integer.parseInt(s));
	}
	
	public void setIndividualFolder(String input) {
		this.individualFolder = Arrays.asList(input.split("|"));		
	}
	
	public void setCrawlersDomain(String input) {
		this.crawlersDomain = Arrays.asList(input.split("|"));
	}
	
	public void setDomainSeeders(String input) {
		this.domainSeeders = Arrays.asList(input.split("|"));
	}
	
	public String getServerHost() {
		return this.serverHost;
	}
	
	public String getPort() {
		return this.port;
	}
	
	public String getCore() {
		return this.core;
	}
	
	public String getSolrPath() {
		return this.solrPath;
	}
	
	public String getFullServerHost() {
		String result = "http://" + serverHost + ":" + port + "/" + solrPath + "/" + core;
		return result;
	}
	
	public String getStorageFolder() {
		return this.crawlStorageFolder;
	}
	
	public int getNumberOfCrawlers() {
		return this.numberOfCrawlers;
	}
	
	public List<Integer> getMaxPagesToFetch() {
		return this.maxPagesToFetch;
	}
	
	public List<Integer> getDelay() {
		return this.delay;		
	}
	
	public List<Integer> getConcurrentThreads() {
		return this.concurrentThreads;
	}
	
	public List<Integer> getMaxDepth() {
		return this.maxDepth;
	}
	
	public List<String> getIndividualFolder() {
		return this.individualFolder;
	}
	
}

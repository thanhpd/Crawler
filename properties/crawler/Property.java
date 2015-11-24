package crawler;

public class Property {
	private String serverHost;
	private String port;
	private String core;
	private String solrPath;
	
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
}

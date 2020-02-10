package org.advancelab.citizensdata.model;

public class Links {

	private String rel;
	private String url;
	
	public Links(String rel, String url) {
		super();
		this.rel = rel;
		this.url = url;
	}
	public String getRel() {
		return rel;
	}
	public String getUrl() {
		return url;
	}public void setRel(String rel) {
		this.rel = rel;
	}public void setUrl(String url) {
		this.url = url;
	}
}

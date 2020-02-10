package org.advancelab.citizensdata.resourcebeans;

import javax.ws.rs.QueryParam;

public class FilterBeans {
	
	private @QueryParam("end") int end;
	private @QueryParam("start") int start;
	private @QueryParam("birthYear") int birthYear;
	private @QueryParam("catagory") String catagory;
	
	
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	public int getBirthYear() {
		return birthYear;
	}
	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}
	public String getCatagory() {
		return catagory;
	}
	public void setCatagory(String catagory) {
		this.catagory = catagory;
	}
}

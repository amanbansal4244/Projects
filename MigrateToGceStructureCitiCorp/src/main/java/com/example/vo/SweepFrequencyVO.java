package com.example.vo;

public class SweepFrequencyVO {

	/** Start: Table Specific Fields **/
	private Long id;
	private String fileName;
	/** End : Table Specific Fields **/
	
	/** Start: Input file Fields **/
	private String brn;
	private String acc;
	private String ccy;
	
	/** End: Input file Fields **/
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public String getBrn() {
		return brn;
	}
	public void setBrn(String brn) {
		this.brn = brn;
	}
	public String getAcc() {
		return acc;
	}
	public void setAcc(String acc) {
		this.acc = acc;
	}
	public String getCcy() {
		return ccy;
	}
	public void setCcy(String ccy) {
		this.ccy = ccy;
	}
	
}

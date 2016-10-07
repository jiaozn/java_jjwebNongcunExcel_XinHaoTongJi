package com.jjweb.util;

public class ExcelCell {
	private long r;
	public long getR() {
		return r;
	}
	public void setR(long r) {
		this.r = r;
	}
	public long getC() {
		return c;
	}
	public void setC(long c) {
		this.c = c;
	}
	public String getEvalue() {
		return evalue;
	}
	public void setEvalue(String evalue) {
		this.evalue = evalue;
	}
	private long c;
	private String evalue;
}

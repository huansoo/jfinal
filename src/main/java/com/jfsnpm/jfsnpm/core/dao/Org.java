package com.jfsnpm.jfsnpm.core.dao;

public class Org {
	private String orgId;
	private String pOrgId;
	private String text;
	private String sortNo;
	private String status;
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getSortNo() {
		return sortNo;
	}
	public void setSortNo(String sortNo) {
		this.sortNo = sortNo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getpOrgId() {
		return pOrgId;
	}
	public void setpOrgId(String pOrgId) {
		this.pOrgId = pOrgId;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	
}

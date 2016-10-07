package com.jjweb.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "nongcun", catalog = "jjweb")
public class Nongcun {
	private String beizhu;
	private String dishi;
	private String editor;
	private long id;
	private String quxian;
	private String shinei2g;
	private String shinei3g;
	private String shinei4g;
	private String shiwai2g;
	private String shiwai3g;
	private String shiwai4g;
	private Timestamp time;
	private String xiangzhen;
	private String xiangzhenleixing;
	private String xingzhengcunming;
	@Column(name = "beizhu", nullable = true, length = 255)
	public String getBeizhu() {
		return beizhu;
	}
	@Column(name = "dishi", nullable = false, length = 255)
	public String getDishi() {
		return dishi;
	}
	@Column(name = "editor", nullable = true, length = 255)
	public String getEditor() {
		return editor;
	}
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public long getId() {
		return id;
	}
	@Column(name = "quxian", nullable = false, length = 255)
	public String getQuxian() {
		return quxian;
	}
	@Column(name = "shinei2g", nullable = true, length = 255)
	public String getShinei2g() {
		return shinei2g;
	}
	@Column(name = "shinei3g", nullable = true, length = 255)
	public String getShinei3g() {
		return shinei3g;
	}
	@Column(name = "shinei4g", nullable = true, length = 255)
	public String getShinei4g() {
		return shinei4g;
	}
	@Column(name = "shiwai2g", nullable = true, length = 255)
	public String getShiwai2g() {
		return shiwai2g;
	}
	@Column(name = "shiwai3g", nullable = true, length = 255)
	public String getShiwai3g() {
		return shiwai3g;
	}
	@Column(name = "shiwai4g", nullable = true, length = 255)
	public String getShiwai4g() {
		return shiwai4g;
	}
	@Column(name = "time", nullable = true, length = 255)
	public Timestamp getTime() {
		return time;
	}
	@Column(name = "xiangzhen", nullable = true, length = 255)
	public String getXiangzhen() {
		return xiangzhen;
	}
	@Column(name = "xiangzhenleixing", nullable = true, length = 255)
	public String getXiangzhenleixing() {
		return xiangzhenleixing;
	}
	@Column(name = "xingzhengcunming", nullable = true, length = 255)
	public String getXingzhengcunming() {
		return xingzhengcunming;
	}
	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
	}
	public void setDishi(String dishi) {
		this.dishi = dishi;
	}
	public void setEditor(String editor) {
		this.editor = editor;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setQuxian(String quxian) {
		this.quxian = quxian;
	}
	
	public void setShinei2g(String shinei2g) {
		this.shinei2g = shinei2g;
	}
	public void setShinei3g(String shinei3g) {
		this.shinei3g = shinei3g;
	}
	public void setShinei4g(String shinei4g) {
		this.shinei4g = shinei4g;
	}
	public void setShiwai2g(String shiwai2g) {
		this.shiwai2g = shiwai2g;
	}
	public void setShiwai3g(String shiwai3g) {
		this.shiwai3g = shiwai3g;
	}
	public void setShiwai4g(String shiwai4g) {
		this.shiwai4g = shiwai4g;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public void setXiangzhen(String xiangzhen) {
		this.xiangzhen = xiangzhen;
	}
	public void setXiangzhenleixing(String xiangzhenleixing) {
		this.xiangzhenleixing = xiangzhenleixing;
	}
	public void setXingzhengcunming(String xingzhengcunming) {
		this.xingzhengcunming = xingzhengcunming;
	}
}

package com.jjweb.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONArray;

import com.jjweb.model.Nongcun;
import com.jjweb.model.NongcunDAO;
import com.opensymphony.xwork2.ActionSupport;

public class GetNongcunAll extends ActionSupport{
	private String result;
	private List<Nongcun> listNongcun;
	@Resource
	private NongcunDAO nongcunDAO;
	public String execute(){
		try {listNongcun=nongcunDAO.findAll();
		JSONArray arr=JSONArray.fromObject(listNongcun);
		System.out.println(arr.toString());
		HttpServletResponse response=ServletActionContext.getResponse();
		  response.setContentType("text/html; charset=utf-8");
		PrintWriter out;
		out = ServletActionContext.getResponse().getWriter();
        out.println(arr.toString());
        out.flush(); 
        out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		return SUCCESS;
		
	}
}

package com.jjweb.action;

import javax.annotation.Resource;

import com.jjweb.model.User;
import com.jjweb.model.UserDAO;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport{
	@Resource
	private UserDAO userDAO;
	private User user;
	
	public String execute(){
		if(userDAO.checkUser(user)){
			user.setAuth(5);
			if (user.getName()=="admin"){
				user.setAuth(7);
			}
			ActionContext.getContext().getSession().put("user", user);
			return SUCCESS;
		}else{
			return "failed";
			}
	}
	
	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String user_logout(){
		ActionContext.getContext().getSession().clear();
		return SUCCESS;
	}
}

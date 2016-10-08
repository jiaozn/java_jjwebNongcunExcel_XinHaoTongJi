package com.jjweb.model;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class UserDAO extends HibernateDaoSupport{
	public boolean checkUser(User user){
		
		try{String queryString = "from User n where n.name=? and n.password=?";
		 List<User> listUser=getHibernateTemplate().find(queryString, new String[]{user.getName(),user.getPassword()});
		 System.out.println("listUser"+"-"+listUser.isEmpty());
		 if(!listUser.isEmpty()){
			 return true;
		 }else{
			 return false;
		 }
		}catch(Exception e){
			return false;
		}
	}
	
	
	public void save(User transientInstance) {
		try {
			getHibernateTemplate().save(transientInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}
}

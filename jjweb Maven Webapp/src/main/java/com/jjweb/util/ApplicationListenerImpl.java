package com.jjweb.util;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Repository;


import com.jjweb.model.Nongcun;
import com.jjweb.model.NongcunDAO;
import com.jjweb.model.User;
import com.jjweb.model.UserDAO;

//import com.jjweb.model.Category;
//import com.jjweb.model.Introduction;
//import com.jjweb.model.IntroductionDAO;
//import com.jjweb.model.User;
//import com.jjweb.service.CategoryService;
//import com.jjweb.service.IntroductionService;
//import com.jjweb.service.UserService;

@Repository
// ����Spring������������Զ�ɨ�����bean�ķ�ʽ������xml����һ������
public class ApplicationListenerImpl implements ApplicationListener {
	@Resource
	private NongcunDAO nongcunDAO;
	@Resource
	private UserDAO userDAO;
	@Override
	public void onApplicationEvent(ApplicationEvent arg0) {
		// TODO Auto-generated method stub
		
//		List list=nongcunDAO.findAll();
//		if(list.size()<=0){
//		Nongcun nongcun=new Nongcun();
//		nongcun.setDishi("̩��");
//		nongcun.setQuxian("�����");
//		nongcun.setXiangzhen("��ҵ���");
//		nongcun.setTime(new Timestamp(new Date().getTime()));
//		nongcunDAO.save(nongcun);
//		}
		
		
		User user=new User();
		user.setName("admin");
		user.setPassword("admin");
		user.setAuth(7);
		userDAO.save(user);
		
		user.setName("dongqu");
		user.setPassword("ta123456");
		user.setAuth(5);
		userDAO.save(user);
	
		user.setName("xiqu");
		user.setPassword("ta123456");
		user.setAuth(5);
		userDAO.save(user);
		
		user.setName("daiyue");
		user.setPassword("ta123456");
		user.setAuth(5);
		userDAO.save(user);
		
		user.setName("feicheng");
		user.setPassword("ta123456");
		user.setAuth(5);
		userDAO.save(user);
		
		user.setName("xintai");
		user.setPassword("ta123456");
		user.setAuth(5);
		userDAO.save(user);
		
		user.setName("dongping");
		user.setPassword("ta123456");
		user.setAuth(5);
		userDAO.save(user);
		
		user.setName("ningyang");
		user.setPassword("ta123456");
		user.setAuth(5);
		userDAO.save(user);
		
//		Category category=new Category();
//		category.setName("��ҳ����");
//		category.setArticals(null);
//		category.setId(1);
//		categoryService.save(category);
//		
//		category.setId(2);
//		category.setArticals(null);
//		category.setName("���ѧϰ");
//		categoryService.save(category);
//		
//		Introduction introduction=new Introduction();
//		introduction.setId(1);
//		introduction.setAccess("0");
//		introduction.setContent(
//				"<p>This is a JWeb application!</p>" +
//				"<p>Init Introduction by Jiaozn.</p>");
//		introduction.setTime(new Timestamp(new Date().getTime()));
//		introductionDAO.merge(introduction);
	}
	public NongcunDAO getNongcunDAO() {
		return nongcunDAO;
	}
	public void setNongcunDAO(NongcunDAO nongcunDAO) {
		this.nongcunDAO = nongcunDAO;
	}

//	public UserService getUserService() {
//		return userService;
//	}
//
//	public void setUserService(UserService userService) {
//		this.userService = userService;
//	}
}
package com.jjweb.model;

import java.sql.SQLException;
import java.util.List;


import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.type.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class NongcunDAO extends HibernateDaoSupport{
	private static final Logger log = LoggerFactory
			.getLogger(NongcunDAO.class);
	// property constants
	public static final String CONTENT = "content";

	protected void initDao() {
		// do nothing
	}

	public void save(Nongcun transientInstance) {
		log.debug("saving Nongcun instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	
	public void saveOrUpdateOnName(Nongcun nongcun){
		String queryString = "from Nongcun n where n.xiangzhen=? and n.xingzhengcunming=?";
		List<Nongcun> listNongcun=getHibernateTemplate().find(queryString, new String[]{nongcun.getXiangzhen(),nongcun.getXingzhengcunming()});
		if (listNongcun==null || listNongcun.size()==0){
			this.save(nongcun);
		}else{
			nongcun.setId(listNongcun.get(0).getId());
			this.merge(nongcun);
		}
	}
	
	
	public void delete(Nongcun persistentInstance) {
		log.debug("deleting Nongcun instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Nongcun findById(long id) {
		log.debug("getting Nongcun instance with id: " + id);
		try {
			Nongcun instance = (Nongcun) getHibernateTemplate().get(
					"com.jjweb.model.Nongcun", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Nongcun> findByExample(Nongcun instance) {
		log.debug("finding Nongcun instance by example");
		try {
			List<Nongcun> results = (List<Nongcun>) getHibernateTemplate()
					.findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Nongcun instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Nongcun as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Nongcun> findByContent(Object content) {
		return findByProperty(CONTENT, content);
	}

	public List findAll() {
		log.debug("finding all Nongcun instances");
		try {
			String queryString = "from Nongcun";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Nongcun merge(Nongcun detachedInstance) {
		log.debug("merging Nongcun instance");
		try {
			Nongcun result = (Nongcun) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Nongcun instance) {
		log.debug("attaching dirty Nongcun instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Nongcun instance) {
		log.debug("attaching clean Nongcun instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	

	public static NongcunDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (NongcunDAO) ctx.getBean("NongcunDAO");
	}
	
	
	
	public PageBean listpage(final int pagenum) {
		final String hql = "from Nongcun order by id desc";
		int num = 0; //定义整个数据库中的信息记录数
		int allpage = 0; //定义总页数
		final PageBean pageBean = new PageBean();
		num = (getHibernateTemplate().find(hql)).size(); //先计算出整个数据记录信息
		pageBean.setAllRow(num); //把记录数保存到pagebean中的allrow(总记录数)里面
		pageBean.setPageSize(10); //设置每页显示几条信息记录
		allpage = PageBean.countTotalPage(pageBean.getPageSize(), pageBean.getAllRow()); //根据每页显示几条和总条数计算出，要用多少页来显示
		pageBean.setTotalPage(allpage);
		if(allpage<pagenum) //如果用户根据地址栏强制输入第几页，那么如果大于总页数，就要把总页数赋值给当前页，否则把传递过来的页数赋值给当前页数
		{
		pageBean.setCurrentPage(allpage);
		}
		else
		{
		pageBean.setCurrentPage(PageBean.countCurrentPage(pagenum));
		}
		pageBean.setOffset(PageBean.countOffset(pageBean.getPageSize(),pageBean.getCurrentPage())); //根据每页显示的信息条数和当前页数，计算当前页开始记录号
		 
		if(pageBean.getTotalPage()>0)
		{
		//首页判断
		//前一页判断
		//满足条件：当当前页为第一页的时候首页和前一页不可用，否则可以使用
		if(pageBean.getCurrentPage() == 1)
		{
		pageBean.setFirstPage(false);
		pageBean.setHasPreviousPage(false);
		}
		else {
		pageBean.setFirstPage(true);
		pageBean.setHasPreviousPage(true);
		}
		 
		//后一页判断
		//尾页判断
		if(pageBean.getTotalPage() == pageBean.getCurrentPage())
		{
		pageBean.setHasNextPage(false);
		pageBean.setLastPage(false);
		}
		else {
		pageBean.setHasNextPage(true);
		pageBean.setLastPage(true);
		}
		 
		}
		 
		//通过回调函数把Goods根据条件查询的集合放到PageBean中的List里面
		pageBean.setList
				(getHibernateTemplate().executeFind(new HibernateCallback()
				{public Object doInHibernate(Session session) throws HibernateException, SQLException //捕捉异常处理：hibernate异常或者SQL异常
					{Query query = session.createQuery(hql);
					query.setFirstResult(pageBean.getOffset());
					query.setMaxResults(pageBean.getPageSize());
					List list =query.list();
					return list;
					}
				}
													)
						);
		return pageBean;
		}
	
	public List searchByKey2(String key){
		List<Nongcun> listNongcun;
		if (key=="" ){
			String queryString = "from Nongcun n where n.dishi LIKE ? or n.quxian LIKE ? or n.xiangzhen LIKE ? or n.xiangzhenleixing LIKE ? or n.xingzhengcunming LIKE ? or n.shinei2g LIKE ? or n.shiwai2g LIKE ? or n.shinei3g LIKE ? or n.shiwai3g LIKE ? or n.shinei4g LIKE ? or n.shiwai4g LIKE ? or n.shifouguihua LIKE ? or n.guihuazhanming LIKE ?";
			listNongcun=getHibernateTemplate().find(queryString, new Object[]{key,key,key,key,key,key,key,key,key,key,key,key,key},new Type[]{Hibernate.STRING,Hibernate.STRING,Hibernate.STRING,Hibernate.STRING,Hibernate.STRING,Hibernate.STRING,Hibernate.STRING,Hibernate.STRING,Hibernate.STRING,Hibernate.STRING,Hibernate.STRING,Hibernate.STRING,Hibernate.STRING});
		}else{
			listNongcun=this.findAll();
		}
		return listNongcun;
	}
	
	public PageBean searchByKey(final int pagenum,String key) {
		//final String hql = "from Nongcun n where n.dishi LIKE ? or n.quxian LIKE ? or n.xiangzhen LIKE ? or n.xiangzhenleixing LIKE ? or n.xingzhengcunming LIKE ? or n.shinei2g LIKE ? or n.shiwai2g LIKE ? or n.shinei3g LIKE ? or n.shiwai3g LIKE ? or n.shinei4g LIKE ? or n.shiwai4g LIKE ? order by id desc";
		try{
			System.out.println("key="+key);
			final String hql;
		if (key.trim()=="" || key==null){
		hql = "from Nongcun order by id desc";
		}else{
		hql = "from Nongcun n where n.dishi like '%"+key+"%' or n.quxian like '%"+key+"%' or n.xiangzhen like '%"+key+"%' or n.xiangzhenleixing like '%"+key+"%' or n.xingzhengcunming like '%"+key+"%' or n.shinei2g like '%"+key+"%' or n.shiwai2g like '%"+key+"%' or n.shinei3g like '%"+key+"%' or n.shiwai3g like '%"+key+"%' or n.shinei4g like '%"+key+"%' or n.shiwai4g like '%"+key+"%' or n.shifouguihua like '%"+key+"%' or n.guihuazhanming like '%"+key+"%' order by id DESC";
		System.out.println(hql);
		}
		int num = 0; //定义整个数据库中的信息记录数
		int allpage = 0; //定义总页数
		final PageBean pageBean = new PageBean();
		//num = (getHibernateTemplate().find(hql,new String[]{key,key,key,key,key,key,key,key,key,key,key})).size(); //先计算出整个数据记录信息
		System.out.println("第一次查询取数字开始");
		num = (getHibernateTemplate().find(hql)).size(); //先计算出整个数据记录信息
		System.out.println("第一次查询取数字结束"+num);
		pageBean.setAllRow(num); //把记录数保存到pagebean中的allrow(总记录数)里面
		pageBean.setPageSize(10); //设置每页显示几条信息记录
		allpage = PageBean.countTotalPage(pageBean.getPageSize(), pageBean.getAllRow()); //根据每页显示几条和总条数计算出，要用多少页来显示
		pageBean.setTotalPage(allpage);
		if(allpage<pagenum) //如果用户根据地址栏强制输入第几页，那么如果大于总页数，就要把总页数赋值给当前页，否则把传递过来的页数赋值给当前页数
		{
		pageBean.setCurrentPage(allpage);
		}
		else
		{
		pageBean.setCurrentPage(PageBean.countCurrentPage(pagenum));
		}
		pageBean.setOffset(PageBean.countOffset(pageBean.getPageSize(),pageBean.getCurrentPage())); //根据每页显示的信息条数和当前页数，计算当前页开始记录号
		 
		if(pageBean.getTotalPage()>0)
		{
		//首页判断
		//前一页判断
		//满足条件：当当前页为第一页的时候首页和前一页不可用，否则可以使用
		if(pageBean.getCurrentPage() == 1)
		{
		pageBean.setFirstPage(false);
		pageBean.setHasPreviousPage(false);
		}
		else {
		pageBean.setFirstPage(true);
		pageBean.setHasPreviousPage(true);
		}
		 
		//后一页判断
		//尾页判断
		if(pageBean.getTotalPage() == pageBean.getCurrentPage())
		{
		pageBean.setHasNextPage(false);
		pageBean.setLastPage(false);
		}
		else {
		pageBean.setHasNextPage(true);
		pageBean.setLastPage(true);
		}
		 
		}
		 
		//通过回调函数把Goods根据条件查询的集合放到PageBean中的List里面
//		pageBean.setList
//				(getHibernateTemplate().executeFind(new HibernateCallback()
//				{public Object doInHibernate(Session session) throws HibernateException, SQLException //捕捉异常处理：hibernate异常或者SQL异常
//					{Query query = session.createQuery(hql);
//					query.setString("xiangzhen",key);
//					query.setString("xingzhengcunming",key);
//					query.setFirstResult(pageBean.getOffset());
//					query.setMaxResults(pageBean.getPageSize());
//					List list =query.list();
//					return list;
//					}
//				}
//													)
//						);
		
		
			  
	           //final String hql = "form KnowledgeExpertArticle as model where model.'"+keyName+"' like '%"+value+"%' order by model.expertArticleId DESC";  
	  
	       //    log.info(hql);  
	           List resultList = getHibernateTemplate().executeFind(  
	  
	              new HibernateCallback() {  
	  
	                  public Object doInHibernate(Session ss)  
	  
	                         throws HibernateException, SQLException {  
	                	  System.out.println("第er次查询取数字开始");
	                     Query query = ss.createQuery(hql);  
	  
	                     query.setFirstResult(pageBean.getOffset());  
	  
	                     query.setMaxResults(pageBean.getPageSize());  
	                     System.out.println("第er次查询取数字jieshu");
	                     return query.list();  
	  
	                  }  
	  
	   
	  
	              });  
	  
	           pageBean.setList(resultList);
	           return pageBean;
	       }catch(RuntimeException re){  
	  
	           throw re;  
	  
	       }  
		
		
		}
	
}

package com.jjweb.model;

import java.util.List;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
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
}

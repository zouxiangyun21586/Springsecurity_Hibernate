package com.yr.h_dao.impl;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yr.entity.Page;
import com.yr.entity.User;
import com.yr.h_dao.UserDao;

@Repository
public class UserDaoImpl implements UserDao{
	
	@Autowired
	private SessionFactory sessionFactory;

	private Session entityManager;
	/**
	 * 获取和当前线程绑定的Session
	 * 
	 * @return
	 */
	private Session getSession() {
		return sessionFactory.getCurrentSession(); // openSession不能自动关闭,且会有其他隐患错误,所以建议使用 getCurrentSession (可以自动关闭)
	}

	/**
	 * 将角色查出,并且将查出的角色赋权给刚添加的用户
	 * 
	 * @author zxy
	 * @param user
	 * 2018年4月14日 上午9:35:13 
	 *
	 */
	@Override
	public void add(User user) {
		entityManager = getSession();
		entityManager.save(user);
	}

	@Override
	public void update(User user) {
		entityManager = getSession();
		
		User u = new User();
		u.setId(user.getId());
		u.setAccount(user.getAccount());
		u.setName(user.getName());
		u.setPassword(user.getPassword());
		entityManager.merge(u);
		
	}

	@Override
	public void delete(Long id) {
		entityManager = getSession();
		
//		Query qy = entityManager.createNativeQuery("delete from zxy_user where id =" + id); // 使用本地sql删除
//		qy.executeUpdate();
//		Query q = entityManager.createQuery("delete from User where id = ?1").setParameter(1, id); // HQL 删除
//		q.executeUpdate();
		
		User user = (User)entityManager.get(User.class, id);
		entityManager.remove(user);
	}

	@Override
	public Page<User> select(Page<User> page){
		entityManager = getSession();
		//      10            5
		//  开始下标                         每页多少条记录                      第几页
		int startIndex = page.getPageSize() * (page.getPage()-1);
		int pageSize = page.getPageSize();
		Query q = entityManager.createQuery("from User ").setFirstResult(startIndex).setMaxResults(pageSize);
		
		//查询总数
		Query q1 = entityManager.createNativeQuery("select count(*) from securityuser");
		BigInteger count = (BigInteger) q1.getSingleResult();
		page.setPageSizeCount(count.intValue());
		
		List<User> listUser = q.getResultList();
		page.setT(listUser);
		return page;
	}
	
	@Override
	public User get(Long id){
		entityManager = getSession();
		Query q = entityManager.createQuery("from User where id = "+id);
		User listUser = (User) q.getSingleResult();
		return listUser;
	}

	@Override
	public List<User> select() {
		entityManager = getSession();
		Query q = entityManager.createQuery("from User");
		List<User> listUser = q.getResultList();
		return listUser;
	}

	@Override
	public User getUserByName(String name) {
		entityManager = getSession();
		Query q = entityManager.createQuery("from User where account = " + name);
		User user = (User) q.getSingleResult();
		return user;
	}

}
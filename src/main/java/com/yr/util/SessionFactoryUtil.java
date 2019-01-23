package com.yr.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * SessionFactory 工具类
 * @author zxy
 * 2018年4月17日 下午2:31:17 
 *
 */
public class SessionFactoryUtil {
	// 生成Session的工厂,构造一个会很消耗资源,所以一般应用下只初始化一个
	private static SessionFactory sessionFactory;
	
	static {
		try {
			// 属性文件（hibernate.properties）
			//
			/**
			 * 创建 Configuration 的两种方式
			 * 方式一：属性文件(hibernate.properties)
			 * 	Configuration cfg = new Configuration()
			 * 方式二：Xml文件 (hibernate.cfg.xml)
			 * 	Configuration cfg = new Configuration().configure();
			 * Configuration 的 configure 方法还支持带参数的访问:(此方法可以使用在将 hibernate.cfg.xml 文件改名后使用)
			 * eg:	File file = new File("ccc.xml"); // 此处的ccc.xml 就是 hibernate.cfg.xml 文件改名后的文件名
			 * 		Configuration cfg = new Configuration().configure(file);
			 */
			Configuration configuration = new Configuration().configure(); // 读取hibernate.cfg.xml 文件( Xml文件)
			// 建立 ServiceRegistry 对象
			// ServiceRegistry 接口是hibernate4新增的,所有基于hibernate的配置或者服务都必须统一向ServiceRegistry注册后才能生效
			sessionFactory = configuration.buildSessionFactory(); // 建立 SessionFactory 对象
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 打开session
	 * 
	 * 1) Session 是应用程序与数据库之间交互操作的一个单线程对象，是 Hibernate 运作的中心
	 * 2) 所有持久化对象必须在 session 的管理下才可以进行持久化操作
	 * 3) session的生命周期很短. Session 对象有一个一级缓存, 显式执行 flush 之前, 所有的
	 * 	     持久层操作的数据都缓存在 session 对象处. 相当于 JDBC 中的 Connection
	 * @return
	 */
	public static Session getSession() {
		return sessionFactory.openSession();
	}

	/**
	 * 关闭session
	 * 
	 * @param session
	 */
	public static void closeSession(Session session) {
		if (session != null) {
			if (session.isOpen()) {
				session.close();
			}
		}
	}

	/**
	 * 得到session
	 * 
	 * @return
	 */
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}

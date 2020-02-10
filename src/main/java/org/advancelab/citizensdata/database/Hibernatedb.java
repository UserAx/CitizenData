package org.advancelab.citizensdata.database;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.context.annotation.Bean;

public class Hibernatedb {
	
	private SessionFactory sessionFactory;

	@Bean
	public SessionFactory getSessionFactory() {
		return sessionFactory = new Configuration().configure().buildSessionFactory();
	}

}

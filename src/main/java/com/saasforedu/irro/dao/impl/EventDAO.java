package com.saasforedu.irro.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.saasforedu.irro.dao.IEventDAO;
import com.saasforedu.irro.model.impl.Event;

public class EventDAO extends HibernateDaoSupport implements IEventDAO {

	public void saveAll(List<Event> events) {
		getHibernateTemplate().saveOrUpdateAll(events);
	}

}

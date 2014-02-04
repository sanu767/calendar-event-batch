package com.saasforedu.irro.dao;

import java.util.List;

import com.saasforedu.irro.model.impl.Event;

public interface IEventDAO {

	public void saveAll(List<Event> events);

}
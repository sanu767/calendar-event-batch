package com.saasforedu.irro.parser;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.saasforedu.irro.service.EventService;
import com.saasforedu.irro.xmlelements.EventElement;
import com.saasforedu.irro.xmlelements.EventsRootElement;

public class XMLUnmarshaller {

	private EventService eventService;

	public void setEventService(EventService eventService) {
		this.eventService = eventService;
	}

	public void unmarshall(String filePath) {
		final List<EventElement> elements = new ArrayList<EventElement>();
		try {
			File folder = new File(filePath);
			for (final File file : folder.listFiles()) {
				if (isValid(file)) {

					JAXBContext jaxbContext = JAXBContext
							.newInstance(EventsRootElement.class);
					Unmarshaller jaxbUnmarshaller = jaxbContext
							.createUnmarshaller();
					EventsRootElement events = (EventsRootElement) jaxbUnmarshaller
							.unmarshal(file);

					for (EventElement elem : events.getListOfEvents()) {
						elements.add(elem);
					}
				}
			}
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		eventService.create(elements);
	}

	private boolean isValid(File file) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date lastMoified = sdf.parse(sdf.format(file.lastModified()));

			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.MONTH, -1);
			Date oneMonthBefore = cal.getTime();

			if (!file.isDirectory()
					&& lastMoified.compareTo(oneMonthBefore) > 0) {
				return true;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return false;
	}
}

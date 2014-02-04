package com.saasforedu.irro;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.saasforedu.irro.parser.XMLUnmarshaller;

public class BatchMain {

	public static void main(String args[]) {
		String filePath = args[0];

		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-service.xml");
		XMLUnmarshaller marshaller = (XMLUnmarshaller) ctx.getBean("xmlMarshaller", XMLUnmarshaller.class);
		marshaller.unmarshall(filePath);
	}
}

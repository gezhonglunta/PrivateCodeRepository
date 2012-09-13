package com.leftright.tiny.website.utils.applicationListener;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class TestListener implements ApplicationListener<TestEvent> {

	public void onApplicationEvent(TestEvent event) {
		System.out.println("TestEvent:" + event.getSource());
	}

}

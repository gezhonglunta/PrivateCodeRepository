package com.leftright.tiny.website.utils.applicationListener;

import org.springframework.context.ApplicationEvent;

@SuppressWarnings("serial")
public class TestEvent extends ApplicationEvent {

	public TestEvent(Object source) {
		super(source);
	}

}

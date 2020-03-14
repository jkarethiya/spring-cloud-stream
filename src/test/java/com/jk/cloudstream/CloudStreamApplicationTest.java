package com.jk.cloudstream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jk.cloudstream.model.LogMessage;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CloudStreamApplication.class)
@DirtiesContext
class CloudStreamApplicationTest {

	@Autowired
	private Processor pipe;

	@Autowired
	private MessageCollector messageCollector;

	@Test
	public void whenSendMessage_thenResponseShouldUpdateText() {
		pipe.input().send(MessageBuilder.withPayload(new LogMessage("This is my message")).build());

		Object payload = messageCollector.forChannel(pipe.output()).poll().getPayload();
	}

}

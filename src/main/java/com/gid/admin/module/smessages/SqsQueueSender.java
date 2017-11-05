package com.gid.admin.module.smessages;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSAsync;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.messaging.support.MessageBuilder;

public class SqsQueueSender {

	private final QueueMessagingTemplate queueMessagingTemplate;

	@Autowired
	public SqsQueueSender(AmazonSQSAsync amazonSqs) {
		this.queueMessagingTemplate = new QueueMessagingTemplate(amazonSqs);
	}

	public void send(String message) {
		this.queueMessagingTemplate.send("physicalQueueName", MessageBuilder.withPayload(message).build());
	}
}

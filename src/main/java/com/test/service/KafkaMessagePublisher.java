package com.test.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class KafkaMessagePublisher {

	private final KafkaTemplate<String, Object> template;

	public void sendMessageToTopic(String msg) {
		CompletableFuture<SendResult<String, Object>> future = template.send("topic-springboot-demo-dynamic", msg);
		future.whenComplete((result, ex) -> {
			if (ObjectUtils.isEmpty(ex))
				System.out.println(
						"Sent Message=[" + msg + "] with offset=[" + result.getRecordMetadata().offset() + "]");
			else
				System.out.println("Unable to send message=[" + msg + "] due to : " + ex.getMessage());
		});
	}
}

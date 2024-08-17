package com.test.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.service.KafkaMessagePublisher;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/producer-app")
@RequiredArgsConstructor
public class EventController {

	private final KafkaMessagePublisher publisher;

	@GetMapping("/publish/{msg}")
	public ResponseEntity<?> publishMessage(@PathVariable String msg) {
		try {

			for (int i = 0; i < 10000; i++) {
				publisher.sendMessageToTopic(msg + "-" + i);
			}

			return ResponseEntity.ok("Message published successfully ...!!");
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

}

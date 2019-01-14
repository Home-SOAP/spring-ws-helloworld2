package com.codenotfound.ws.controller;

import com.codenotfound.ws.service.HelloWorldClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
public class HelloWorldClientProcessor {

	private static final Logger logger = LoggerFactory.getLogger(HelloWorldClientProcessor.class);

	@Autowired
	private HelloWorldClientService helloWorldClientService;

	/**
	 * http://localhost:8080/testMarshalSendAndReceive
	 * http://localhost:8080/spring-ws-helloworld2-0.0.1-SNAPSHOT/testMarshalSendAndReceive
	 */
	@RequestMapping("/testMarshalSendAndReceive")
	public String testMarshalSendAndReceive() {
		helloWorldClientService.testMarshalSendAndReceive();
		return "OK";
	}

	/**
	 * http://localhost:8080/spring-ws-helloworld2-0.0.1-SNAPSHOT/sendAndReceive/Alex
	 */
	@RequestMapping("/sendAndReceive/{name}")
	public String sendAndReceive(@PathVariable String name) throws ExecutionException, InterruptedException {
		Future<String> sendAndReceive = helloWorldClientService.sendAndReceive(name);
		return sendAndReceive.get();
	}

}

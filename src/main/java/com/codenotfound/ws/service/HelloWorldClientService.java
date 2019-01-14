package com.codenotfound.ws.service;

import com.codenotfound.ws.SpringWsApplication;
import com.codenotfound.ws.client.HelloWorldClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class HelloWorldClientService {

    private static final Logger logger = LoggerFactory.getLogger(HelloWorldClientService.class);

    @Autowired
    @Qualifier("fixedThreadPool")
    private ExecutorService executorService;

    @Autowired
    private HelloWorldClient helloWorldClient;

    public Future<String> marshalSendAndReceive(Callable<String> wsRequest) {
        return executorService.submit(wsRequest);
    }

    public void testMarshalSendAndReceive() {
        List<Future<String>> responses = new ArrayList<>();

        for (int request=1; request<=10; request++) {
            responses.add(marshalSendAndReceive(new WsRequest("John-" + request, "Doe-" + request)));
        }

        for (Future<String> response : responses) {
            try {
                logger.info(" >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> ");
                String get = response.get();
                logger.info("                                        OK ... {}", get);
            } catch (InterruptedException|ExecutionException e) {
                logger.error(" <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< ");
            }
        }

        //shut down the executor service now
//    executorService.shutdown();
    }


    public Future<String> sendAndReceive(String name) {
        return executorService.submit(new WsRequest(name, name));
    }


    class WsRequest implements Callable<String> {

        private String name;

        private String surName;

        WsRequest (String name, String surName) {
            this.name = name;
            this.surName = surName;
        }

        @Override
        public String call() {
            logger.info(" >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> ThreadID = {}", Thread.currentThread().getId());
            return helloWorldClient.sayHello(name, surName);
        }
    }
}

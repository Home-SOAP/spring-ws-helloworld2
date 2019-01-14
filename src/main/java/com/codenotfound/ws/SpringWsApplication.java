package com.codenotfound.ws;

import com.codenotfound.ws.service.HelloWorldClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class SpringWsApplication extends SpringBootServletInitializer implements CommandLineRunner {

  @Autowired
  private HelloWorldClientService helloWorldClientService;

  public static void main(String[] args) {
    SpringApplication.run(SpringWsApplication.class, args);

  }

  @Override
  public void run(String... args) {
    helloWorldClientService.testMarshalSendAndReceive();
  }

}

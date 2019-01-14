package com.codenotfound.ws;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.codenotfound.ws.client.HelloWorldClient;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class SpringWsApplicationTests {

  @Autowired
  private HelloWorldClient helloWorldClient;

  @Test
  public void testSayHello() {
    assertThat(helloWorldClient.sayHello("John", "Doe")).isEqualTo("Hello John Doe!");
  }

  @Test
  public void testSayHelloMultiple() {
    String sayHelloResponse1 = helloWorldClient.sayHello("John", "Doe");
    String sayHelloResponse2 = helloWorldClient.sayHello("John", "Doe");
    String sayHelloResponse3 = helloWorldClient.sayHello("John", "Doe");
    String sayHelloResponse4 = helloWorldClient.sayHello("John", "Doe");
    String sayHelloResponse5 = helloWorldClient.sayHello("John", "Doe");
    String sayHelloResponse6 = helloWorldClient.sayHello("John", "Doe");
    String sayHelloResponse7 = helloWorldClient.sayHello("John", "Doe");
    String sayHelloResponse8 = helloWorldClient.sayHello("John", "Doe");
    String sayHelloResponse9 = helloWorldClient.sayHello("John", "Doe");
    String sayHelloResponse10 = helloWorldClient.sayHello("John", "Doe");

    assertThat(sayHelloResponse1).isEqualTo("Hello John Doe!");
    assertThat(sayHelloResponse2).isEqualTo("Hello John Doe!");
    assertThat(sayHelloResponse3).isEqualTo("Hello John Doe!");
    assertThat(sayHelloResponse4).isEqualTo("Hello John Doe!");
    assertThat(sayHelloResponse5).isEqualTo("Hello John Doe!");
    assertThat(sayHelloResponse6).isEqualTo("Hello John Doe!");
    assertThat(sayHelloResponse7).isEqualTo("Hello John Doe!");
    assertThat(sayHelloResponse8).isEqualTo("Hello John Doe!");
    assertThat(sayHelloResponse9).isEqualTo("Hello John Doe!");
    assertThat(sayHelloResponse10).isEqualTo("Hello John Doe!");
  }

  @Test
  public void testSayHelloThreads() {

    Runnable task = () -> {
      String sayHelloResponse = helloWorldClient.sayHello("John", "Doe");
      assertThat(sayHelloResponse).isEqualTo("Hello John Doe!");
    };

    new Thread(task).start();
    new Thread(task).start();
    new Thread(task).start();
    new Thread(task).start();
    new Thread(task).start();
    new Thread(task).start();
    new Thread(task).start();
    new Thread(task).start();
    new Thread(task).start();
    new Thread(task).start();
  }

  @Test
  public void testSayHelloExecutors() throws ExecutionException, InterruptedException {
    ExecutorService executor = Executors.newFixedThreadPool(10);

    Callable<String> callable = new MyCallable();
    //submit Callable tasks to be executed by thread pool
    Future<String> future1 = executor.submit(callable);
    Future<String> future2 = executor.submit(callable);
    Future<String> future3 = executor.submit(callable);
    Future<String> future4 = executor.submit(callable);
    Future<String> future5 = executor.submit(callable);
    Future<String> future6 = executor.submit(callable);
    Future<String> future7 = executor.submit(callable);
    Future<String> future8 = executor.submit(callable);
    Future<String> future9 = executor.submit(callable);
    Future<String> future10 = executor.submit(callable);

    assertThat(future1.get()).isEqualTo("Hello John Doe!");
    assertThat(future2.get()).isEqualTo("Hello John Doe!");
    assertThat(future3.get()).isEqualTo("Hello John Doe!");
    assertThat(future4.get()).isEqualTo("Hello John Doe!");
    assertThat(future5.get()).isEqualTo("Hello John Doe!");
    assertThat(future6.get()).isEqualTo("Hello John Doe!");
    assertThat(future7.get()).isEqualTo("Hello John Doe!");
    assertThat(future8.get()).isEqualTo("Hello John Doe!");
    assertThat(future9.get()).isEqualTo("Hello John Doe!");
    assertThat(future10.get()).isEqualTo("Hello John Doe!");

    //shut down the executor service now
    executor.shutdown();
  }

  private class MyCallable implements Callable<String> {
    @Override
    public String call() {
      return helloWorldClient.sayHello("John", "Doe");
    }
  }
}

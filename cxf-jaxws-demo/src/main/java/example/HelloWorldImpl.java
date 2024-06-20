package example;

import javax.jws.WebService;

@WebService(endpointInterface = "example.HelloWorld")
public class HelloWorldImpl implements HelloWorld {
  public String sayHi(String text) {
     try {
          Thread.sleep(2_000);
      } catch (InterruptedException ignore) {}

      return "Hello " + text;
  }
}

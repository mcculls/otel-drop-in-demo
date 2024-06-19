package example;

import javax.jws.WebService;

@WebService(endpointInterface = "example.HelloWorld")
public class HelloWorldImpl implements HelloWorld {
  public String sayHi(String text) {
    return "Hello " + text;
  }
}

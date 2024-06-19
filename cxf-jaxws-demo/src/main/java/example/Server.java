package example;

import javax.xml.ws.Endpoint;
import org.apache.cxf.feature.LoggingFeature;

public class Server {
  private static final String ENDPOINT_ADDRESS = "http://localhost:9000/helloWorld";

  private Server() {}

  public static void main(String[] args) {
    HelloWorldImpl implementor = new HelloWorldImpl();

    System.out.println("Publishing " + ENDPOINT_ADDRESS);
    Endpoint.publish(ENDPOINT_ADDRESS, implementor, new LoggingFeature());
  }
}

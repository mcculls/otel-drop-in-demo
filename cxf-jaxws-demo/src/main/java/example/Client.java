package example;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;

public final class Client {
  private static final String ENDPOINT_ADDRESS = "http://localhost:9000/helloWorld";
  private static final QName SERVICE_NAME = new QName("http://example/", "HelloWorld");
  private static final QName PORT_NAME = new QName("http://example/", "HelloWorldPort");

  private Client() {}

  public static void main(String[] args) {
    Service service = Service.create(SERVICE_NAME);
    service.addPort(PORT_NAME, SOAPBinding.SOAP11HTTP_BINDING, ENDPOINT_ADDRESS);

    System.out.println("Invoking " + ENDPOINT_ADDRESS);
    HelloWorld hw = service.getPort(HelloWorld.class);
    System.out.println(hw.sayHi("World"));
  }
}

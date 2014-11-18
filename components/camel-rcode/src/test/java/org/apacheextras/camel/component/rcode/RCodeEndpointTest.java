package org.apacheextras.camel.component.rcode;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class RCodeEndpointTest {

  @Test(expected = UnsupportedOperationException.class)
  public void testEmptyConstructor() throws Exception {
    RCodeEndpoint endpoint = new RCodeEndpoint();
    assertNotNull(endpoint);

    endpoint.createConsumer(new Processor() {
      @Override
      public void process(Exchange exchange) throws Exception {
        // Nothing to do
      }
    });
  }

  @Test(expected = NullPointerException.class)
  public void testUriComponentConstructor() {
    RCodeComponent component = new RCodeComponent();
    RCodeEndpoint endpoint = new RCodeEndpoint("rcode://tmp", component);
    assertNotNull(endpoint);

    endpoint.reconnect();
  }
}
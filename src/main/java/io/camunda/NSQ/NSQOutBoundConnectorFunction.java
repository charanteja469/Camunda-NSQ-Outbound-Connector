package io.camunda.NSQ;

import io.camunda.connector.api.annotation.OutboundConnector;
import io.camunda.connector.api.outbound.OutboundConnectorContext;
import io.camunda.connector.api.outbound.OutboundConnectorFunction;
import io.camunda.connector.generator.java.annotation.ElementTemplate;
import io.camunda.NSQ.dto.NSQ;
import io.camunda.NSQ.dto.NsqOutboundConnectorRequest;
import io.camunda.NSQ.dto.NsqOutboundConnectorResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@OutboundConnector(
    name = "NSQ OutBound Connector",
    inputVariables = {"nsqdAddress", "Topic", "message"},
    type = "io.camunda:template:1")
@ElementTemplate(
    id = "6e8e97cb-5b66-4d2f-ba7c-76c37bea309a",
    name = "NSQ OutBound Connector",
    version = 1,
    description = "This custom connector links Camunda 8 to NSQ, allowing workflows to publish and consume messages for real-time, event-driven process automation.",
    inputDataClass = NsqOutboundConnectorRequest.class)
public class NSQOutBoundConnectorFunction implements OutboundConnectorFunction {

  private static final Logger LOGGER = LoggerFactory.getLogger(NSQOutBoundConnectorFunction.class);

  @Override
  public Object execute(OutboundConnectorContext context) {
    final var connectorRequest = context.bindVariables(NsqOutboundConnectorRequest.class);
    return executeConnector(connectorRequest);
  }

  private NsqOutboundConnectorResult executeConnector(final NsqOutboundConnectorRequest connectorRequest) {

    String NSQDAddress = connectorRequest.nsqdAddress();
    String NSQTopic =  connectorRequest.Topic();
    String Message = connectorRequest.message();
    NSQ nsq = new NSQ();
    String result=nsq.messagePublish(NSQDAddress,NSQTopic,Message);

    System.out.println("NSQDAddress : "+NSQDAddress);
    System.out.println("NSQTopic : "+NSQTopic);
    System.out.println("Message : "+Message);
    System.out.println("Message Published Status : " +result);

    return new NsqOutboundConnectorResult("Message Published Status : " +result);
  }
}

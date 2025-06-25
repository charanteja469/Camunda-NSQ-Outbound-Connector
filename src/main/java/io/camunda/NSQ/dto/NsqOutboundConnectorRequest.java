package io.camunda.NSQ.dto;

import io.camunda.connector.generator.java.annotation.TemplateProperty;
import io.camunda.connector.generator.java.annotation.TemplateProperty.PropertyType;
import jakarta.validation.constraints.NotEmpty;

public record NsqOutboundConnectorRequest(
    @NotEmpty @TemplateProperty(group = "compose", type = PropertyType.Text)
    String nsqdAddress,
    String Topic,
    String message) {}

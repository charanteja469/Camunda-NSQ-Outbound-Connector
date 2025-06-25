package io.camunda.NSQ.dto;


import com.sproutsocial.nsq.Publisher;

public class NSQ {
    public String messagePublish(String nsqdAddress, String nsqTopic, String message) {

        Publisher publisher = new Publisher(nsqdAddress);

        publisher.publish(nsqTopic,(message).getBytes());

        return "OK";
    }




}

package com.baeldung.spring.cloud.stream.rabbit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;

import org.springframework.messaging.support.MessageBuilder;

import com.baeldung.spring.cloud.stream.rabbit.processor.ConditionalProcessor;

@SpringBootApplication
@EnableBinding(ConditionalProcessor.class)
public class MultipleOutputsWithConditionsServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(MultipleOutputsWithConditionsServiceApplication.class, args);
    }

    @Autowired
    private ConditionalProcessor processor;

    @StreamListener(target = ConditionalProcessor.INPUT, condition = "headers['type']=='bogey'")
    public void routeValuesToAnOutput(Integer val) {
    	processor.lowOutput().send(message("bogey"));
        processor.lowOutput().send(message(val));
    }

    @StreamListener(target = ConditionalProcessor.INPUT, condition = "headers['type']=='eagle'")
    public void routeValuesToAnotherOutput(Integer val) {
    	processor.highOutput().send(message("eagle"));
        processor.highOutput().send(message(val));
    }

    private static final <T> Message<T> message(T val) {
        return MessageBuilder.withPayload(val)
            .build();
    }
}

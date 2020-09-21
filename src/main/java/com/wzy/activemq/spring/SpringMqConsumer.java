package com.wzy.activemq.spring;

import org.apache.camel.component.test.TestComponent;
import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

@Service
public class SpringMqConsumer {

    @Autowired
    private JmsTemplate jmsTemplate;

    public void receive() {
        Message message = jmsTemplate.receive("spring-active-queue");
        if (null != message && message instanceof TextMessage) {
            try {
                String text = ((TextMessage) message).getText();
                System.out.println("消费者收到："+ text);
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");

        SpringMqConsumer consumer = (SpringMqConsumer) context.getBean(SpringMqConsumer.class);
        consumer.receive();

        System.out.println("***receive task over***");
    }

}


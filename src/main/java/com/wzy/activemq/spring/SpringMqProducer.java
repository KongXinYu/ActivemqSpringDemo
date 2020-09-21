package com.wzy.activemq.spring;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

@Service
public class SpringMqProducer {

    @Autowired
    private JmsTemplate jmsTemplate;

    public void send(String msg) {
//        jmsTemplate.send(new MessageCreator() {
//            public Message createMessage(Session session) throws JMSException {
//                TextMessage textMessage = session.createTextMessage(msg);
//                return textMessage;
//            }
//        });
        jmsTemplate.send(session -> {
            TextMessage textMessage = session.createTextMessage(msg);
            return textMessage;
        });
    }

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");

        SpringMqProducer producer = (SpringMqProducer) context.getBean(SpringMqProducer.class);
        producer.send("spring mq send!");

        System.out.println("***send task over***");
    }

}


package com.dorotajachtoma.tacocloud.converter;

import org.springframework.messaging.converter.MessageConversionException;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

public interface MessageConverter {

    Message toMessage(Object object, Session session) throws JMSException, MessageConversionException;
    Object fromMessage(Message message);
}

package pl.poleng.messaging;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import pl.poleng.dao.model.User;

@Component
public class MessageSender {
 
    @Autowired
    JmsTemplate jmsTemplate;
 
    public void sendMessage(final User user) {
 
        jmsTemplate.send(new MessageCreator(){
                @Override
                public Message createMessage(Session session) throws JMSException{
                	
                	ObjectMapper mapper = new ObjectMapper();
                	String msg = "";
					try {
						msg = mapper.writeValueAsString(user);
					} catch (JsonProcessingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                	
                    TextMessage textMessage = session.createTextMessage(msg);
                    return textMessage;
                }
            });
               
    }
 
}
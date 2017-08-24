package pl.poleng.messaging;

import java.io.IOException;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import pl.poleng.dao.model.User;
import pl.poleng.service.UserService;

@Component
public class MessageReceiver implements MessageListener{
 
    static final Logger LOG = LoggerFactory.getLogger(MessageReceiver.class);
     
    @Autowired
    MessageConverter messageConverter;
     
    @Autowired
    UserService userService;
    
    @Autowired
    JmsTemplate jmsTemplate;

     
    @Override
    public void onMessage(Message message) {
        try {        	
            LOG.info("-----------------------------------------------------");
            //User user = (User) messageConverter.fromMessage(message);
            String msg = (String) messageConverter.fromMessage(message);
            ObjectMapper mapper = new ObjectMapper();
            User user = mapper.readValue(msg, User.class);
            
            LOG.info("Application : user received : {}",user);  
            userService.saveUser(user);
            LOG.info("-----------------------------------------------------");
                        
            //throw new RuntimeException("Message could not be consumed. Roll back transaction");                        
        } catch (JMSException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}        
         
    }
    
    public User receive() {
    	jmsTemplate.setReceiveTimeout(5000);
    	Message msg = jmsTemplate.receive();
    	User user = null;
		try {
			user = (User) messageConverter.fromMessage(msg);
		} catch (MessageConversionException | JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return user;
    }
}
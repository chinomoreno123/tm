package pl.poleng.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import pl.poleng.dao.TranslatorDao;
import pl.poleng.dao.model.Translator;

//@WebAppConfiguration
//@RunWith(SpringJUnit4ClassRunner.class)
@RunWith(MockitoJUnitRunner.class)
//@ContextConfiguration(locations = { "classpath:application-context.xml" })
public class TranslatorServiceTest {
	
	@InjectMocks
	TranslatorService translatorService;

	@Mock
	TranslatorDao translatorDao;
	
	@Before
	public void init() {
		Translator translator = new Translator();
		translator.setEmail("JUNITTEST@JUNIT.COM");
		translator.setFirstName("junitFirstName");
		translator.setLastName("junitLastName");
		translatorDao.save(translator);	
	}
	
	@Test
	public final void testGetTranslators() {
		List<Translator> translators = translatorService.getTranslators();		
		//translators.forEach(System.out::println);
		assertNotNull(translators);			
	}
	
	@After
	public void clean(){
		Translator translator = translatorDao.findByEmail("JUNITTEST@JUNIT.COM");
		translatorDao.delete(translator);
	}

}

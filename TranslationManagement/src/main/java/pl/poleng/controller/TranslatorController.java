package pl.poleng.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.poleng.dao.model.Translator;
import pl.poleng.service.TranslatorService;

@Controller
public class TranslatorController {
	
	@Autowired
	TranslatorService translatorService;

	@RequestMapping(value = { "/translator" }, method = { RequestMethod.GET })
	@ResponseBody
	public List<Translator> listTranslators() {
		return this.translatorService.getTranslators();
	}
}

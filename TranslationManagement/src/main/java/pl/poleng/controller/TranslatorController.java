package pl.poleng.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonView;

import pl.poleng.dao.model.Translator;
import pl.poleng.service.TranslatorService;

@Controller
@RequestMapping({ "/translators" })
public class TranslatorController {

	@Autowired
	TranslatorService translatorService;

	@RequestMapping(value = { "/", "" }, method = { RequestMethod.GET })
	public String translatorsView(ModelMap model) {
		model.addAttribute("loggedinuser", "ALAMAKOTA");
		return "translatorslist";
	}

	@RequestMapping(value = { "/json" }, method = { RequestMethod.GET })
	@ResponseBody
	public List<Translator> listTranslators() {
		return this.translatorService.getTranslators();
	}

	@JsonView(DataTablesOutput.View.class)
	@RequestMapping(value = { "/listTranslators" }, method = { RequestMethod.GET })
	@ResponseBody
	public DataTablesOutput<Translator> list(DataTablesInput input) {
		return this.translatorService.getTranslators(input);
	}

}

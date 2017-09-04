package pl.poleng.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;

import pl.poleng.dao.TranslatorDao;
import pl.poleng.dao.model.Translator;

@Service
public class TranslatorService {
	@Autowired
	TranslatorDao translatorDao;

	public List<Translator> getTranslators() {
		return (List<Translator>) this.translatorDao.findAll();
	}

	public DataTablesOutput<Translator> getTranslators(DataTablesInput input) {
		return this.translatorDao.findAll(input);
	}
}

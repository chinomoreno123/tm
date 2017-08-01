package pl.poleng.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.poleng.dao.model.Translator;

@Repository
public abstract interface TranslatorDao extends CrudRepository<Translator, Long> {
	
	@Query("SELECT t.firstName FROM Translator t where t.id = :id")
	public abstract String findFirstNameById(@Param("id") Long paramLong);

	@Query("SELECT t.email FROM Translator t where t.id = :id")
	public abstract String findEmailById(@Param("id") Long paramLong);
}

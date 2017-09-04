package pl.poleng.dao;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pl.poleng.dao.model.Translator;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public abstract interface TranslatorDao extends DataTablesRepository<Translator, Long> {
	
	@Query("SELECT t.firstName FROM Translator t where t.id = :id")
	public abstract String findFirstNameById(@Param("id") Long paramLong);

	@Query("SELECT t.email FROM Translator t where t.id = :id")
	public abstract String findEmailById(@Param("id") Long paramLong);
	
	@Query("SELECT t FROM Translator t where t.email = :email")
	public abstract Translator findByEmail(@Param("email") String email);
}

package pl.poleng.dao;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pl.poleng.dao.model.User;

public abstract interface UserRepository extends DataTablesRepository<User, Long>, UserRepositoryCustom {
	
	@Query("SELECT u FROM User u join fetch u.userProfiles up where u.id = :id")
	public abstract User findByIdAndWihProfiles(@Param("id") long id);
}

package pl.poleng.dao;

import org.springframework.data.repository.CrudRepository;
import pl.poleng.dao.model.UserProfile;

public abstract interface UserProfileRepository extends CrudRepository<UserProfile, Long>, UserProfileRepositoryCustom {
}

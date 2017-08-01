package pl.poleng.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import pl.poleng.dao.model.User;

public abstract interface UserRepository extends PagingAndSortingRepository<User, Long>, UserRepositoryCustom {
}

package pl.poleng.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pl.poleng.dao.model.User;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class UserRepositoryImpl implements UserRepositoryCustom {
	static final Logger logger = LoggerFactory.getLogger(UserRepositoryImpl.class);
	@PersistenceContext
	private EntityManager entityManager;

	public EntityManager getEntityManager() {
		return this.entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public User findByUsername(String username) {
		logger.info("USERNAME : {}", username);

		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();

		CriteriaQuery<User> criteriaQuery = cb.createQuery(User.class);
		Root<User> userRoot = criteriaQuery.from(User.class);
		criteriaQuery.select(userRoot);

		criteriaQuery.where(cb.equal(userRoot.get("username"), username));

		Query query = getEntityManager().createQuery(criteriaQuery);

		List results = query.getResultList();
		User user = null;
		if (results.size() == 1) {
			user = (User) getEntityManager().createQuery(criteriaQuery).getSingleResult();
		}
		if (user != null) {
			Hibernate.initialize(user.getUserProfiles());
		}
		return user;
	}



	public void deleteByUsername(String username) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();

		CriteriaQuery<User> query = cb.createQuery(User.class);
		Root<User> userRoot = query.from(User.class);
		query.select(userRoot);

		query.where(cb.equal(userRoot.get("username"), username));
		User user = (User) getEntityManager().createQuery(query).getSingleResult();

		getEntityManager().remove(user);
	}
}

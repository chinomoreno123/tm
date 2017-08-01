package pl.poleng.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import pl.poleng.dao.model.UserProfile;

@Repository
public class UserProfileRepositoryImpl implements UserProfileRepositoryCustom {
	@PersistenceContext
	private EntityManager entityManager;

	public EntityManager getEntityManager() {
		return this.entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public UserProfile findByType(String type) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();

		CriteriaQuery<UserProfile> criteriaQuery = cb.createQuery(UserProfile.class);
		Root<UserProfile> userProfileRoot = criteriaQuery.from(UserProfile.class);
		criteriaQuery.select(userProfileRoot);
		criteriaQuery.where(cb.equal(userProfileRoot.get("type"), type));

		Query query = getEntityManager().createQuery(criteriaQuery);

		List<?> results = query.getResultList();
		UserProfile userProfile = null;
		if (results.size() == 1) {
			userProfile = (UserProfile) getEntityManager().createQuery(criteriaQuery).getSingleResult();
		}
		return userProfile;
	}
}

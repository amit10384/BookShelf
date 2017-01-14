package com.amit.query.runner;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.amit.domain.AuthorResponseDTO;

@Service
public class NativeQueryRunnerImpl {
	
	private static Logger LOG = LoggerFactory.getLogger(NativeQueryRunnerImpl.class);
	
	@Autowired
	@Qualifier("amitFactory")
	private EntityManagerFactory entityManagerFactory;
	
	public List<AuthorResponseDTO> returnAuthorListIfNameMatches(Map<String,Object> queryParams, String queryFromFile) throws Exception {
		EntityManager entityManager = null;
		try {
		entityManager = createEntityManager();
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createSQLQuery(queryFromFile).setResultTransformer(Transformers.aliasToBean(AuthorResponseDTO.class));
		if ( queryParams.size() > 0) {
			Set<String> keySet = queryParams.keySet();
			for (String key : keySet) {
				query = query.setParameter(key, queryParams.get(key));
			}
		}
		List<AuthorResponseDTO> results = query.list();
		LOG.info("Query {} executed successful with result :{}", query, results);
		return results;
	} catch (HibernateException ex) {
		LOG.error("HibernateException occured: Couldn't execute query {} for params: {} ", queryFromFile, queryParams, ex);
		throw new Exception("HibernateException:Couldn't execute query. Query execution failed!!", ex);
	} finally {
		this.closeEntityManager(entityManager);
	}
}

	private EntityManager createEntityManager() {

		return entityManagerFactory.createEntityManager();
	}

	private void closeEntityManager(EntityManager entityManager) {

		entityManager.close();
	}
}

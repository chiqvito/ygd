package it.bialek.jpa.dao;

import it.bialek.jpa.model.ProductEntity;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Stateless
public class ProductDaoBean implements ProductDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Long getProductsCount() {
		CriteriaQuery<Long> cq = em.getCriteriaBuilder().createQuery(Long.class);
		Root<ProductEntity> rt = cq.from(ProductEntity.class);
		cq.select(em.getCriteriaBuilder().count(rt));
		TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult();
	}

	@Override
	public List<ProductEntity> getProducts(int maxResults, int firstResult) {
		CriteriaQuery<ProductEntity> cq = em.getCriteriaBuilder().createQuery(ProductEntity.class);
		cq.select(cq.from(ProductEntity.class));
		TypedQuery<ProductEntity> q = em.createQuery(cq);
		q.setMaxResults(maxResults);
		q.setFirstResult(firstResult);
		return q.getResultList();
	}

}

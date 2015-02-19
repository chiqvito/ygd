package it.bialek.jpa.dao;

import it.bialek.jpa.model.OrderEntity;
import it.bialek.jpa.model.ProductEntity;

import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

@Stateless
public class OrderDaoBean implements OrderDao {

	@PersistenceContext
	private EntityManager em;

	@Inject
	private Event<OrderEntity> memberEventSrc;

	@Override
	public boolean save(OrderEntity order) {
		if (order.getId() == null) {
			em.persist(order);
		} else {
			em.merge(order);
		}
		memberEventSrc.fire(order);
		return true;
	}

	@Override
	public Long getOrdersCount() {
		CriteriaQuery<Long> cq = em.getCriteriaBuilder().createQuery(Long.class);
		Root<OrderEntity> rt = cq.from(OrderEntity.class);
		cq.select(em.getCriteriaBuilder().count(rt));
		TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult();
	}

	@Override
	public List<OrderEntity> getOrders(int maxResults, int firstResult) {
		CriteriaQuery<OrderEntity> cq = em.getCriteriaBuilder().createQuery(OrderEntity.class);
		Root<OrderEntity> rt = cq.from(OrderEntity.class);
		Fetch<OrderEntity, ProductEntity> ft = rt.fetch("product", JoinType.INNER);
		TypedQuery<OrderEntity> q = em.createQuery(cq);
		q.setMaxResults(maxResults);
		q.setFirstResult(firstResult);
		return q.getResultList();
	}

}

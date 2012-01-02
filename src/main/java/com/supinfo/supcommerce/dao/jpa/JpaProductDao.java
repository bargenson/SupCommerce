package com.supinfo.supcommerce.dao.jpa;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;

import com.supinfo.supcommerce.dao.ProductDao;
import com.supinfo.supcommerce.exception.UnknownProductException;
import com.supinfo.supcommerce.model.Product;

public class JpaProductDao extends JpaAbstractDao<Product, Long> implements ProductDao {
	
	public JpaProductDao(EntityManagerFactory emf) {
		super(emf);
	}

	@Override
	public Product addProduct(Product product) {
		return persist(product);
	}

	@Override
	public Product findProductById(Long productId) {
		try {
			return findById(productId);
		} catch (NoResultException e) {
			throw new UnknownProductException(productId, e);
		}
	}

	@Override
	public List<Product> getAllProducts() {
		return findAll();
	}

	@Override
	public void updateProduct(Product product) {
		update(product);
	}

	@Override
	public void removeProduct(Product product) {
		remove(product);
	}
	
}

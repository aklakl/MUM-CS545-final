package edu.mum.coffee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.mum.coffee.domain.Product;
import edu.mum.coffee.domain.ProductType;
import edu.mum.coffee.repository.ProductRepository;

@Service
@Transactional
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public Product save(Product product) {
		return productRepository.save(product);
	}

	public void deleteById(int productId) {
		Product product = getProduct(productId);
		delete(product);
	}

	public void delete(Product product) {
		productRepository.delete(product);
	}

	public Product getProduct(int productId) {
		return productRepository.findOne(productId);
	}

	public List<Product> getAllProduct() {
		return productRepository.findAll();
	}

	public List<Product> findByTextSearch(String criteria) {
		if (!criteria.contains("%")) {
			criteria = "%" + criteria + "%";
		}
		return productRepository.findByProductNameLikeOrDescriptionLikeAllIgnoreCase(criteria, criteria);
	}

	public List<Product> findByPrice(double minPrice, double maxPrice) {
		return productRepository.findByPriceBetween(minPrice, maxPrice);
	}

	public List<Product> findByProductType(ProductType productType) {
		return productRepository.findByProductType(productType);
	}

	public List<Product> deleteAPI(int productId) {
		deleteById(productId);
		return getAllProduct();
	}

	public List<Product> updateProductAPI(Product product) {
		save(product);
		return getAllProduct();
	}

	public List<Product> insertProductAPI(Product product) {
		save(product);
		return getAllProduct();
	}

}

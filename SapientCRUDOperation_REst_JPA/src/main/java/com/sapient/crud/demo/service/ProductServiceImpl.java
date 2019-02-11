package com.sapient.crud.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sapient.crud.demo.intf.ProductService;
import com.sapient.crud.demo.model.Product;
import com.sapient.crud.demo.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepository productRepository;

	public List<Product> getAllProducts(){
		List<Product> products = (List<Product>) productRepository.findAll();
		return products;
	}

	public Product getProduct(int id) {
		return productRepository.findById(id).get();
	}

	public Product addProduct(Product product) {
		return productRepository.save(product);
	}

	public Product updateProduct(Product product, int id) {
		return productRepository.save(product);

	}

	public void deleteProduct(int id) {
		productRepository.deleteById(id);
	}

	public void deleteAllProducts() {
		productRepository.deleteAll();
	}

	@Override
	public Optional<Product> isProductExist(int id) {
		return productRepository.findById(id);
	}

}

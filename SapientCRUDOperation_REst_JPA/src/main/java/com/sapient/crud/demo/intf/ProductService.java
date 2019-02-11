package com.sapient.crud.demo.intf;

import java.util.List;
import java.util.Optional;

import com.sapient.crud.demo.model.Product;

public interface ProductService {

	public List<Product> getAllProducts();

	public Product getProduct(int id);

	public Product addProduct(Product product);

	public Product updateProduct(Product product, int id);

	public void deleteProduct(int id);

	public void deleteAllProducts();
	
	public Optional<Product> isProductExist(int id);
}

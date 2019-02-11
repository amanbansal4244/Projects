package com.sapient.crud.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.sapient.crud.demo.exception.ResourceNotFoundError;
import com.sapient.crud.demo.intf.ProductService;
import com.sapient.crud.demo.model.Product;

@RestController
public class ProductController {

	@Autowired
	private ProductService productService;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	//@RequestMapping("/products")
	@GetMapping("/products")
	public ResponseEntity<List<Product>> getAllProducts() {
		List<Product> products = productService.getAllProducts();
		if (products.size()==0) {
			return new ResponseEntity("No product found",HttpStatus.NO_CONTENT);// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	//@RequestMapping("products/{productId}") 
	//@GetMapping("/products/{productId}")
	@GetMapping(value = "/products/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> getProduct(@PathVariable("productId") int id) {
		Optional<Product>  p = productService.isProductExist(id);
		Product product = null;
		if (p.isPresent()) {
			product = productService.getProduct(id);
			return new ResponseEntity<Product>(product, HttpStatus.OK);
		}else 
			return new ResponseEntity(new ResourceNotFoundError("Product with id " + id 
					+ " is not found"), HttpStatus.NOT_FOUND);
	}

	//@RequestMapping(method=RequestMethod.POST , value="/products") 
	@SuppressWarnings({ "rawtypes", "unchecked" })
	//@PostMapping("/products")
	@PostMapping(value="/products",headers="Accept=application/json")
	public ResponseEntity<Void> addProduct(@RequestBody Product product, UriComponentsBuilder ucBuilder) {
		Optional<Product>  p = productService.isProductExist(product.getId());
		if (!(p.isPresent())) {
			product = productService.addProduct(product );
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/product/{id}").buildAndExpand(product.getId()).toUri());
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		}else {
			return new ResponseEntity(new ResourceNotFoundError("Unable to create. A Product with ID " + 
					product.getId() + " is already exist."), HttpStatus.CONFLICT);
		}
	}

	//@RequestMapping(method=RequestMethod.PUT , value="/products/{productId}") 
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PutMapping("/products/{productId}")
	public ResponseEntity<Product> updateProduct(@RequestBody Product product, @PathVariable int productId) {

		Optional<Product>  p = productService.isProductExist(productId);
		if (p.isPresent()) {
			product = productService.updateProduct(product,productId);
			return new ResponseEntity<Product>(product, HttpStatus.CREATED);
		}else {
			return new ResponseEntity(new ResourceNotFoundError("Unable to Update. Product with id " + productId + " is not available."),
					HttpStatus.NOT_FOUND);
		}

	}

	//@RequestMapping(method=RequestMethod.DELETE , value="/products/{productId}") 
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@DeleteMapping("/products/{productId}")
	public ResponseEntity<Product> deleteProduct(@PathVariable int productId) {
		Optional<Product>  p = productService.isProductExist(productId);
		if (p.isPresent()) {
			productService.deleteProduct(productId);
			return new ResponseEntity<Product>(HttpStatus.OK);
		}else {
			return new ResponseEntity(new ResourceNotFoundError("Unable to delete. Product with id " + productId + " is not found."),
					HttpStatus.NOT_FOUND);
		}

	}

	//@RequestMapping(value = "/products/", method = RequestMethod.DELETE)
	@DeleteMapping("/products")
	public ResponseEntity<Product> deleteAllProducts() {
		productService.deleteAllProducts();
		return new ResponseEntity<Product>(HttpStatus.OK);
	}
}

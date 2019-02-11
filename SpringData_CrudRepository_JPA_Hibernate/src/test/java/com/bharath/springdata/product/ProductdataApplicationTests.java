package com.bharath.springdata.product;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.bharath.springdata.product.entities.Product;
import com.bharath.springdata.product.repos.ProductRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductdataApplicationTests {

	@Autowired
	ProductRepository repository;
	
	@Autowired
	EntityManager entityManager;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testCreate() {
		Product product = new Product();
		product.setId(1);
		product.setName("Iphone");
		product.setDesc("Awesome");
		product.setPrice(1000d);

		repository.save(product);
	}

	@Test
	public void testRead() {
		Product product = repository.findOne(1);
		assertNotNull(product);
		assertEquals("Iphone", product.getName());
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + product.getDesc());
	}

	@Test
	public void testUpdate() {
		Product product = repository.findOne(1);
		product.setPrice(1200d);
		repository.save(product);

	}

	@Test
	public void testDelete() {
		if (repository.exists(1)) {
			System.out.println("Deleting a product");
			repository.delete(1);
		}
	}

	@Test
	public void testCount() {
		System.out.println("Total Records===============>>>>>>>>>>>>>>>" + repository.count());

	}

	@Test
	public void testFindByName() {
		Product findOne = repository.findOne(1);
		List<Product> products = repository.findByName("IWatch");
		products.forEach(p -> System.out.println(p.getPrice()));

		List<Product> products1 = repository.findByName("IWatch");
		products1.forEach(p -> System.out.println(p.getPrice()));
	}

	@Test
	public void testFindByNameAndDesc() {
		List<Product> products = repository.findByNameAndDesc("TV", "From Samsung Inc");
		products.forEach(p -> System.out.println(p.getPrice()));
	}

	@Test
	public void testFindByPriceGreaterThan() {
		List<Product> products = repository.findByPriceGreaterThan(1000d);
		products.forEach(p -> System.out.println(p.getName()));
	}

	@Test
	public void testFindByDescContains() {
		List<Product> products = repository.findByDescContains("Apple");
		products.forEach(p -> System.out.println(p.getName()));
	}

	@Test
	public void testFindByPriceBetween() {
		List<Product> products = repository.findByPriceBetween(500d, 2500d);
		products.forEach(p -> System.out.println(p.getName()));
	}

	@Test
	public void testFindByDescLike() {
		List<Product> products = repository.findByDescLike("%LG%");
		products.forEach(p -> System.out.println(p.getName()));
	}

	@Test
	public void testFindByIdsIn() {
		List<Product> products = repository.findByIdIn(Arrays.asList(1, 2, 3));
		products.forEach(p -> System.out.println(p.getName()));
	}
	
	@Test
	@Transactional
	public void testCaching() {
		Session session = entityManager.unwrap(Session.class);
		Product product = repository.findOne(1);
		
		repository.findOne(1);
		
		session.evict(product);
		
		repository.findOne(1);
		
	}
	

}













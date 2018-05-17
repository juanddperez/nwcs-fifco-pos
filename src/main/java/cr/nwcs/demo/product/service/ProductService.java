package cr.nwcs.demo.product.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cr.nwcs.demo.product.entity.Product;
import cr.nwcs.demo.product.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public Optional<Product> findProductById(Long id) {
		return productRepository.findById(id);
	}

	public List<Product> findAllProducts() {
		return productRepository.findAll();
	}

	public Product saveProduct(@Valid Product product) {
		return productRepository.save(product);
	}
}

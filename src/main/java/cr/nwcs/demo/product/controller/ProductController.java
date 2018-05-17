package cr.nwcs.demo.product.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cr.nwcs.demo.product.entity.Product;
import cr.nwcs.demo.product.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping
	public List<Product> findAllProducts() {
		return productService.findAllProducts();
	}

	@PostMapping
	public Product saveProduct(@Valid @RequestBody Product product) {
		product.setId(null);
		return productService.saveProduct(product);
	}
}

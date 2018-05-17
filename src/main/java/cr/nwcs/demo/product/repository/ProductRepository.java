package cr.nwcs.demo.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cr.nwcs.demo.product.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}

package cr.nwcs.demo.order.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cr.nwcs.demo.order.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

		Optional<Order> findByOrderNr(String orderNr);
}

package cr.nwcs.demo.order.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cr.nwcs.demo.order.entity.Order;
import cr.nwcs.demo.order.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	public Optional<Order> findOrderByOrderNr(String orderNr) {
		return orderRepository.findByOrderNr(orderNr);
	}

	public Order saveOrder(Order order) {
		return orderRepository.save(order);
	}

	public List<Order> findAllOrders() {
		return orderRepository.findAll();
	}
}

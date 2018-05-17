package cr.nwcs.demo.order.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cr.nwcs.demo.order.dto.OrderDto;
import cr.nwcs.demo.order.entity.Order;
import cr.nwcs.demo.order.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<OrderDto> findAll() {
		return orderService.findAllOrders().stream().map(OrderController::convertOrderToDto).collect(Collectors.toList());
	}

	@GetMapping(value = "/{orderNr}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> findOrderByOrderNr(@PathVariable("orderNr") String orderNr) {
		return orderService.findOrderByOrderNr(orderNr).map(o -> ResponseEntity.ok(convertOrderToDto(o))).orElse(ResponseEntity.notFound().build());
	}

	public static OrderDto convertOrderToDto(Order source) {
		final OrderDto target = new OrderDto();
		target.setOrderNr(source.getOrderNr());
		target.setTotalAmount(source.getTotalAmount());
		target.setTotalElements(source.getTotalElements());
		target.setTotalPoints(source.getTotalPoints());
		return target;
	}
}

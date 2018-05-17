package cr.nwcs.demo.cart.controller;

import java.util.UUID;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import cr.nwcs.demo.cart.dto.ShoppingCartDto;
import cr.nwcs.demo.cart.dto.ShoppingCartItemDto;
import cr.nwcs.demo.gamification.service.GamificationApiService;
import cr.nwcs.demo.order.entity.Order;
import cr.nwcs.demo.order.service.OrderService;

@RestController
@RequestMapping("/shopping-cart")
public class ShoppingCartController {
	private static final String SHOPPING_CART = "shopping-cart";

	@Autowired
	private OrderService orderService;

	@Autowired
	private GamificationApiService gamificationApiService;

	@Autowired
	private HttpSession httpSession;

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void saveShoppingCart(@Valid @RequestBody ShoppingCartDto request) {
		httpSession.setAttribute(SHOPPING_CART, request);
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public Object getShoppingCart() {
		return httpSession.getAttribute(SHOPPING_CART);
	}

	@PostMapping(value = "/checkout")
	public ResponseEntity<?> checkout() {
		ShoppingCartDto cart = (ShoppingCartDto) httpSession.getAttribute(SHOPPING_CART);

		final Order order = convertOrderFromShoppingCartDto(cart);
		order.setOrderNr(UUID.randomUUID().toString());
		orderService.saveOrder(order);

		gamificationApiService.customerBuys(order.getCustomerId(), order.getTotalPoints());

		httpSession.setAttribute(SHOPPING_CART, null);

		return ResponseEntity.created(UriComponentsBuilder.fromPath("/orders").path("/".concat(order.getOrderNr())).build().toUri()).build();
	}

	public static Order convertOrderFromShoppingCartDto(ShoppingCartDto source) {
		final Order target = new Order();
		target.setCustomerId(source.getCustomerId());
		target.setTotalPoints(source.getElements().values().stream().mapToInt(ShoppingCartItemDto::getTotalPoints).sum());
		target.setTotalAmount(source.getTotalAmount());
		target.setTotalElements(source.getTotalElements());
		return target;
	}
}

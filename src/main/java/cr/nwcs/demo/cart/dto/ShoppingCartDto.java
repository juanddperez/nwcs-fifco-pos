package cr.nwcs.demo.cart.dto;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import lombok.Data;

@Data
public class ShoppingCartDto {
	private Map<String, ShoppingCartItemDto> elements = new HashMap<>();
	private Integer totalElements;
	private BigDecimal totalAmount;
	private String customerId;
}

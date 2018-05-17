package cr.nwcs.demo.cart.dto;

import java.math.BigDecimal;

import cr.nwcs.demo.product.dto.ProductDto;
import lombok.Data;

@Data
public class ShoppingCartItemDto {

	private ProductDto product;
	private Integer quantity;
	private BigDecimal total;
	private Integer totalPoints;
}

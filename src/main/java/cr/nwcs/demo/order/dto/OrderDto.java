package cr.nwcs.demo.order.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class OrderDto {
	private String orderNr;
	private Integer totalPoints;
	private BigDecimal totalAmount;
	private Integer totalElements;
}

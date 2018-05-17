package cr.nwcs.demo.product.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProductDto {
	private String code;
	private String description;
	private String imageSrc;
	private BigDecimal price;
	private Integer points;
}

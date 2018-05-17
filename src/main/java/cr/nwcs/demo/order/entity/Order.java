package cr.nwcs.demo.order.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String customerId;

	@NotNull
	private String orderNr;

	@NotNull
	private Integer totalPoints;

	@NotNull
	private BigDecimal totalAmount;

	@NotNull
	private Integer totalElements;
}

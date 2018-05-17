package cr.nwcs.demo.gamification.dto;

import lombok.Data;

@Data
public class PlayerDto {
	private String playerId;
	private Boolean isPublic;
	private String name;
	private String image;
}

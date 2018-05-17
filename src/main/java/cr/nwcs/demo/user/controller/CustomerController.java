package cr.nwcs.demo.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cr.nwcs.demo.gamification.dto.PlayerDto;
import cr.nwcs.demo.gamification.service.GamificationApiService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	private GamificationApiService gamificationApiService;

	@GetMapping("/{id}/player-info")
	public PlayerDto getPlayerInfo(@PathVariable("id") String id) {
		return gamificationApiService.getPlayer(id);
	}
}

package controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnalysisController {
	
	@GetMapping("analys")
	public String runNow() {
		return "Server Up Now";
	}
}

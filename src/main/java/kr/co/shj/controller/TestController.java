package kr.co.shj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TestController {

	@RequestMapping(value="/doA")
	public String doA(Model model, @RequestParam("msg") String msg) {
		System.out.println("doA 실행됨.");
		
		model.addAttribute("info",msg); //msg값을 받음.
		
		return "doA";
	}
	
}

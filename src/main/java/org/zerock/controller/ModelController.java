package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/model/*")
@Log4j
public class ModelController {

	@RequestMapping("/ex1")
	public void method(Model model) {
		log.info("method1");
		
//		request.setAttribute("abc", abc); -> 이것과 유사하게 Model을 사용
		model.addAttribute("name", "java");
		//Model객체에 넣어둔 값을 스프링에서 request객체로 자동으로 옮겨다 줌
		//jsp파일에서는 EL로 똑같이 사용가능
	}
}

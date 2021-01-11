package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.domain.Member;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/model/*")
@Log4j
public class ModelController {

	@RequestMapping("/ex1")
	public void method1(Model model) {
		log.info("method1");
		
//		request.setAttribute("abc", abc); -> 이것과 유사하게 Model을 사용
		model.addAttribute("name", "java");
		//Model객체에 넣어둔 값을 스프링에서 request객체로 자동으로 옮겨다 줌
		//jsp파일에서는 EL로 똑같이 사용가능
	}
	
	@RequestMapping("/ex2")
	public void method2(@ModelAttribute("name") String name) {
		//@ModelAttribute 어노테이션
		//Model객체 직접 작성할 필요 없이 해당 이름으로 Model에 데이터를 붙여줌
		log.info("method2");
//		model.addAttribute("name", name);
	}
	
	@RequestMapping("/ex3")
	public void method3(@ModelAttribute("name") String name, @ModelAttribute("age") int age) {
		log.info("method3");
		log.info(name);
		log.info(age);
	}
	
	@RequestMapping("/ex4")
	public void method4(@ModelAttribute Member member) {
		//@ModelAttribute의 파라미터 이름(model attribute에 저장할 이름)과
		//메소드 파라미터 변수의 이름이 같으면
		//@ModelAttribute("member") -> 어노테이션의 파라미터 생략 가능
		log.info("method4");
		log.info(member);
		
//		model.addAttribute("member", member);
	}
	
	@RequestMapping("/ex5")
	public String method5(Member member) {
		//@ModelAttribute 어노테이션 까지도 생략 가능(Model객체에 attribute를 자동으로 담아줌)
		log.info("method5");
		log.info(member);
		
		return"model/ex4";
	}
	
	
}

package org.zerock.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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
		//기본적으로 forward 전달이기 때문에 jsp파일에서는 EL로 똑같이 사용가능
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
		//저장할 데이터가 특정 클래스 타입인 경우(기본타입, 스트링은 생략 안 됨)
		//@ModelAttribute의 파라미터 이름(model attribute에 저장할 이름)과
		//메소드 파라미터 타입의 이름이 같으면
		//@ModelAttribute("member") -> 어노테이션의 파라미터 생략 가능
		//jsp에서는 메소드 파라미터 타입의 이름의 맨 첫글자의 소문자화한 속성명으로
		//EL을 통해 사용가능
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
	
	@RequestMapping("/ex7")
	public String method7(Model model, HttpSession session, RedirectAttributes rattr) {
		log.info("method7");
		model.addAttribute("myattr1", "myvalue1");
		session.setAttribute("myAttr2", "myValue2");
		rattr.addFlashAttribute("myAttr3", "myValue3");
		//redirect할 때 일회성으로 attributes를 붙여주는 RedirectAttributes 인터페이스
		
		return "redirect:ex8";
	}
	
	@RequestMapping("/ex8")
	public String method8(Model model) {
		log.info("method8");
		
		Map<String, Object> map = model.asMap();
		log.info(map.get("myattr1"));
		log.info(map.get("myAttr2"));
		log.info(map.get("myAttr3"));
		
		return "redirectex1";		
	}
	
	
}
